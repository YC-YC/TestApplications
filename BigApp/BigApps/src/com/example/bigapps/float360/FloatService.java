package com.example.bigapps.float360;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Administrator 2016-10-21下午10:04:11
 * TODO：
 */
public class FloatService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		FloatViewManager manager = FloatViewManager.getInstances(this);
		manager.showCircleView();
		super.onCreate();
	}

}

