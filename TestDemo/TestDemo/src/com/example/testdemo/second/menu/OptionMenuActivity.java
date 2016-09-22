package com.example.testdemo.second.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.testdemo.R;
import com.example.testdemo.second.dialog.DialogActivity;
import com.example.testdemo.second.toast.ToastActivity;

public class OptionMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_option);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		/*
		 * 配置文件添加方法
		 */
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.main, menu);
		/*
		 * 动态加载
		 */
		menu.add(0, 100, 0, "菜单一");
		menu.add(0, 101, 1, "菜单二");
		menu.add(1, 102, 2, "菜单三");
		menu.add(1, 102, 3, "菜单四");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.optionmenu1:
		case 100:
			item.setIntent(new Intent(this, MenuActivity.class));
			break;
		case R.id.optionmenu2:
		case 101:
			item.setIntent(new Intent(this, ToastActivity.class));
			break;
		case R.id.optionmenu3:
		case 102:
			item.setIntent(new Intent(this, DialogActivity.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
