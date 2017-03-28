package com.bc.stdsys.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.bc.stdsys.util.DButil;

public class FindServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj instanceof Teacher) {
			Teacher teacher = (Teacher) obj;
			TeacherDao dao = new TeacherDaoImpl();
			List<String> list = dao.findMyclassByTeacher(teacher);
			session.setAttribute("myClass", list);//向session中放入班级列表
//			Map<String, ArrayList<Student>> map = dao.findStudentByTeacher(list);
//			Set<String> kSet = map.keySet();
//			Iterator<String> it = kSet.iterator();
//			while (it.hasNext()) {
//				String key = it.next();
//				ArrayList<Student> listA = map.get(key);
//				for (int i = 0; i < listA.size(); i++) {
//					System.out.println(key + ": " + listA.get(i).getName());
//				}
//			}
			request.getRequestDispatcher("main/teacher.jsp").forward(request, response);

		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
