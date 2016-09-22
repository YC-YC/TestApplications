package com.example.resources.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author YC
 * @time 2016-3-12 上午10:31:15
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper	{
	
	 private static final String DATABASE_NAME = "person.db"; //数据库名称  
	   private static final int DATABASE_VERSION = 1;//数据库版本  
	
	public DataBaseOpenHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		db.execSQL("create table if not exists " + "person" +"(_id integer primary key autoincrement, name varchar(20), age varchar(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根
		db.execSQL("DROP TABLE IF EXISTS person");
	}
}
