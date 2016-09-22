package com.example.testdemo.second.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testdemo.R;

/*
 * 1、给View注册上下文菜单registerForContextMenu
 * 2、添加内容到上下文菜单
 * 	可手动、可xml添加
 * 3、设置点击事件onContextItemSelected()
 */
public class ContextMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_context);
		showListView();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("文件操作");
		menu.setHeaderIcon(R.drawable.ic_launcher);
		/*
		 * 手动添加
		 */
//		menu.add(1, 1, 1, "复制");
//		menu.add(1, 2, 1, "粘贴");
//		menu.add(1, 3, 1, "剪切");
//		menu.add(1, 4, 1, "删除");
		/*
		 * xml添加
		 */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
		case R.id.contextmenu1:
			Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
			break;
		case 2:
		case R.id.contextmenu2:
			Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
			break;
		case 3:
		case R.id.contextmenu3:
			Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
			break;
		case 4:
		case R.id.contextmenu4:
			Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	private void showListView() {
		ListView listView = (ListView) findViewById(R.id.contextmenu_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		this.registerForContextMenu(listView);//给view添加上下文菜单
	}

	private ArrayList<String> getData() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("文件" + i);
		}
		return list;
	}
}
