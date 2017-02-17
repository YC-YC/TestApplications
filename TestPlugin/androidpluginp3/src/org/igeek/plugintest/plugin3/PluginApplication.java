package org.igeek.plugintest.plugin3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.igeek.plugintest.plugin.PluginAplication;

/**
 * @author 作者 E-mail:hangxin1940@gmail.com
 * @version 创建时间：2011-12-14 下午01:20:30
 * 类说明
 */
public class PluginApplication extends PluginAplication<String, List<String>> {

	
	
	@Override
	public Map<String,List<String>> getDesciption() {
		Map<String,List<String>> methods=new HashMap<String, List<String>>();

		List<String> cname=new ArrayList<String>();
		cname.add("PluginActivity");
		
		methods.put("classes", cname);
		
		
		List<String> method=new ArrayList<String>();
		
		methods.put("methods",method);
		return methods;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
	}

}
