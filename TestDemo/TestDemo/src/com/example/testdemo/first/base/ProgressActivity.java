package com.example.testdemo.first.base;

import com.example.testdemo.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends Activity implements OnClickListener{
	
	private ProgressBar progressBar;
	private Button add, reduce, reset, dialogProgress;
	private TextView tv;
	private ProgressDialog progressDialog;
	private int firstProgress, secondProgress, maxProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//启用带进度的进度条和不带进度的进度条
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progress);
		//显示两种进度条
		setProgressBarVisibility(true);
		setProgressBarIndeterminate(true);
		setProgress(60);
		
		initView();
	}

	private void initView() {
		progressBar = (ProgressBar) findViewById(R.id.progressBar3);
		
		add = (Button) findViewById(R.id.pro_button1);
		add.setOnClickListener(this);
		
		reduce = (Button) findViewById(R.id.pro_button2);
		reduce.setOnClickListener(this);
		
		reset = (Button) findViewById(R.id.pro_button3);
		reset.setOnClickListener(this);
		
		dialogProgress = (Button) findViewById(R.id.pro_button4);
		dialogProgress.setOnClickListener(this);
		
		tv = (TextView) findViewById(R.id.pro_textView1);
		
		firstProgress = progressBar.getProgress();
		secondProgress = progressBar.getSecondaryProgress();
		maxProgress = progressBar.getMax();
		
		tv.setText("第一进度条为：" + (int)(firstProgress*100/maxProgress) 
				+"%,第二进度条为：" + (int)(secondProgress*100/maxProgress)
				+"%");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pro_button1:
			firstProgress += 10;
			secondProgress += 10;
			break;
		case R.id.pro_button2:
			firstProgress -= 10;
			secondProgress -= 10;
			break;
		case R.id.pro_button3:
			firstProgress = 50;
			secondProgress = 80;
			break;
		case R.id.pro_button4:
			progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setIcon(R.drawable.ic_launcher);
			progressDialog.setTitle("标题");
			progressDialog.setMessage("欢迎使用进度条对话框");
			progressDialog.setMax(maxProgress);
			progressDialog.incrementProgressBy(firstProgress);
			progressDialog.setSecondaryProgress(secondProgress);
			
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(ProgressActivity.this, "点击了确定", Toast.LENGTH_LONG).show();
				}
			});
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(ProgressActivity.this, "点击了取消", Toast.LENGTH_LONG).show();
				}
			});
			
			//是否可通过返回退出
			progressDialog.setCancelable(true);
			progressDialog.show();
			return;
		}
		progressBar.setProgress(firstProgress);
		progressBar.setSecondaryProgress(secondProgress);
		tv.setText("第一进度条为：" + (int)(firstProgress*100/maxProgress) 
				+"%,第二进度条为：" + (int)(secondProgress*100/maxProgress)
				+"%");
	}
}
