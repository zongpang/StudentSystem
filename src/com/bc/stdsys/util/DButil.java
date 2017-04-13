package com.bc.stdsys.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Deanery;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Teacher;

public class DButil {
	static Connection conn;// 获得连接
	static PreparedStatement statment;// 获得预编译对象
	static ResultSet rst;// 获得结果

	/**
	 * 获得数据库连接对象
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Context context = new InitialContext();
		Context envContext = (Context) context.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/demo");
		conn = ds.getConnection();
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
		case 2:
		case 3:
		case 4:
			// 查询验证登陆
			if (objs instanceof Teacher) {
				try {
					statement.setString(1, ((Teacher) objs).getName());
					statement.setString(2, ((Teacher) objs).getPassWord());
					rst = statement.executeQuery();
				} catch (Exception e1) {
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
	
		return rst;
	}

	/**
	 * 验证登陆方法
	 * 
	 * @param userId
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */

	public static Object login(String userId, String name, String password) throws Exception {
		if (userId.equals("教师")) {
			Teacher teacher = new Teacher();
			teacher.setName(name);
			teacher.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn,
					"select * from teacher where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, teacher);
			try {
				if (rst.next()) {
					teacher.setNum(rst.getInt("num"));
					teacher.setName(rst.getString("name"));
					teacher.setPassWord(rst.getString("password"));
					teacher.setPhone(rst.getString("phone"));
					teacher.setaDdress(rst.getString("address"));
					return teacher;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rst.close();
				statement.close();
				conn.close();
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
				if (rst.next()) {
					classWorker.setNum(rst.getInt("num"));
					classWorker.setName(rst.getString("name"));
					classWorker.setPassWord(rst.getString("password"));
					classWorker.setPhone(rst.getString("phone"));
					classWorker.setaDdress(rst.getString("address"));
					return classWorker;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rst.close();
				statement.close();
				conn.close();
			}
		} else if (userId.equals("管理员")) {
			Master master = new Master();
			master.setName(name);
			master.setPassWord(password);
			Connection conn = DButil.getConnection();
			PreparedStatement statement = DButil.getStatement(conn, "select * from master where name=? and password=?");
			ResultSet rst = DButil.getResultSet(statement, 4, master);
			try {
				if (rst.next()) {
					master.setNum(rst.getInt("num"));
					return master;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rst.close();
				statement.close();
				conn.close();
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
				if (rst.next()) {
					deanery.setNum(rst.getInt("num"));
					return deanery;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rst.close();
				statement.close();
				conn.close();
			}

		}

		return false;
	}

}
