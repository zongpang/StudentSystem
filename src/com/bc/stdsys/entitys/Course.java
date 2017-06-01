package com.bc.stdsys.entitys;

import java.io.Serializable;

public class Course implements Serializable {
	private int id;
	private int teacherNum;// 教师工号
	private int classWorkerNum;//班主任工号
	private String name;// 课程名称
	private String myClass;// 所受班级
	private String teacher;// 教师姓名
	private String date;// 开课日期
    private String classWorker;
    
	public Course() {

	}

	public Course(int id, int teacherNum, String name, String myClass, String teacher, String date) {
		this.id = id;
		this.teacherNum = teacherNum;
		this.name = name;
		this.myClass = myClass;
		this.teacher = teacher;
		this.date = date;
		
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
	public int getClassWorkerNum() {
		return classWorkerNum;
	}

	public void setClassWorkerNum(int classWorkerNum) {
		this.classWorkerNum = classWorkerNum;
	}

	public String getClassWorker() {
		return classWorker;
	}

	public void setClassWorker(String classWorker) {
		this.classWorker = classWorker;
	}
}
