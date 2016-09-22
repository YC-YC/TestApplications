package com.example.list;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.list.MyListView.ILoadListener;
import com.example.testdemo.R;

public class ListActivity extends Activity implements ILoadListener{
	
	private MyListView listview;
	
	private MyAdapter adapter;
	
	private ArrayList<ApkBean> list = new ArrayList<ApkBean>();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview2);
		getData();
		showListView(list);
	}

	private void showListView(ArrayList<ApkBean> list2) {
		if (adapter == null){
			listview = (MyListView) findViewById(R.id.listView2);
			adapter = new MyAdapter(ListActivity.this, list);
			listview.setAdapter(adapter);
			listview.setLoadListener(this);	//设置回调函数接口
		}else{
			adapter.OnDataChange(list);
		}
		
	}

	private void getData() {
		for (int i = 0; i < 15; i++) {
			ApkBean apkBean = new ApkBean();
			apkBean.setApkName("测试程序");
			apkBean.setApkInfo("50w用户");
			apkBean.setApkDes("这是一个神奇的应用！");
			list.add(apkBean);
		}
	}

	@Override
	public void onLoadMore() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				loadMoreData();
				showListView(list);
				listview.loadComplete();
			}
		}, 2000);
	}

	private void loadMoreData() {
		for (int i = 0; i < 2; i++) {
			ApkBean apkBean = new ApkBean();
			apkBean.setApkName("底部加载程序");
			apkBean.setApkInfo("50w用户");
			apkBean.setApkDes("这是一个神奇的应用！");
			list.add(apkBean);
		}		
	}
	
	private void refreshMoreData() {
		for (int i = 0; i < 2; i++) {
			ApkBean apkBean = new ApkBean();
			apkBean.setApkName("顶部加载程序");
			apkBean.setApkInfo("50w用户");
			apkBean.setApkDes("这是一个神奇的应用！");
			list.add(0, apkBean);
		}		
	}
	

	@Override
	public void onRefresh() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				refreshMoreData();
				showListView(list);
				listview.refreshComplete();
			}
		}, 2000);
	}
}
