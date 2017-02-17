package org.igeek.plugintest.plugin1;

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

	
	/**
	 * 这个方法是获取插件的描述信息，这里只是简要的大概意思一下
	 * 实际上就是方便主程序通过ioc控制反转来自适应的列出插件信息并调用
	 */
	@Override
	public Map<String,List<String>> getDesciption() {
		Map<String,List<String>> methods=new HashMap<String, List<String>>();

		List<String> cname=new ArrayList<String>();
		cname.add("PluginActivity");
		
		methods.put("classes", cname);
		
		
		List<String> method=new ArrayList<String>();
		method.add("showDialogByPlugin");
		method.add("toastByPlugin");
		
		methods.put("methods",method);
		return methods;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
	}

}
