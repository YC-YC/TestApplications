package com.example.luckypan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.testdemo.R;


public class LuckyPanActivity extends Activity {

	private LuckyPan mLuckyPan;
	private ImageView mStartBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lucky_main);
		mLuckyPan = (LuckyPan) findViewById(R.id.luckypan);
		mStartBt = (ImageView) findViewById(R.id.start);
		mStartBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!mLuckyPan.isStart())
				{
					mLuckyPan.luckyStart(1);
					mStartBt.setImageResource(R.drawable.stop);
				}else
				{
					if (!mLuckyPan.isShouldEnd())
					{
						mLuckyPan.luckyEnd();
						mStartBt.setImageResource(R.drawable.start);
					}
				}
			}
		});
	}
	
}
