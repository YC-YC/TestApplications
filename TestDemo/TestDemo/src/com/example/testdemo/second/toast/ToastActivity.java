package com.example.testdemo.second.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testdemo.R;

public class ToastActivity extends Activity implements OnClickListener {
	private Button bt_toast1;
	private Button bt_toast2;
	private Button bt_toast3;
	private Button bt_toast4;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toast_activity);
		initView();
	}

	private void initView() {
		bt_toast1 = (Button) findViewById(R.id.bt_toast1);
		bt_toast1.setOnClickListener(this);

		bt_toast2 = (Button) findViewById(R.id.bt_toast2);
		bt_toast2.setOnClickListener(this);

		bt_toast3 = (Button) findViewById(R.id.bt_toast3);
		bt_toast3.setOnClickListener(this);

		bt_toast4 = (Button) findViewById(R.id.bt_toast4);
		bt_toast4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_toast1:
			showToast1();
			break;
		case R.id.bt_toast2:
			showToast2();
			break;
		case R.id.bt_toast3:
			showToast3();
			break;
		case R.id.bt_toast4:
			showToast4();
			break;
		default:
			break;
		}
	}

	/*
	 * 显示默认的Toast
	 */
	private void showToast1() {
		Toast.makeText(this, "显示默认的Toast", Toast.LENGTH_LONG).show();
	}

	/*
	 * 改变位置的Toast
	 */
	private void showToast2() {
		Toast toast = Toast.makeText(this, "改变位置的Toast", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 50, 0);
		toast.show();
	}

	/*
	 * 显示带图片的Toast
	 */
	private void showToast3() {
		Toast toast = Toast.makeText(this, "显示带图片的Toast", Toast.LENGTH_LONG);
		LinearLayout toast_layout = (LinearLayout) toast.getView();
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.ic_launcher);
		toast_layout.addView(imageView, 0);// index为位置
		toast.setView(toast_layout);
		toast.show();
	}

	/*
	 * 完全自定义的Toast
	 */
	private void showToast4() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View toast_view = inflater.inflate(R.layout.mytoast, null);
		Toast toast = new Toast(this);
		toast.setView(toast_view);
		toast.show();
	}
}
