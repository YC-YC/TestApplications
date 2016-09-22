package com.example.testdemo.second.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.testdemo.R;

public class MenuActivity extends Activity implements OnClickListener {

	private Button bt_menu1;
	private Button bt_menu2;
	private Button bt_menu3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main);
		initView();
	}
	
	private void initView() {
		bt_menu1 = (Button) findViewById(R.id.bt_menu1);
		bt_menu1.setOnClickListener(this);
		bt_menu2 = (Button) findViewById(R.id.bt_menu2);
		bt_menu2.setOnClickListener(this);
		bt_menu3 = (Button) findViewById(R.id.bt_menu3);
		bt_menu3.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_menu1:
			startActivity(new Intent(this, OptionMenuActivity.class));
			break;
		case R.id.bt_menu2:
			startActivity(new Intent(this, ContextMenuActivity.class));
			break;
		case R.id.bt_menu3:
			startActivity(new Intent(this, SubMenuActivity.class));
			break;
		default:
			break;
		}
	}

}
