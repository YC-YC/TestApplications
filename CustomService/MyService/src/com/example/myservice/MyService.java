/**
 * 
 */
package com.example.myservice;
import android.os.RemoteException;

import com.example.myservicelib.aidl.*;

/**
 * @author YC
 * @time 2017-4-20 下午3:11:49
 * TODO:
 */
public class MyService extends IMyService.Stub{

	private int mValue;
	
	@Override
	public int getVal() throws RemoteException {
		return mValue;
	}

	@Override
	public void setVal(int val) throws RemoteException {
		mValue = val;
	}

}
