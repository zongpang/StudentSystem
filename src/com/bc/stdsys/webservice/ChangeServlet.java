package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.Localutil;

import net.sf.json.JSONObject;

public class ChangeServlet extends HttpServlet {
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
				if (myType == 1) {// 修改学生历史成绩（当月）
					String pj = request.getParameter("pj");
					String wr = request.getParameter("wr");
					String cp = request.getParameter("cp");
					String no = request.getParameter("no");
					String teacherSpeak = request.getParameter("ts");
					String date = request.getParameter("date");
					try {
						double faceToFace = Double.parseDouble(pj);// 答辩
						double write = Double.parseDouble(wr);// 笔试
						double computer = Double.parseDouble(cp);// 机试
						double average = Localutil.average(faceToFace, write, computer);
						int studentNo = Integer.parseInt(no);
						if (daoT == null)
							daoT = new TeacherDaoImpl();// 实例化teacherDao
						daoT.updateStudentHistoryScore(studentNo, faceToFace, write, computer, teacherSpeak, date,
								average);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(no, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(no, "输入格式有误！");// 修改失败
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				}
			}

		} else if (obj instanceof ClassWorker) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 修改学生历史成绩（当月）
					String pj = request.getParameter("pj");
					String wr = request.getParameter("wr");
					String cp = request.getParameter("cp");
					String no = request.getParameter("no");
					String teacherSpeak = request.getParameter("ts");
					String date = request.getParameter("date");
					try {
						double faceToFace = Double.parseDouble(pj);// 答辩
						double write = Double.parseDouble(wr);// 笔试
						double computer = Double.parseDouble(cp);// 机试
						double average = Localutil.average(faceToFace, write, computer);
						int studentNo = Integer.parseInt(no);
						if (daoC == null)
							daoC = new ClassWorkerDaoImpl();// 实例化teacherDao
						daoC.updateStudentHistoryScore(studentNo, faceToFace, write, computer, teacherSpeak, date,
								average);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(no, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} catch (Exception e) {
						e.printStackTrace();
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(no, "输入格式有误！");// 修改失败
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				}
			}

		} else if (obj instanceof Master) {

		} else {

		}

	}

}
