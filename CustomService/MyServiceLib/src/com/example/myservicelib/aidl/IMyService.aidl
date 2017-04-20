package com.example.myservicelib.aidl;

/**
 * @author YC
 * @time 2017-4-20 下午3:09:02
 * TODO:
 */
interface IMyService {
	/**
	 * 
	 * @param val
	 */
	void setVal(int val);
	
	/**
	 * 
	 * @return
	 */
	int getVal();
}
