package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.dao.ClassWorkerDao;
import com.bc.stdsys.dao.DeaneryDao;
import com.bc.stdsys.dao.MasterDao;
import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.daoimpl.ClassWorkerDaoImpl;
import com.bc.stdsys.daoimpl.DeaneryDaoImpl;
import com.bc.stdsys.daoimpl.MasterDaoImpl;
import com.bc.stdsys.daoimpl.TeacherDaoImpl;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Deanery;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Score;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.Localutil;

import net.sf.json.JSONObject;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class ChangeServlet extends HttpServlet {
	JSONObject json;// 创建JO对象
	HttpSession session;
	TeacherDao daoT;
	ClassWorkerDao daoC;
	MasterDao daoM;
	DeaneryDao daoD;
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
		if (daoT == null) {
			daoT = new TeacherDaoImpl();// 实例化teacherDao
		}
		if (daoC == null) {
			daoC = new ClassWorkerDaoImpl();// 实例化teacherDao
		}
		if (daoM == null) {
			daoM = new MasterDaoImpl();
		}
		if (daoD == null) {
			daoD = new DeaneryDaoImpl();
		}
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
				} else if (myType == 2) {
					String new_p = request.getParameter("new_p");
					String old_p = request.getParameter("old_p");
					String userName = ((Teacher) obj).getName();
					String passWord = ((Teacher) obj).getPassWord();
					if (old_p.equals(passWord)) {// 旧密码输入正确
						daoT.changeUserPassWord(userName, new_p);
						((Teacher) obj).setPassWord(new_p);
						session.setAttribute("user", obj);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改失败！密码不正确！");// 修改成功
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
						// double faceToFace = Double.parseDouble(pj);// 答辩
						// double write = Double.parseDouble(wr);// 笔试
						// double computer = Double.parseDouble(cp);// 机试
						// double average = Localutil.average(faceToFace, write,
						// computer);
						int studentNo = Integer.parseInt(no);
						daoC.updateStudentHistoryScore(studentNo, teacherSpeak, date);
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
				} else if (myType == 2) {
					String new_p = request.getParameter("new_p");
					String old_p = request.getParameter("old_p");
					String userName = ((ClassWorker) obj).getName();
					String passWord = ((ClassWorker) obj).getPassWord();
					if (old_p.equals(passWord)) {// 旧密码输入正确
						daoC.changeUserPassWord(userName, new_p);
						((ClassWorker) obj).setPassWord(new_p);
						session.setAttribute("user", obj);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改失败！密码不正确！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}

				}
			}

		} else if (obj instanceof Master) {// 详细作修改操作
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
						daoM.updateStudentHistoryScore(studentNo, faceToFace, write, computer, teacherSpeak, date,
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
				} else if (myType == 2) {// 修改密码
					String new_p = request.getParameter("new_p");
					String old_p = request.getParameter("old_p");
					String userName = ((Master) obj).getName();
					String passWord = ((Master) obj).getPassWord();
					if (old_p.equals(passWord)) {// 旧密码输入正确
						daoM.changeUserPassWord(userName, new_p);
						((Master) obj).setPassWord(new_p);
						session.setAttribute("user", obj);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改失败！密码不正确！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();

					}

				} else if (myType == 3) {// 作修改某一课程
					String myClass = request.getParameter("myClass");
					String course = request.getParameter("course");
					String date = request.getParameter("date");
					daoM.changeCourse(myClass,course,date);//修改课程
					List<Course> list=daoM.findCourseByMaster();//查出
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put("修改成功", list);// 修改成功
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				}else if (myType==4) {//师资分配
					String myClass = request.getParameter("myClass");
					String teacher=request.getParameter("teacher");
					String classWorker=request.getParameter("classWorker");
					daoM.changeTeacherAndClassWorkerInCourseAndMyclass(myClass,teacher,classWorker);
					List<Course> list=daoM.findCourseByMaster();//查出
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put("修改成功", list);// 修改成功
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				}
			}

		} else if (obj instanceof Deanery) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {// 院长修改密码
				Integer myType = Integer.parseInt(type);
				if (myType == 2) {
					String new_p = request.getParameter("new_p");
					String old_p = request.getParameter("old_p");
					String userName = ((Deanery) obj).getName();
					String passWord = ((Deanery) obj).getPassWord();
					if (old_p.equals(passWord)) {// 旧密码输入正确
						daoD.changeUserPassWord(userName, new_p);
						((Deanery) obj).setPassWord(new_p);
						session.setAttribute("user", obj);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改成功！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(userName, "修改失败！密码不正确！");// 修改成功
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				}
			}

		}

	}

}
