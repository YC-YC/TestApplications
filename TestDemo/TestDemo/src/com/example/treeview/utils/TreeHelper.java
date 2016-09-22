package com.example.treeview.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.testdemo.R;


public class TreeHelper {
	/*
	 * 将用户的数据转换成树形数据
	 * 通过“反射”+“注解”获取字段，填充List(添加Anotation)并抛出异常
	 * 排序
	 * 过滤出需要显示的数据
	 * 
	 */
	public static <T> List<Node> convertData2Nodes(List<T> datas) throws IllegalAccessException, IllegalArgumentException
	{
		List<Node> nodes = new ArrayList<Node>();
		int id = 0;
		int pid = 0;
		String label = "";
		Node node = null;
		for (T t:datas)
		{
			Class clazz = t.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field:fields)
			{
				if (field.getAnnotation(TreeNodeId.class) != null)//有注释
				{
					field.setAccessible(true);
					id = field.getInt(t);
				}
				
				if (field.getAnnotation(TreeNodePid.class) != null)//有注释
				{
					field.setAccessible(true);
					pid = field.getInt(t);
				}
				
				if (field.getAnnotation(TreeNodeLabel.class) != null)//有注释
				{
					field.setAccessible(true);
					label = (String) field.get(t);
				}
			}
			node = new Node(id, pid, label);
			nodes.add(node);
		}
		/*
		 * 所有节点进行关系排序
		 */
		for (int i = 0; i < nodes.size(); i++)
		{
			Node node1 = nodes.get(i);
			for (int j = i+1; j < nodes.size(); j++)
			{
				Node node2 = nodes.get(j);
				
				if (node2.getPid() == node1.getId())//node2的父节点时node1的节点，则将node2加到node1
				{
					node1.getChild().add(node2);
					node2.setParent(node1);
				}
				else if (node2.getId() == node1.getPid())//node2的节点时node1的父节点，则将node2加到node1
				{
					node2.getChild().add(node1);
					node1.setParent(node2);
				}
			}
			
		}
		
		/*
		 * 为Node设置图片
		 */
		for (Node n: nodes)
		{
			setNodeIcon(n);
		}
		return nodes;
	}

	/*
	 * 为Node设置图片
	 */
	private static void setNodeIcon(Node n) {
		if (n.getChild().size() > 0 && n.isExpand())
		{
			n.setIcon(R.drawable.tree_ex);
		}
		else if (n.getChild().size() > 0 && !n.isExpand())
		{
			n.setIcon(R.drawable.tree_ec);
		}
		else
		{
			n.setIcon(-1);
		}
	}
	
	/*
	 * 排序
	 */
	public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException, IllegalArgumentException
	{
		List<Node> result = new ArrayList<Node>();
		List<Node> nodes = convertData2Nodes(datas);
		
		List<Node> rootNodes = getRootNodes(nodes);
		
		for (Node node:rootNodes)
		{
			addNode(result, node, defaultExpandLevel,1);
		}
		
		return result;
	}

	//以根节点为基础添加子节点
	private static void addNode(List<Node> result, Node node,
			int defaultExpandLevel, int currentLevel) {
		result.add(node);
		if (defaultExpandLevel >= currentLevel)
		{
			node.setExpand(true);
		}
		if (node.isLeaf())
			return;
		for (int i = 0; i < node.getChild().size(); i++)
		{
			addNode(result, node.getChild().get(i), defaultExpandLevel, currentLevel + 1);
		}
	}

	//从所有节点中获取根节点
	private static List<Node> getRootNodes(List<Node> nodes) {
		List<Node> root = new ArrayList<Node>();
		for (Node n :nodes)
		{
			if (n.isRoot())
			{
				root.add(n);
			}
		}
		return root;
	}
	
	/*
	 * 过滤出需要显示的数据
	 */
	public static List<Node> filterVisibleNodes(List<Node> datas)
	{
		List<Node> result = new ArrayList<Node>();
		for (Node node: datas)
		{
			if (node.isRoot() || node.isParentExpand())
			{
				setNodeIcon(node);
				result.add(node);
			}
			
		}
		return result;
	}
}
