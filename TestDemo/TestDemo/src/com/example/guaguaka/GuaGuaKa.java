package com.example.guaguaka;

import com.example.testdemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GuaGuaKa extends View {

	private int mTextSize = 80;
	
	private Paint mOutterPaint;
	private Path mPath;	//手指路径
	private Canvas mCanvas;
	private Bitmap mBitmap;	//底图
	private Bitmap mOutterBitmap;
	
	private int mLastX;
	private int mLastY;
	
//	private Bitmap bitmapbg;
	
	private String mText;
	private Paint mBackPaint;
	private Rect mTextBound = new Rect();//记录刮奖文字的宽高
	
	private volatile boolean mComplete = false;
	
	public GuaGuaKa(Context context) {
		this(context, null);
	}
	public GuaGuaKa(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public GuaGuaKa(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	private void init() {
		mOutterPaint = new Paint();
		mBackPaint = new Paint();
		mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fg_guaguaka);
		mPath = new Path();
//		bitmapbg = BitmapFactory.decodeResource(getResources(), R.drawable.t2);
	
		mText = "500,000";
		
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		
		mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		
		//设置画笔属性
		mOutterPaint.setColor(Color.parseColor("#c0c0c0"));//颜色
		mOutterPaint.setAntiAlias(true);//抗锯齿
		mOutterPaint.setDither(true);
		mOutterPaint.setStrokeJoin(Paint.Join.ROUND);//圆角
		mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
		mOutterPaint.setStyle(Style.FILL);
		mOutterPaint.setStrokeWidth(20);
		
		//设置获奖信息的画笔属性
		mBackPaint.setColor(Color.BLACK);
		mBackPaint.setStyle(Style.FILL);
		mBackPaint.setTextSize(mTextSize);
		//获得文本的宽和高
		mBackPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
		
//		mCanvas.drawColor(Color.parseColor("#c0c0c0"));//灰色图层
		mCanvas.drawRoundRect(new RectF(0, 0, width, height), 30, 30, mOutterPaint);//圆角
		mCanvas.drawBitmap(mOutterBitmap, null, 
				new Rect(0, 0, getWidth(), getHeight()), null);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastX = x;
			mLastY = y;
			mPath.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = Math.abs(x - mLastX);
			int dy = Math.abs(y - mLastY);
			
			if (dx > 3 || dy > 3)
			{
				mPath.lineTo(x, y);
			}
			mLastX = x;
			mLastY = y;
			break;
		case MotionEvent.ACTION_UP:
			new Thread(mRunnable).start();
			break;
		}
		invalidate();//有作用
		return true;
	}
	
	private Runnable mRunnable = new Runnable() {
		
		@Override
		public void run() {
			int w = getWidth();
			int h = getHeight();
			
			float wipeArea = 0;
			float totalArea = w*h;
			
			Bitmap bitmap = mBitmap;
			int[] mPixels = new int[w*h];	//像素点
			mBitmap.getPixels(mPixels, 0, w, 0, 0, w, h);
			
			for (int i = 0; i < w; i++)
			{
				for (int j = 0; j < h; j++)
				{
					int index = i + j*w;
					if (mPixels[index] == 0)
					{
						wipeArea++;
					}
				}
			}
			
			if (wipeArea > 0 && totalArea > 0)
			{
				int percent = (int) (wipeArea*100/totalArea);
				if (percent > 60)
				{
					//清除掉图层区域
					mComplete = true;
					postInvalidate();
				}
			}
		}
	};

	
	
	//绘制
	@Override
	protected void onDraw(Canvas canvas) {
		
//		canvas.drawBitmap(bitmapbg , 0, 0, null);
		canvas.drawText(mText, 
				getWidth()/2 - mTextBound.width()/2, 
				getHeight()/2 + mTextBound.height()/2, 
				mBackPaint);
		if (!mComplete)
		{
			drawPath();
			canvas.drawBitmap(mBitmap, 0, 0, null);
		}
	}
	private void drawPath() {
		mOutterPaint.setStyle(Style.STROKE);
		mOutterPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
		mCanvas.drawPath(mPath, mOutterPaint);
	}

	
}
