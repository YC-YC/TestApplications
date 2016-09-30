/**
 * 
 */
package com.example.resources.loading;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * @author YC
 * @time 2016-9-30 下午12:07:56
 * TODO:PathMeasure实现路径和MaskFilter实现阴影
 */
public class DynamicHeartView extends View {

	 // 起始点
    private static final int[] START_POINT = new int[] {
            300, 270
    };
    // 爱心下端点
    private static final int[] BOTTOM_POINT = new int[] {
            300, 400
    };
    // 左侧控制点
    private static final int[] LEFT_CONTROL_POINT = new int[] {
    	150, 200
    };
    // 右侧控制点
    private static final int[] RIGHT_CONTROL_POINT = new int[] {
    	450, 200
    };
    
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private Path mPath;
    
    private ValueAnimator valueAnimator;
    
    private float[] mCurPoint = new float[2];
    
	
	public DynamicHeartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public DynamicHeartView(Context context) {
		this(context, null);
	}
	public DynamicHeartView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
		valueAnimator.start();
	}

	private void init() {
		
		//要禁用硬件加速才能实现MaskFilter
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		
		mPaint = new Paint();		
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(2);
		mPaint.setColor(Color.GREEN);
		
		mPath = new Path();
		mPath.moveTo(START_POINT[0], START_POINT[1]);
		mPath.quadTo(RIGHT_CONTROL_POINT[0], RIGHT_CONTROL_POINT[1],
				BOTTOM_POINT[0], BOTTOM_POINT[1]);
		mPath.quadTo(LEFT_CONTROL_POINT[0], LEFT_CONTROL_POINT[1],
				START_POINT[0], START_POINT[1]);
		
		mPathMeasure = new PathMeasure(mPath, false);
		
		valueAnimator = ValueAnimator.ofFloat(0f, mPathMeasure.getLength()).setDuration(3000);
		valueAnimator.setRepeatCount(-1);
		valueAnimator.setInterpolator(new DecelerateInterpolator());
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (Float) animation.getAnimatedValue();
				mPathMeasure.getPosTan(value, mCurPoint, null);
//				Log.i(TAG, "animation value = " + value);
				invalidate();
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//阴影
		mPaint.setMaskFilter(new BlurMaskFilter(3.0f, Blur.NORMAL));
		canvas.drawPath(mPath, mPaint);
		mPaint.setMaskFilter(null);

		 canvas.drawCircle(RIGHT_CONTROL_POINT[0], RIGHT_CONTROL_POINT[1], 5, mPaint);
	        canvas.drawCircle(LEFT_CONTROL_POINT[0], LEFT_CONTROL_POINT[1], 5, mPaint);

	        // 绘制对应目标
	        canvas.drawCircle(mCurPoint[0], mCurPoint[1], 10, mPaint);
	}


}
