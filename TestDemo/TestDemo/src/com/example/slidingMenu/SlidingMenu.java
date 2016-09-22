package com.example.slidingMenu;

import com.example.testdemo.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/*
 * 1、onMeasure:决定内部View(子View)的宽和高以及自己的宽和高
 * 2、onLayout:决定子View的位置
 * 3、复写onTouchEvent实现滑动松开后处理
 * 4、自定义padding
 * 5、添加按键切换
 * 6、抽屉式侧划方式一：仿佛菜单在内容底下
 * 	方法：计算偏移量mMenuWidth~0
 * 	属性动画：TraslationX
 * 	梯度getScroolX:mMenuWidth~0
 * 	调用动画的时机：在ACTION_MOVE中不好，在onScrollChanged中处理
 * 7、抽屉式侧划方式二：
 * a、右侧内容偏移量：1.0~0.7
 * scale:1.0~0.0
 * 值: 0.7+（scale*0.3）
 * b、菜单显示时有缩放、透明度变化0.7~1.0  0.6~1.0
 * 值：1-0.3*scale		1.0 - 0.4*scale
 * 
 * 
 * 
 * 
 */
public class SlidingMenu extends HorizontalScrollView {

	private static final String TAG = "SlidingMenu";
	
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;	//屏幕宽度
	private int mMenuRightPadding = 50;	//菜单到右边的距离dp
	
	private boolean once = false;
	private boolean inOpen = false;	//是否打开菜单
	
	private int mMenuWidth;	//菜单的宽度
	
	/*
	 * 代码中直接New会调用此方法
	 */
	public SlidingMenu(Context context) {
		this(context,null);
	}

	
	/*
	 * 未定义属性时调用
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	/*
	 * 自定义属性时调用
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		/*
		 * 获取自定义属性
		 */
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, 
				R.styleable.SliderMenu, 
				defStyle, 
				0);
		
		int n = a.getIndexCount();//获取自定义数量
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.SliderMenu_rightPadding:
				//把dp转成px
				mMenuRightPadding = a.getDimensionPixelOffset(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));
				break;
			}
		}
		
		a.recycle();
		
		//获取屏幕宽度
		WindowManager wm =(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		mScreenWidth = metrics.widthPixels;
		
//		//把dp转成px
//		mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, 
//				context.getResources().getDisplayMetrics());
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		if (!once)
		{
			once = true;
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			
			//菜单宽度
			mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;	
			mMenuWidth = mScreenWidth - mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			LOG("mMenuWidth = " + mMenuWidth + ", mScreenWidth = " + mScreenWidth);
			
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			this.scrollTo(mMenuWidth, 0);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			LOG("scrollX=" + scrollX);
			if (scrollX >= mMenuWidth /2)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				inOpen = false;
			}
			else
			{
				this.smoothScrollTo(0, 0);
				inOpen = true;
			}
			return true;
		case MotionEvent.ACTION_DOWN:
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	/*
	 * l：ScrollX
	 * 
	 * @see android.view.View#onScrollChanged(int, int, int, int)
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {

		super.onScrollChanged(l, t, oldl, oldt);
		//调用属性动画，设置translationX
		float scale = l*1.0f/mMenuWidth;	//1~0
		
		/* a、右侧内容偏移量：1.0~0.7
		 * scale:1.0~0.0
		 * 值: 0.7+（scale*0.3）
		 * b、菜单显示时有缩放、透明度变化0.7~1.0  0.6~1.0
		 * 值：1-0.3*scale		1.0 - 0.4*scale
		 */
		float rightScale = 0.7f + (0.3f*scale);
		float leftScale = 1.0f - (0.3f*scale);
		float leftAlpha = 1.0f - (0.4f*scale);
		
		
//		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		//setTranslationX的偏移量原本是mMenuWidth~0
		//但效果不好可，加个偏移量
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.8f);
		
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight()/2);//设置缩放点为左侧的中心点
		
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
		
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
	}
	
	private void openMenu()
	{
		if (inOpen) return;
		this.smoothScrollTo(0, 0);
		inOpen = true;
	}
	
	private void closeMenu()
	{
		if (!inOpen) return;
		this.smoothScrollTo(mMenuWidth, 0);
		inOpen = false;
	}
	
	/*
	 * 按键切换
	 */
	public void toggleMenu()
	{
		if (inOpen)
		{
			closeMenu();
		}
		else
		{
			openMenu();
		}
	}
	
	private void LOG(String string)
	{
		Log.i(TAG, string);
	}

}
