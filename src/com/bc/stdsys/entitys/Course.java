package com.bc.stdsys.entitys;

public class Course {
	private int id;
	private int teacherNum;
	private String name;
	private String myClass;
	private String teacher;
	private String date;// 开课日期

	public Course() {

	}

	public Course(int id, String name, String myClass, String teacher, String date, int teacherNum) {
		this.id = id;
		this.name = name;
		this.myClass = myClass;
		this.teacher = teacher;
		this.date = date;
		this.teacherNum = teacherNum;
	}

	public int getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
