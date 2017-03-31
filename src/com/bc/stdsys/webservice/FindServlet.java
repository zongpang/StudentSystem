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
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.Localutil;

import net.sf.json.JSONObject;

public class FindServlet extends HttpServlet {
	JSONObject json;// 创建JO对象
	HttpSession session;
	TeacherDao dao;
	final int PAGE_SIZE = 3;

	// boolean teacherFlag = true;// 判断首次登陆
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("findServlet进来了");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		Object obj = session.getAttribute("user");
		// Object obj = request.getAttribute("user");
		if (obj instanceof Teacher) {
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆

			if (teacherFlag) {// 首次登陆数据初始化
				// System.out.println(obj.toString());
				Teacher teacher = (Teacher) obj;
				dao = new TeacherDaoImpl();
				List<String> list = dao.findMyclassByTeacher(teacher);
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/teacher.jsp");
			}
			// 分页查询代码段
			String classNo = request.getParameter("classNo");
			String pageNow = request.getParameter("pageNow");
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 作分页查询
					if (dao == null)
						dao = new TeacherDaoImpl();
					ArrayList<Student> students = dao.findStudentByTeacher(classNo);// 得到该班级全体学生的集合
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE,
							Integer.parseInt(pageNow));// （学生集合，每页条数，当前页）得到分页后每页的集合
					// session.setAttribute("myStudentC", students);//班级所有学生的集合
					// session.setAttribute("myStudentP", stu);//每页展示学生的集合
					// Integer totalP=Localutil.totalPage(students.size(),
					// PAGE_SIZE);//总页数
					json = new JSONObject();
					json.put(classNo, stu);// 放入数据(以总页数为key)
					System.out.println(json.toString());
					PrintWriter pw = response.getWriter();
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				}
			}

		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
