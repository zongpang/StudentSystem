package com.bc.stdsys.entitys;

public class Student {
	private int id;
	private int num;
	private String name;
	private String Dorm;// 宿舍号
	private String joinDate;// 入学时间
	private String quitDate;// 毕业、退学时间
	private String state;// 状态（在校、毕业、处分（劝退、留校察看））
	private double credit;// 学分

	public Student() {

	}

	public Student(int id, int num, String name, String dorm, String joinDate, String quitDate, String state,
			double credit) {
		this.id = id;
		this.num = num;
		this.name = name;
		Dorm = dorm;
		this.joinDate = joinDate;
		this.quitDate = quitDate;
		this.state = state;
		this.credit = credit;
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
		return Dorm;
	}

	public void setDorm(String dorm) {
		Dorm = dorm;
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

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

}
