package com.example.testdemo.third.base;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.testdemo.R;

public class SharedPreferenceActivity extends Activity implements
		OnClickListener {

	/*
	 * 本质是基于XML文件存储key-value
	 * 
	 * SharedPreference只能获取数据而不支持存储和修改 存储修改是通过Editor对象实现
	 * 
	 * 1、获取SharedPreference对象 2、获取SharedPreference.Editor对象
	 * 3、通过Editor的Putxxx保存数据 4、Editor.commit提交
	 * 
	 * 点击屏幕其它地方实现隐藏输入法键盘
	 */

	private RelativeLayout layout;
	private EditText mEditUser;
	private EditText mEditPsw;
	private CheckBox mCheckBox;
	private Button login;
	private SharedPreferences pref = null;
	Editor mEdit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpraference);
		
		initView();
		initEvent();
	}

	private void initEvent() {
		
		SetOnTouch(layout);
		
		login.setOnClickListener(this);

		// pref = PreferenceManager.getDefaultSharedPreferences(this);
		// 设置文件名称和权限
		pref = getSharedPreferences("mypref", MODE_PRIVATE);
		mEdit = pref.edit();
		String name = pref.getString("name", "");
		String psw = pref.getString("psw", "");
		if (name != "") {
			mEditUser.setText(name);
			if (psw != "") {
				mEditPsw.setText(psw);
				mCheckBox.setChecked(true);
			}
		}else{
			mEditUser.setText("");
			mEditPsw.setText("");
			mCheckBox.setChecked(false);
		}
	}

	private void initView() {

		layout = (RelativeLayout) findViewById(R.id.sharedpreference);
		mEditUser = (EditText) findViewById(R.id.et_user);
		mEditPsw = (EditText) findViewById(R.id.et_psw);
		mCheckBox = (CheckBox) findViewById(R.id.checkBox1);
		login = (Button) findViewById(R.id.login);

		// mEdit.putString("name", "黄熙");
		// mEdit.putInt("age", 18);
		// mEdit.putLong("time", System.currentTimeMillis());
		// mEdit.putBoolean("man", true);
		// mEdit.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			String name = mEditUser.getText().toString().trim();
			String psw = mEditPsw.getText().toString().trim();
			if (name != null && psw != null && mCheckBox.isChecked()) {
				mEdit.putString("name", name);
				mEdit.putString("psw", psw);
				mEdit.commit();
				MyToast("保存成功");
			} else {
				mEdit.remove("name");
				mEdit.commit();
				MyToast("没有保存");
			}
			break;

		default:
			break;
		}

	}
	

	//设置OnTouch监听事件
	private void SetOnTouch(View view)
	{
		if (!(view instanceof EditText))
		{
			view.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(SharedPreferenceActivity.this);
					return false;
				}
			});
		}
		
		//递归监听OnTouch事件
		if (!(view instanceof ViewGroup))
		{
			for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
				SetOnTouch(((ViewGroup)view).getChildAt(i));
			}
		}
	}
	//隐藏输入法
	private void hideSoftKeyboard(Activity activity)
	{
		InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	private void MyToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
}
