package com.example.resources;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.resources.ImgUtils.ImgUtils;
import com.example.resources.utils.LogUtil;

/**
 * @author YC
 * @time 2016-3-10 下午5:05:39
 */
public class ReflectActivity extends Activity {
	
	private static final String TAG = "ReflectActivity";
	
	private ImageView mImgDvr;
	private static final String TEST_BTN_TAG = "Android Logo"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reflect_main);
		mImgDvr = (ImageView) findViewById(R.id.img_dvr);
		mImgDvr.setTag(TEST_BTN_TAG);
		mImgDvr.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				ClipData.Item item = new ClipData.Item((CharSequence)mImgDvr.getTag());
				String[] mimiType = {ClipDescription.MIMETYPE_TEXT_PLAIN};
				ClipData clipData = new ClipData(mImgDvr.getTag().toString(), mimiType, item);
				View.DragShadowBuilder shadowBuilder = new DragShadowBuilder(mImgDvr);
				v.startDrag(clipData, shadowBuilder, null, 0);
				return true;
			}
		});
		mImgDvr.setOnDragListener(new View.OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
				Log.i(TAG, "event.getAction() = " + event.getAction());
				switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					Log.i(TAG, "DragEvent.ACTION_DRAG_STARTED");
					layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
					break;
				case DragEvent.ACTION_DRAG_ENTERED:
					Log.i(TAG, "DragEvent.ACTION_DRAG_ENTERED");
					int x = (int) event.getX();
					int y = (int) event.getY();
					break;
				case DragEvent.ACTION_DRAG_EXITED:
					Log.i(TAG, "DragEvent.ACTION_DRAG_EXITED");
					x = (int) event.getX();
					y = (int) event.getY();
					layoutParams.leftMargin = x;
					layoutParams.topMargin = y;
					v.setLayoutParams(layoutParams);
					break;
				case DragEvent.ACTION_DRAG_LOCATION:
					Log.i(TAG, "DragEvent.ACTION_DRAG_LOCATION");
					x = (int) event.getX();
					y = (int) event.getY();
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					Log.i(TAG, "DragEvent.ACTION_DRAG_ENDED");
					break;
				case DragEvent.ACTION_DROP:
					Log.i(TAG, "DragEvent.ACTION_DRAG_ENDED");
					break;
				default:
					break;
				}
				return true;
			}
		});
	}
	
	public void reflect(View view)
	{
		Bitmap bitmap = ((BitmapDrawable)mImgDvr.getDrawable()).getBitmap();
		mImgDvr.setImageBitmap(ImgUtils.getReflectBitmapWithOrigin(bitmap));
	}
	public void scale(View view)
	{
		Bitmap bitmap = ((BitmapDrawable)mImgDvr.getDrawable()).getBitmap();
		mImgDvr.setImageBitmap(ImgUtils.zoomBitmap(bitmap, 100, 100));
	}
	public void corner(View view)
	{
		Bitmap bitmap = ((BitmapDrawable)mImgDvr.getDrawable()).getBitmap();
		mImgDvr.setImageBitmap(ImgUtils.getRoundedCornerBitmap(bitmap, 80));
	}
	public void round(View view)
	{
		Bitmap bitmap = ((BitmapDrawable)mImgDvr.getDrawable()).getBitmap();
		mImgDvr.setImageBitmap(ImgUtils.getRoundedCornerBitmap(bitmap));
	}
	public void blur(View view)
	{
		Bitmap bitmap = ((BitmapDrawable)mImgDvr.getDrawable()).getBitmap();
		LogUtil.startTime("高斯模糊");
		mImgDvr.setImageBitmap(ImgUtils.blurBitmap(this, bitmap));
//		mImgDvr.setImageBitmap(ImgUtils.blurBitmapJni(bitmap, 10, true));
		LogUtil.endUseTime("高斯模糊");
		mImgDvr.setOnTouchListener(new OnTouchListener() {
			
			private float mLastY;
			
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					mLastY =  event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					float y = event.getY();
					float alphaDelt = (y - mLastY)/10000;
					float alpha = mImgDvr.getAlpha() + alphaDelt;
					if (alpha > 1.0f)
					{
						alpha = 1.0f;
					}
					else if (alpha < 0.0f)
					{
						alpha = 0.0f;
					}
					Log.i(TAG, "alpha = " + alpha);
					mImgDvr.setAlpha(alpha);
					break;
				case MotionEvent.ACTION_UP:

					break;
				}
				return true;
			}
		});
	}
}
