package com.example.resources.provider;

/**
 * @author YC
 * @time 2016-3-12 上午10:36:37
 */
public class Person {
	private Integer _id;
	private String name;
	private String age;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
