package com.example.testdemo.flashing;

import com.example.testdemo.Utils;

import android.os.Bundle;


/**
 * 责任链模式
 *@Author Administrator
 *@Time 2016-3-4 上午1:08:21
 */
public class FlashLightMainActivity extends FlashActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Utils.LogFlash("FlashLightMainActivity onCreate ------");
		super.onCreate(savedInstanceState);
		Utils.LogFlash("FlashLightMainActivity onCreate ++++++");
	}
}
