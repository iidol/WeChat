package com.zxj.wechat.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//将微信创建时间格式化为标准时间
	public static String formatDate(String createTime){
		long time = Long.valueOf(createTime);
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(new Date(time));
	}
}
