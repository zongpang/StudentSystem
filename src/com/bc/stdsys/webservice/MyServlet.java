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
		//2017-3-26
		boolean isLogin = DButil.login(userId, userName, passWord);
		if (isLogin) {
			HttpSession session = request.getSession();
			Object obj = null;
			if ("教师".equals(userId)) {
				obj = new Teacher(userName, passWord);
			} else if ("班主任".equals(userId)) {
				obj = new ClassWorker(userName, passWord);
			} else if ("管理员".equals(userId)) {
				obj = new Master(userName, passWord);
			} else if ("院长".equals(userId)) {
				obj = new Deanery(userName, passWord);
			}
			session.setAttribute("user", obj);
		} else {
			response.sendRedirect("login/Login.jsp");
		}

	}
}