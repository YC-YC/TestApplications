package com.example.testdemo.first.gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.testdemo.R;

/*
 * 1、准备数据源
 * 2、新建适配器（simpleadapter）
 * 3、GridView添加适配器
 * 4、事件监听
 */
public class GridViewActivity extends Activity implements OnItemClickListener{
	
	private GridView mGridView;
	List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
	private int[] icons = {R.drawable.address_book, 
			R.drawable.calendar,
			R.drawable.camera,
			R.drawable.clock,
			R.drawable.games_control,
			R.drawable.messenger,
			R.drawable.ringtone,
			R.drawable.settings,
			R.drawable.speech_balloon,
			R.drawable.weather,
			R.drawable.world,
			R.drawable.youtube};
	
	private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "信息"
			, "铃声", "设置", "语音", "天气", "浏览器", "视频"};
	
	private SimpleAdapter mSimpleAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		
		initView();
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.gridView1);
		
		data = getData();
		mSimpleAdapter = new SimpleAdapter(this, data, R.layout.gridview_item, 
				new String[]{"image", "text"}, 
				new int[]{R.id.gridview_img, R.id.gridview_tv});
		mGridView.setAdapter(mSimpleAdapter);
		mGridView.setOnItemClickListener(this);
	}

	private List<Map<String, Object>> getData() {
		for (int i = 0; i < icons.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icons[i]);
			map.put("text", iconName[i]);
			data.add(map);
		}
		return data;
	}
 
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "点击了"+iconName[position], Toast.LENGTH_LONG).show();
	}
}
