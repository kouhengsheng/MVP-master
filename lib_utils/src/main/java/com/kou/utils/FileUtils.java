package com.kou.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by yw on 2017/5/2.
 */

public class FileUtils {
	//录音文件的存放目录
	public static final String DATA_DIRECTORY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/record/";

	private FileUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	//寻找音频文件
	public static void searchAudioFile(File soundFile, String ogg, List mList) {
		//mList.clear();
		//寻找ogg文件

		if (soundFile != null) {
			if (soundFile.isDirectory()) {//如果是文件夹
				File[] listFiles = soundFile.listFiles();//列出soundFile文件夹下的所有文件并存放在listFiles这个File类型数组中
				if (listFiles != null) {
					for (int i = 0; i < listFiles.length; i++) {
						searchAudioFile(listFiles[i], ogg, mList);//递归，直到把所有文件遍历完
					}
				}
			} else {//否则就是文件
				String fileName = soundFile.getAbsolutePath();//返回抽象路径名的绝对路径名字符串
				String name = soundFile.getName();//获得文件的名称
				if (fileName.endsWith(ogg)) {//判断文件后缀名是否包含我们定义的格式
					mList.add(fileName);
				}
				Log.e("searchAudioFile: ", mList.toString());
			}
		}
		//把mList结合倒序排列，这样最新的录音文件，就会放在集合的首位。
		Collections.reverse(mList);

	}

	/**
	 * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
	 *
	 * @param path :待创建文件路径
	 */
	public static File createNewFile(String path) {
		File file = new File(path);
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	//把字符串写入到text文件中去
	public static void writeString2txt(String str, String filePath) {
		byte[] sourceByte = str.getBytes();
		if (null != sourceByte) {
			try {
				File file = new File(filePath);     //文件路径（路径+文件名）
				if (!file.exists()) {   //文件不存在则创建文件，先创建目录
					File dir = new File(file.getParent());
					dir.mkdirs();
					file.createNewFile();
				}
				FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
				outStream.write(sourceByte);
				outStream.close();  //关闭文件输出流
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 读取文件的最后修改时间的方法
	 */
	public static String getFileLastModifiedTime(File f) {
		Calendar cal = Calendar.getInstance();
		long time = f.lastModified();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.setTimeInMillis(time);
		return formatter.format(cal.getTime());
	}

	/**
	 * 复制assets目录下的文件
	 * 　　* @param myContext
	 * 　　* @param ASSETS_NAME 要复制的文件名
	 * 　　* @param savePath 要保存的路径
	 * 　　* @param saveName 复制后的文件名
	 */
	public static void copy(final Context myContext, final String ASSETS_NAME, String savePath, String saveName) {
		final String filename = savePath + "/" + saveName;
		File dir = new File(savePath);
		// 如果目录不中存在，创建这个目录
		if (!dir.exists()) {
			dir.mkdir();
		}

		try {
			InputStream is = myContext.getResources().getAssets().open(ASSETS_NAME);
			FileOutputStream fos = new FileOutputStream(filename);
			byte[] buffer = new byte[1024 * 8];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 文件拷贝
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean fileCopy(String from, String to) {
		boolean result = false;

		int size = 1 * 1024;

		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			byte[] buffer = new byte[size];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
			}
		}
		return result;
	}

	/**
	 * 复制单个文件
	 *
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/fqf.txt
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时
				InputStream inStream = new FileInputStream(oldPath); //读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			LogUtil.e("eee", e.toString());
		}

	}


	/**
	 * 截取文件名称
	 *
	 * @param fileName 文件名称
	 */
	static String[] splitFileName(String fileName) {
		String name = fileName;
		String extension = "";
		int i = fileName.lastIndexOf(".");
		if (i != -1) {
			name = fileName.substring(0, i);
			extension = fileName.substring(i);
		}

		return new String[]{name, extension};
	}

	/**
	 * 获取文件名称
	 *
	 * @param context 上下文
	 * @param uri     uri
	 * @return 文件名称
	 */
	static String getFileName(Context context, Uri uri) {
		String result = null;
		if (uri.getScheme().equals("content")) {
			Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
			try {
				if (cursor != null && cursor.moveToFirst()) {
					result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		if (result == null) {
			result = uri.getPath();
			int cut = result.lastIndexOf(File.separator);
			if (cut != -1) {
				result = result.substring(cut + 1);
			}
		}
		return result;
	}

	/**
	 * 获取真实的路径
	 *
	 * @param context 上下文
	 * @param uri     uri
	 * @return 文件路径
	 */
	static String getRealPathFromURI(Context context, Uri uri) {
		Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
		if (cursor == null) {
			return uri.getPath();
		} else {
			cursor.moveToFirst();
			int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
			String realPath = cursor.getString(index);
			cursor.close();
			return realPath;
		}
	}

	/**
	 * 清空文件夹
	 *
	 * @return 文件删除成功返回true，否则返回false
	 */
	public static boolean deleteDirs(String path) {
		if (hasSDCard()) {
			File file = new File(path);
			if (file.exists()) {
				try {
					clearCache(file);
				} catch (Exception e) {
					LogUtil.e("eee", e.toString());
				}
			}
		}
		return false;
	}

	/**
	 * 检测文件是否可用
	 */
	public static boolean checkFile(File f) {
		if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0))) {
			return true;
		}
		return false;
	}

	/**
	 * 检测文件是否可用
	 */
	public static boolean checkFile(String path) {
		if (StringUtil.isNotEmpty(path)) {
			File f = new File(path);
			if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0)))
				return true;
		}
		return false;
	}

	//删除文件
	public static boolean deleteFile(File f) {
		if (f != null && f.exists() && !f.isDirectory()) {
			return f.delete();
		}
		return false;
	}

	//删除文件夹
	public static void deleteDir(File f) {
		if (f != null && f.exists() && f.isDirectory()) {
			for (File file : f.listFiles()) {
				if (file.isDirectory())
					deleteDir(file);
				file.delete();
			}
			f.delete();
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param filePath 被删除文件的文件名
	 * @return 文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			return file.delete();
		}
		return false;
	}


	/**
	 * 清空文件夹
	 *
	 * @return 文件删除成功返回true，否则返回false
	 */
	public static boolean deleteDirs() {
		if (hasSDCard()) {
			File file = new File(DATA_DIRECTORY);
			if (file.exists()) {
				try {
					clearCache(file);
				} catch (Exception e) {
				}
			}
		}
		return false;
	}

	/**
	 * @Description 清空文件夹
	 * @author DataTang
	 */
	private static void clearCache(File file) throws Exception {
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory())
				clearCache(files[i]);
			else {
				files[i].delete();
			}
		}
		file.delete();
	}


	//删除缓存文件
	public static void deleteCacheFile(String f) {
		if (f != null && f.length() > 0) {
			File files = new File(f);
			if (files.exists() && files.isDirectory()) {
				for (File file : files.listFiles()) {
					if (!file.isDirectory() && (file.getName().contains(".ts") || file.getName().contains("temp"))) {
						file.delete();
					}

				}
			}
		}
	}

	//删除以ts结尾的文件
	public static void deleteCacheFile2TS(String f) {
		if (f != null && f.length() > 0) {
			File files = new File(f);
			if (files.exists() && files.isDirectory()) {
				for (File file : files.listFiles()) {
					if (!file.isDirectory() && (file.getName().contains(".ts"))) {
						file.delete();
					}

				}
			}
		}
	}



	/**
	 * 获取栈顶的activity
	 *
	 * @param context
	 * @return
	 */
	public static String getTopActivity(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

		if (runningTaskInfos != null) {
			return (runningTaskInfos.get(0).topActivity).toString();
		} else
			return null;
	}


	/**
	 * 写文件，the bytes will be written to the begin of the file
	 *
	 * @param file
	 * @param stream
	 * @return
	 * @see {@link #writeFile(File, InputStream, boolean)}
	 */
	public static boolean writeFile(File file, InputStream stream) {
		return writeFile(file, stream, false);
	}

	/**
	 * 写文件
	 *
	 * @param file   the file to be opened for writing.
	 * @param stream the input stream
	 * @param append if <code>true</code>, then bytes will be written to the end of the file rather than the beginning
	 * @return return true
	 * @throws RuntimeException if an error occurs while operator FileOutputStream
	 */
	public static boolean writeFile(File file, InputStream stream, boolean append) {
		OutputStream o = null;
		try {
			makeDirs(file.getAbsolutePath());
			o = new FileOutputStream(file, append);
			byte data[] = new byte[1024];
			int length = -1;
			while ((length = stream.read(data)) != -1) {
				o.write(data, 0, length);
			}
			o.flush();
			return true;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (o != null) {
				try {
					o.close();
					stream.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * 创建文件夹
	 * @param filePath
	 * @return
	 */
	public static boolean makeDirs(String filePath) {
		String folderName = getFolderName(filePath);
		if (StringUtil.isEmpty(folderName)) {
			return false;
		}

		File folder = new File(folderName);
		return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
	}

	/**
	 * 创建不同的目录
	 * 在SD根目录创建文件夹
	 */
	public static void createDirectory(String folderName) {
		if (hasSDCard()) {
			File file = new File(folderName);
			if (!file.exists()) {
				file.mkdirs();
			}
		} else {
			File file = new File(folderName);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	/**
	 * 创建不同的目录
	 */
//	public static void createDirectory(String childtaskid) {
//		if (hasSDCard()) {
//			File file = new File(DATA_DIRECTORY + "/" + childtaskid);
//			if (!file.exists()) {
//				file.mkdirs();
//			}
//		}
//	}

	/**
	 * 获取文件夹名称
	 * @param filePath
	 * @return
	 */
	public static String getFolderName(String filePath) {

		if (StringUtil.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
	}


	/**
	 * 判断手机是否有SD卡。
	 *
	 * @return 有SD卡返回true，没有返回false。
	 */
	public static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}
