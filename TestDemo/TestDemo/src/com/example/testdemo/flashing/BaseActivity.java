package com.example.testdemo.flashing;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.testdemo.R;
import com.example.testdemo.Utils;

/**
 * 基准窗口
 *
 *@Author Administrator
 *@Time 2016-3-6 上午12:28:40
 */
public class BaseActivity extends Activity {

	/**
	 * 组件及变量在此声明，并为protected,继承类可以引用
	 */
	protected ImageView mImgFlashlight;
	protected ImageView mImgFlashlightController;
	
	protected Camera mCamera;
	protected Parameters mParameters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.LogFlash("BaseActivity onCreate ------");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash_main);

		mImgFlashlight = (ImageView) findViewById(R.id.img_flashlight);
		mImgFlashlightController = (ImageView) findViewById(R.id.img_flashlight_controller);

		Utils.LogFlash("BaseActivity onCreate ++++++");
	}
}
