/**
 * 
 */
package com.example.resources.view;

import com.example.resources.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 1、在values创建attr.xml
 * 
 * @author YC
 * @time 2016-3-21 下午9:01:33
 */
public class CustomView extends View {

	private String mText;
	private int mTextColor;
	private int mTextSize;
	
	private Paint mPaint;
	private Rect mBound;
	private static final int PADDING_TOP = 10;
	public CustomView(Context context) {
		this(context, null);
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CustomTitleView, defStyleAttr, 0);

		int count = a.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTitleView_titleText:
				mText = a.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				mTextColor = a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				mTextSize = a.getDimensionPixelSize(attr, 
						(int) TypedValue.applyDimension(
								 // 默认设置为16sp，TypeValue也可以把sp转化为px  
								TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
		a.recycle();
		
		mPaint = new Paint();
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
		mBound = new Rect();
		mPaint.getTextBounds(mText, 0, mText.length(), mBound );
	}
	
	/**
	 * 当我们设置明确的宽度和高度时，系统帮我们测量的结果就是我们设置的结果
	 * 当我们设置为WRAP_CONTENT,或者MATCH_PARENT系统帮我们测量的结果就是MATCH_PARENT的长度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
		    int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
		    int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
		    int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
		    int width;  
		    int height ;  
		    
		    if (widthMode == MeasureSpec.EXACTLY)  
		    {  
		        width = widthSize;  
		    } else  
		    {  
		    	mPaint.setTextSize(mTextSize);  
		        mPaint.getTextBounds(mText, 0, mText.length(), mBound);  
		        float textWidth = mBound.width();  
		        
		        width = (int) (getPaddingLeft() + textWidth + getPaddingRight())+ 2*PADDING_TOP;
		        
		    }
		    if (heightMode == MeasureSpec.EXACTLY)  
		    {  
		        height = heightSize;  
		    } else  
		    {  
		    	mPaint.setTextSize(mTextSize);  
		        mPaint.getTextBounds(mText, 0, mText.length(), mBound);  
		        float textHeight = mBound.height();  
		        
		        height = (int) (getPaddingTop() + textHeight + getPaddingBottom()) + 2*PADDING_TOP;
		    }
		    
		    setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
		
		mPaint.setColor(mTextColor);
		canvas.drawText(mText, getWidth()/2 - mBound.width()/2, getHeight()/2 + mBound.height()/2, mPaint);
		
	}	

}
