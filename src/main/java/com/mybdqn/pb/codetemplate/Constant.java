package com.mybdqn.pb.codetemplate;

import java.net.URL;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class Constant {
	
	static{
		URL url = Constant.class.getClassLoader().getResource("db.properties");
		String tpath = url.getPath().substring(1);
		properties_path = tpath.replaceFirst("target/.*", "resources/");
		properties_path = tpath.replaceFirst("bin/.*", "resources/");
	}

	public static String properties_path;
    public static String template_path = properties_path + "template";

}
