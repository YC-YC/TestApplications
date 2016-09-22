/**
 * 
 */
package com.example.resources.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

/**
 * @author YC
 * @time 2016-4-21 下午2:09:12
 */
public class FileUtils {

	public final static int DEFAULT_FILE_OPERATE_MODE = 0; // 创建文件时File对象是绝对路径，如果存在文件则覆盖
	public final static int IGNORE_NOT_RECREATE_MODE = 1; // 创建文件时File对象是相对储存器的路径，如果存在文件则表示已经存在，不进行操作
	public final static int IGNORE_AND_RECREATE_MODE = 2; // 创建文件时File对象是相对储存器的路径，如果存在文件则表示已经存在，不进行操作
	public final static int NOT_IGNORE_RECREATE_MODE = 3; // 创建文件时File对象是绝对路径，如果存在文件则覆盖
	private final static boolean DEFAULT_IGNORE_STYLE = false;
	private final static boolean DEFAULT_AUTO_CREATE_DIRECTORY = true; // 如果目录不存在是否自动创建

	public static void createFile(File file, int mode) throws IOException {

		if (file == null /* || StringUtils.isEmpty(file.getAbsolutePath()) */) {
			return;
		}
		if (mode == IGNORE_NOT_RECREATE_MODE
				|| mode == IGNORE_AND_RECREATE_MODE) {
			file = new File(/* StorageUtils.getStorageFile(), */
			file.getAbsolutePath());
		}
		if (mode == DEFAULT_FILE_OPERATE_MODE
				|| mode == IGNORE_AND_RECREATE_MODE) {
			deleteFile(file);
		}
		file.createNewFile();
	}

	public static void createFolder(File folder, int mode) {

		if (folder == null/* || StringUtils.isEmpty(folder.getAbsolutePath()) */) {
			return;
		}
		if (!folder.isDirectory()) {
			return;
		}
		if (mode == IGNORE_NOT_RECREATE_MODE
				|| mode == IGNORE_AND_RECREATE_MODE) {
			folder = new File(/* StorageUtils.getStorageFile(), */
			folder.getAbsolutePath());
		}
		if (mode == DEFAULT_FILE_OPERATE_MODE
				|| mode == IGNORE_AND_RECREATE_MODE) {
			deleteFolder(folder);
		}
		folder.mkdirs();

	}

	public static void deleteFile(File file) {

		if (file == null/* || StringUtils.isEmpty(file.getAbsolutePath()) */) {
			return;
		}
		if (file.exists()) {
			if (!file.isDirectory()) {
				file.delete();
			}
		}
	}

	public static void deleteFolder(File folder) {

		if (folder == null/* || StringUtils.isEmpty(folder.getAbsolutePath()) */) {
			return;
		}
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				if (files != null) {
					for (File file : files) {
						deleteFolder(file);
					}
				}
			} else {
				deleteFile(folder);
			}
		}
	}

	public static List<File> getAllWithEnd(File file, boolean ignore,
			String... extensions) {

		if (file == null/* StringUtils.isEmpty(file.getAbsolutePath()) */) {
			return null;
		}
		for (String extension : extensions) {
			if (extension == null/* StringUtils.isEmpty(extension) */) {
				return null;
			}
		}
		if (ignore) {
			file = new File(/* StorageUtils.getStorageFile(), */
			file.getAbsolutePath());
		}
		if ((!file.exists()) && file.isDirectory()) {
			return null;
		}
		List<File> files = new ArrayList<File>();
		fileFilter(file, files, extensions);
		return files;

	}

	public static void fileFilter(File file, List<File> files,
			String... extensions) {

		if (!file.isDirectory()) {
			return;
		}
		File[] allFiles = file.listFiles();
		File[] allExtensionFiles = file.listFiles(new MyFileFilter(extensions));
		if (allExtensionFiles != null) {
			for (File single : allExtensionFiles) {
				files.add(single);
			}
		}
		if (allFiles != null) {
			for (File single : allFiles) {
				if (single.isDirectory()) {
					fileFilter(single, files, extensions);
				}
			}
		}
	}

	static class MyFileFilter implements FilenameFilter {

		private String[] mIgnors;

		public MyFileFilter(String[] extensions) {
			mIgnors = extensions;
		}

		@Override
		public boolean accept(File dir, String filename) {
			for (String ignore : mIgnors) {
				if (filename.contains(ignore)) {
					return true;
				}
			}
			return false;
		}

	}


	/**
	 * 复制Assets目录下的文件到指定位置
	 * 
	 * @param strAssetsFilePath
	 * @param strDesFilePath
	 * @return
	 */
	public boolean assetsCopyData(Context context, String strAssetsFilePath,
			String strDesFilePath) {

		boolean bIsSuc = true;
		InputStream inputStream = null;
		OutputStream outputStream = null;

		File file = new File(strDesFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
				Runtime.getRuntime().exec("chmod 766 " + file);
			} catch (IOException e) {
				bIsSuc = false;
			}

		} else {// 存在
			return true;
		}

		try {
			inputStream = context.getAssets().open(strAssetsFilePath);
			outputStream = new FileOutputStream(file);

			int nLen = 0;

			byte[] buff = new byte[1024 * 1];
			while ((nLen = inputStream.read(buff)) > 0) {
				outputStream.write(buff, 0, nLen);
			}

		} catch (IOException e) {
			bIsSuc = false;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				bIsSuc = false;
			}

		}

		return bIsSuc;
	}

	public static boolean copyFile(File src, File dst) throws IOException {

		if ((!src.exists()) || src.isDirectory() || dst.isDirectory()) {
			return false;
		}
		if (!dst.exists()) {
			dst.createNewFile();
			return false;
		}
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		inputStream = new FileInputStream(src);
		outputStream = new FileOutputStream(dst);
		int readLen = 0;
		byte[] buf = new byte[1024];
		while ((readLen = inputStream.read(buf)) != -1) {
			outputStream.write(buf, 0, readLen);
		}
		outputStream.flush();
		inputStream.close();
		outputStream.close();
		return true;
	}

	public static boolean copyFolder(File srcDir, File destDir, boolean auto)
			throws IOException {

		if ((!srcDir.exists())) {
			return false;
		}
		if (srcDir.isFile() || destDir.isFile())
			return false;
		if (!destDir.exists()) {
			if (auto) {
				destDir.mkdirs();
			} else {
				return false;
			}
		}
		File[] srcFiles = srcDir.listFiles();
		int len = srcFiles.length;
		for (int i = 0; i < len; i++) {
			if (srcFiles[i].isFile()) {
				File destFile = new File(destDir.getPath() + "//"
						+ srcFiles[i].getName());
				copyFile(srcFiles[i], destFile);
			} else if (srcFiles[i].isDirectory()) {
				File theDestDir = new File(destDir.getPath() + "//"
						+ srcFiles[i].getName());
				copyFolder(srcFiles[i], theDestDir, auto);
			}
		}
		return true;
	}

	public static boolean moveFile(File src, File dst) {
		boolean isCopy = false;
		try {
			isCopy = copyFile(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!isCopy) {
			return false;
		}
		deleteFile(src);
		return true;
	}

	public static boolean moveFolder(File srcDir, File destDir, boolean auto) {
		if (!srcDir.isDirectory() || !destDir.isDirectory()) {
			return false;
		}
		if (!srcDir.exists()) {
			return false;
		}
		if (!destDir.exists()) {
			if (auto) {
				destDir.mkdirs();
			} else {
				return false;
			}
		}
		File[] srcDirFiles = srcDir.listFiles();
		int len = srcDirFiles.length;
		if (len <= 0) {
			srcDir.delete();
		}
		for (int i = 0; i < len; i++) {
			if (srcDirFiles[i].isFile()) {
				File oneDestFile = new File(destDir.getPath() + "//"
						+ srcDirFiles[i].getName());
				moveFile(srcDirFiles[i], oneDestFile);
			} else if (srcDirFiles[i].isDirectory()) {
				File oneDestFile = new File(destDir.getPath() + "//"
						+ srcDirFiles[i].getName());
				moveFolder(srcDirFiles[i], oneDestFile, auto);
				deleteFolder(srcDirFiles[i]);
			}

		}
		return true;
	}
}
