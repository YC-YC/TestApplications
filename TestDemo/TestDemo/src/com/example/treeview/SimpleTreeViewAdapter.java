package com.example.treeview;

import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testdemo.R;
import com.example.treeview.utils.Node;
import com.example.treeview.utils.TreeHelper;
import com.example.treeview.utils.TreeViewAdapter;

public class SimpleTreeViewAdapter <T> extends TreeViewAdapter<T> {

	public SimpleTreeViewAdapter(Context context, ListView tree, List<T> datas,
			int defaultExpandLevel) throws IllegalAccessException,
			IllegalArgumentException {
		super(context, tree, datas, defaultExpandLevel);
	}

	
	@Override
	public View getConvertView(Node node, int position, View convertView,
			ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.list_item_treeview, parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.item_icon);
			holder.text = (TextView) convertView.findViewById(R.id.item_text);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		if (node.getIcon() == -1)
		{
			holder.img.setVisibility(View.INVISIBLE);
		}
		else
		{
			holder.img.setVisibility(View.VISIBLE);
			holder.img.setImageResource(node.getIcon());
		}
		holder.text.setText(node.getName());
		return convertView;
	}
	
	private class ViewHolder
	{
		ImageView img;
		TextView text;
	}


	public void addExtraNode(int position, String string) {
		Node node = mVisibleNodes.get(position);
		int indexOf = mAllNodes.indexOf(node);
		
		if (!node.isExpand())
		{
			node.setExpand(true);
		}
		Node extraNode = new Node(-1, node.getId(), string);
		extraNode.setParent(node);
		node.getChild().add(extraNode);
		mAllNodes.add(indexOf+1, extraNode);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
		notifyDataSetChanged();
	}

}
