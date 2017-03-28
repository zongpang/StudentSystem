package com.bc.stdsys.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;

/**
 * 
 * @author mayiong 教师数据库操作接口
 *
 */
public interface TeacherDao {
	/**
	 * 根据教师工号
	 * 
	 * @param teacher
	 * @return 返回教师所教授的班级名称列表
	 */
	public List<String> findMyclassByTeacher(Teacher teacher);

	/**
	 * 根据班级名称
	 * 
	 * @param teacher
	 * @return students 返回学生集合
	 */
	public Map<String, ArrayList<Student>> findStudentByTeacher(List<String> list);
}