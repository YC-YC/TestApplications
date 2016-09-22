package com.example.list;

import java.util.ArrayList;

import com.example.testdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ApkBean> list;
	LayoutInflater mInflater;
	public MyAdapter(Context context, ArrayList<ApkBean> list) {
		this.mContext = context;
		this.list = list;
		mInflater = LayoutInflater.from(mContext);
	}
	
	/*
	 * 更新数据
	 */
	public void OnDataChange(ArrayList<ApkBean> list){
		this.list = list;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ApkBean apkBean= list.get(position);
		ViewHolder holder = null;
		
		if (convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.listview2_item, null);
			holder.apkName = (TextView) convertView.findViewById(R.id.tv_apkname);
			holder.apkInfo = (TextView) convertView.findViewById(R.id.tv_apkinfo);
			holder.apkDes = (TextView) convertView.findViewById(R.id.tv_apkdes);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.apkName.setText(apkBean.getApkName());
		holder.apkInfo.setText(apkBean.getApkInfo());
		holder.apkDes.setText(apkBean.getApkDes());
		return convertView;
	}
	
	class ViewHolder{
		TextView apkName;
		TextView apkInfo;
		TextView apkDes;
	}
}
