package com.bc.stdsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.dao.ClassWorkerDao;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.util.DButil;

/**
 * 
 * @author asus 班主任操作实现类
 */
public class ClassWorkerDaoImpl implements ClassWorkerDao {
	private Connection conn;// 连接对象

	public ClassWorkerDaoImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = DButil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Course> findCourseByClassWorker(ClassWorker ClassWorker) {// 班主任不需要
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<String> findMyclassByClassWorker(ClassWorker ClassWorker) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();// 数据集
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			String sql = "select name from myclass where classworker=?;";
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);// 这里pst直接获取，避免大量调用Dbutil静态方法
			pst.setString(1, ClassWorker.getName());
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
	public ArrayList<Student> findStudentByClassWorker(String className) {
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
		String sql = "update student set myclass='' where num=?;";
		PreparedStatement pst = null;// 预编译对象

		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(stdNum));
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

	@Override
	public Student addStudentByNameAndNum(String name, String num) {
		// TODO Auto-generated method stub
		Student std = null;
		String sql = "select * from student where name=? and num=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集

		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, Integer.parseInt(num));
			rst = pst.executeQuery();
			while (rst.next()) {
				std = new Student();
				std.setNum(rst.getInt("num"));
				std.setName(rst.getString("name"));
				std.setDorm(rst.getString("dorm"));
				std.setJoinDate(rst.getString("joindate"));
				std.setQuitDate(rst.getString("quitdate"));
				std.setCount_time(rst.getInt("count_time"));
				std.setState(rst.getString("state"));
				std.setCredit(rst.getInt("credit"));// 学分
				std.setMyClass(rst.getString("myclass"));
				std.setSex(rst.getString("sex"));
				std.setAddress(rst.getString("address"));
				std.setPhone(rst.getString("phone"));
				std.setParentPhone(rst.getString("parentphone"));
				return std;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return std;
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
		return std;
	}

	@Override
	public void addStudentInMyClass(String myClass, Student stu) {
		// TODO Auto-generated method stub
		String sql = "update student set myclass=? where num=?;";
		PreparedStatement pst = null;// 预编译对象

		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, myClass);
			pst.setInt(2, stu.getNum());
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

	@Override
	public void updateStudentHistoryScore(int studentNo, double faceToFace, double write, double computer,
			String ClassWorkerSpeak, String date, double average) {
		// TODO Auto-generated method stub
		Score score = null;
		List<Score> list = null;
		String sql = "update score set facetoface=?,writescore=?,computer=?,average=?,classworkerspeak=? where studentnum=? and date=?;";
		PreparedStatement pst = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, faceToFace);
			pst.setDouble(2, write);
			pst.setDouble(3, computer);
			pst.setDouble(4, average);
			pst.setString(5, ClassWorkerSpeak);
			pst.setInt(6, studentNo);
			pst.setString(7, date);
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
