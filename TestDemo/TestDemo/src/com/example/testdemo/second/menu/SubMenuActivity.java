package com.example.testdemo.second.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.testdemo.R;

public class SubMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_option);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*
		 * 手动添加
		 */
		/*SubMenu file = menu.addSubMenu("文件");
		SubMenu edit = menu.addSubMenu("编辑");
		file.setHeaderTitle("文件操作");
		file.setHeaderIcon(R.drawable.ic_launcher);
		file.add(1, 1, 1, "新建");
		file.add(1, 2, 1, "打开");
		file.add(1, 3, 1, "保存");

		edit.setHeaderTitle("编辑操作");
		edit.setHeaderIcon(R.drawable.ic_launcher);
		edit.add(2, 1, 1, "复制");
		edit.add(2, 2, 1, "粘贴");
		edit.add(2, 3, 1, "剪切");*/
		
		/*
		 * xml添加
		 */
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sub_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getGroupId() == 1 || item.getGroupId() == R.id.submenu1) {
			switch (item.getItemId()) {
			case 1:
			case R.id.submenu1_1:
				Toast.makeText(this, "点击了新建", Toast.LENGTH_SHORT).show();
				break;
			case 2:
			case R.id.submenu1_2:
				Toast.makeText(this, "点击了打开", Toast.LENGTH_SHORT).show();
				break;
			case 3:
			case R.id.submenu1_3:
				Toast.makeText(this, "点击了保存", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu1_4:
				Toast.makeText(this, "点击了另存为", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_1:
				Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_2:
				Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_3:
				Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
				break;
			case R.id.submenu2_4:
				Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
//		} else if (item.getGroupId() == 2|| item.getGroupId() == R.id.submenu2) {
//			switch (item.getItemId()) {
//			case 1:
//			case R.id.submenu2_1:
//				Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
//				break;
//			case 2:
//			case R.id.submenu2_2:
//				Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
//				break;
//			case 3:
//			case R.id.submenu2_3:
//				Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.submenu2_4:
//				Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
//				break;
//			}
//		}
		return super.onOptionsItemSelected(item);
	}
}
