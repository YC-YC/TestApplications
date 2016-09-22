package com.example.testdemo.dictionary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.testdemo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * @author YC
 * @time 2016-3-4 下午12:17:49
 */
public class DictionaryActivity extends Activity implements OnClickListener, TextWatcher {

	private static final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/dictionary";
	private final String DATABASE_FILENAME = "dictionary.db";
	
	private Button mBtnCheck;
	private AutoCompleteTextView mActvWord;
	
	private SQLiteDatabase mDatabase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dictionary_main);
		mDatabase = openDatabase();
		mBtnCheck = (Button) findViewById(R.id.btn_check);
		mBtnCheck.setOnClickListener(this);
		mActvWord = (AutoCompleteTextView) findViewById(R.id.actv_word);
		mActvWord.addTextChangedListener(this);
	}

	private SQLiteDatabase openDatabase() {
		String databaseFileName = DATABASE_PATH + File.separator
				+ DATABASE_FILENAME;
		LOG("databaseFileName = " + databaseFileName);
		try {
			File dir = new File(DATABASE_PATH);
			if (!dir.exists())
			{
				LOG("mkdir = " + DATABASE_PATH);
				dir.mkdir();
			}
			// sdcard/dictionary目录下没有时，将raw上录下文件拷贝过去
			if (!(new File(databaseFileName)).exists()) {
				LOG("copyFile = ");
				InputStream inputStream = getResources().openRawResource(
						R.raw.dictionary);
				FileOutputStream fileOutputStream = new FileOutputStream(
						databaseFileName);
				byte[] buffer = new byte[8192];
				int count = 0;
				while ((count = inputStream.read(buffer)) > 0) {
					fileOutputStream.write(buffer, 0, count);
				}
				fileOutputStream.close();
				inputStream.close();
			}
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					databaseFileName, null);
			return database;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		/*Cursor cursor = mDatabase.rawQuery("selete english as _id from t_words where english like ?", new String[]{
				s.toString()+"%"
		});*/
		LOG("getString = " + s.toString());
		Cursor cursor = mDatabase.rawQuery(
				"select english as _id from t_words where english like ?",
				new String[]
				{ s.toString() + "%" });
		DictionaryAdapter adapter = new DictionaryAdapter(this, cursor, true);
		mActvWord.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_check:
			check();
			break;
		}
	}

	private void check() {
		String sql = "select chinese from t_words where english=?";
		Cursor cursor = mDatabase.rawQuery(sql, 
				new String[]{mActvWord.getText().toString()});
		String result = "未查到结果";
		if (cursor.getCount() > 0)
		{
			cursor.moveToFirst();
			result = cursor.getString(cursor.getColumnIndex("chinese"));
		}
		new AlertDialog.Builder(this).setTitle("查询结果").setMessage(result)
				.setPositiveButton("关闭", null).show();
	}
	
	
	private void LOG(String string)
	{
		Log.i("Dictionary", string);
	}
	
}
