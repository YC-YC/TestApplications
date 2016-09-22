/**
 * 
 */
package com.example.resources.ImgUtils;

import android.graphics.Bitmap;


/**
 * @author YC
 * @time 2016-3-28 下午8:22:19
 */
public class ImageBlur {
    
    public static native void blurIntArray(int[] pImg, int w, int h, int r);
	public static native void blurBitMap(Bitmap bitmap, int r);
	static {  
        System.loadLibrary("Jni_ImageBlur");  
    }  
}
