/**
 * 
 */
package com.yc.test;

import com.tencent.bugly.Bugly;

import android.app.Application;

/**
 * @author YC
 * @time 2016-9-30 下午3:44:37
 * TODO:
 */
public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Bugly.init(this, "900055496", false);
	}
}
