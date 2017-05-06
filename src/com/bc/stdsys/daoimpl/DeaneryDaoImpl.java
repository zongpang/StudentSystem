package com.bc.stdsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.dao.DeaneryDao;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.DButil;

/**
 * 
 * @author asus 院长操作实现类
 */
public class DeaneryDaoImpl implements DeaneryDao {

	private Connection conn;// 连接对象

	public DeaneryDaoImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = DButil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Course> findCourseByDeanery() {
		// TODO Auto-generated method stub
		List<Course> myCourse = new ArrayList<Course>();
		String sql = "select * from course;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集

		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			Course c = null;
			while (rst.next()) {
				c = new Course();
				c.setName(rst.getString("name"));
				c.setDate(rst.getString("date"));
				c.setMyClass(rst.getString("myclass"));
				c.setTeacher(rst.getString("teacher"));
				c.setTeacherNum(rst.getInt("teachernum"));
				myCourse.add(c);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return myCourse;
	}

	@Override
	public List<String> findMyclassByDeanery() {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();// 数据集
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			String sql = "select name from myclass;";
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);// 这里pst直接获取，避免大量调用Dbutil静态方法
			rst = pst.executeQuery();
			while (rst.next()) {
				list.add(rst.getString("name"));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public ArrayList<Student> findStudentByDeanery(String className) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		ArrayList<Student> listStd = null;
		String sql = "select * from student where myclass=?;";
		try {
			listStd = new ArrayList<Student>();// 创建学生集合
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listStd;
	}

	@Override
	public List<Score> findScoreByStudentNum(String stdNum) {
		// TODO Auto-generated method stub
		List<Score> historyScore = new ArrayList<Score>();
		String sql = "select * from score where studentnum=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集

		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(stdNum));
			rst = pst.executeQuery();
			Score s = null;
			while (rst.next()) {
				s = new Score();
				s.setStudentNum(rst.getInt("studentnum"));
				s.setMyClass(rst.getString("myclass"));
				s.setCourse(rst.getString("course"));
				s.setFaceToFace(rst.getDouble("facetoface"));
				s.setWriteScore(rst.getDouble("writescore"));
				s.setComputer(rst.getDouble("computer"));
				s.setAverage(rst.getDouble("average"));
				s.setTeacher(rst.getString("teacher"));
				s.setTeacherSpeak(rst.getString("teacherspeak"));
				s.setClassWorker(rst.getString("classworker"));
				s.setClassWorkerSpeak(rst.getString("classworkerspeak"));
				s.setDate(rst.getString("date"));
				historyScore.add(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return historyScore;
	}

	@Override
	public void deleteMyclassFromStudent(String stdNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public Student addStudentByNameAndNum(String name, String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStudentInMyClass(String myClass, Student stu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudentHistoryScore(int studentNo, double faceToFace, double write, double computer,
			String DeanerySpeak, String date, double average) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Teacher> findAllTeacher() {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		String sql = "select * from teacher;";
		ArrayList<Teacher> listTeacher = new ArrayList<Teacher>();
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			Teacher t = null;
			while (rst.next()) {
				t = new Teacher();
				t.setName(rst.getString("name"));
				t.setNum(rst.getInt("num"));
				t.setaDdress(rst.getString("address"));
				t.setPhone(rst.getString("phone"));
				listTeacher.add(t);
			}
			return listTeacher;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listTeacher;
	}

	@Override
	public List<ClassWorker> findAllClassWorker() {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		String sql = "select * from classworker;";
		ArrayList<ClassWorker> listClassWwoker = new ArrayList<ClassWorker>();
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			ClassWorker cw = null;
			while (rst.next()) {
				cw = new ClassWorker();
				cw.setName(rst.getString("name"));
				cw.setNum(rst.getInt("num"));
				cw.setaDdress(rst.getString("address"));
				cw.setPhone(rst.getString("phone"));
				listClassWwoker.add(cw);
			}
			return listClassWwoker;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listClassWwoker;
	}

	@Override
	public void changeUserPassWord(String name, String new_pass) {
		// TODO Auto-generated method stub
		String sql = "update deanery set password=? where name=?;";
		PreparedStatement pst = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
            pst.setString(1, new_pass);
            pst.setString(2, name);
			pst.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
