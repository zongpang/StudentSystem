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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FindServlet extends HttpServlet {
	boolean flag = true;// 判断是否首次登陆
	JSONObject json = new JSONObject();// 创建JO对象
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
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj instanceof Teacher) {
			Teacher teacher = (Teacher) obj;
			TeacherDao dao = new TeacherDaoImpl();
			List<String> list = dao.findMyclassByTeacher(teacher);
			session.setAttribute("myClass", list);// 向session中放入班级列表
			// 分页
			String classNo = request.getParameter("classNo");
			String pageNow = request.getParameter("pageNow");
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 作分页查询
					ArrayList<Student> students = dao.findStudentByTeacher(classNo);// 得到该班级全体学生的集合
					ArrayList<Student> stu = Localutil.findStudentByPage(students, 3, Integer.parseInt(pageNow));// （学生集合，每页条数，当前页）得到分页后每页的集合
					session.setAttribute("myStudent", stu);
					// for (int i = 0; i < stu.size(); i++) {
					// System.out.println(stu.get(i).getName());
					// }
					json.put(classNo, stu);// 放入数据
					System.out.println(json.toString());
					PrintWriter pw = response.getWriter();
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				}
			}
			if (flag) {
				flag = false;
				request.getRequestDispatcher("main/teacher.jsp").forward(request, response);
			}

			// response.sendRedirect("main/teacher.jsp");

		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
