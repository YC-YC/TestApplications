package com.example.testdemo;

import java.util.ArrayList;
import java.util.List;

import com.tencent.bugly.crashreport.CrashReport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private List<String> mTitle;
	private FragmentPagerAdapter mAdapter;
	private PagerTabStrip mTabStrip;
	private LinearLayout mTab01, mTab02, mTab03, mTab04;
	private ImageView mImg01, mImg02, mImg03, mImg04;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		CrashReport.initCrashReport(this, "900021446", false);
		initView();
		initEvent();
	}
	private void initEvent() {
		mTab01.setOnClickListener(this);
		mTab02.setOnClickListener(this);
		mTab03.setOnClickListener(this);
		mTab04.setOnClickListener(this);
		
		/*
		 * 设置PagerTabStrip属性
		 */
		mTabStrip.setBackgroundColor(Color.BLACK);
		mTabStrip.setTextColor(Color.WHITE);
		mTabStrip.setDrawFullUnderline(false);
		//设置下线颜色
		mTabStrip.setTabIndicatorColor(Color.GREEN);
	}
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		/*
		 * 使用PagerTabStrip设置标题头
		 */
		mTabStrip = (PagerTabStrip) findViewById(R.id.tab);
		
		mTab01 = (LinearLayout) findViewById(R.id.tab_01);
		mTab02 = (LinearLayout) findViewById(R.id.tab_02);
		mTab03 = (LinearLayout) findViewById(R.id.tab_03);
		mTab04 = (LinearLayout) findViewById(R.id.tab_04);
		
		mImg01 = (ImageView) findViewById(R.id.img_01);
		mImg02 = (ImageView) findViewById(R.id.img_02);
		mImg03 = (ImageView) findViewById(R.id.img_03);
		mImg04 = (ImageView) findViewById(R.id.img_04);
		
		mTitle = new ArrayList<String>();
		mTitle.add("第一页");
		mTitle.add("第二页");
		mTitle.add("第三页");
		mTitle.add("第四页");
		
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new FirstFragment());
		mFragments.add(new SecondFragment());
		mFragments.add(new ThirdFragment());
		mFragments.add(new ForthFragment());
		
		mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 
				mFragments,mTitle);
		
		mViewPager.setAdapter(mAdapter);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				setSelItem(arg0);
			}
			
			

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_01:
			setSelItem(0);
			break;
		case R.id.tab_02:
			setSelItem(1);
			break;
		case R.id.tab_03:
			setSelItem(2);
			break;
		case R.id.tab_04:
			setSelItem(3);
			break;
		}
	}
	
	private void setSelItem(int item) {
		resetAll();
		switch (item) {
		case 0:
			mViewPager.setCurrentItem(0);
			mImg01.setImageResource(R.drawable.tab_01_pressed);
			break;
		case 1:
			mViewPager.setCurrentItem(1);
			mImg02.setImageResource(R.drawable.tab_02_pressed);
			break;
		case 2:
			mViewPager.setCurrentItem(2);
			mImg03.setImageResource(R.drawable.tab_03_pressed);
			break;
		case 3:
			mViewPager.setCurrentItem(3);
			mImg04.setImageResource(R.drawable.tab_04_pressed);
			break;

		default:
			break;
		}
	}
	private void resetAll() {
		mImg01.setImageResource(R.drawable.tab_01_normal);
		mImg02.setImageResource(R.drawable.tab_02_normal);
		mImg03.setImageResource(R.drawable.tab_03_normal);
		mImg04.setImageResource(R.drawable.tab_04_normal);
	}
	

}
