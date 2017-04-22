package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.dao.ClassWorkerDao;
import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.daoimpl.ClassWorkerDaoImpl;
import com.bc.stdsys.daoimpl.TeacherDaoImpl;
import com.bc.stdsys.entitys.ClassWorker;

import com.bc.stdsys.entitys.Master;

import com.bc.stdsys.entitys.Teacher;

import net.sf.json.JSONObject;

public class DelServlet extends HttpServlet {
	JSONObject json;// 创建JO对象
	HttpSession session;
	TeacherDao daoT;
	ClassWorkerDao daoC;
	final int PAGE_SIZE = 2;// 分页查找每页的容量
	PrintWriter pw;

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

		} else {

		}

	}

}
