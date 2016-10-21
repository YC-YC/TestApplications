package com.example.bigapps.float360;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.bigapps.R;

/**
 * @author Administrator
 *下午9:35:43
 * TODO：
 */
public class FloatCircleView extends View {

	private static final String TAG = "FloatCircleView";
	public int mWidth = 150, mHeight = 150;
	private Paint textPaint;
	private Paint circlePaint;
	private String text = "50%";
	private boolean mDrag = false;
	private Bitmap bitmap;
	
	public FloatCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaints();
	}


	public FloatCircleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FloatCircleView(Context context) {
		this(context, null);
	}
	
	private void initPaints() {
		circlePaint = new Paint();
		circlePaint.setColor(Color.GRAY);
		circlePaint.setAntiAlias(true);
		
		textPaint = new Paint();
		textPaint.setTextSize(30);
		textPaint.setColor(Color.WHITE);
		textPaint.setAntiAlias(true);
		textPaint.setFakeBoldText(true);//文字加粗
		
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, true);
	}
	
	/**
	 * 设置拖动状态
	 * @param bDrag
	 */
	public void setDragState(boolean bDrag){
		mDrag = bDrag;
		invalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY);
		int height = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
		super.onMeasure(width, height);
		Log.i(TAG, "onMeasure width = " + getMeasuredWidth());
		
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		Log.i(TAG, "onDraw");
		if (!mDrag){
			canvas.drawCircle(mWidth/2, mHeight/2, mWidth/2, circlePaint);
			float textWidth = textPaint.measureText(text);
			float x = mWidth/2 - textWidth/2;
			FontMetrics fontMetrics = textPaint.getFontMetrics();//获取文字规格
			float y = mHeight/2 - (fontMetrics.descent+fontMetrics.ascent)/2; 
			canvas.drawText(text, x, y, textPaint);
		}
		else{
			canvas.drawBitmap(bitmap, 0, 0, null);
		}
	}
	

}

