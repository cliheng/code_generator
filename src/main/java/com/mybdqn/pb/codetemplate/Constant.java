package com.mybdqn.pb.codetemplate;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class Constant {

	/* 静态块分析已经实现路径配置的解析，如果无法正确加载配置文件时
	*  请尝试给下面的两个常量进行手工赋值(通常不需要手工配置)
	 */
	public static String properties_path;
	public static String template_path;
	
	static{
		URL url = Constant.class.getResource("/");
		String tpath = url.getPath();
		if (System.getProperty("os.name").toLowerCase().indexOf("window") > -1) {
			tpath = tpath.substring(1);
		}
		//properties_path = tpath.replaceFirst("target/.*", "resources/");
		//properties_path = properties_path.replaceFirst("bin/.*", "resources/");
		try {
			properties_path = URLDecoder.decode(tpath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		template_path = properties_path + "template";
	}
}
