/**
 * 
 */
package com.example.resources.loading;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author YC
 * @time 2016-9-29 下午6:28:51
 * TODO:主要是PathMeasure的使用
 */
public class SearchView extends View {

	protected static final String TAG = "SearchView";
	private int mWidth, mHeight;
	private ValueAnimator valueAnimator;
	private Paint mPaint;
	private Path mPath;
	/**截取Path一部分显示*/
	private PathMeasure mPathMeasure;
	private float value;
	
	public SearchView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SearchView(Context context) {
		this(context, null);
	}
	
	public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
		valueAnimator.start();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(15);
		mPaint.setColor(Color.WHITE);
		//设置圆笔
		mPaint.setStrokeCap(Cap.ROUND);
		//抗锯齿
		mPaint.setAntiAlias(true);
		
		mPath = new Path();
		RectF rect = new RectF(-150, -150, 150, 150);
		mPath.addArc(rect, -90, 359.9f);
		
		mPathMeasure = new PathMeasure(mPath, false);
		
		valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(3000);
		valueAnimator.setRepeatCount(-1);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				value = (Float) animation.getAnimatedValue();
//				Log.i(TAG, "animation value = " + value);
				invalidate();
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.translate(mWidth/2, mHeight/2);
		Path path = new Path();
		if (value > 0.95f){
			canvas.drawPoint(0, -150, mPaint);
		}
		int num = (int) (value/0.05);
		float start, length, tmpVal;
		switch (num) {
		default:
		case 3:
			tmpVal = value - 0.15f*(1-value);
			length = mPathMeasure.getLength();
//			start = length*tmpVal;
			//左抛物线
			start = - length*tmpVal*tmpVal + 2*length*tmpVal;
			mPathMeasure.getSegment(start, start+1, path, true);
		case 2:
			tmpVal = value - 0.10f*(1-value);
			length = mPathMeasure.getLength();
//			start = length*tmpVal;
			start = - length*tmpVal*tmpVal + 2*length*tmpVal;
			mPathMeasure.getSegment(start, start+1, path, true);
		case 1:
			tmpVal = value - 0.05f*(1-value);
			length = mPathMeasure.getLength();
//			start = length*tmpVal;
			start = - length*tmpVal*tmpVal + 2*length*tmpVal;
			mPathMeasure.getSegment(start, start+1, path, true);
		case 0:
			tmpVal = value;
			length = mPathMeasure.getLength();
//			start = length*tmpVal;
			start = - length*tmpVal*tmpVal + 2*length*tmpVal;
			mPathMeasure.getSegment(start, start+1, path, true);
			break;
		}
//		mPathMeasure.getSegment(mPathMeasure.getLength()*value, mPathMeasure.getLength()*value+1, path, true);
		canvas.drawPath(path , mPaint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
	}

}
