package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.dao.ClassWorkerDao;
import com.bc.stdsys.dao.MasterDao;
import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.daoimpl.ClassWorkerDaoImpl;
import com.bc.stdsys.daoimpl.MasterDaoImpl;
import com.bc.stdsys.daoimpl.TeacherDaoImpl;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;

import net.sf.json.JSONObject;

public class DelServlet extends HttpServlet {

	HttpSession session;
	TeacherDao daoT;
	ClassWorkerDao daoC;
	MasterDao daoM;
	final int PAGE_SIZE = 2;// 分页查找每页的容量
	JSONObject json;// 创建JO对象
	PrintWriter pw;// 打印器

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj instanceof Teacher) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 删除学生
					String classNo = request.getParameter("classNo");// 得到班级号
					String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdNum = request.getParameter("stdN");// 得到学号
					if (daoT == null)
						daoT = new TeacherDaoImpl();// 实例化teacherDao
					daoT.deleteMyclassFromStudent(stdNum);// 将student表中的myClass更新为‘’
					request.setAttribute("stdNum", classNo);
					request.setAttribute("pageNow", pageNow);
					request.setAttribute("del", "1");
					request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询
				}
			}

		} else if (obj instanceof ClassWorker) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 删除学生
					String classNo = request.getParameter("classNo");// 得到班级号
					String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdNum = request.getParameter("stdN");// 得到学号
					if (daoC == null)
						daoC = new ClassWorkerDaoImpl();// 实例化teacherDao
					daoC.deleteMyclassFromStudent(stdNum);// 将student表中的myClass更新为‘’
					request.setAttribute("stdNum", classNo);
					request.setAttribute("pageNow", pageNow);
					request.setAttribute("del", "1");
					request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询
				}
			}

		} else if (obj instanceof Master) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (daoM == null)
					daoM = new MasterDaoImpl();// 实例化teacherDao
				if (myType == 1) {// 将学生的班级清空
					String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdNum = request.getParameter("stdN");// 得到学号
					String classNo = request.getParameter("classNo");// 得到班级号
					daoM.deleteMyclassFromStudent(stdNum);// 将student表中的myClass更新为‘’
					request.setAttribute("stdNum", classNo);
					request.setAttribute("pageNow", pageNow);
					request.setAttribute("del", "1");
					request.getRequestDispatcher("/find").forward(request, response);// ??（改：在前端自动触发事件）//
																						// 删除后交给/find作分页查询
				} else if (myType == 2) {
					String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdNum = request.getParameter("stdN");// 得到学号
					String classNo = request.getParameter("classNo");// 得到班级号
					daoM.deleteStudent(stdNum);// 根据学号删除某一学生(在前端调用下一页方法)
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put("删除成功", "删除成功");//
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					System.out.println(json.toString());
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();

				} else if (myType == 3) {
					String cl = request.getParameter("myClass");
					System.out.println(cl);
					daoM.deleteCourse(cl);// 根据班级删除课程表
					List<Course> list = daoM.findCourseByMaster();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put("删除成功", list.toString());//
					// if (pw == null)
					// pw = response.getWriter();// 得到printWriter
					PrintWriter pw = response.getWriter();
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				}
			}
		} else {

		}

	}

}
