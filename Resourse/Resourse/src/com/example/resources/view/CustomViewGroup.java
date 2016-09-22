/**
 * 
 */
package com.example.resources.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author YC
 * @time 2016-3-21 下午7:46:25
 */
public class CustomViewGroup extends ViewGroup {

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 只需要ViewGroup能够支持margin即可，那么我们直接使用系统的MarginLayoutParams
	 */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		/**
		 * 获取父组件的大小与模式
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		// 记录wrap_content时父组件的w,h
		int width = 0;
		int height = 0;

		// 子View
		int cCount = getChildCount();

		// 子view的w,h
		int cWidth = 0;
		int cHeight = 0;
		// 因为本例中只关注Margin，所以用MarginLayoutParams
		MarginLayoutParams cParams = null;

		int tWidth = 0;
		int bWidth = 0;
		int lHeight = 0;
		int rHeight = 0;

		// 遍历子View
		for (int i = 0; i < cCount; i++) {
			View view = getChildAt(i);
			cWidth = view.getMeasuredWidth();
			cHeight = view.getMeasuredHeight();
			cParams = (MarginLayoutParams) view.getLayoutParams();

			// 顶宽
			if (i == 0 || i == 1) {
				tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
			}

			// 底宽
			if (i == 2 || i == 3) {
				bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
			}

			// 左高
			if (i == 0 || i == 2) {
				lHeight += cHeight + cParams.bottomMargin + cParams.topMargin;
			}

			// 右高
			if (i == 1 || i == 3) {
				rHeight += cHeight + cParams.bottomMargin + cParams.topMargin;
			}
		}

		width = Math.max(bWidth, tWidth);
		height = Math.max(lHeight, rHeight);

		/**
		 * 如果是wrap_content设置为我们计算的值 否则：直接设置为父容器计算的值
		 */
		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
				: width, heightMode == MeasureSpec.EXACTLY ? heightSize
				: height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		int cCount = getChildCount();
		MarginLayoutParams cParams = null;

		int cWidth = 0;
		int cHeight = 0;

		for (int i = 0; i < cCount; i++) {
			View child = getChildAt(i);
			cWidth = child.getMeasuredWidth();
			cHeight = child.getMeasuredHeight();
			cParams = (MarginLayoutParams) child.getLayoutParams();

			switch (i) {
			case 0:
				l = cParams.leftMargin;
				t = cParams.topMargin;
				break;
			case 1:
				l = getWidth() - cParams.leftMargin - cWidth - cParams.rightMargin;
				t = cParams.topMargin;
				break;
			case 2:
				l = cParams.leftMargin;
				t = getHeight() - cParams.topMargin - cHeight - cParams.bottomMargin;
				break;
			case 3:
				l = getWidth() - cParams.leftMargin - cWidth - cParams.rightMargin;
				t = getHeight() - cParams.topMargin - cHeight - cParams.bottomMargin;
				break;
			default:
				break;
			}
			
			r = l + cWidth;
			b = t + cHeight;
			child.layout(l, t, r, b);
		}

	}

}
