package com.bc.stdsys.entitys;

import java.io.Serializable;

public class Deanery implements Serializable{
	private int id;
	private int num;
	private String name;
	private String passWord;

	public Deanery() {

	}


	public Deanery(String name, String password) {
		this.name = name;
		this.passWord = password;
	}


	public Deanery(int id, int num, String name, String passWord) {
		this.id = id;
		this.num = num;
		this.name = name;
		this.passWord = passWord;
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

}
