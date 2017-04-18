package com.bc.stdsys.entitys;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private int num;
	private String name;
	private String sex;
	private String dorm;// 宿舍号
	private String joinDate;// 入学时间
	private String quitDate;// 毕业、退学时间
	private String state;// 状态（在校、毕业、处分（劝退、留校察看））
	private int credit;// 学分
	private String myClass;// 当前所在班级
	private String address;// 家庭地址
	private String phone;// 学生联系电话
	private String parentPhone;// 家长联系电话
	private int count_time;

	public Student() {

	}

	public Student(int id, int num, String name, String sex, String dorm, String joinDate, String quitDate,
			String state, int credit, String myClass, String address, String phone, String parentPhone,int count_time) {
		this.id = id;
		this.num = num;
		this.name = name;
		this.sex = sex;
		this.dorm = dorm;
		this.joinDate = joinDate;
		this.quitDate = quitDate;
		this.state = state;
		this.credit = credit;
		this.myClass = myClass;
		this.address = address;
		this.phone = phone;
		this.parentPhone = parentPhone;
		this.count_time=count_time;
	}


	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		dorm = dorm;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	public int getCount_time() {
		return count_time;
	}

	public void setCount_time(int count_time) {
		this.count_time = count_time;
	}


}
