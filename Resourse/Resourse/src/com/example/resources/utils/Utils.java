/**
 * 
 */
package com.example.resources.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.KeyEvent;

import com.example.plug.IComm;
import com.example.resources.ImgUtils.ImgUtils;

import dalvik.system.DexClassLoader;

/**
 * @author YC
 * @time 2016-4-21 下午3:54:11 功能函数收集
 */
public class Utils {

	private static final String TAG = "Utils";

	/**
	 * 截取视频文件的帧
	 * @param path
	 * @param targetPath
	 * @param time ms
	 * @return
	 */
	public static boolean getVideoAtFrame(String path, String targetPath, long time){
		LogUtil.startTime("视频截图使用时间");
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		Bitmap bitmap = null;
		try {
			retriever.setDataSource(path);
			String timeString = retriever
					.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
			long totalTime = Long.parseLong(timeString);
			if (time > totalTime || time < 0) {
				Log.i(TAG, "Illegal time arguement");
				retriever.release();
				return false;
			}
			bitmap = retriever.getFrameAtTime(time);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			retriever.release();
		}
		if (bitmap == null){
			Log.i(TAG, "get null bitmap");
			return false;
		}
		
		File file = new File(targetPath);
		if (!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		
		try {
			Bitmap zoomBitmap = ImgUtils.zoomBitmap(bitmap, 100, 100);
			FileOutputStream fos = new FileOutputStream(file);
			zoomBitmap.compress(Bitmap.CompressFormat.PNG, 100	, fos);
			fos.close();
			bitmap.recycle();
			zoomBitmap.recycle();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		LogUtil.endUseTime("视频截图使用时间");
		return true;
	}
	
	/**读取插件*/
	public static void ReadPlugIn(Context context, ClassLoader parent) {
		LogUtil.startTime("获取插件信息");
		Intent it = new Intent("com.example.plugin.client", null);
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> plugins = packageManager.queryIntentActivities(it, 0);
		ResolveInfo rinfo = plugins.get(0);
		ActivityInfo ainfo = rinfo.activityInfo;
		

		String div = System.getProperty("path.separator");
		String packageName = ainfo.packageName;
		try {
			Resources res = packageManager.getResourcesForApplication(packageName);
			int id = 0;
			id = res.getIdentifier("version", "string", packageName);
			String version = res.getString(id);
			Log.i(TAG, "get pulgin version = " + version);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String dexPath = ainfo.applicationInfo.sourceDir;
		String outDir = context.getApplicationInfo().dataDir;
		String libraryDir = ainfo.applicationInfo.nativeLibraryDir;
		
		DexClassLoader loader = new DexClassLoader(dexPath, outDir, libraryDir, parent);
	
		try {
			Class<?> clazz = loader.loadClass(packageName + ".PlugInClass");
			/*Object object = clazz.newInstance();
			Class[] parames = new Class[2];
			parames[0] = Integer.TYPE;
			parames[1] = Integer.TYPE;
			Method method = clazz.getMethod("function1", parames);
			Integer result = (Integer)method.invoke(object, 12, 34);*/
			IComm comm = (IComm)clazz.newInstance();
			int result = comm.function1(12, 45);
			Log.i(TAG, "return val = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.endUseTime("获取插件信息");
	}
	
	/**
	 * 洗牌算法
	 */
	public static void shuffle() {
		Vector<String> array = new Vector<String>();
		for (int i = 0; i < 52; i++) {
			array.add("string" + i);
		}
		for (int i = 0; i < 52; i++) {
			Log.i(TAG, array.get(i));
		}

		for (int i = 0; i < array.size(); i++) {
			int key = i;
			int temp = new Random().nextInt(array.size() - 1);
			String tempStr = array.get(temp);
			array.set(temp, array.get(key));
			array.set(key, tempStr);
		}
		for (int i = 0; i < 52; i++) {
			Log.i(TAG, array.get(i));
		}
	}

	/**
	 * 调用Runtime
	 */
	public static void runRuntimeFun() {
		new Thread() {
			public void run() {
				try {
					Runtime.getRuntime().exec(new String[] { "logcat", "-c" })
							.waitFor();
					Process process = Runtime.getRuntime().exec(
							new String[] { "logcat" });
					Log.i(TAG, "exec logcat");

					BufferedReader reader = new BufferedReader(
							new InputStreamReader(process.getInputStream()));

					String line;
					while ((line = reader.readLine()) != null) {
						// Log.i(TAG, "get logcat = " + line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();
	}
	
	/**
	 * 线程池测试
	 */
	public static void testThreadPool() {
//		testCachedThreadPool();
//		testFixedThreadPool();
//		testScheduledThreadPool();
		testSingleThread();
	}
	
	/**
	 * 创建单一线程，顺序执行各个任务，访问遵循先进先出
	 */
	private static void testSingleThread() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++){
			final int index = i;
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			singleThreadExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.i(TAG, "execute " + index);
				}
			});
		}
	}

	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行
	 */
	private static void testScheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
			scheduledThreadPool.schedule(new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "execute ");
				}
			}, 1, TimeUnit.SECONDS);
			
			scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					Log.i(TAG, "execute ");
				}
			}, 1, 3, TimeUnit.SECONDS);
	}

	/**
	 * 定长线程池（最多n个，超过n个任务时会进入队列等待）
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
	 * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
	 */
	private static void testFixedThreadPool() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++){
			final int index = i;
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			fixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.i(TAG, "execute " + index);
				}
			});
		}
	}

	/**
	 * 可缓存线程池(最优情况是只创建一个线程，会根据实际需求可能创建多个)
	 * 当执行第二个任务时第一个任务已经完成，60s无使用资源才会回收资源，否则会复用执行第一个任务的线程，而不用每次新建线程。
	 * 如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
	 */
	private static void testCachedThreadPool() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++){
			final int index = i;
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			cachedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.i(TAG, "execute " + index);
				}
			});
		}
	}


}
