/**
 * 
 */
package com.example.binderpool;

import java.util.concurrent.CountDownLatch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.util.Log;

import com.example.binderpool.aidl.BinderPool;

/**
 * @author YC
 * @time 2017-4-14 上午10:20:37
 * TODO:另一个方案可以将BinderPool放到ContentProvider里面
 */
public class BinderPoolUtils {
	
	public static final int BINDER_HELLO = 1;
	public static final int BINDER_GOODBYE = 2;
	protected static final String TAG = "Binderpool";

	private BinderPool mBinderPool;
	private static BinderPoolUtils sBinderPoolUtils;
	private Context mContext;
	private CountDownLatch mCountDownLatch;
	
	private BinderPoolUtils(Context context) {
		mContext = context.getApplicationContext();
		connectService();
	}

	public static BinderPoolUtils newInstance(Context context) {
		if (sBinderPoolUtils == null) {
			synchronized (BinderPoolUtils.class) {
				if (sBinderPoolUtils == null) {
					sBinderPoolUtils = new BinderPoolUtils(context);
				}
			}
		}
		return sBinderPoolUtils;
	}
	
	public IBinder queryBinder(int bindercode) {
		Binder binder = null;
		try {
			binder = (Binder) mBinderPool.queryBinder(bindercode);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return binder;
	}
	
	private void connectService() {
		Intent i = new Intent(mContext, BinderPoolService.class);
		mContext.bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
		mCountDownLatch = new CountDownLatch(1);
		try {
			mCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mBinderPool = BinderPool.Stub.asInterface(service);
			try {
				mBinderPool.asBinder().linkToDeath(mDeathRecipient, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			mCountDownLatch.countDown();
			Log.e(TAG, "连接服务端，，，，，，，，，，");
		}
	};
	
	
	private DeathRecipient mDeathRecipient = new DeathRecipient() {

		@Override
		public void binderDied() {
			connectService();
		}
	};
	
	public static class BinderPoolImpl extends BinderPool.Stub {

		@Override
		public IBinder queryBinder(int binderCode) throws RemoteException {
			Binder mIBinder = null;
			switch (binderCode) {
			case BINDER_HELLO:
				mIBinder = new HelloImpl();
				break;
			case BINDER_GOODBYE:
				mIBinder = new GoodbyeImpl();
				break;
			default:
				break;
			}
			return mIBinder;
		}
	}
}
