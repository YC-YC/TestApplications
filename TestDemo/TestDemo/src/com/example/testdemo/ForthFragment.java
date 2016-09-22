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

import com.example.arcmenu.ArmMenuActivity;
import com.example.customerview.CustomActivity;
import com.example.flowlayout.FlowLayoutActivity;
import com.example.guaguaka.GuaGuaActivity;
import com.example.list.ListActivity;
import com.example.luckypan.LuckyPanActivity;
import com.example.recylerview.RecyclerActivity;
import com.example.slidingMenu.SlidingActivity;
import com.example.testdemo.flashing.FlashLightMainActivity;
import com.example.treeview.TreeView;

public class ForthFragment extends Fragment implements OnClickListener{

	private Button for_button1;
	private Button for_button2;
	private Button for_button3;
	private Button for_button4;
	private Button for_button5;
	private Button for_button6;
	private Button for_button7;
	private Button for_button8;
	private Button for_button9;
	private Button for_button10;
	private View mView;
	private Context mContext;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.forth_main, null);
		mContext = mView.getContext();
		initView();
		return mView;
	}
	private void initView() {
		for_button1 = (Button) mView.findViewById(R.id.for_button1);
		for_button1.setOnClickListener(this);

		for_button2 = (Button) mView.findViewById(R.id.for_button2);
		for_button2.setOnClickListener(this);
		
		for_button3 = (Button) mView.findViewById(R.id.for_button3);
		for_button3.setOnClickListener(this);

		for_button4 = (Button) mView.findViewById(R.id.for_button4);
		for_button4.setOnClickListener(this);
		
		for_button5 = (Button) mView.findViewById(R.id.for_button5);
		for_button5.setOnClickListener(this);
		
		for_button6 = (Button) mView.findViewById(R.id.for_button6);
		for_button6.setOnClickListener(this);
		
		for_button7 = (Button) mView.findViewById(R.id.for_button7);
		for_button7.setOnClickListener(this);
		
		for_button8 = (Button) mView.findViewById(R.id.for_button8);
		for_button8.setOnClickListener(this);
		
		for_button9 = (Button) mView.findViewById(R.id.for_button9);
		for_button9.setOnClickListener(this);
		
		for_button10 = (Button) mView.findViewById(R.id.for_button10);
		for_button10.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.for_button1:
			startActivity(new Intent(mContext, ListActivity.class));
			break;
		case R.id.for_button2:
			startActivity(new Intent(mContext, CustomActivity.class));
			break;
		case R.id.for_button3:
			startActivity(new Intent(mContext, SlidingActivity.class));
			break;
		case R.id.for_button4:
			startActivity(new Intent(mContext, ArmMenuActivity.class));
			break;
		case R.id.for_button5:
			startActivity(new Intent(mContext, TreeView.class));
			break;
		case R.id.for_button6:
			startActivity(new Intent(mContext, FlowLayoutActivity.class));
			break;
		case R.id.for_button7:
			startActivity(new Intent(mContext, LuckyPanActivity.class));
			break;
		case R.id.for_button8:
			startActivity(new Intent(mContext, RecyclerActivity.class));
			break;
		case R.id.for_button9:
			startActivity(new Intent(mContext, GuaGuaActivity.class));
			break;
		case R.id.for_button10:
			startActivity(new Intent(mContext, FlashLightMainActivity.class));
			break;
		default:
			break;
		}
	}
}
