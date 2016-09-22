package com.example.guaguaka;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class GuaGuaActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guaguaka_main);
		
	}
}
