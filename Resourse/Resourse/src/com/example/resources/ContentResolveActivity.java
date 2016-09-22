package com.example.resources;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * 测试ContentProvide与ContentResolver
 * @author YC
 * @time 2016-3-12 上午9:54:39
 */
public class ContentResolveActivity extends Activity {

	private   SimpleCursorAdapter adapter;  
    private ListView listView; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO 自动生成的方法存根
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.resolver_main);
    	listView = (ListView) findViewById(R.id.listView_content);
    	
    	ContentResolver resolver = getContentResolver();
    	
    	Uri uri = Uri.parse("content://com.example.resources.PersonContentProvider/person");
    	Cursor cursor = resolver.query(uri, null, null, null, null);
    	adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, 
    			new String[]{"_id", "name", "age"}, 
    			new int[]{R.id.id, R.id.name, R.id.age});
    	listView.setAdapter(adapter);
    	
    	Button insert = (Button) findViewById(R.id.btn_insert);
    	insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentResolver resolver = getContentResolver();
		    	Uri uri = Uri.parse("content://com.example.resources.PersonContentProvider/person");
		    	ContentValues values = new ContentValues();
		    	values.put("name", "huangxi");
		    	values.put("age", 25);
		    	Uri insertUri = resolver.insert(uri, values);
		    	Cursor cursor = resolver.query(uri, null, null, null, null);
		    	adapter = new SimpleCursorAdapter(ContentResolveActivity.this, R.layout.item, cursor, 
		    			new String[]{"_id", "name", "age"}, 
		    			new int[]{R.id.id, R.id.name, R.id.age});
		    	listView.setAdapter(adapter);
		    	Toast.makeText(ContentResolveActivity.this, "添加完成", 1).show();  
			}
		});
    }
	
}
