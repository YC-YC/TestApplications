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

import com.example.testdemo.dictionary.DictionaryActivity;
import com.example.testdemo.floatview.FloatView;
import com.example.testdemo.second.base.DynamicFragmentActivity;
import com.example.testdemo.second.base.GalleryActivity;
import com.example.testdemo.second.base.StaticFragmentActivity;
import com.example.testdemo.second.base.ViewFlipperActivity;
import com.example.testdemo.second.dialog.DialogActivity;
import com.example.testdemo.second.menu.MenuActivity;
import com.example.testdemo.second.notify.NotifyActivity;
import com.example.testdemo.second.toast.ToastActivity;

public class SecondFragment extends Fragment implements OnClickListener{
	private View mView;
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	
	private Context mContext;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.second_main, null);
		mContext = mView.getContext();
		initView();
		return mView;
	}

	private void initView() {
		button1 = (Button) mView.findViewById(R.id.button1);
		button1.setOnClickListener(this);

		button2 = (Button) mView.findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		button3 = (Button) mView.findViewById(R.id.button3);
		button3.setOnClickListener(this);

		button4 = (Button) mView.findViewById(R.id.button4);
		button4.setOnClickListener(this);
		
		button5 = (Button) mView.findViewById(R.id.button5);
		button5.setOnClickListener(this);
		
		button6 = (Button) mView.findViewById(R.id.button6);
		button6.setOnClickListener(this);
		
		button7 = (Button) mView.findViewById(R.id.button7);
		button7.setOnClickListener(this);
		
		button8 = (Button) mView.findViewById(R.id.button8);
		button8.setOnClickListener(this);
		
		button9 = (Button) mView.findViewById(R.id.button9);
		button9.setOnClickListener(this);
		
		button10 = (Button) mView.findViewById(R.id.button10);
		button10.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(mContext, ToastActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(mContext, DialogActivity.class));
			break;
		case R.id.button3:
			startActivity(new Intent(mContext, NotifyActivity.class));
			break;
		case R.id.button4:
			startActivity(new Intent(mContext, MenuActivity.class));
			break;
		case R.id.button5:
			startActivity(new Intent(mContext, StaticFragmentActivity.class));
			break;
		case R.id.button6:
			startActivity(new Intent(mContext, DynamicFragmentActivity.class));
			break;
		case R.id.button7:
			startActivity(new Intent(mContext, ViewFlipperActivity.class));
			break;
		case R.id.button8:
			startActivity(new Intent(mContext, GalleryActivity.class));
			break;
		case R.id.button9:
			startActivity(new Intent(mContext, FloatView.class));
			break;
		case R.id.button10:
			startActivity(new Intent(mContext, DictionaryActivity.class));
			break;
		default:
			break;
		}
	}
}
