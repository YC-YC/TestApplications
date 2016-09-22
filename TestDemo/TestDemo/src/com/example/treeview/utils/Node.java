package com.example.treeview.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	// 根节点
	private int pid = 0;
	private String name;
	// 级数
	private int level;
	//是否展开
	private boolean isExpand;
	private int icon;
	
	private Node parent;
	private List<Node> child = new ArrayList<Node>();
	
	public Node(int id, int pid, String label)
	{
		this.id = id;
		this.pid = pid;
		this.name = label;
	}
	/*
	 * 是否是根节点
	 */
	public boolean isRoot()
	{
		return parent == null;
	}
	
	/*
	 * 父节点是否展开
	 */
	public boolean isParentExpand()
	{
		if (parent == null)
			return false;
		return parent.isExpand();
	}
	
	/*
	 * 是否是叶节点
	 */
	public boolean isLeaf()
	{
		return child.size() == 0;
	}
	
	/*
	 * 得到当前节点的层级
	 */
	public int getLevel() {
		return parent == null?0:parent.getLevel() + 1;
	}
	
	public boolean isExpand() {
		return isExpand;
	}
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
		if (!this.isExpand)
		{
			//递归子节点
			for (Node node : child) {
				node.setExpand(false);
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChild() {
		return child;
	}
	public void setChild(List<Node> child) {
		this.child = child;
	}
	
	
}
