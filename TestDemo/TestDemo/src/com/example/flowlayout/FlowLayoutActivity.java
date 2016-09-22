package com.example.flowlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.testdemo.R;


public class FlowLayoutActivity extends Activity {
	
	private FlowerLayout mFlowerLayout1;
	
	private String[] valus = new String[]{"Hello", "World", "LinearLayout",
			"Hello World", "World Up", "LinearLayout",
			"Hello Button", "World", "LinearLayout",
			"Hello", "World LinearLayout", "LinearLayout",
			"Hello", "World Button", "LinearLayout"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flowlayout_main);
		mFlowerLayout1 = (FlowerLayout) findViewById(R.id.id_flowLayout);
		initData();
	}

	private void initData() {
		LayoutInflater inflater = LayoutInflater.from(this);
		for (int i = 0; i < valus.length; i++)
		{
//			Button bt = new Button(FlowLayoutActivity.this);
//			MarginLayoutParams lp = new MarginLayoutParams(
//					MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
//			bt.setText(valus[i]);
//			mFlowerLayout1.addView(bt, lp);
			TextView view = (TextView) inflater.inflate(R.layout.tv, mFlowerLayout1, false);
			view.setText(valus[i]);
			mFlowerLayout1.addView(view);
		}
	}
}
