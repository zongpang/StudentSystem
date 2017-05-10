package com.bc.stdsys.webservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.dao.ClassWorkerDao;
import com.bc.stdsys.dao.MasterDao;
import com.bc.stdsys.dao.TeacherDao;
import com.bc.stdsys.daoimpl.ClassWorkerDaoImpl;
import com.bc.stdsys.daoimpl.MasterDaoImpl;
import com.bc.stdsys.daoimpl.TeacherDaoImpl;
import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Course;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Student;
import com.bc.stdsys.entitys.Teacher;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	JSONObject json;// 创建JO对象
	HttpSession session;
	TeacherDao daoT;
	ClassWorkerDao daoC;
	MasterDao daoM;
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
			String type = request.getParameter("type");// 表示请求类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 为当前班级添加学生
					String classNo = request.getParameter("classNo");// 得到班级号
					// String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdName = request.getParameter("stdName");// 得到学生姓名
					String stdNum = request.getParameter("stdN");// 得到学号
					if (daoT == null)
						daoT = new TeacherDaoImpl();// 实例化teacherDao
					Student std = daoT.addStudentByNameAndNum(stdName, stdNum);
					if (std != null) {// 如果存在该学生则将其修改为当前班级，并做转发分页查询
						daoT.addStudentInMyClass(classNo, std);
						request.setAttribute("stdNum", classNo);
						request.setAttribute("pageNow", 1);// 添加后显示第一页
						request.setAttribute("add", "1");
						request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询
					} else {// 打印该生不存在
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(stdName, "该生不存在");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				}
			}

		} else if (obj instanceof ClassWorker) {
			String type = request.getParameter("type");// 表示查询类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 为当前班级添加学生
					String classNo = request.getParameter("classNo");// 得到班级号
					// String pageNow = request.getParameter("pageNow");// 得到当前页
					String stdName = request.getParameter("stdName");// 得到学生姓名
					String stdNum = request.getParameter("stdN");// 得到学号
					if (daoC == null)
						daoC = new ClassWorkerDaoImpl();// 实例化teacherDao
					Student std = daoC.addStudentByNameAndNum(stdName, stdNum);
					if (std != null) {// 如果存在该学生则将其修改为当前班级，并做转发分页查询
						daoC.addStudentInMyClass(classNo, std);
						request.setAttribute("stdNum", classNo);
						request.setAttribute("pageNow", 1);// 添加后显示第一页
						request.setAttribute("add", "1");
						request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询
					} else {// 打印该生不存在
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(stdName, "该生不存在");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				}
			}
		} else if (obj instanceof Master) {
			String type = request.getParameter("type");// 表示请求类型
			if (type != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == 1) {// 为当前班级添加学生
					String classNo = request.getParameter("classNo");// 得到班级号
					String stdName = request.getParameter("stdName");// 得到学生姓名
					String stdNum = request.getParameter("stdN");// 得到学号
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化teacherDao
					Student std = daoM.addStudentByNameAndNum(stdName, stdNum);// 根据姓名学号找出该生(注意：方法命名改正)
					if (std != null) {// 如果存在该学生则将其修改为当前班级，并做转发分页查询
						daoM.addStudentInMyClass(classNo, std);// 在页面当前班级添加一个学生
						request.setAttribute("stdNum", classNo);
						request.setAttribute("pageNow", 1);// 添加后显示第一页
						request.setAttribute("add", "1");
						request.getRequestDispatcher("/find").forward(request, response);// 删除后交给/find作分页查询
					} else {// 打印该生不存在
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(stdName, "该生不存在");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				} else if (myType == 2) {
					String name = request.getParameter("name");
					String num = request.getParameter("num");
					if (num == null) {
						num = "-1";
					}
					int num_new = Integer.parseInt(num);
					String sex = request.getParameter("sex");
					String myClass = request.getParameter("myClass");
					String joinDate = request.getParameter("join");
					String address = request.getParameter("address");
					String phone = request.getParameter("phone");
					String parentPhone = request.getParameter("parentPhone");

					Student stu = new Student();// 封装对象
					stu.setName(name);
					stu.setNum(num_new);
					stu.setSex(sex);
					stu.setMyClass(myClass);
					stu.setJoinDate(joinDate);
					;
					stu.setAddress(address);
					stu.setPhone(phone);
					stu.setParentPhone(parentPhone);
					if (daoM == null)
						daoM = new MasterDaoImpl();
					boolean a = daoM.findStudentInSchool(stu);
					if (a) {
						daoM.addNewStudentInSchool(stu);
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(name, "添加成功");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(name, "该生已存在！");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				} else if (myType == 3) {
					String myCourse = request.getParameter("myCourse");
					String myClass = request.getParameter("myClass");
					String myTeacher = request.getParameter("myTeacher");
					String myDate = request.getParameter("myDate");
					Course c = new Course();
					c.setName(myCourse);
					c.setMyClass(myClass);
					c.setTeacher(myTeacher);
					c.setDate(myDate);
					if (daoM == null)
						daoM = new MasterDaoImpl();
					boolean a = daoM.findClassInCourse(c);

					if (a) {
						daoM.addNewCourse(c);
						List<Course> list = daoM.findCourseByMaster();
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加成功", list);//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						// System.out.println(json.toString());
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加失败", "添加失败");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}
				} else if (myType == 4) {// 添加一名教师
					String name = request.getParameter("teaName");
					String num = request.getParameter("teaNum");
					String passWord = request.getParameter("teaPassWord");
					String phone = request.getParameter("teaPhone");
					String address = request.getParameter("teaAddress");
					Teacher t = new Teacher();
					t.setName(name);
					if (num == null){
						num = "-1";
					}
					t.setNum(Integer.parseInt(num));//
					t.setPassWord(passWord);
					t.setPhone(phone);
					t.setaDdress(address);
					if (daoM == null)
						daoM = new MasterDaoImpl();
					boolean a = daoM.findTeacherByName(t);
					if (a) {
						daoM.addNewTeacher(t);
						List<Teacher> list = daoM.findAllTeacher();
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加成功", list);//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加失败", "添加失败");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}

				} else if (myType == 5) {// 添加一名班主任
					String name = request.getParameter("cwName");
					String num = request.getParameter("cwNum");
					String passWord = request.getParameter("cwPassWord");
					String phone = request.getParameter("cwPhone");
					String address = request.getParameter("cwAddress");
					ClassWorker c = new ClassWorker();
					c.setName(name);
					if (num == null){
						num = "-1";
					}
					c.setNum(Integer.parseInt(num));
					c.setPassWord(passWord);
					c.setPhone(phone);
					c.setaDdress(address);
					if (daoM == null)
						daoM = new MasterDaoImpl();
					boolean a = daoM.findClassWorkerByName(c);
					if (a) {
						daoM.addNewClassWorker(c);
						List<ClassWorker> list = daoM.findAllClassWorker();
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加成功", list);//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put("添加失败", "添加失败");//
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					}

				}
			}
		} else {

		}

	}

}
