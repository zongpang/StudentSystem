package com.bc.stdsys.dao;

import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.entitys.Deanery;

/**
 * @author mayiong 院长数据库操作接口
 */
public interface DeaneryDao {
	
	/**
	 * 根据教师姓名返回课程集合
	 * @param Deanery
	 * @return
	 */
	public List<Course> findCourseByDeanery();

	/**
	 * 根据教师姓名（如遇重名管理员注意区分）
	 * 
	 * @param Deanery
	 * @return 返回教师所教授的班级名称列表
	 */
	public List<String> findMyclassByDeanery();

	/**
	 * 根据班级名称
	 * 
	 * @param Deanery
	 * @return students 返回学生集合
	 */
	public  ArrayList<Student> findStudentByDeanery(String className);
	
	
	/**
	 * 根据学号返回该生的历史成绩
	 * @param stdNum
	 * @return
	 */
	public List<Score> findScoreByStudentNum(String stdNum);
	
	/**
	 * 根据学号将学生所在班级更新为‘’
	 * @param stdNum
	 */
	public void deleteMyclassFromStudent(String stdNum);
	
	/**
	 * 根据学生姓名和学号查出该学生
	 * @param name
	 * @param num
	 * @return
	 */
	public Student addStudentByNameAndNum(String name,String num);
	
	/**
	 * 为本班添加一名学生
	 */
	public void addStudentInMyClass(String myClass,Student stu);
	/**
	 * 修改学生历史信息
	 * @param studentNo
	 * @param faceToFace
	 * @param write
	 * @param computer
	 * @param DeanerySpeak
	 */
	public void updateStudentHistoryScore(int studentNo,double faceToFace,double write,double computer,String DeanerySpeak,String date,double average);
    /**
     * 查出所有教师信息
     * @return
     */
	public List<Teacher> findAllTeacher();
    /**
     * 查出所有班主任信息
     * @return
     */
	public List<ClassWorker> findAllClassWorker();
}
