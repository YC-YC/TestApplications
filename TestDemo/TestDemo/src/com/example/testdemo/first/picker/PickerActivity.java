package com.example.testdemo.first.picker;

import java.util.Calendar;

import com.example.testdemo.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class PickerActivity extends Activity {

	private DatePicker mDatePicker;
	private TimePicker mTimePicker;
	private Calendar mCalendar;
	private int mYear,mMonth, mDay, mHour, mMinute;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picker);
		
		initView();
	}

	private void initView() {
		
		mCalendar = Calendar.getInstance();
		mYear = mCalendar.get(Calendar.YEAR);
		mMonth = mCalendar.get(Calendar.MONTH)+1;
		mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
		mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
		mMinute = mCalendar.get(Calendar.MINUTE);
//		String strtime = getResources().getString(R.string.time_format);
//		String str = String.format(strtime, mYear, mMonth-1, mDay, mHour, mMinute);
//		setTitle(str);
//		setTitle(mYear + "-" + mMonth + "-" +mDay + " " + mHour + ":" + mMinute);
		setTitle(String.format("%d-%d-%d %02d:%02d", mYear, mMonth-1, mDay, mHour, mMinute));
		mDatePicker = (DatePicker) findViewById(R.id.datePicker1);
		mTimePicker = (TimePicker) findViewById(R.id.timePicker1);
		
		mDatePicker.init(mYear, mMonth-1, mDay, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear + 1;
				mDay = dayOfMonth;
				String strtime = getResources().getString(R.string.time_format);
				String str = String.format(strtime, mYear, mMonth-1, mDay, mHour, mMinute);
				setTitle(str);
//				setTitle(mYear + "-" + mMonth + "-" +mDay + " " + mHour + ":" + mMinute);
//				setTitle(String.format("%d-%d-%d %02d:%02d", mYear, mMonth-1, mDay, mHour, mMinute));

			}
		});
		
		mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				mHour = hourOfDay;
				mMinute = minute;
				String strtime = getResources().getString(R.string.time_format);
				String str = String.format(strtime, mYear, mMonth-1, mDay, mHour, mMinute);
				setTitle(str);
//				setTitle(mYear + "-" + mMonth + "-" +mDay + " " + mHour + ":" + mMinute);
//				setTitle(String.format("%d-%d-%d %02d:%02d", mYear, mMonth-1, mDay, mHour, mMinute));

			}
		});
		
		
		new DatePickerDialog(this, new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear + 1;
				mDay = dayOfMonth;
				setTitle(mYear + "-" + mMonth + "-" +mDay + " " + mHour + ":" + mMinute);
			}
		}, mYear, mMonth-1, mDay).show();
		
		new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mHour = hourOfDay;
				mMinute = minute;
				setTitle(mYear + "-" + mMonth + "-" +mDay + " " + mHour + ":" + mMinute);
							
			}
		}, mHour, mMinute, true).show();
	}
}
