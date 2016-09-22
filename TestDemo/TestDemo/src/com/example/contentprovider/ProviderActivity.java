package com.example.contentprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class ProviderActivity extends Activity {

	private static final String TAG = "ProviderActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * 获取系统自带的通讯录
	 * 需要添加读电话本和写电话本权限
	 */
	private void getSystemContracts()
	{
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, 
				new String[]{Contacts._ID, Contacts.DISPLAY_NAME}, 
				null, null, null);
		if (null != cursor)
		{
			while(cursor.moveToNext())
			{
				int id = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
				String name = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
				LOG("id=" + id +" name=" + name);
				/*
				 * 查询联系人电话号码
				 */
				Cursor cursor2 = resolver.query(Phone.CONTENT_URI, 
						new String[]{Phone.NUMBER, Phone.TYPE}, //查号码
						Phone.CONTACT_ID + "=" + id, //条件是查到的id
						null, null);
				if (null != cursor2)
				{
					while(cursor2.moveToNext())
					{
						int type = cursor2.getInt(cursor2.getColumnIndex(Phone.TYPE));
						if (type == Phone.TYPE_HOME)//家庭号码
						{
							int number = cursor2.getInt(cursor2.getColumnIndex(Phone.NUMBER));
							LOG("家庭号码：" + number);
						}
						else if (type == Phone.TYPE_MOBILE)//手机号码
						{
							int number = cursor2.getInt(cursor2.getColumnIndex(Phone.NUMBER));
							LOG("手机号码：" + number);
						}
					}
					cursor2.close();
				}
				
				/*
				 * 根据ID查询邮箱地址
				 */
				Cursor cursor3 = resolver.query(Email.CONTENT_URI,
						new String[]{Email.DATA, Email.TYPE},
						Email.CONTACT_ID + "=" + id, 
						null, null);
				if (null != cursor3)
				{
					while(cursor3.moveToNext())
					{
						int type = cursor3.getInt(cursor3.getColumnIndex(Email.TYPE));
						if (type == Email.TYPE_WORK)//工作邮箱
						{
							String email = cursor3.getString(cursor3.getColumnIndex(Email.DATA));
							LOG("邮箱是：" + email);
						}
					}
					cursor3.close();
				}
			}
			cursor.close();
		}
	}
	
	private void insertItem()
	{
		ContentResolver resolver = getContentResolver();
		ContentValues values = new ContentValues();
		Uri uri = resolver.insert(RawContacts.CONTENT_URI, values);
		long id = ContentUris.parseId(uri);
		//插入人名
		values.clear();
		values.put(StructuredName.RAW_CONTACT_ID, id);
		values.put(StructuredName.DISPLAY_NAME, "张三");
		values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		resolver.insert(Data.CONTENT_URI, values);
		
		//插入电话信息
		values.clear();
		values.put(Phone.RAW_CONTACT_ID, id);
		values.put(Phone.NUMBER, "234567");
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		resolver.insert(Data.CONTENT_URI, values);
		
	}
	
	private void LOG(String string)
	{
		Log.e(TAG, string);
	}
}
