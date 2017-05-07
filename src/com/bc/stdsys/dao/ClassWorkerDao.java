package com.bc.stdsys.dao;

import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.ClassWorker;

/**
 * 
 * @author mayiong 班主任数据库操作接口
 *
 */
public interface ClassWorkerDao {

	/**
	 * 根据教师姓名返回课程集合
	 * @param ClassWorker
	 * @return
	 */
	public List<Course> findCourseByClassWorker(ClassWorker ClassWorker);

	/**
	 * 根据教师姓名（如遇重名管理员注意区分）
	 * 
	 * @param ClassWorker
	 * @return 返回教师所教授的班级名称列表
	 */
	public List<String> findMyclassByClassWorker(ClassWorker ClassWorker);

	/**
	 * 根据班级名称
	 * 
	 * @param ClassWorker
	 * @return students 返回学生集合
	 */
	public  ArrayList<Student> findStudentByClassWorker(String className);
	
	
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
	 * @param ClassWorkerSpeak
	 */
	public void updateStudentHistoryScore(int studentNo,String ClassWorkerSpeak,String date);
	
	/**
	 * 修改用户密码
	 * @param name
	 * @param new_pass
	 */
	public void changeUserPassWord(String name ,String new_pass);
	
	
}
