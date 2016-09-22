package com.example.testdemo.Media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 *@Author Administrator
 *@Time 2016-2-26 上午1:03:12
 */
public class VideoViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VideoView vv = new VideoView(this);
		setContentView(vv);
		vv.setVideoPath("mnt/sdcard/video.wmv");
//		vv.setVideoPath("mnt/sdcard/y31.rm");
		vv.setMediaController(new MediaController(this));
	}
}
