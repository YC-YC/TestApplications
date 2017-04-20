/**
 * 
 */
package com.example.binderpool;
import android.os.RemoteException;
import android.util.Log;
import com.example.binderpool.aidl.IHello;
/**
 * @author YC
 * @time 2017-4-13 下午9:36:28
 * TODO:
 */
public class HelloImpl extends IHello.Stub{

	private static final String TAG = "Binderpool";

	@Override
	public void sayHello() throws RemoteException {
		Log.i(TAG, "hello");
	}


}
