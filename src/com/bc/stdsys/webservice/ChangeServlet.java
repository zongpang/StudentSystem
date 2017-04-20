package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Teacher;

import net.sf.json.JSONObject;

public class ChangeServlet extends HttpServlet {
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
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 修改学生历史成绩（当月）
					
						String pj = request.getParameter("pj");
						String wr = request.getParameter("wr");
						String cp = request.getParameter("cp");
						String no = request.getParameter("no");
						String teacherSpeak = request.getParameter("ts");
						try {
						double faceToFace = Double.parseDouble(pj);
						double write = Double.parseDouble(wr);
						double computer = Double.parseDouble(cp);
						int studentNo = Integer.parseInt(no);
						if (daoT == null)
							daoT = new TeacherDaoImpl();// 实例化teacherDao
						daoT.updateStudentHistoryScore(studentNo, faceToFace, write, computer, teacherSpeak);
						request.setAttribute("stdNum", no);
						request.setAttribute("update", "3");//作详情查询
						request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询						
					} catch (Exception e) {
						e.printStackTrace();
					}

				
				}
			}

		} else if (obj instanceof ClassWorker) {

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
