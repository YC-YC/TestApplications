/**
 * 
 */
package com.example.binderpool;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.testdemo.R;

/**
 * @author YC
 * @time 2017-4-14 上午10:31:51
 * TODO:
 */
public class TestBinderpoolActivity extends Activity {

	private static final String TAG = "Binderpool";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testbinderpool);
	}
	
	public void doClick(View view){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.i(TAG, "start ------");
				BinderPoolUtils mBinderPoolUtils = BinderPoolUtils.newInstance(TestBinderpoolActivity.this);
				Log.i(TAG, "start ++++++");
				IBinder helloBinder = mBinderPoolUtils.queryBinder(BinderPoolUtils.BINDER_HELLO);
				IBinder goodbyeBinder = mBinderPoolUtils.queryBinder(BinderPoolUtils.BINDER_GOODBYE);
				HelloImpl helloImpl = (HelloImpl) HelloImpl.asInterface(helloBinder);
				GoodbyeImpl goodbyeImpl = (GoodbyeImpl) GoodbyeImpl.asInterface(goodbyeBinder);
				try {
					helloImpl.sayHello();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					goodbyeImpl.sayGoodbye();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	
}
