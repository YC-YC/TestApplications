package com.example.treeview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testdemo.R;
import com.example.treeview.utils.FileBean;
import com.example.treeview.utils.Node;
import com.example.treeview.utils.OrgBean;
import com.example.treeview.utils.TreeViewAdapter.OnTreeNodeClickListener;

/*
 * ListView's + paddingLeft(level)+expand include
 * 1、将数据Bean-->Node(TreeHelper)
 * 2、设置节点间的关系，以及排序
 */
public class TreeView extends Activity {

	private ListView mTree;
//	private SimpleTreeViewAdapter<FileBean> mAdapter;
	private SimpleTreeViewAdapter<OrgBean> mAdapter;
	private List<FileBean> mDatas = new ArrayList<FileBean>();
	private List<OrgBean> mDatas2 = new ArrayList<OrgBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treeview_main);
		mTree = (ListView) findViewById(R.id.treeview_listview);
		initData();
		try {
//			mAdapter = new SimpleTreeViewAdapter<FileBean>(this, mTree, mDatas, 1);
			mAdapter = new SimpleTreeViewAdapter<OrgBean>(this, mTree, mDatas2, 1);
			mTree.setAdapter(mAdapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		initEvent();
	}

	private void initEvent() {
		//叶添加点击事件
		mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {

			@Override
			public void onClick(Node node, int position) {
				if (node.isLeaf())
				{
					Toast.makeText(TreeView.this, node.getName(), 100).show();
				}
			}
		});
		
		//添加长按添加事件
		mTree.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				final EditText et = new EditText(TreeView.this);
				new AlertDialog.Builder(TreeView.this).setTitle("Add Node").setView(et)
				.setPositiveButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mAdapter.addExtraNode(position, et.getText().toString());
					}
				}).setNegativeButton("Cancel", null).show();
				return true;
			}
		});
	}

	private void initData() {
		FileBean bean = new FileBean(1, 0, "根目录1");
		mDatas.add(bean);
		bean = new FileBean(2, 0, "根目录2");
		mDatas.add(bean);
		bean = new FileBean(3, 0, "根目录3");
		mDatas.add(bean);
		bean = new FileBean(4, 1, "根目录1-1");
		mDatas.add(bean);
		bean = new FileBean(5, 1, "根目录1-2");
		mDatas.add(bean);
		bean = new FileBean(6, 5, "根目录1-2-1");
		mDatas.add(bean);
		bean = new FileBean(7, 3, "根目录3-1");
		mDatas.add(bean);
		bean = new FileBean(8, 3, "根目录3-2");
		mDatas.add(bean);
		
		OrgBean bean2 = new OrgBean(1, 0, "根目录1");
		mDatas2.add(bean2);
		bean2 = new OrgBean(2, 0, "根目录2");
		mDatas2.add(bean2);
		bean2 = new OrgBean(3, 0, "根目录3");
		mDatas2.add(bean2);
		bean2 = new OrgBean(4, 1, "根目录1-1");
		mDatas2.add(bean2);
		bean2 = new OrgBean(5, 1, "根目录1-2");
		mDatas2.add(bean2);
		bean2 = new OrgBean(6, 5, "根目录1-2-1");
		mDatas2.add(bean2);
		bean2 = new OrgBean(7, 3, "根目录3-1");
		mDatas2.add(bean2);
		bean2 = new OrgBean(8, 3, "根目录3-2");
		mDatas2.add(bean2);
	}
}
