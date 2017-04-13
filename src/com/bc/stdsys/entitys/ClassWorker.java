package com.bc.stdsys.entitys;

import java.io.Serializable;

public class ClassWorker implements Serializable{
	private int id;
	private int num;
	private String name;
	private String passWord;
	private String phone;
	private String aDdress;
	//private String myClass;
	public ClassWorker() {

	}
	public ClassWorker(int id, int num, String name, String passWord, String phone, String aDdress) {
		this.id = id;
		this.num = num;
		this.name = name;
		this.passWord = passWord;
		this.phone = phone;
		this.aDdress = aDdress;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getaDdress() {
		return aDdress;
	}
	public void setaDdress(String aDdress) {
		this.aDdress = aDdress;
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

//	public String getMyClass() {
//		return myClass;
//	}
//
//	public void setMyClass(String myClass) {
//		this.myClass = myClass;
//	}

}
