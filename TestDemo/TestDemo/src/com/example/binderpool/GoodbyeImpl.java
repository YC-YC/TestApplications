/**
 * 
 */
package com.example.binderpool;
import android.os.RemoteException;
import android.util.Log;

import com.example.binderpool.aidl.IGoodbye;;

/**
 * @author YC
 * @time 2017-4-13 下午9:36:28
 * TODO:
 */
public class GoodbyeImpl extends IGoodbye.Stub{

	private static final String TAG = "Binderpool";

	@Override
	public void sayGoodbye() throws RemoteException {
		Log.i(TAG, "goodbye");
	}

}
