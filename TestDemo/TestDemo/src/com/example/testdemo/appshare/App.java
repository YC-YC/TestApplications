package com.example.testdemo.appshare;

import android.app.Application;

/**
 *@Author Administrator
 *@Time 2016-2-24 下午7:42:55
 */
public class App extends Application {

	private String mShareString = "default";

	public String getmShareString() {
		return mShareString;
	}

	public void setmShareString(String mShareString) {
		this.mShareString = mShareString;
	}
	
	//生命周期
	//创建应用时调用，比其它Activity早执行
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}
	
	//内存清理
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
	}
}
