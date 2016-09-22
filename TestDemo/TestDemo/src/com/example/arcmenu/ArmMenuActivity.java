package com.example.arcmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.arcmenu.ArcMenu.OnMenuItemClickListener;
import com.example.testdemo.R;

public class ArmMenuActivity extends Activity {

	private ArcMenu arcMenu;
	private ListView listview;

	private List<String> mData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arcmenu_main);
		
		initView();
		initData();
		listview.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mData));
		initEvent();
	}


	private void initView() {
		arcMenu = (ArcMenu) findViewById(R.id.right_bottom_menu);		
		listview = (ListView) findViewById(R.id.listview);		
	}

	private void initData() {
		mData = new ArrayList<String>();
		for (int i = 'A'; i < 'Z'; i++) {
			mData.add((char) i + "");
		}
	}
	
	private void initEvent() {
		listview.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (arcMenu.isOpen())
				{
					arcMenu.toggleMenu(600);
				}
			}
		});
		
		arcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public void onClick(View view, int position) {
				Toast.makeText(ArmMenuActivity.this, position + "" + view.getTag(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
