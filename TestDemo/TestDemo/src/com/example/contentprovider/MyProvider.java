package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/*
 * 继承ContentProvider
 * mxl添加注册并且
 * 重写方法
 * uri:content://com.exampel.contentprovider/music/#
 * content://：固定格式
 * com.exampel.contentprovider:mxl中注册的provider中的android:authorites属性对应的值
 * music：操作于哪个条目
 * #:指定条目下的哪条记录（#是通配符）
 * 
 * 通过UriMatcher类型封装Uri
 * 通过ContentResolver操作ContentProvider中的数据
 * 
 */
public class MyProvider extends ContentProvider {

	//Provider被创建的时候调用 
	@Override
	public boolean onCreate() {
		return false;
	}
	//根据Uri的selection查询数据
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	//返回当前Uri的MIME类型，如果该Uri对应的数据有多条记录，那么MIME类型字符串就是以vnd.anrdoid.dir开头
	//如果只有一条，MIMEy就是以vnd.android.cursor.item开头
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据Uri插入数据
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据Uri的selection删除数据
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	//根据Uri修改selection指定的全部记录
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
