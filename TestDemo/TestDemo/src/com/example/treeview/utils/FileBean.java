package com.example.treeview.utils;

public class FileBean {
	@TreeNodeId
	private int id;
	@TreeNodePid
	private int pid;
	@TreeNodeLabel
	private String label;
	
	private String desc;
	
	
	public FileBean(int id, int pid, String label) {
		super();
		this.id = id;
		this.pid = pid;
		this.label = label;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
