package com.example.testdemo.flashing;

import java.io.IOException;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.example.testdemo.Utils;

/**
 * 手电筒窗口
 *@Author Administrator
 *@Time 2016-3-6 上午12:29:38
 */
public class FlashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.LogFlash("FlashActivity onCreate ------");
		super.onCreate(savedInstanceState);
		Point point = new Point();
		//获取屏幕长宽
		getWindowManager().getDefaultDisplay().getSize(point);
		LayoutParams mParams = (LayoutParams) mImgFlashlightController.getLayoutParams();
		mParams.height = point.y*3/4;
		mParams.width = point.x*1/3;
		mImgFlashlightController.setLayoutParams(mParams);
		Utils.LogFlash("FlashActivity onCreate ++++++");
		mImgFlashlight.setTag(false);
	}
	
	public void onClick_Flashlight(View view)
	{
		if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
		{
			Toast.makeText(this, "有摄像头属性", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "没有摄像头属性", Toast.LENGTH_SHORT).show();
		}
		
		if ((Boolean) mImgFlashlight.getTag())
		{
			closeFlashLight();
		}
		else
		{
			openFlashLight();
		}
	}

	private void openFlashLight() {
		TransitionDrawable drawable = (TransitionDrawable) mImgFlashlight.getDrawable();
		drawable.startTransition(200);
		mImgFlashlight.setTag(true);
		
		mCamera = Camera.open();
		int texName = 0;
		try {
			mCamera.setPreviewTexture(new SurfaceTexture(texName));
			mCamera.startPreview();
			
			mParameters = mCamera.getParameters();
			
			mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mParameters);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeFlashLight() {
		
		TransitionDrawable drawable = (TransitionDrawable) mImgFlashlight.getDrawable();
		if ((Boolean) mImgFlashlight.getTag())
		{
			drawable.reverseTransition(200);
			mImgFlashlight.setTag(false);
			if (mCamera != null)
			{
				mParameters = mCamera.getParameters();
				
				mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(mParameters);
				
				mCamera.startPreview();
				mCamera.release();
				mCamera = null;
			}
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		closeFlashLight();
	}
}
