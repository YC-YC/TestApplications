package com.example.arcmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.testdemo.R;


/*
 * 一、自定义ViewGroup
 * 	1、自定义属性
 * 		a、attr.xml
 *  	b、在布局中获取
 *  	c、在自定义控件中进行读取
 *  2、onMeasure:测量值
 *  3、onLayout:决定View显示位置
 *  4、设置主按钮的旋转动画
 *  5、实现子菜单点击动画
 */

public class ArcMenu extends ViewGroup implements OnClickListener {

	private static final String TAG = "ArcMenu";
	private static final int POS_LEFT_TOP = 0;
	private static final int POS_LEFT_BOTTON = 1;
	private static final int POS_RIGHT_TOP = 2;
	private static final int POS_RIGHT_BOTTON = 3;

	private Position mPosition = Position.LEFT_TOP;
	private Status mCurStatus = Status.CLOSE;
	private int mRadius;
	
	private View mCButton;	//中间位置按键
	private OnMenuItemClickListener mClickListener;
	
	/*
	 * 位置
	 */
	public enum Position{
		LEFT_TOP, LEFT_BOTTON, RIGHT_TOP, RIGHT_BOTTON
	}
	
	/* 状态
	 */
	public enum Status{
		OPEN, CLOSE
	}
	
	/*
	 * 菜单点击回调接口
	 */
	public interface OnMenuItemClickListener{
		void onClick(View view, int position);
	}
	
	public void setOnMenuItemClickListener(OnMenuItemClickListener listener)
	{
		mClickListener = listener;
	}
	
	
	
	public ArcMenu(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public ArcMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	public ArcMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//默认值
		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
		//获取自定义属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, 
				R.styleable.ArcMenu, 
				defStyle, 
				0);
		int pos = a.getInt(R.styleable.ArcMenu_position, POS_LEFT_TOP);
		switch (pos) {
		case POS_LEFT_TOP:
			mPosition = Position.LEFT_TOP;
			break;
		case POS_LEFT_BOTTON:
			mPosition = Position.LEFT_BOTTON;
			break;
		case POS_RIGHT_TOP:
			mPosition = Position.RIGHT_TOP;
			break;
		case POS_RIGHT_BOTTON:
			mPosition = Position.RIGHT_BOTTON;
			break;
		}
		
		mRadius = (int) a.getDimension(R.styleable.ArcMenu_arcradius,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
						100, getResources().getDisplayMetrics()));
		a.recycle();
		
		LOG("mPosition = " + mPosition + ", mRadius = " + mRadius);
	}

	public boolean isOpen()
	{
		return (mCurStatus == Status.OPEN)?true:false;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			//测量子View
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed)
		{
			layoutCButton();
			layoutChild();
		}
	}

	
	
	/*
	 * 定位主菜单按键
	 */
	private void layoutCButton() {
		mCButton = getChildAt(0);
		mCButton.setOnClickListener(this);
		
		int l = 0;	//左
		int t = 0;	//上
		
		int width = mCButton.getMeasuredWidth();
		int height = mCButton.getMeasuredHeight();
		
		switch (mPosition) {
		case LEFT_TOP:
			l= 0;
			t = 0;
			break;
		case LEFT_BOTTON:
			l= 0;
			t = getMeasuredHeight() - height;
			break;
		case RIGHT_TOP:
			l= getMeasuredWidth() - width;
			t = 0;
			break;
		case RIGHT_BOTTON:
			l= getMeasuredWidth() - width;
			t = getMeasuredHeight() - height;
			break;
		}
		mCButton.layout(l, t, l+width, t+height);
	}
	/*
	 * 定位子按键
	 */
	private void layoutChild() {
		int count = getChildCount();
		for (int i = 0; i < count - 1; i++) {
			
			View child = getChildAt(i+1);
			
			child.setVisibility(View.GONE);
			
			int cl = (int) (mRadius*Math.sin(Math.PI/2/(count-2)*i));
			int ct = (int) (mRadius*Math.cos(Math.PI/2/(count-2)*i));
			
			int cWidth = child.getMeasuredWidth();
			int cHeight = child.getMeasuredHeight();
			
			//左下、右下
			if (mPosition == Position.LEFT_BOTTON || mPosition == Position.RIGHT_BOTTON)
			{
				ct = getMeasuredHeight() - cHeight - ct;
			}
			//右下、右下
			if (mPosition == Position.RIGHT_BOTTON || mPosition == Position.RIGHT_TOP)
			{
				cl = getMeasuredWidth() - cWidth - cl;
			}
			child.layout(cl, ct, cl + cWidth, ct + cHeight);
		}
	}


	private void LOG(String str)
	{
		Log.i(TAG, str);
	}



	@Override
	public void onClick(View v) {
	
//		mCButton = findViewById(R.id.id_button);
		rotateCButton(v, 0f, 360f, 300);
		toggleMenu(300);
	}



	/*
	 * 切换菜单
	 */
	public void toggleMenu(int duration) {
		//为menu添加平移动画
		
		int count = getChildCount();
		for (int i = 0; i < count - 1; i++) {
			final View childView = getChildAt(i+1);
			childView.setVisibility(View.VISIBLE);
			//end 0，0
			//start
			
			int cl = (int) (mRadius*Math.sin(Math.PI/2/(count-2)*i));
			int ct = (int) (mRadius*Math.cos(Math.PI/2/(count-2)*i));
			
			int xflag = 1;
			int yflag = 1;
			//左上、左下
			if (mPosition == Position.LEFT_BOTTON || mPosition == Position.LEFT_TOP)
			{
				xflag = -1;
			}
			//右下、右下
			if (mPosition == Position.LEFT_TOP || mPosition == Position.RIGHT_TOP)
			{
				yflag = -1;
			}
			AnimationSet animationSet = new AnimationSet(true);
			Animation tranAnim = null;
			
			if (mCurStatus == Status.CLOSE)
			{
				tranAnim = new TranslateAnimation(xflag *cl, 0, yflag * ct, 0);
				childView.setClickable(true);
				childView.setFocusable(true);
			}
			else
			{
				tranAnim = new TranslateAnimation(0, xflag *cl, 0, yflag * ct);
				childView.setClickable(false);
				childView.setFocusable(false);
			}
			tranAnim.setFillAfter(true);
			tranAnim.setDuration(duration);
			tranAnim.setStartOffset((i*100)/count);//设置先后顺序
			/*
			 * 设置动画监听事件，完成动画结束时显示隐藏
			 */
			tranAnim.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					if (mCurStatus == Status.CLOSE)
					{
						childView.setVisibility(View.GONE);
					}
				}
			});
		
			//为menu添加旋转动画
			RotateAnimation rotateAnim = new RotateAnimation(0, 720,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnim.setFillAfter(true);
			rotateAnim.setDuration(duration);
			
			animationSet.addAnimation(rotateAnim);//先旋转再平移
			animationSet.addAnimation(tranAnim);
			
			childView.startAnimation(animationSet);
			
			//实现子菜单点击动画
			final int pos = i+1;
			childView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (mClickListener != null)
						mClickListener.onClick(v, pos);
					
					menuItemAnim(pos - 1);
					changeStatus();
				}

			});
		}
		changeStatus();
	}

	//添加menuItem的点击动画
	private void menuItemAnim(int position) {
		for (int i = 0; i < getChildCount() - 1; i++) {
			View childView = getChildAt(i+1);
			if (i == position)
			{
				childView.startAnimation(scaleBigAnim(300));
			}
			else
			{
				childView.startAnimation(scaleSmallAnim(300));
			}
			childView.setClickable(false);
			childView.setFocusable(false);
		}
	}

	/*
	 * 变小和透明度降低
	 */
	private Animation scaleSmallAnim(int duration) {
		
		AnimationSet set = new AnimationSet(true);
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0f, 1.0f,
				0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);
		set.setDuration(duration);
		set.setFillAfter(true);
		return set;
	}


	/*
	 * 变大和透明度降低
	 */
	private Animation scaleBigAnim(int duration) {
		AnimationSet set = new AnimationSet(true);
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 4.0f, 1.0f,
				4.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);
		set.setDuration(duration);
		set.setFillAfter(true);
		return set;
	}



	/*
	 * 切换状态
	 */
	private void changeStatus() {
		mCurStatus = (mCurStatus == Status.CLOSE ? Status.OPEN:Status.CLOSE);
	}



	/*
	 * 旋转中间按键
	 */
	private void rotateCButton(View v, float start, float end, int duration) {
		RotateAnimation anim = new RotateAnimation(start, end,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(duration);
		anim.setFillAfter(true);
		v.startAnimation(anim);
	}

}
