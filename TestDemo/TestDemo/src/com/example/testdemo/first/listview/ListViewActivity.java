package com.example.testdemo.first.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.testdemo.R;

/*
 * 1、新建适配器
 * 2、加载数据
 * 3、视图添加适配器
 */
public class ListViewActivity extends Activity {
	
	private ListView mListView;
	private ArrayAdapter<String> arr_adAdapter;
	private SimpleAdapter simp_aAdapter;
	
	private List<Map<String, Object>> datalist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		initView();
	}

	private void initView() {
		mListView = (ListView) findViewById(R.id.listView1);
		/*
		 * BaseAdapter
		 */
//		String[] arr_data = {"语文", "英语", "数学", "物理"};
//		arr_adAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
//		mListView.setAdapter(arr_adAdapter);
		
		/*
		 * simpleAdapter
		 * 
		 * data:数据源（ List<? extends Map<String, ?>> data），一个map组成的List集合
		 * 		每个map对应listView列表中的一行
		 * 		每个map（键值对）中的键为from所指定的键
		 * from:map的键值名
		 * to:视图ID，与from对应
		 * 
		 * from作为键值数组，与to对应视图的id,map中的Key值与from对应，value是实际的内容
		 */
		datalist = new ArrayList<Map<String, Object>>();
		datalist = getData();
		simp_aAdapter = new SimpleAdapter(this, datalist, R.layout.list_item, 
				new String[]{"pic", "text"}, 
				new int[]{R.id.list_item_img, R.id.list_item_textview});
		mListView.setAdapter(simp_aAdapter);
	}

	private List<Map<String, Object>> getData() {
		for(int i = 0; i < 20; i++)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", R.drawable.ic_launcher);
			map.put("text", "幕课" + i);
			datalist.add(map);
		}
		return datalist;
	}
}
