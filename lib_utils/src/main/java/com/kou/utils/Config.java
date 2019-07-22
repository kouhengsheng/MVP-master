package com.kou.utils;

import android.os.Environment;

/**
 * 配置文件
 * Created by kouhengsheng on 2017/7/17.
 */

public class Config {

	/**
	 * 是debug的时候就用8293，是release的时候就用8298
	 */
	public static String baseUrl = BuildConfig.DEBUG ? "http://116.236.134.54:8293/" : "http://116.236.134.54:8298/";

	//baseUrl_for_js是给js代码用的url。
	public static String baseUrl_for_js = BuildConfig.DEBUG ? "http://116.236.134.54:8293/api" : "http://116.236.134.54:8298/api";

	public static String loginUrl       = baseUrl + "api/Login/DoLogin?user_name=";
	public static String avatarUrl      = baseUrl + "api/FileUpload/ImgUpload";
	public static String uploadUrl      = baseUrl + "api/FileUpload/PostFile";
	public static String infoDbUrl      = baseUrl + "api/ProofInfoCollect/Add";
	public static String inspectDbUrl   = baseUrl + "api/InspectionItemProof/Add";
	public static String downloadHTML   = baseUrl + "api/LawEnforceDocRecord/Find?id=";
	public static String logout         = baseUrl + "api/Login/LoginOut?user_id=";
	public static String printFrequency = baseUrl + "api/LawEnforceDocRecord/EditPrintCount";
	public static String printDataUrl   = baseUrl + "api/LawEnforceDocRecord/GetLawEnforceDocRecord";
	//保存文书信息到服务器
	public static String documentDB     = baseUrl + "api/LawEnforceDocRecord/Add";
	public static String downloadWord   = baseUrl + "api/LawEnforceDocRecord/Find/";

	public static String isExist = baseUrl + "api/LawEnforceDocRecord/IsExistLawEnforceDocRecord";

	//执法文书模板
	public static final String TEMPLATE_PATH = "/mnt/sdcard/anjian/cache/";

	//移动执法APP的数据存储路径
	public static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/anjian/";

	//执法文书的存放路径
	public static final String DOCUMENT_PATH = BASE_PATH + "document/";


}
