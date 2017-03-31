package com.bc.stdsys.entitys;

import java.io.Serializable;

public class Score implements Serializable{
	private int id;
	private String studentNum;
	private String course;// 课程名称
	private double faceToFace;// 面试成绩
	private double write;// 笔试成绩
	private double computer;// 机试成绩
	private double average;// 三科综合成绩
	private String teacher;
	private String teacherSpeak;// 教师评价
	private String classWorker;
	private String classWorkerSpeak;// 班主任评价
	private String date;// 考试日期

	public Score() {

	}

	public Score(int id, String studentNum, String course, double faceToFace, double write, double computer,
			double average, String teacher, String teacherSpeak, String classWorker, String classWorkerSpeak,
			String date) {
		this.id = id;
		this.studentNum = studentNum;
		this.course = course;
		this.faceToFace = faceToFace;
		this.write = write;
		this.computer = computer;
		this.average = average;
		this.teacher = teacher;
		this.teacherSpeak = teacherSpeak;
		this.classWorker = classWorker;
		this.classWorkerSpeak = classWorkerSpeak;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getFaceToFace() {
		return faceToFace;
	}

	public void setFaceToFace(double faceToFace) {
		this.faceToFace = faceToFace;
	}

	public double getWrite() {
		return write;
	}

	public void setWrite(double write) {
		this.write = write;
	}

	public double getComputer() {
		return computer;
	}

	public void setComputer(double computer) {
		this.computer = computer;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTeacherSpeak() {
		return teacherSpeak;
	}

	public void setTeacherSpeak(String teacherSpeak) {
		this.teacherSpeak = teacherSpeak;
	}

	public String getClassWorker() {
		return classWorker;
	}

	public void setClassWorker(String classWorker) {
		this.classWorker = classWorker;
	}

	public String getClassWorkerSpeak() {
		return classWorkerSpeak;
	}

	public void setClassWorkerSpeak(String classWorkerSpeak) {
		this.classWorkerSpeak = classWorkerSpeak;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
