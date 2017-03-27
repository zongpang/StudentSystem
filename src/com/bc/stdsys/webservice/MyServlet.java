package com.bc.stdsys.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Deanery;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.DButil;

public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("passWord");
		String userId = request.getParameter("user");
		System.out.println(userId + "   " + userName + "    " + passWord);
		// 2017-3-26
		Object obj = null;
		try {
			obj = DButil.login(userId, userName, passWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if (obj instanceof Teacher) {
			session.setAttribute("user", obj);
			request.getRequestDispatcher("main/teacher.jsp").forward(request, response);
		} else if (obj instanceof ClassWorker) {
			session.setAttribute("user", obj);
			request.getRequestDispatcher("main/classworker.jsp").forward(request, response);
		} else if (obj instanceof Master) {
			session.setAttribute("user", obj);
			request.getRequestDispatcher("main/master.jsp").forward(request, response);
		} else if (obj instanceof Deanery) {
			session.setAttribute("user", obj);
			request.getRequestDispatcher("main/deanery.jsp").forward(request, response);
		} else {
			response.sendRedirect("login/Login.jsp");
		}
	}
}