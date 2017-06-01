package com.bc.stdsys.dao;

import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;

/**
 * 
 * @author mayiong 管理员数据库操作接口
 *
 */
public interface MasterDao {
	/**
	 * 根据教师姓名返回课程集合
	 * @param Master
	 * @return
	 */
	public List<Course> findCourseByMaster();

	/**
	 * 根据教师姓名（如遇重名管理员注意区分）
	 * 
	 * @param Master
	 * @return 返回教师所教授的班级名称列表
	 */
	public List<String> findMyclassByMaster();

	/**
	 * 根据班级名称
	 * 
	 * @param Master
	 * @return students 返回学生集合
	 */
	public  ArrayList<Student> findStudentByMaster(String className);
	
	
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
	 * @param MasterSpeak
	 */
	public void updateStudentHistoryScore(int studentNo,double faceToFace,double write,double computer,String MasterSpeak,String date,double average);
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
	
	/**
	 * 修改用户密码
	 * @param name
	 * @param new_pass
	 */
	public void changeUserPassWord(String name ,String new_pass);
    /**
     * 查出所有学生
     * @return
     */
	public ArrayList<Student> findAllStudentByMaster();
    /**
     * 增加一个学生的基础信息
     * @param stu
     */
	public void addNewStudentInSchool(Student stu);
/**
 * 查看该生是否存在
 * @param stu
 * @return
 */
	public boolean findStudentInSchool(Student stu);
/**
 * 查看课程表中某个班级是否存在
 * @param c
 * @return
 */
	public boolean findClassInCourse(Course c);
/**
 * 为课程表添加一个新的班级
 * @param c
 */
	public void addNewCourse(Course c);
/**
 * 查看该教师是否存在
 * @param t
 * @return
 */
	public boolean findTeacherByName(Teacher t);
/**
 * 添加一名新的教师
 * @param t
 */
public void addNewTeacher(Teacher t);
/**
 * 按姓名查找教师
 * @param c
 * @return
 */
public boolean findClassWorkerByName(ClassWorker c);
/**
 * 添加一名教师
 * @param c
 */
public void addNewClassWorker(ClassWorker c);
/**
 * 根据学号删除一个学生（在学生表中）
 * @param stdNum
 */
public void deleteStudent(String stdNum);
/**
 * 删除一门课程
 * @param myClass
 */
public void deleteCourse(String myClass);
/**
 * 删除一位老师
 * @param name
 */
public void deleteTeacher(String name);
/**
 * 删除一位班主任
 * @param name
 */
public void deleteClassWorker(String name);
/**
 * 查找该班级是否存在（myclass表）
 * @param c
 * @return
 */
public boolean findclassInMyClass(Course c);
/**
 * 在班级表中新增一个班级
 * @param c
 */
public void addNewMyClass(Course c);
/**
 * 根据班级删除myclass表中的一行数据
 * @param myClass
 */
public void deleteMyclass(String myClass);
/**
 * 修改课程表
 * @param myClass
 * @param course
 * @param date
 */
public void changeCourse(String myClass, String course, String date);
/**
 * 作师资分配 该方法将改变course&myclass
 * @param myClass
 * @param teacher
 * @param classWorker
 */
public void changeTeacherAndClassWorkerInCourseAndMyclass(String myClass, String teacher, String classWorker);
/**
 * 在数据库中找出张三（201711114）
 * @return
 */
public List<Score> findZhangSanScore();
/**
 * 向成绩表（score）为每一个学生插入一条当月的成绩模板
 * @param students
 * @param nowDate
 */
public void addAnewScore(List<Student> students, String nowDate);
/**
 * 按姓名模糊查询学生
 * @param name
 * @return
 */
public List<Student> findstudentByName(String name);

	
}
