package com.example.resources.utils;

import java.util.HashMap;
import java.util.Map;

import android.os.SystemClock;
import android.util.Log;

/**
 * @author YC
 * @time 2016-3-14 下午2:26:21
 */
public class LogUtil {

	private static final String tag = "LogUtil";
	
	private static Map<String, Long> timeRecord;	
	
	/**
	 * 打印开始时间
	 * @param tip
	 */
	public static void startTime(String tip){
		if (timeRecord == null)
		{
			timeRecord = new HashMap<String, Long>();
		}
		timeRecord.put(tip, SystemClock.uptimeMillis());
	}
	
	/**
	 * 结束打印时间
	 * @param tip
	 */
	public static void endUseTime(String tip){
		if (timeRecord != null && !timeRecord.containsKey(tip))
		{
			Log.i(tag, "未配置开始时间");
			return;
		}
		
		long diff = SystemClock.uptimeMillis() - timeRecord.get(tip);
		timeRecord.remove(tip);
		Log.i(tag, tip + "耗时：" + diff);
	}
}
