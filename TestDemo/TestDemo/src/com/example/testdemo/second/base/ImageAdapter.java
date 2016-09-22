package com.example.testdemo.second.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {

	private int[] icons;
	
	private Context mContext;
	
	public ImageAdapter(int[] icons, Context context) {
		this.icons = icons;  
		mContext = context;
	}
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return icons[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView = new ImageView(mContext);
		imageView.setBackgroundResource(icons[position%icons.length]);
		imageView.setLayoutParams(new Gallery.LayoutParams(400, 300));
		imageView.setScaleType(ScaleType.FIT_XY);
		return imageView;
	}

}
