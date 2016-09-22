package com.example.testdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Topbar extends RelativeLayout{

	private Button leftButton, rightButton;
	private TextView tvTitle;
	
	private int leftTextColor;
	private Drawable leftBackground;
	private String leftText;
	
	private int rightTextColor;
	private Drawable rightBackground;
	private String rightText;
	
	private float titleTextSize;
	private int titleTextColor;
	private String title;
	
	private LayoutParams leftParams, rightParams, titleParams;
	Context mContext = null;
	private topbarClickListener mListener;
	
	/*
	 * 添加回调接口
	 */
	public interface topbarClickListener{
		public void leftClick();
		public void rightClick();
	}
	
	public void setOnTopbarClickListener(topbarClickListener listener)
	{
		mListener = listener;
	}
	
	public Topbar( Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		//载入atts.xml属性
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
	
		//分配属性
		leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
		leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
		leftText = ta.getString(R.styleable.Topbar_leftText);
		
		
		rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
		rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
		rightText = ta.getString(R.styleable.Topbar_rightText);
		
		titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 10);
		titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor, 255);
		title = ta.getString(R.styleable.Topbar_title);
		
		//回收资源
		ta.recycle();
		
		//创建控件
		leftButton = new Button(context);
		rightButton = new Button(context);
		tvTitle = new TextView(context);
		
		//设置属性
		leftButton.setTextColor(leftTextColor);
		leftButton.setBackground(leftBackground);
		leftButton.setText(leftText);
		leftButton.setTextSize(20);
		
		rightButton.setTextColor(rightTextColor);
		rightButton.setBackground(rightBackground);
		rightButton.setText(rightText);
		
		tvTitle.setTextSize(titleTextSize);
		tvTitle.setTextColor(titleTextColor);
		tvTitle.setText(title);
		tvTitle.setGravity(Gravity.CENTER);
		
		setBackgroundColor(0XFFF59563);
		
		//设置布局属性
		leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		addView(leftButton, leftParams);
		
		rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		addView(rightButton, rightParams);
//		
		titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
				LayoutParams.MATCH_PARENT);
		titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		addView(tvTitle, titleParams);
		
		leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.leftClick();
			}
		});
		rightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.rightClick();				
			}
		});
	}


}
