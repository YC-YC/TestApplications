package com.example.testdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.file.FileActivity;
import com.example.testdemo.Media.MediaActivity;
import com.example.testdemo.second.base.DynamicFragmentActivity;
import com.example.testdemo.second.base.StaticFragmentActivity;
import com.example.testdemo.second.menu.MenuActivity;
import com.example.testdemo.second.notify.NotifyActivity;
import com.example.testdemo.sensor.SensorActivity;
import com.example.testdemo.third.base.SQLiteActivity;
import com.example.testdemo.third.base.SharedPreferenceActivity;

public class ThirdFragment extends Fragment implements OnClickListener{

	private Button thi_button1;
	private Button thi_button2;
	private Button thi_button3;
	private Button thi_button4;
	private Button thi_button5;
	private Button thi_button6;
	private Button thi_button7;
	private Button thi_button8;
	private View mView;
	
	private Context mContext;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.third_main, null);
		mContext = mView.getContext();
		initView();
		return mView;
	}
	
	private void initView() {
		thi_button1 = (Button) mView.findViewById(R.id.thi_button1);
		thi_button1.setOnClickListener(this);

		thi_button2 = (Button) mView.findViewById(R.id.thi_button2);
		thi_button2.setOnClickListener(this);
		
		thi_button3 = (Button) mView.findViewById(R.id.thi_button3);
		thi_button3.setOnClickListener(this);

		thi_button4 = (Button) mView.findViewById(R.id.thi_button4);
		thi_button4.setOnClickListener(this);
		
		thi_button5 = (Button) mView.findViewById(R.id.thi_button5);
		thi_button5.setOnClickListener(this);
		
		thi_button6 = (Button) mView.findViewById(R.id.thi_button6);
		thi_button6.setOnClickListener(this);
		
		thi_button7 = (Button) mView.findViewById(R.id.thi_button7);
		thi_button7.setOnClickListener(this);
		
		thi_button8 = (Button) mView.findViewById(R.id.thi_button8);
		thi_button8.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.thi_button1:
			startActivity(new Intent(mContext, SharedPreferenceActivity.class));
			break;
		case R.id.thi_button2:
			startActivity(new Intent(mContext, SQLiteActivity.class));
			break;
		case R.id.thi_button3:
			startActivity(new Intent(mContext, FileActivity.class));
			break;
		case R.id.thi_button4:
			startActivity(new Intent(mContext, MenuActivity.class));
			break;
		case R.id.thi_button5:
			startActivity(new Intent(mContext, StaticFragmentActivity.class));
			break;
		case R.id.thi_button6:
			startActivity(new Intent(mContext, DynamicFragmentActivity.class));
			break;
		case R.id.thi_button7:
			startActivity(new Intent(mContext, MediaActivity.class));
			break;
		case R.id.thi_button8:
			startActivity(new Intent(mContext, SensorActivity.class));
			break;
		default:
			break;
		}
	}
	
}
