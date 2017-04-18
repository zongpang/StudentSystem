package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.daoimpl.TeacherDaoImpl;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.Localutil;

import net.sf.json.JSONObject;

public class FindServlet extends HttpServlet {
	JSONObject json;// 创建JO对象
	HttpSession session;
	TeacherDao daoT;
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
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆
			Teacher teacher = (Teacher) obj;
			if (teacherFlag) {// 首次登陆数据初始化
				daoT = new TeacherDaoImpl();
				List<String> list = daoT.findMyclassByTeacher(teacher);// 查出所教班级的名称
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/teacher.jsp");// 重定向到main/teacher.jsp
			}
			// 处理ajax请求
			String type = request.getParameter("type");// 表示查询类型
			String del = request.getParameter("del");// 接收删除后转发请求(删除后重新分页查询)
			String add = request.getParameter("add");// 接收添加后转发请求(添加后重新分页查询)
			if (type != null || del != null || add != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == null) {
					myType = Integer.parseInt(del);
				}else if (myType==null&&del==null) {
					myType = Integer.parseInt(add);
				}
				if (myType == 1) {// 作学生分页查询
					String classNo = request.getParameter("classNo");// 得到班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoT == null)
						daoT = new TeacherDaoImpl();// 实例化teacherDao
					ArrayList<Student> students = daoT.findStudentByTeacher(classNo);// 得到该班级全体学生的集合
					int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
					int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
					if (pageNowOn > totalP) // 如果当前页大于总页数
						pageNowOn = totalP;// 设为最后一页
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(totalP, stu);// 放入数据(以总页数为key)
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				} else if (myType == 2) {// 作课程查询
					List<Course> myCourse = daoT.findCourseByTeacher(teacher);// 查出所有课程
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(teacher.getName(), myCourse);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 3) {// 作学生详情展示
					String stdNum = request.getParameter("stdN");
					ArrayList<Score> historyScore = (ArrayList<Score>) daoT.findScoreByStudentNum(stdNum);
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(stdNum, historyScore);
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				}
			}

		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
