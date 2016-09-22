package com.example.testdemo.third.base;

import java.io.File;

import com.example.testdemo.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


/*
 * SQLite支持NULL/InTEGER/REAL/TEXT/BLOB
 * 多个进程可以在同一时间从同一数据库读取数据，但只有一个可以写入数据
 * 1、SQLiteDatabase
 * 		提供一些管理SQLite数据库的类
 * 		提供创建、删除、执行SQL命令并执行其他常见数据库管理任务的方法，每个应用只有一个数据库
 * 		execSQL/insert/delete/update/query/rawQuery
 * 2、SQLiteOpenHelper
 * 
 */
public class SQLiteActivity extends Activity {

	private TextView tv;
	
	private static final String TABLE_NAME = "usertb";
	
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite);
		tv = (TextView) findViewById(R.id.sqlite);
		//创建一个数据库并且打开
		db = openOrCreateDatabase("mysqlite1.db", MODE_PRIVATE, null);
		
		
		tv.setText("创建数据库\r\n");
		db.execSQL("create table if not exists "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
		tv.append("执行execSQL:create table if not exits "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)\r\n");
		
//		db.execSQL("delete from "+TABLE_NAME);
//		tv.append("\r\n清空表中数据");
//		db.execSQL("create table "+TABLE_NAME + "(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
		
		tv.append("\r\n执行execSQL语句插入1条数据");
		db.execSQL("insert into  "+TABLE_NAME + "(name,sex,age) values('张三','男','18')");
		/*
		 * ContentValues类似hashmap
		 */
		ContentValues values = new ContentValues();
		values.put("name", "李四");
		values.put("sex", "女");
		values.put("age", "19");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "王五");
		values.put("sex", "女");
		values.put("age", "20");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "赵六");
		values.put("sex", "女");
		values.put("age", "21");
		db.insert(TABLE_NAME, null, values);
		values.clear();
		values.put("name", "张七");
		values.put("sex", "女");
		values.put("age", "21");
		db.insert(TABLE_NAME, null, values);
		tv.append("\r\n执行ContentValues方法插入4条数据");
		getDBContent();
		values.clear();
		values.put("sex", "男");
		//ID大于3的改成“男”
		tv.append("\r\n更新ID大于3的改成“男”");
		db.update(TABLE_NAME, values, "_id>?", new String[]{"3"});
		tv.append("\r\n删除名字带有“张”的记录");
		db.delete(TABLE_NAME, "name like ?", new String[]{"%张%"});
		getDBContent();
//		db.execSQL("drop table "+TABLE_NAME);
//		tv.append("\r\n清空表中数据");
		db.close();
		
	}
	private void getDBContent() {
		//查询
		Cursor cursor = db.rawQuery("select * from  "+TABLE_NAME + "", null);
		if (cursor != null)
		{
			tv.append("\r\n查询有数据");
			while(cursor.moveToNext())
			{
				tv.append("\r\n获取值为 id=" + cursor.getInt(cursor.getColumnIndex("_id"))
						+ "name=" +cursor.getString(cursor.getColumnIndex("name"))
						+ "sex=" +cursor.getString(cursor.getColumnIndex("sex"))
						+ "age=" +cursor.getInt(cursor.getColumnIndex("age")));
			}
			cursor.close();
		}
	}
}
