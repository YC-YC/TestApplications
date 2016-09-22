package com.example.testdemo.first.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testdemo.R;

/*
 * 1、添加数据源
 * 2、定义适配器
 * 3、设置下拉菜单 样式
 * 4、添加适配器到下拉列表中
 * 5、设置点击响应事件
 */
public class SpinnerActivity extends Activity implements OnItemSelectedListener{
	
	private TextView mTextView;
	private Spinner mSpinner;
	
	private List<String> mData = new ArrayList<String>();
	private ArrayAdapter<String> mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner);
		
		initView();
	}

	private void initView() {
		mTextView = (TextView) findViewById(R.id.spinner_tv);
		mSpinner = (Spinner) findViewById(R.id.spinner1);
		mTextView.setText("选择的城市是：北京");
		mData.add("北京");
		mData.add("上海");
		mData.add("广州");
		mData.add("深圳");
		
		mAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, 
				mData);
		mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		mSpinner.setAdapter(mAdapter);
		
		mSpinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		mTextView.setText("选择的城市是：" + mAdapter.getItem(position));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


}
