package com.bc.stdsys.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Teacher;

public class FindServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		// ystem.out.println("find出来了");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj instanceof Teacher) {

			
			
		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
