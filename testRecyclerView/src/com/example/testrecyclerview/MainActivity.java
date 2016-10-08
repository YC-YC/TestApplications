package com.example.testrecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.admin.DeviceAdminInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RecyclerView mRecyclerView;
	
	private HomeAdapter mAdapter;
	private List<String> mData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initData();
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
		
		//添加分割线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
	}
	
	
	private void initData() {
		mData = new ArrayList<String>();
		for (int i = 'A'; i < 'Z'; i++){
			mData.add("" + (char)i);
		}
	}


	private class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
		
		
		class MyViewHolder extends ViewHolder{

			TextView tv;
			
			public MyViewHolder(View arg0) {
				super(arg0);
				tv = (TextView) arg0.findViewById(R.id.id_num);
			}
			
		}

		@Override
		public int getItemCount() {
			return mData.size();
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int location) {
			holder.tv.setText(mData.get(location));
			
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
					MainActivity.this).inflate(R.layout.item, parent, false));
			return holder;
		}
	}

}
