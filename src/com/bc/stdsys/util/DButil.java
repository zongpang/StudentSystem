package com.bc.stdsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;

import com.bc.stdsys.entitys.Deanery;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.MyClass;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;

public class DButil {
	static Connection conn;// 获得连接
	static PreparedStatement statment;// 获得预编译对象
	static ResultSet rst;// 获得结果

	/**
	 * 获得数据库连接对象
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/studentsystem?useUnicode=true&characterEncoding=utf-8";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	/**
	 * 获得预编译对象
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getStatement(Connection conn, String sql) {

		try {
			statment = conn.prepareStatement(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return statment;
	}

	/**
	 * 执行数据库操作功能
	 * 
	 * @param statement
	 * @param type
	 *            1.增加 2.删除 3.修改 4.查询登陆
	 * @return
	 */
	public static ResultSet getResultSet(PreparedStatement statement, int type, Object objs) {
		ResultSet rst = null;

		switch (type) {
		case 1:

			if (objs instanceof Student) {// 增加一个学生

			} else if (objs instanceof Teacher) {// 增加一个老师

			} else if (objs instanceof ClassWorker) {// 增加一个班主任

			} else if (objs instanceof MyClass) {// 添加一个班级

			} else if (objs instanceof Course) {// 添加一门课程

			} else if (objs instanceof Score) {// 插入一次学生月度考试成绩

			}

		case 2:

			if (objs instanceof Student) {// 删除一个学生

			} else if (objs instanceof Teacher) {// 删除一个老师

			} else if (objs instanceof ClassWorker) {// 删除一个班主任

			} else if (objs instanceof MyClass) {// 删除一个班级

			} else if (objs instanceof Course) {// 删除一门课程

			} else if (objs instanceof Score) {// 删除一次学生月度考试成绩

			}

		case 3:

			if (objs instanceof Student) {// 修改一个学生

			} else if (objs instanceof Teacher) {// 修改一个老师

			} else if (objs instanceof ClassWorker) {// 修改一个班主任

			} else if (objs instanceof MyClass) {// 修改一个班级

			} else if (objs instanceof Course) {// 修改一门课程

			} else if (objs instanceof Score) {// 修改一次学生月度考试成绩

			}

		case 4:
			// 查询验证登陆
			if (objs instanceof Teacher) {
				try {
					statement.setString(1, ((Teacher) objs).getName());
					statement.setString(2, ((Teacher) objs).getPassWord());
					rst = statement.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return rst;// 返回登陆后的查询
			} else if (objs instanceof ClassWorker) {
				try {
					statement.setString(1, ((ClassWorker) objs).getName());
					statement.setString(2, ((ClassWorker) objs).getPassWord());
					rst = statement.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return rst;
			} else if (objs instanceof Master) {
				try {
					statement.setString(1, ((Master) objs).getName());
					statement.setString(2, ((Master) objs).getPassWord());
					rst = statement.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return rst;

			} else if (objs instanceof Deanery) {
				try {
					statement.setString(1, ((Deanery) objs).getName());
					statement.setString(2, ((Deanery) objs).getPassWord());
					rst = statement.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return rst;
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 验证登陆方法
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @return
	 */

	public static boolean login(String userId, String name, String password) {
		if (userId.equals("教师")) {
			Teacher teacher = new Teacher();
			teacher.setName(name);
			teacher.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn,
					"select * from teacher where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, teacher);
			try {
				if (rst.next())
					return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (userId.equals("班主任")) {
			ClassWorker classWorker = new ClassWorker();
			classWorker.setName(name);
			classWorker.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn,
					"select * from classworker where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, classWorker);
			try {
				if (rst.next())
					return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (userId.equals("管理员")) {
			Master master = new Master();
			master.setName(name);
			master.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn, "select * from master where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, master);
			try {
				if (rst.next())
					return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (userId.equals("院长")) {
			Deanery deanery = new Deanery();
			deanery.setName(name);
			deanery.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn,
					"select * from deanery where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, deanery);
			try {
				if (rst.next())
					return true;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
