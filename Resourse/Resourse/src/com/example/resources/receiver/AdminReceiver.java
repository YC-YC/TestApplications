/**
 * 
 */
package com.example.resources.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * @author YC
 * @time 2016-4-29 下午4:29:10
 */
public class AdminReceiver extends DeviceAdminReceiver {

	@Override
	public DevicePolicyManager getManager(Context context) {
		// TODO Auto-generated method stub
		return super.getManager(context);
	}

	@Override
	public ComponentName getWho(Context context) {
		// TODO Auto-generated method stub
		return super.getWho(context);
	}

	@Override
	public void onEnabled(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onEnabled(context, intent);
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		// TODO Auto-generated method stub
		return super.onDisableRequested(context, intent);
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onDisabled(context, intent);
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordChanged(context, intent);
	}

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordFailed(context, intent);
	}

	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordSucceeded(context, intent);
	}

	@Override
	public void onPasswordExpiring(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordExpiring(context, intent);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

}
