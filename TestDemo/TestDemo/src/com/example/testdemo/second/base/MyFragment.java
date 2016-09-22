package com.example.testdemo.second.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.testdemo.R;

public class MyFragment extends Fragment {
	private static final String TAG = "MyFragment";
	
	private TextView tv;
	private Button bt;
	private Button bt2;
	
	/*
	 * Fragment向Activity传递数据的方式
	 */
	public interface FragmentCallBack{
		public void callback(String code);
	}
	
	private FragmentCallBack callBack;

	//当Fragment被添加到Activity时调用
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			callBack = (FragmentCallBack) activity;
		}
	
	//每次创建都会调用
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		 * 获取所在的Activity
		 */
		Log.i(TAG, getActivity().toString());
		View view = inflater.inflate(R.layout.fragment, container, false);
		tv = (TextView) view.findViewById(R.id.fragment_tv);
		bt = (Button) view.findViewById(R.id.fragment_bt);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getArguments() != null)
				{
					tv.setText("Fragment中获取到的数据为：" + getArguments().get("name"));
				}
				else
				{
					tv.setText("Activity并没有向Fragment传递数据");
				}
			}
		});
		bt2 = (Button) view.findViewById(R.id.fragment_bt2);
		bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callBack.callback("Fragment向Activity传递数据");
			}
		});
		return view;
	}

	

	//创建Fragment时会被调用
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	//Fragment创建完成时调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	
	//启动Fragment
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	//恢复Fragment时被调用，每次调用onStart时会调用
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	//停止fragment
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	//销毁Fragment所包含的View组件时调用
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	//销毁Fragment时会被调用
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	//Fragment从Activity时调用 
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
	
}
