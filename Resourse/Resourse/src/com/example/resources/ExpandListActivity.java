/**
 * 
 */
package com.example.resources;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author YC
 * @time 2016-4-19 上午10:40:35
 */
public class ExpandListActivity extends Activity  {

	private ExpandableListView mExpandableListView;
	private List<String> group;
	private List<List<String>> child;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_expandedlist);
		initData();
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandedlist);
		mExpandableListView.setAdapter(new MyBaseExpandableListAdapter());
		mExpandableListView.setCacheColorHint(0);
		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(ExpandListActivity.this, "点击了第" + groupPosition + "组，第" + childPosition + "项", 100).show();
				return false;
			}
		});
	}
	
	
	private void initData() {
		group = new ArrayList<String>();
		child = new ArrayList<List<String>>();
		
		addInfo("Andy",new String[]{"male","138123***","GuangZhou"});  
        addInfo("Fairy",new String[]{"female","138123***","GuangZhou"});  
        addInfo("Jerry",new String[]{"male","138123***","ShenZhen"});  
        addInfo("Tom",new String[]{"female","138123***","ShangHai"});  
        addInfo("Bill",new String[]{"male","138231***","ZhanJiang"});  
	}


	private void addInfo(String parent, String[] childs) {
		group.add(parent);
		List<String> childitems = new ArrayList<String>();
		 for(int i=0;i<childs.length;i++){  
			 childitems.add(childs[i]);  
	        }  
	        child.add(childitems); 
	}


	private class MyBaseExpandableListAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {
			return group.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return group.get(groupPosition);
		}
		
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		
		@Override
		public int getChildrenCount(int groupPosition) {
			return child.get(groupPosition).size();
		}


		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String string = group.get(groupPosition);
			/*ImageView mgroupimage=(ImageView)convertView.findViewById(R.id.mGroupimage);
	           mgroupimage.setImageBitmap(mla);
	           if(!isExpanded){
	                  mgroupimage.setImageBitmap(mshou);//或者imageView.setImageResource(R.drawable.mm_submenu_down);
	            }*/
			return getGenericView(string);
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			String string = child.get(groupPosition).get(childPosition);
			return getGenericView(string);
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
		//创建组/子视图    
        public TextView getGenericView(String s) {    
            // Layout parameters for the ExpandableListView    
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(    
                    ViewGroup.LayoutParams.MATCH_PARENT, 40);  
    
            TextView text = new TextView(ExpandListActivity.this);    
            text.setLayoutParams(lp);    
            // Center the text vertically    
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);    
            // Set the text starting position    
            text.setPadding(36, 0, 0, 0);    
                
            text.setText(s);    
            return text;    
        }    
		
	}
}
