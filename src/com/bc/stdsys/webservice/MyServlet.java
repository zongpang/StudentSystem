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
		Object obj = null;
		try {
			obj = DButil.login(userId, userName, passWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if (obj != null && obj instanceof Teacher) {
			session.setAttribute("user", obj);
			session.setAttribute("loginFirst", true);
			request.getRequestDispatcher("find").forward(request, response);
		} else if (obj != null && obj instanceof ClassWorker) {
            
		} else if (obj != null && obj instanceof Master) {

		} else if (obj != null && obj instanceof Deanery) {

		}else {//重定向到登陆界面
			response.sendRedirect("login/Login.jsp");
		}
	}
}