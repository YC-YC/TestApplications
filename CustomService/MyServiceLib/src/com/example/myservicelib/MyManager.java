/**
 * 
 */
package com.example.myservicelib;

import android.os.RemoteException;
import android.os.ServiceManager;

import com.example.myservicelib.aidl.IMyService;
import com.example.myservicelib.data.Constant;

/**
 * @author YC
 * @time 2017-4-20 下午3:22:49
 * TODO:
 */
public class MyManager {
	
	private static MyManager sManager;
	private IMyService mService;
	
	public static MyManager getInstance() {
		if (sManager == null) {
			synchronized (MyManager.class) {
				if (sManager == null) {
					sManager = new MyManager();
				}
			}
		}
		return sManager;
	}
	
	private MyManager(){
		mService = IMyService.Stub.asInterface(ServiceManager.getService(Constant.SERVICE_NAME));
	}
	
	public void setVal(int val){
		try {
			mService.setVal(val);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public int getVal(){
		try {
			return mService.getVal();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
