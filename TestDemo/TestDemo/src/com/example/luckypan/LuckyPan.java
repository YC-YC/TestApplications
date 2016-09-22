package com.example.luckypan;

import com.example.testdemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/*
 * SurfaceView是在一个子线程中对自己进行绘制，优势是避免造成UI线程的阻塞
 * SurfaceView包含一个专门用于绘制的Surface，Surface中含有一个Canvas
 * getHolder-->SurfaceHolder
 * Holder-->Canvas +管理SurfaceView的生命周期（callback） 
 */

public class LuckyPan extends SurfaceView implements Callback, Runnable {
	
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	private Thread thread;
	private boolean isRunning;
	
	private String[] mStrs = new String[]{
			"单反相机", 
			"IPAD", 
			"恭喜发财", 
			"IPHONE", 
			"服装一套", 
			"恭喜发财"};
	private int[] mImgs = new int[]{
			R.drawable.danfan, 
			R.drawable.ipad, 
			R.drawable.f015,
			R.drawable.iphone,
			R.drawable.meizi,
			R.drawable.f040};
	
	private int[] mColor = new int[]{
			0xFFFFC300,
			0xFFF17E01,
			0xFFFFC300,
			0xFFF17E01,
			0xFFFFC300,
			0xFFF17E01
	};
	
	private int mItemCount = 6;
	
	private Bitmap[] mImgBitmap;
	
	//整个盘块的范围
	private RectF mRange = new RectF();
	//盘块直径
	private int mRadius;
	//盘块画笔
	private Paint mArcPaint;
	private Paint mTextPaint;
	
	private double mSpeed = 0;//滚动速度
	private volatile float mStartAngle = 0;
	
	private boolean isShouldEnd;	//是否点击的停止
	
	private int mCenter;//中心位置
	private int mPadding;
	
	//背景图
	private Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
	
	private float mTextSize = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 
			20, 
			getResources().getDisplayMetrics());
	
	public LuckyPan(Context context) {
		this(context, null);
	}

	public LuckyPan(Context context, AttributeSet attrs) {
		super(context, attrs);
		mHolder = getHolder();
		mHolder.addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setKeepScreenOn(true);//常亮
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
		
		mPadding = getPaddingLeft();
		mRadius = width - mPadding*2;
		
		mCenter = width/2;
		setMeasuredDimension(width, width);
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		mArcPaint = new Paint();
		mArcPaint.setAntiAlias(true);
		mArcPaint.setDither(true);
		
		mTextPaint = new Paint();
		mTextPaint.setColor(0xffffffff);
		mTextPaint.setTextSize(mTextSize);
		
		mRange = new RectF(mPadding, mPadding, mPadding+mRadius, mPadding+mRadius);
		
		mImgBitmap = new Bitmap[mItemCount];
		for (int i = 0; i < mItemCount; i++)
		{
			mImgBitmap[i] = BitmapFactory.decodeResource(getResources(), mImgs[i]);
		}
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isRunning = false;
		
	}

	@Override
	public void run() {
		while (isRunning) {
			long start = System.currentTimeMillis();
			draw();
			long end = System.currentTimeMillis();
			if (end - start < 50)
			{
				try {
					Thread.sleep(50 - (end-start));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void luckyStart(int index)
	{
		float angle = 360/mItemCount;
		float from = 270 -(index+1)*angle;
		float end = from + angle;
		
		//设置停下来需要旋转的距离
		float targetFrom = 4*360 + from;
		float targetEnd = targetFrom + angle;
		
		/*
		 * v1--->0
		 * (v1 = 0)*（v1 +1)/2 = targetFrom
		 * 求得v1=(-1 + Math.sqr(1+8*targetFrom）)/2
		 */

		float v1 = (float) ((-1 + Math.sqrt(1+8*targetFrom))/2);
		float v2 = (float) ((-1 + Math.sqrt(1+8*targetEnd))/2);
		
		mSpeed = v1 + Math.random()*(v2-v1);
		
//		mSpeed = 50;
		isShouldEnd = false;
	}
	
	public void luckyEnd()
	{
		mStartAngle = 0;
		isShouldEnd = true;
	}
	
	public boolean isStart()
	{
		return mSpeed != 0;
	}
	
	public boolean isShouldEnd()
	{
		return isShouldEnd;
	}
	
	private void draw() {
		try {
			mCanvas = mHolder.lockCanvas();
			if (mCanvas != null)
			{
				//draw something
				drawBg();
				drawPan();
			}
		} catch (Exception e) {
		}
		finally
		{
			if (mCanvas != null)
			{
				mHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}

	private void drawPan() {
		float tmpAngel = mStartAngle;
		float sweepAngle = 360/mItemCount;
		for (int i = 0; i < mItemCount; i++)
		{
			mArcPaint.setColor(mColor[i]);
			mCanvas.drawArc(mRange, tmpAngel, sweepAngle, true, mArcPaint);
		
			drawText(tmpAngel, sweepAngle, mStrs[i]);
			drawIcon(tmpAngel, mImgBitmap[i]);
			
			tmpAngel += sweepAngle;
		}
		mStartAngle += mSpeed;
		
		if (isShouldEnd)//停止
		{
			mSpeed -= 1;
			if (mSpeed <= 0)
			{
				mSpeed = 0;
				isShouldEnd = false;
			}
		}
	}

	private void drawIcon(float tmpAngel, Bitmap bitmap) {
		int imgWidth = mRadius/8;
		
		float angle = (float) ((tmpAngel + 360/mItemCount/2)*Math.PI/180);
		int x = (int) (mCenter + mRadius/2/2*Math.cos(angle));
		int y = (int) (mCenter + mRadius/2/2*Math.sin(angle));
		
		Rect rect = new Rect(x - imgWidth/2, y-imgWidth/2, x+imgWidth/2, y+imgWidth/2);
		mCanvas.drawBitmap(bitmap, null, rect, null);
		
	}

	private void drawText(float tmpAngel, float sweepAngle, String string) {
		Path path = new Path();
		path.addArc(mRange, tmpAngel, sweepAngle);
		int textWidth = (int) mTextPaint.measureText(string);
		int hOffset = (int) (mRadius*Math.PI/mItemCount/2 - textWidth /2);
		mCanvas.drawTextOnPath(string, path, hOffset, mRadius/12, mTextPaint);
	}

	private void drawBg() {
		mCanvas.drawColor(0xffffffff);
		mCanvas.drawBitmap(mBgBitmap, null, 
				new Rect(mPadding/2, 
						mPadding/2, 
						getMeasuredWidth() - mPadding/2, 
						getMeasuredHeight() - mPadding/2), 
				null);
	}

	
}
