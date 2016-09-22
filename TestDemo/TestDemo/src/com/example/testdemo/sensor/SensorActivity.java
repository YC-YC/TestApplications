package com.example.testdemo.sensor;

import com.example.testdemo.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author YC
 * @time 2016-2-29 上午9:32:55
 */
public class SensorActivity extends Activity implements OnClickListener, SensorEventListener {
	
	private Button mBtnStart, mBtnReset, mBtnStop;
	private TextView mTvMsg;
	
	private SensorManager mSensorManager;
	private int mCount = 0;
	private boolean flag;
	private float lastPoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_main);
		mBtnStart = (Button) findViewById(R.id.btn_sensor_start);
		mBtnStart.setOnClickListener(this);
		mBtnReset = (Button) findViewById(R.id.btn_sensor_reset);
		mBtnReset.setOnClickListener(this);
		mBtnStop = (Button) findViewById(R.id.btn_sensor_stop);
		mBtnStop.setOnClickListener(this);
		mTvMsg = (TextView) findViewById(R.id.tv_sensor_count);
		flag = true;
	}
	@Override
	public void onClick(View v) {
		String msg = "";
		switch (v.getId()) {
		case R.id.btn_sensor_start:
			mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
			mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
			msg = "开始计数";
			break;
		case R.id.btn_sensor_reset:
			mCount = 0;
			msg = "重置计数";
			break;
		case R.id.btn_sensor_stop:
			mSensorManager.unregisterListener(this);
			mCount = 0;
			msg = "结束计数";
			break;
		default:
			break;
		}
		mTvMsg.setText(String.valueOf(mCount));
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (flag)
		{
			lastPoint = event.values[1];
			flag = false;
		}
		
		if (Math.abs(event.values[1] - lastPoint) > 8)
		{
			lastPoint = event.values[1];
			mCount++;
			mTvMsg.setText(String.valueOf(mCount));
		}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO 自动生成的方法存根
		
	}
}
