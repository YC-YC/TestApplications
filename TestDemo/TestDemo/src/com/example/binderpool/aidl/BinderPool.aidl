package com.example.binderpool.aidl;

interface BinderPool{
	/**
	 * 
	 * @param binderCode
	 * @return
	 */
	IBinder queryBinder(int binderCode);
	
}