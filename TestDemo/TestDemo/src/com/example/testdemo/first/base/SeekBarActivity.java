package com.example.testdemo.first.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarActivity extends Activity implements OnSeekBarChangeListener{

	/*
	 * SeekBar继承ProgressBar
	 * 很多方法与ProgressBar一样，需要实现OnSeekBarChangeListener
	 */
	private SeekBar mSeekBar = null;
	private TextView mTextView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);
		
		initView();
	}

	private void initView() {
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		
		mTextView = (TextView) findViewById(R.id.seekbar_value);
		
	}

	//数值改变
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mTextView.setText("当前值为：" + progress);
	}

	//开始拖动
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	//停止拖动
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
}
