/**
 * 
 */
package com.example.customview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author YC
 * @time 2016-3-24 下午7:30:52
 */
public class TextViewWithPic extends View {

	private static final String tag = "TextViewWithPic";
	
	private Map<String, Integer> mCharMap;
	private String mStringText;
	private List<Bitmap> mBitmaps;
	private Context mContext;
	
	/** 显示的宽度和高度 */
	private int mWidth = 0;
	private int mHeight = 0;
	
	public TextViewWithPic(Context context) {
		this(context, null);
	}

	public TextViewWithPic(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public TextViewWithPic(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
	}
	
	/**
	 * 
	 * @param text 需要显示的内容，text的每个元素必需在charMap里
	 * @param charMap 字符集
	 */
	public void setText(String text, Map<String, Integer> charMap)
	{
		mCharMap = charMap;
		mStringText = text;
		mBitmaps = new ArrayList<Bitmap>();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = 0;
		mHeight = 0;
		mBitmaps.clear();
		for (int i = 0; i < mStringText.length(); i++)
		{
			if (mCharMap != null)
			{
				int id = mCharMap.get(mStringText.charAt(i)+"");
				Drawable drawable = mContext.getResources().getDrawable(id);
				mWidth += drawable.getIntrinsicWidth();
				mHeight = Math.max(mHeight, drawable.getIntrinsicHeight());
				mBitmaps.add(drawableToBitmap(drawable));
			}
		}
//		Log.i(tag, "getPic width = " + mWidth + ", height = " + mHeight);
		setMeasuredDimension(mWidth, mHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		int offset = 0;
		for (int i = 0; i < mBitmaps.size(); i++)
		{
			canvas.drawBitmap(mBitmaps.get(i), offset, mHeight - mBitmaps.get(i).getHeight(), null);
			offset += mBitmaps.get(i).getWidth();
		}
	}
	
	/**
	 * drawable转成Bitmap
	 * @param drawable
	 * @return
	 */
	private Bitmap drawableToBitmap(Drawable drawable)
	{
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		
		Bitmap bitmap = Bitmap.createBitmap(width, height, 
				drawable.getOpacity() != PixelFormat.OPAQUE ?
						Bitmap.Config.ARGB_8888:
							Bitmap.Config.RGB_565);//透明度
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0 , width, height);
		drawable.draw(canvas);
		return bitmap;
		
	}

}
