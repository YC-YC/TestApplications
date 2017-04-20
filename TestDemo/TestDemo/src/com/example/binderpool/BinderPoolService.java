/**
 * 
 */
package com.example.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author YC
 * @time 2017-4-13 下午9:29:05
 * TODO:
 */
public class BinderPoolService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new BinderPoolUtils.BinderPoolImpl();
	}
	
	

}
