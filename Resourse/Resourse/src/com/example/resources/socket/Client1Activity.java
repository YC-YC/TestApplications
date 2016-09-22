package com.example.resources.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.example.resources.R;
import com.example.resources.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 连接到服务端，接收服务端返回的数据
 * @author YC
 * @time 2016-3-14 上午11:27:12
 */
public class Client1Activity extends Activity implements OnClickListener {

	private static final String tag = "Client1Activity";
	private static final String START_WITH = "USER_ONE";
	private static final int SOCKET_PORT = 50000;
	private static final int UPDATE_MESSAGE = 0x11;
	private static final String CONTENT_KEY = "content";
	
	private EditText mEtSendContent;
	private Button mBtnSend;
	private TextView mTvContent; 
	private ScrollView mScrollContent;
	
	private Socket socket;
	private BufferedReader reader;	//读
	private PrintWriter writer;	//写
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_client1);
		
		initViews();

		new Thread(){
			public void run() {
				LogUtil.startTime("Client1Activity onCreate");
				try {
					socket = new Socket("127.0.0.1", SOCKET_PORT);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
					
				} catch (UnknownHostException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				LogUtil.endUseTime("Client1Activity onCreate");
			};
		}.start();
		new ReadThread().start();
	}

	private void initViews() {
		mEtSendContent = (EditText) findViewById(R.id.et_client1);
		mBtnSend = (Button) findViewById(R.id.btn_client1_send);
		mBtnSend.setOnClickListener(this);
		mTvContent  = (TextView) findViewById(R.id.tv_client1_content);
		mScrollContent = (ScrollView) findViewById(R.id.scroll_content);
	}

	@Override
	public void onClick(View v) {
		String content =  mEtSendContent.getText().toString();
		//启动线程发送数据到客户端
		Log.i(tag, "ready to send " + content);
		new SendThread(content).start();
		mEtSendContent.setText("");
	}
	
	/**
	 * 发送线程
	 * @author YC
	 * @time 2016-3-14 下午4:28:04
	 */
	private class SendThread extends Thread
	{
		String sendContent = null;
		public SendThread(String content)
		{
			sendContent = START_WITH + content + "\n";
		}
		
		@Override
		public void run() {
			if (socket.isConnected())
			{
				if (!socket.isOutputShutdown())
				{
					Log.i(tag, "send " + sendContent);
					writer.println(sendContent);
				}else
				{
					Log.i(tag, "socket OutputShutdown");
				}
			}else
			{
				Log.i(tag, "socket unConnect");
			}
		}
	}
	
	private class ReadThread extends Thread{
		@Override
		public void run() {
			try {
				while (true)
				{
					if (socket != null && socket.isConnected())
					{
						if (!socket.isInputShutdown())
						{
							String line = null;
							while ((line = reader.readLine()) != null)
							{
								line += "\n";
								//通过Handle更新UI
								Message msg = new Message();
								msg.what = UPDATE_MESSAGE;
								Bundle bundle = new Bundle();
								bundle.putString(CONTENT_KEY, line);
								msg.setData(bundle);
								mHandler.sendMessage(msg);
							}
						}
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_MESSAGE:
				Bundle bundle = msg.getData();
				mTvContent.append(bundle.getString(CONTENT_KEY));
				//需要通过post才能更新
				new Handler().post(new Runnable() {
					
					@Override
					public void run() {
						mScrollContent.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
					}
				});
				break;

			}
		};
	};
	
}
