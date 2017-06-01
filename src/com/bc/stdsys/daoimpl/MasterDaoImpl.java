package com.bc.stdsys.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bc.stdsys.dao.MasterDao;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.DButil;

/**
 * 
 * @author mayizong 管理员操作实现类
 */
public class MasterDaoImpl implements MasterDao {
	private Connection conn;// 连接对象

	public MasterDaoImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = DButil.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Course> findCourseByMaster() {
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
				c.setClassWorker(rst.getString("classworker"));
				c.setTeacherNum(rst.getInt("classworkernum"));
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
	public List<String> findMyclassByMaster() {
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
	public ArrayList<Student> findStudentByMaster(String className) {
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
			String MasterSpeak, String date, double average) {
		// TODO Auto-generated method stub
		String sql = "update score set facetoface=?,writescore=?,computer=?,average=?,teacherspeak=? where studentnum=? and date=?;";
		PreparedStatement pst = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, faceToFace);
			pst.setDouble(2, write);
			pst.setDouble(3, computer);
			pst.setDouble(4, average);
			pst.setString(5, MasterSpeak);
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
		String sql = "update master set password=? where name=?;";
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

	@Override
	public ArrayList<Student> findAllStudentByMaster() {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		ArrayList<Student> listStd = null;
		String sql = "select * from student;";
		try {
			listStd = new ArrayList<Student>();// 创建学生集合
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
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
	public void addNewStudentInSchool(Student stu) {
		// TODO Auto-generated method stub

		PreparedStatement pst = null;// 预编译对象
		String sql = "insert into student (num,name,sex,address,phone,parentphone,myclass,joindate) values (?,?,?,?,?,?,?,?);";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, stu.getNum());
			pst.setString(2, stu.getName());
			pst.setString(3, stu.getSex());
			pst.setString(4, stu.getAddress());
			pst.setString(5, stu.getParentPhone());
			pst.setString(6, stu.getParentPhone());
			pst.setString(7, stu.getMyClass());
			pst.setString(8, stu.getJoinDate());
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
	public boolean findStudentInSchool(Student stu) {
		// TODO Auto-generated method stub

		String sql = "select * from student where name=? and num=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu.getName());
			pst.setInt(2, stu.getNum());
			rst = pst.executeQuery();
			while (rst.next()) {
				return false;
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
		return true;
	}

	@Override
	public boolean findClassInCourse(Course c) {
		// TODO Auto-generated method stub
		String sql = "select * from course where myclass=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getMyClass());
			rst = pst.executeQuery();
			while (rst.next()) {
				return false;
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
		return true;
	}

	@Override
	public void addNewCourse(Course c) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "insert into course (name,myclass,teacher,date) values (?,?,?,?);";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getName());
			pst.setString(2, c.getMyClass());
			pst.setString(3, c.getTeacher());
			pst.setString(4, c.getDate());
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
	public boolean findTeacherByName(Teacher t) {
		String sql = "select * from teacher where name=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, t.getName());
			rst = pst.executeQuery();
			while (rst.next()) {
				return false;
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
		return true;
	}

	@Override
	public void addNewTeacher(Teacher t) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "insert into teacher (name,num,password,phone,address) values (?,?,?,?,?);";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, t.getName());
			pst.setInt(2, t.getNum());
			pst.setString(3, t.getPassWord());
			pst.setString(4, t.getPhone());
			pst.setString(5, t.getaDdress());
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
	public boolean findClassWorkerByName(ClassWorker c) {
		// TODO Auto-generated method stub
		String sql = "select * from classworker where name=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getName());
			rst = pst.executeQuery();
			while (rst.next()) {
				return false;
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
		return true;
	}

	@Override
	public void addNewClassWorker(ClassWorker c) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "insert into classworker (name,num,password,phone,address) values (?,?,?,?,?);";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getName());
			pst.setInt(2, c.getNum());
			pst.setString(3, c.getPassWord());
			pst.setString(4, c.getPhone());
			pst.setString(5, c.getaDdress());
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
	public void deleteStudent(String stdNum) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "delete from student where num=?";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, stdNum);
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
	public void deleteCourse(String myClass) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "delete from course where myclass=?";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, myClass);
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
	public void deleteTeacher(String name) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "delete from teacher where name=?";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
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
	public void deleteClassWorker(String name) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "delete from classworker where name=?";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
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
	public boolean findclassInMyClass(Course c) {
		// TODO Auto-generated method stub
		String sql = "select * from myclass where name=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getMyClass());
			rst = pst.executeQuery();
			while (rst.next()) {
				return false;
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
		return true;
	}

	@Override
	public void addNewMyClass(Course c) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "insert into myclass (name,teacher) values (?,?);";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c.getMyClass());
			pst.setString(2, c.getTeacher());
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
	public void deleteMyclass(String myClass) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;// 预编译对象
		String sql = "delete from myclass where name=?";
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, myClass);
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
	public void changeCourse(String myClass, String course, String date) {
		// TODO Auto-generated method stub
		String sql = "update course set name=?,date=? where myclass=?;";
		PreparedStatement pst = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, course);
			pst.setString(2, date);
			pst.setString(3, myClass);
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
	public void changeTeacherAndClassWorkerInCourseAndMyclass(String myClass, String teacher, String classWorker) {
		// TODO Auto-generated method stub
		String sql = "update course set teacher=?,classworker=? where myclass=?;";
		PreparedStatement pst = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, teacher);
			pst.setString(2, classWorker);
			pst.setString(3, myClass);
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

		String sql2 = "update myclass set teacher=?,classworker=? where name=?;";
		PreparedStatement pst2 = null;// 预编译对象
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, teacher);
			pst2.setString(2, classWorker);
			pst2.setString(3, myClass);
			pst2.execute();
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
	public List<Score> findZhangSanScore() {
		// TODO Auto-generated method stub
		List<Score> listScore = new ArrayList<Score>();
		String sql = "select * from score where studentnum=?;";
		PreparedStatement pst = null;// 预编译对象
		ResultSet rst = null;// 结果集
		try {
			if (conn == null || conn.isClosed())
				conn = DButil.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, 20111114);
			rst = pst.executeQuery();
			Score c = null;
			while (rst.next()) {
				c = new Score();
				c.setId(rst.getInt("id"));
				c.setDate(rst.getString("date"));
				listScore.add(c);
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
		return listScore;
	}

	@Override
	public void addAnewScore(List<Student> students, String nowDate) {
		// TODO Auto-generated method stub
		for (int i = 0; i < students.size(); i++) {
			/*
			 * 根据学生所在班级查出教师与班主任
			 */
			PreparedStatement pst1 = null;// 预编译对象
			ResultSet rst1 = null;// 结果集
			String teacher = null;
			String classWorker = null;
			try {
				String sql = "select * from myclass where name=?;";
				if (conn == null || conn.isClosed())
					conn = DButil.getConnection();
				pst1 = conn.prepareStatement(sql);
				pst1.setString(1, students.get(i).getMyClass());
				rst1 = pst1.executeQuery();
				while (rst1.next()) {
					teacher = rst1.getString("teacher");
					classWorker = rst1.getString("classworker");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rst1.close();
					pst1.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			/*
			 * 根据班级查出学生课程名称
			 */
			PreparedStatement pst2 = null;// 预编译对象
			ResultSet rst2 = null;// 结果集
		    String course=null;
			try {
				String sql = "select name from course where myclass=?;";
				if (conn == null || conn.isClosed())
					conn = DButil.getConnection();
				pst2 = conn.prepareStatement(sql);
				pst2.setString(1, students.get(i).getMyClass());
				rst2 = pst2.executeQuery();
				while (rst2.next()) {
				       course=rst2.getString("name");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rst2.close();
					pst2.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			/*
			 * 以下为插入语句
			 */

			PreparedStatement pst = null;// 预编译对象
			String sql = "insert into score (studentnum,date,myclass,teacher,classworker,course) values (?,?,?,?,?,?);";
			try {
				if (conn == null || conn.isClosed())
					conn = DButil.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setInt(1, students.get(i).getNum());
				pst.setString(2, nowDate);
				pst.setString(3, students.get(i).getMyClass());
				pst.setString(4, teacher);
				pst.setString(5, classWorker);
				pst.setString(6, course);
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

}
