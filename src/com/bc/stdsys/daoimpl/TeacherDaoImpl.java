package com.bc.stdsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.DButil;

public class TeacherDaoImpl implements TeacherDao {
	private Connection conn;// 连接对象

	public TeacherDaoImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = DButil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// String sql="select * from student inner join myclass on
	// student.myclass=myclass.name inner join course on
	// myclass.teacher=course.teacher and
	// myclass.teachernum=course.teachernum;";

	@Override
	public List<String> findMyclassByTeacher(Teacher teacher) {
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();// 数据集
		try {
			String sql = "select name from myclass where teachernum=?;";
			pst = conn.prepareStatement(sql);// 这里pst直接获取，避免大量调用Dbutil静态方法
			pst.setInt(1, teacher.getNum());
			rst = pst.executeQuery();
			while (rst.next()) {
				list.add(rst.getString("name"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Student> findStudentByTeacher(String className) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		ArrayList<Student> listStd = null;
		String sql = "select * from student where myclass=?;";
		try {
			listStd = new ArrayList<Student>();// 创建学生集合
			pst = conn.prepareStatement(sql);
			pst.setString(1, className);
			rst = pst.executeQuery();
			Student std = null;
			while (rst.next()) {
				std = new Student();// 创建学生对象
				std.setName(rst.getString("name"));// 姓名
				std.setCredit(rst.getInt("credit"));// 学分
				std.setDorm(rst.getString("dorm"));
				std.setMyClass(rst.getString("myclass"));
				std.setJoinDate(rst.getString("joinDate"));
				std.setQuitDate(rst.getString("quitDate"));
				std.setState(rst.getString("state"));
				std.setNum(rst.getInt("num"));// 学号
				std.setSex(rst.getString("sex"));
				listStd.add(std);
			}
			return listStd;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStd;
	}

}
