package com.example.resources.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 创建服务，当接收到信息后，向所有连接的客户端广播消息
 * @author YC
 * @time 2016-3-14 上午11:24:42
 */
public class ServerActivity extends Activity {

	private static final String tag = "ServerActivity";
	
	private static final int SOCKET_PORT = 50000;
	
	public static List<Socket> socketList = new ArrayList<Socket>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		Button btnStartServer = new Button(this);
		btnStartServer.setText("打开服务器");
		btnStartServer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		btnStartServer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(){
					@Override
					public void run() {
						startServer();
					}
				}.start();
				
				getLocalHostIp();
				getLocalMac();
			}

			

			
		});
		LinearLayout layout_main = new LinearLayout(this);
		layout_main.addView(btnStartServer);
		setContentView(layout_main);
	}
	
	private void getLocalMac() {
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		Log.i(tag, "本地MAC是：" + wifiInfo.getMacAddress());
	}
	
	/**
	 * 获取本地IP
	 */
	private void getLocalHostIp() {
		try {
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			
			//遍历所有网络接口
			while(networks.hasMoreElements())
			{
				NetworkInterface network = networks.nextElement();
				Enumeration<InetAddress> addresses = network.getInetAddresses();
				//遍历每一个绑定的IP
				while (addresses.hasMoreElements())
				{
					InetAddress address = addresses.nextElement();
					if (!address.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(address.getHostAddress()))
					{
						Log.i(tag, "本地ip是：" + address.getHostAddress());
					}
				}
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	private void startServer()
	{
		try {
			ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
			while(true)
			{
				//一直等待到有客户端连接上
				Socket socket = serverSocket.accept();
				Log.i(tag, "连接新对象");
				socketList.add(socket);
				//为每个客户端创建一个线程管理信息
				new Thread(new ServerRunable(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private class ServerRunable implements Runnable{

		private Socket mSocket;
		
		private BufferedReader bufferedReader;	//从socket读数据
		
		ServerRunable(Socket socket) throws IOException
		{
			this.mSocket = socket;
			bufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		}
		/**
		 * 将读到的客户端内容进行广播
		 */
		@Override
		public void run() {
			
			try {
				String content;
				while ((content = bufferedReader.readLine()) != null)
				{
					for(Socket socket: socketList)
					{
						//发送给每一个客户端
						PrintStream printStream = new PrintStream(socket.getOutputStream());
						printStream.println(makePackMessage(content));
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 对要广播的数据进行包装
		 * @param content
		 * @return
		 */
		private String makePackMessage(String content) {
			String result = "";
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			if (content.startsWith("USER_ONE"))
			{
				String message = content.substring(8);
				result = "\n" + "客户端1 " + format.format(new Date()) + "\n" + message;
			}else if (content.startsWith("USER_TWO"))
			{
				String message = content.substring(8);
				result = "\n" + "客户端2 " + format.format(new Date()) + "\n" + message;
			}
			Log.i(tag, "Server respone " + result);
			return result;
		}
		
	}
}
