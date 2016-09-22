package com.example.luckypan;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/*
 * SurfaceView是在一个子线程中对自己进行绘制，优势是避免造成UI线程的阻塞
 * SurfaceView包含一个专门用于绘制的Surface，Surface中含有一个Canvas
 * getHolder-->SurfaceHolder
 * Holder-->Canvas +管理SurfaceView的生命周期（callback） 
 */

public class SurfaceViewTemplate extends SurfaceView implements Callback, Runnable {
	
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	private Thread thread;
	private boolean isRunning;
	
	public SurfaceViewTemplate(Context context) {
		this(context, null);
	}

	public SurfaceViewTemplate(Context context, AttributeSet attrs) {
		super(context, attrs);
		mHolder = getHolder();
		mHolder.addCallback(this);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setKeepScreenOn(true);//常亮
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
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
			draw();
		}
	}

	private void draw() {
		try {
			mCanvas = mHolder.lockCanvas();
			if (mCanvas != null)
			{
				//draw something
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

	
}
