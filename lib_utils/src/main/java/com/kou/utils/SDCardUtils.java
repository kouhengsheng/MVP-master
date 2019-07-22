package com.kou.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kouhengsheng on 2018/11/16.
 */
public class SDCardUtils {
	//判断sd卡是否被挂载
	public static boolean isSDCardMounted() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获得SDCard的根目录/storage/sdcard
	 */
	public static String getSDCardBaseDir() {
		if (isSDCardMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}

	//获取sdcard公有的目录的路径
	public static String getSDCardPublicDir(String type) {
		if (isSDCardMounted()) {
			return Environment.getExternalStoragePublicDirectory(type).toString();
		}
		return null;
	}



	/**
	 * 15、获取SDCard私有File目录路径 /storage/sdcard/Android/data/包名/files/{type}
	 *
	 * @param context
	 * @param type
	 * @return
	 */
	public static String getSDCardPrivateFilesDir(Context context, String type) {
		if (isSDCardMounted()) {
			File dir = context.getExternalFilesDir(type);
			return dir.getAbsolutePath();
		}
		return null;
	}


	/**
	 * 3、获得SDCard的全部空间大小(单位:M)
	 */
	public static long getSDCardSize() {
		if (isSDCardMounted()) {
			String baseDir = getSDCardBaseDir();   //获取根目录
			StatFs statFs = new StatFs(baseDir);   //获取StatFs对象
			long blockCount = statFs.getBlockCountLong();  //通过StatFs对象获取块的数量
			long blockSize = statFs.getBlockSizeLong();    //通过StatFs对象获取每块的大小（字节）
			return (blockCount * blockSize / 1024 / 1024);  //块数量*每块大小/1024/1024  转化单位为M
		}
		return 0;
	}

	/**
	 * 4、获取SDCard空闲空间的大小(单位:M)
	 */
	public static long getSDCardFreeSize() {
		if (isSDCardMounted()) {
			String baseDir = getSDCardBaseDir();   //获取根目录
			StatFs statFs = new StatFs(baseDir);   //获取StatFs对象
			long freeBlock = statFs.getFreeBlocksLong();//通过StatFs对象获取空闲块的数量
			long blockSize = statFs.getBlockSizeLong();  //通过StatFs对象获取每块的大小（字节）
			return (freeBlock * blockSize / 1024 / 1024);  //空闲块数量*每块大小/1024/1024  转化单位为M
		}
		return 0;
	}

	/**
	 * 5、 获取SDCard可用空间的大小(单位:M)
	 */
	public static long getSDCardAvailSize() {
		if (isSDCardMounted()) {
			String baseDir = getSDCardBaseDir();  //获取根目录
			StatFs statFs = new StatFs(baseDir);   //获取StatFs对象
			long availBlock = statFs.getAvailableBlocksLong();  //通过StatFs对象获取可用块的数量
			long blockSize = statFs.getBlockSizeLong();         //通过StatFs对象获取每块的大小（字节）
			return (availBlock * blockSize / 1024 / 1024);  //可用块数量*每块大小/1024/1024  转化单位为M
		}
		return 0;
	}


	/**
	 * 6、往SDCard公有目录下保存文件 (九大公有目录中的一个，具体由type指定) /storage/sdcard/{type}/{filename}
	 * 公有目录即 SDCard跟目录下的 系统创建的文件夹
	 *
	 * @param data     要写入的数据
	 * @param type     文件夹名
	 * @param filename 文件名
	 * @return boolean
	 */
	public static boolean saveData2SDCardPublicDir(byte[] data, String type, String filename) {
		if (isSDCardMounted()) {
			String dir = getSDCardBaseDir() + File.separator + type;  //九大公有目录中的一个
			String file = dir + File.separator + filename;            //文件路径名
			BufferedOutputStream bos = null;                          //缓冲输出流
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);  //写入数据
				bos.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {              //finally总是会执行，尽管上面已经return true;了
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 7、往SDCard的自定义目录中保存数据 /storage/sdcard/{dir}
	 */
	public static boolean saveData2SDCardCustomDir(byte[] data, String dir, String filename) {
		if (isSDCardMounted()) {
			String saveDir = getSDCardBaseDir() + File.separator + dir;
			File saveFile = new File(saveDir);
			//如果不存在就创建该文件
			if (!saveFile.exists()) {
				saveFile.mkdirs();//递归创建自定义目录
			}
			String file = saveFile.getAbsolutePath() + File.separator + filename;
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);
				bos.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {     //finally总是会执行，尽管上面已经return true;了
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 8、往SDCard的私有File目录下保存文件 /storage/sdcard/Android/data/包名/files/{type}/{filename}
	 */
	public static boolean saveData2SDCardPrivateFileDir(byte[] data, String type, String filename, Context context) {
		if (isSDCardMounted()) {
			File dir = context.getExternalFilesDir(type);  //获得私有File目录下
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String file = dir.getAbsolutePath() + File.separator + filename;
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);
				bos.flush();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 9、往SDCard的私有Cache目录下保存文件 /storage/sdcard/Android/data/包名/cache/{filename}
	 */
	public static boolean saveData2SDCardPrivateCacheDir(byte[] data, String filename, Context context) {
		if (isSDCardMounted()) {
			File dir = context.getExternalCacheDir();  //获取私有Cache目录
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String file = dir.getAbsolutePath() + File.separator + filename;
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);
				bos.flush();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 11、从SDCard读取指定文件 /storage/sdcard/{filePath}
	 *
	 * @param filePath 要读取的文件名
	 * @return byte[]
	 */
	public static byte[] loadFileFromSDCard(String filePath) {
		if (isSDCardMounted()) {
			File dir = new File(getSDCardBaseDir());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String file = dir.getAbsolutePath() + File.separator + filePath;
			BufferedInputStream bis = null;
			byte[] bytes = new byte[1024];
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int hasRead;
				while (true) {
					hasRead = bis.read(bytes);
					if (hasRead < 0) {
						break;
					}
					baos.write(bytes, 0, hasRead);
				}
				baos.flush();
				return baos.toByteArray();  //返回byte的数组
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 12、从SDCard读取Bitmap并返回 /storage/sdcard/{filePath}
	 *
	 * @param filePath 要读取的图片名
	 * @return Bitmap 返回图片资源
	 */
	public static Bitmap loadBitmapFromSDCard(String filePath) {
		if (isSDCardMounted()) {
			String file = getSDCardBaseDir() + File.separator + filePath;
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024];
				int hasRead;
				while (true) {
					hasRead = bis.read(bytes);
					if (hasRead < 0) {
						break;
					}
					baos.write(bytes);
				}
				byte[] data = baos.toByteArray();
				return BitmapFactory.decodeByteArray(data, 0, data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 14、获取SDCard私有Cache目录路径  /storage/sdcard/Android/data/包名/cache/
	 *
	 * @param context
	 * @return
	 */
	public static String getSDCardPrivateCacheDir(Context context) {
		if (isSDCardMounted()) {
			File dir = context.getExternalCacheDir();
			return dir.getAbsolutePath();
		}
		return null;
	}

	/**
	 * 16、判断一个文件是否存在
	 *
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExists(String filePath) {
		if (isSDCardMounted()) {
			String dir = getSDCardBaseDir() + File.separator + filePath;
			File file = new File(dir);
			return file.exists();
		}
		return false;
	}

	/**
	 * 17、删除一个文件
	 *
	 * @param filePath
	 * @return
	 */
	public static boolean removeFileFromSDCard(String filePath) {
		if (isSDCardMounted()) {
			String dir = getSDCardBaseDir() + File.separator + filePath;
			File file = new File(dir);
			if (file.exists()) {
				return file.delete();
			}
		}
		return false;
	}

	//保存bitmap图片到sdcard的私有目录
	public static boolean saveBitmapToSDCardCacheDir(Bitmap bitmap, String fileName, Context context) {
		if (isSDCardMounted()) {
			BufferedOutputStream bos = null;
			//获取私有的cache的缓存目录
			File file = context.getExternalCacheDir();
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
				if (fileName != null && (fileName.contains(".png") || fileName.contains(".PNG"))) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				} else {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				}
				bos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			return true;
		} else {
			return false;
		}
	}

	//将图片保存到sdcard公有目录
	public static boolean saveBitmapToSDCardPublicDir(Bitmap bm, String type, String fileName) {
		if (isSDCardMounted()) {
			String filepath = getSDCardPublicDir(type) + File.separator + fileName;
			BufferedOutputStream bos = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
				bos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			}
		}
		return false;
	}




	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.isFile();
	}


	/**
	 * 输入流转字节数组
	 */
	public static byte[] streamToByteArray(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = 0;
		byte[] buffer = new byte[8 * 1024];
		try {
			while ((c = is.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * Created by 风情万种冷哥哥 on 2016.
	 * * @return
	 */
	public static String streamToString(InputStream is, String charsetName) {
		BufferedInputStream bis = new BufferedInputStream(is);
		StringBuilder sb = new StringBuilder();
		int c = 0;
		byte[] buffer = new byte[8 * 1024];
		try {
			while ((c = bis.read(buffer)) != -1) {
				sb.append(new String(buffer, charsetName));
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Created by 风情万种冷哥哥 on 2016.
	 * 字符串转输入流
	 */
	public static InputStream stringToInputStream(String str) {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return is;
	}

}
