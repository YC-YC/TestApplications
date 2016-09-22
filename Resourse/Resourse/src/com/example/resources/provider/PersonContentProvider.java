package com.example.resources.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 重写增删改查
 * @author YC
 * @time 2016-3-12 上午9:57:20
 */
public class PersonContentProvider extends ContentProvider {

	//URI的指定，此处的字符串必须和声明的authorities一致
	public static final String AUTHORITIES = "com.example.resources.PersonContentProvider";
	private static final int PERSONS = 1;  //查询所有的返回码
	private static final int PERSON = 2; 	//查询单个的返回码
	private DataBaseOpenHelper dbOpenHelper;
//	常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		MATCHER.addURI(AUTHORITIES, "person", PERSONS);	
		MATCHER.addURI(AUTHORITIES, "person/#", PERSON);	
	}
	@Override
	public boolean onCreate() {
		this.dbOpenHelper = new DataBaseOpenHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSONS:
			return db.query("person", projection, selection, selectionArgs, 
					null, null, sortOrder);
		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && "".equals(selection))
			{
				where = selection + "and" + where;
				
			}
			return db.query("person", projection, where, selectionArgs,
					null, null, sortOrder);
			default:
				throw new IllegalArgumentException("Unknow Uri:" + uri.toString());
		}
	}

	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/person";
		case PERSON:
			return "vnd.android.cursor.item/person"; 
			default:
				throw new IllegalArgumentException("Unknow Uri:" + uri.toString());
		}
	}

	// 插入person表中的所有记录 /person  
    // 插入person表中指定id的记录 /person/10  
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSONS:
			// 特别说一下第二个参数是当name字段为空时，将自动插入一个NULL。  
            long rowid = db.insert("person", "name", values); 
            Uri insertUri = ContentUris.withAppendedId(uri, rowid);// 得到代表新增记录的Uri  
            this.getContext().getContentResolver().notifyChange(uri, null);  
            return insertUri;
			default:
				throw new IllegalArgumentException("Unknow Uri:" + uri.toString());
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {  
        case PERSONS:  
            count = db.delete("person", selection, selectionArgs);  
            return count;  
  
        case PERSON:  
            long id = ContentUris.parseId(uri);  
            String where = "_id=" + id;  
            if (selection != null && !"".equals(selection)) {  
                where = selection + " and " + where;  
            }  
            count = db.delete("person", where, selectionArgs);  
            return count;  
  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        }  
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {  
		case PERSONS:  
            count = db.update("person", values, selection, selectionArgs);  
            return count;  
        case PERSON:  
            long id = ContentUris.parseId(uri);  
            String where = "_id=" + id;  
            if (selection != null && !"".equals(selection)) {  
                where = selection + " and " + where;  
            }  
            count = db.update("person", values, where, selectionArgs);  
            return count;  
        default:  
            throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());  
        }  
	}

}
