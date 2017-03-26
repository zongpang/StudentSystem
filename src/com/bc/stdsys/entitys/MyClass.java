package com.bc.stdsys.entitys;

public class MyClass {
	private int id;
	private String name;
	private String classRoom;//班级所在房间
	private String teacher;
	private String classWorker;

	public MyClass() {

	}

	public MyClass(int id, String name, String classRoom, String teacher, String classWorker) {

		this.id = id;
		this.name = name;
		this.classRoom = classRoom;
		this.teacher = teacher;
		this.classWorker = classWorker;
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

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getClassWorker() {
		return classWorker;
	}

	public void setClassWorker(String classWorker) {
		this.classWorker = classWorker;
	}

}
