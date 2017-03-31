package com.bc.stdsys.entitys;

import java.io.Serializable;

public class Teacher implements Serializable {
	private int id;
	private int num;
	private String name;
	private String passWord;
	private String course;
	private String myClass;

	public Teacher() {

	}

	public Teacher(String name, String passwrod) {
		this.name = name;
		this.passWord = passwrod;
	}

	public Teacher(int id, int num, String name, String passWord, String course, String myClass) {

		this.id = id;
		this.num = num;
		this.name = name;
		this.passWord = passWord;
		this.course = course;
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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

}
