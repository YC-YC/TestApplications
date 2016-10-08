/**
 * 
 */
package com.example.testrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author YC
 * @time 2016-10-8 下午2:35:31
 * TODO:RecyclerView在绘制的时候，去会绘制decorator，即调用该类的onDraw和onDrawOver方法
 */
public class DividerItemDecoration extends ItemDecoration {

	private static final String TAG = "DividerItemDecoration";

	private static final int[] ATTRS = new int[]{
		android.R.attr.listDivider
	};
	
	public static final int HORIZONTAL_LIST = LinearLayout.HORIZONTAL;
	public static final int VERTICAL_LIST = LinearLayout.VERTICAL;

	
	private Drawable mDivider;
	private int mOrientation;
	
	public DividerItemDecoration(Context context, int orientation) {
		super();
		final TypedArray a = context.obtainStyledAttributes(ATTRS);
		mDivider = a.getDrawable(0);
		a.recycle();
		setOrientation(orientation);
		
	}

	private void setOrientation(int orientation) {
		if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
			Log.i(TAG, "IllegalArgument orientation = " + orientation);
			throw new IllegalArgumentException("ivalild argument");
		}
		mOrientation = orientation;
	
	}

	/**
	 * onDraw方法先于drawChildren
	 */
	@Override
	@Deprecated
	public void onDraw(Canvas c, RecyclerView parent) {
		Log.i(TAG, "onDraw()");
		if (mOrientation == VERTICAL_LIST){
			drawVertical(c, parent);
		}
		else{
			drawHorizontal(c, parent);
		}
	}
	
	/**
	 * @param c
	 * @param parent
	 */
	private void drawHorizontal(Canvas c, RecyclerView parent) {
		final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
	}

	/**
	 * @param c
	 * @param parent
	 */
	private void drawVertical(Canvas c, RecyclerView parent) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();
		
		final int count = parent.getChildCount();
		for (int i = 0; i < count; i++){
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (LayoutParams) child.getLayoutParams();
			
			final int top = child.getBottom() + params.bottomMargin; 
			final int bottom = top +  mDivider.getIntrinsicHeight();
			
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	/**
	 * 可以通过outRect.set()为每个Item设置一定的偏移量，主要用于绘制Decorator。
	 */
	@Override
	@Deprecated
	public void getItemOffsets(Rect outRect, int itemPosition,
			RecyclerView parent) {
		if (mOrientation == HORIZONTAL_LIST){
			outRect.set(0 , 0, mDivider.getIntrinsicWidth(), 0);
		}
		else{
			outRect.set(0 , 0, 0, mDivider.getIntrinsicHeight());
		}
	}
}
