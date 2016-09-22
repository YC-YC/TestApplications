/**
 * 
 */
package com.example.customview;

import java.util.HashMap;
import java.util.Map;

import com.example.resources.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author YC
 * @time 2016-3-24 下午7:57:58
 */
public class CustomTextViewActivity extends Activity {

	private TextViewWithPic mTeWithPic;
	
	private Map<String, Integer> mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_customtextview);
		mTeWithPic = (TextViewWithPic) findViewById(R.id.id_custom_textview);
		getCharMap();
		mTeWithPic.setText("21度", mMap);
	}

	/**
	 * 
	 */
	private void getCharMap() {
		mMap = new HashMap<String, Integer>();
		mMap.put("0", R.drawable.num0);
		mMap.put("1", R.drawable.num1);
		mMap.put("2", R.drawable.num2);
		mMap.put("3", R.drawable.num3);
		mMap.put("4", R.drawable.num4);
		mMap.put("5", R.drawable.num5);
		mMap.put("6", R.drawable.num6);
		mMap.put("7", R.drawable.num7);
		mMap.put("8", R.drawable.num8);
		mMap.put("9", R.drawable.num9);
		mMap.put("度", R.drawable.degree);
	}
}
