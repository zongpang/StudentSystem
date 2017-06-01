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

public class FindServlet extends HttpServlet {

	HttpSession session;// session
	TeacherDao daoT;// 教师操作接口
	ClassWorkerDao daoC;// 班主任操作接口
	DeaneryDao daoD;// 院长操作接口
	MasterDao daoM;// 管理员操作接口
	final int PAGE_SIZE = 2;// 分页查找每页的容量
	JSONObject json;// 创建JO对象
	PrintWriter pw;// printerwriter
	ArrayList<Student> students;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj instanceof Teacher) {
			System.out.println("findteacher进来了");
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆
			Teacher teacher = (Teacher) obj;
			if (teacherFlag) {// 首次登陆数据初始化
				daoT = new TeacherDaoImpl();
				List<String> list = daoT.findMyclassByTeacher(teacher);// 查出所教班级的名称
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/teacher.jsp");// 重定向到main/teacher.jsp
			}
			// 处理ajax请求
			String type = request.getParameter("type");// 表示查询类型
			String del = (String) request.getAttribute("del");// 接收删除后转发请求(删除后重新分页查询)
			String add = (String) request.getAttribute("add");// 接收添加后转发请求(添加后重新分页查询)
			String upd = (String) request.getAttribute("update");// 接收修改后的转发请求(修改后返回新的学生历史信息)
			if (type != null || del != null || add != null || upd != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == null) {
					if (del != null) {
						myType = Integer.parseInt(del);
					} else if (add != null) {
						myType = Integer.parseInt(add);
					} else if (upd != null) {
						myType = Integer.parseInt(upd);
						System.out.println(upd);
					}
				}
				if (myType == 1) {// 作学生分页查询
					String classNo = request.getParameter("classNo");// 得到当前选中班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoT == null)
						daoT = new TeacherDaoImpl();// 实例化teacherDao
					students = daoT.findStudentByTeacher(classNo);// 得到该班级全体学生的集合
					int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
					int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
					if (pageNowOn > totalP) // 如果当前页大于总页数
						pageNowOn = totalP;// 设为最后一页
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(totalP, stu);// 放入数据(以总页数为key)
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					try {
						pw.close();
					} catch (Exception e) {
						pw = response.getWriter();
						pw.close();
					}
				} else if (myType == 2) {// 作课程查询
					List<Course> myCourse = daoT.findCourseByTeacher(teacher);// 查出所有课程
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(teacher.getName(), myCourse);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 3) {// 作学生详情展示
					String stdNum = request.getParameter("stdN");
					if (stdNum == null)
						stdNum = (String) request.getAttribute("stdNum");
					ArrayList<Score> historyScore = (ArrayList<Score>) daoT.findScoreByStudentNum(stdNum);
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(stdNum, historyScore);
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 4) {// 按钮分页
					String classNo = request.getParameter("classNo");// 得到当前选中班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoT == null)
						daoT = new TeacherDaoImpl();// 实例化teacherDao
					int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
					int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
					if (pageNowOn > totalP) // 如果当前页大于总页数
						pageNowOn = totalP;// 设为最后一页
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(totalP, stu);// 放入数据(以总页数为key)
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					try {
						pw.close();
					} catch (Exception e) {
						pw = response.getWriter();
						pw.close();
					}
				}
			}

		} else if (obj instanceof ClassWorker) {
			System.out.println("classworker 进来了");
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆
			ClassWorker classWorker = (ClassWorker) obj;
			if (teacherFlag) {// 首次登陆数据初始化
				daoC = new ClassWorkerDaoImpl();
				List<String> list = daoC.findMyclassByClassWorker(classWorker);// 查出所教班级的名称
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/classworker.jsp");// 重定向到main/teacher.jsp
			}
			// 处理ajax请求
			String type = request.getParameter("type");// 表示查询类型
			String del = (String) request.getAttribute("del");// 接收删除后转发请求(删除后重新分页查询)
			String add = (String) request.getAttribute("add");// 接收添加后转发请求(添加后重新分页查询)
			// String upd = (String) request.getAttribute("update");//
			// 接收修改后的转发请求(修改后返回新的学生历史信息)
			if (type != null || del != null || add != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == null) {
					if (del != null) {
						myType = Integer.parseInt(del);
					} else if (add != null) {
						myType = Integer.parseInt(add);
					}
				}
				if (myType == 1) {// 作学生分页查询
					String classNo = request.getParameter("classNo");// 得到班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoC == null)
						daoC = new ClassWorkerDaoImpl();// 实例化teacherDao
					ArrayList<Student> students = daoC.findStudentByClassWorker(classNo);// 得到该班级全体学生的集合
					int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
					int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
					if (pageNowOn > totalP) // 如果当前页大于总页数
						pageNowOn = totalP;// 设为最后一页
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(totalP, stu);// 放入数据(以总页数为key)
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				} else if (myType == 2) {// 作课程查询
					List<Course> myCourse = daoC.findCourseByClassWorker(classWorker);// 查出所有课程
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(classWorker.getName(), myCourse);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 3) {// 作学生详情展示
					String stdNum = request.getParameter("stdN");
					if (stdNum == null)
						stdNum = (String) request.getAttribute("stdNum");
					ArrayList<Score> historyScore = (ArrayList<Score>) daoC.findScoreByStudentNum(stdNum);
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(stdNum, historyScore);
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				}
			}

		} else if (obj instanceof Deanery) {
			System.out.println("deanery进来了");
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆
			Deanery deanery = (Deanery) obj;
			if (teacherFlag) {// 首次登陆数据初始化
				daoD = new DeaneryDaoImpl();
				List<String> list = daoD.findMyclassByDeanery();// 查出所教班级的名称
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/deanery.jsp");// 重定向到main/Deanery.jsp
			}
			// 处理ajax请求
			String type = request.getParameter("type");// 表示查询类型
			String del = (String) request.getAttribute("del");// 接收删除后转发请求(删除后重新分页查询)
			String add = (String) request.getAttribute("add");// 接收添加后转发请求(添加后重新分页查询)
			String upd = (String) request.getAttribute("update");// 接收修改后的转发请求(修改后返回新的学生历史信息)
			if (type != null || del != null || add != null || upd != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == null) {
					if (del != null) {
						myType = Integer.parseInt(del);
					} else if (add != null) {
						myType = Integer.parseInt(add);
					} else if (upd != null) {
						myType = Integer.parseInt(upd);
						System.out.println(upd);
					}
				}
				if (myType == 1) {// 作学生分页查询
					String classNo = request.getParameter("classNo");// 得到班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoD == null)
						daoD = new DeaneryDaoImpl();// 实例化DeaneryDao
					ArrayList<Student> students = daoD.findStudentByDeanery(classNo);// 得到该班级全体学生的集合
					int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
					int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
					if (pageNowOn > totalP) // 如果当前页大于总页数
						pageNowOn = totalP;// 设为最后一页
					ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(totalP, stu);// 放入数据(以总页数为key)
					if (pw == null)
						pw = response.getWriter();// 得到printWriter
					pw.print(json.toString());// 以字符串的格式传给ajax
					pw.close();
				} else if (myType == 2) {// 作课程查询
					List<Course> myCourse = daoD.findCourseByDeanery();// 查出所有课程
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(deanery.getName(), myCourse);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 3) {// 作学生详情展示
					String stdNum = request.getParameter("stdN");
					if (stdNum == null)
						stdNum = (String) request.getAttribute("stdNum");
					ArrayList<Score> historyScore = (ArrayList<Score>) daoD.findScoreByStudentNum(stdNum);
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(stdNum, historyScore);
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 4) {// 查出所有教师
					List<Teacher> myTeacher = daoD.findAllTeacher();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(deanery.getName(), myTeacher);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();

				} else if (myType == 5) {// 查出所有班主任
					List<ClassWorker> myTeacher = daoD.findAllClassWorker();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(deanery.getName(), myTeacher);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				}
			}

		} else {
			System.out.println("find master进来了");
			boolean teacherFlag = (boolean) session.getAttribute("loginFirst");// 判断首次登陆
			Master master = (Master) obj;
			if (teacherFlag) {// 首次登陆数据初始化
				daoM = new MasterDaoImpl();
				List<String> list = daoM.findMyclassByMaster();// 查出所教班级的名称
				session.setAttribute("myClass", list);// 向session中放入班级
				session.setAttribute("loginFirst", false);// 首次登陆置否
				response.sendRedirect("main/master.jsp");// 重定向到main/Deanery.jsp
			}
			// 处理ajax请求
			String type = request.getParameter("type");// 表示查询类型
			String del = (String) request.getAttribute("del");// 接收删除后转发请求(删除后重新分页查询)
			String add = (String) request.getAttribute("add");// 接收添加后转发请求(添加后重新分页查询)
			String upd = (String) request.getAttribute("update");// 接收修改后的转发请求(修改后返回新的学生历史信息)
			if (type != null || del != null || add != null || upd != null) {
				Integer myType = Integer.parseInt(type);
				if (myType == null) {
					if (del != null) {
						myType = Integer.parseInt(del);
					} else if (add != null) {
						myType = Integer.parseInt(add);
					} else if (upd != null) {
						myType = Integer.parseInt(upd);
						System.out.println(upd);
					}
				}
				if (myType == 1) {// 作学生分页查询
					String classNo = request.getParameter("classNo");// 得到当前班级号班级号
					if (classNo == null)
						classNo = (String) request.getAttribute("classNo");
					String pageNow = request.getParameter("pageNow");// 得到当前页
					if (pageNow == null)
						pageNow = request.getParameter("pageNow");
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao

					if (classNo.equals("allStudent")) {// 查询所有学生
						ArrayList<Student> students = daoM.findAllStudentByMaster();// 得到该班级全体学生的集合
						int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
						int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
						if (pageNowOn > totalP) // 如果当前页大于总页数
							pageNowOn = totalP;// 设为最后一页
						ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(totalP, stu);// 放入数据(以总页数为key)
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();
					} else {// 按班级查询学生
						ArrayList<Student> students = daoM.findStudentByMaster(classNo);// 得到该班级全体学生的集合
						int totalP = Localutil.totalPage(students.size(), PAGE_SIZE);// 总页数
						int pageNowOn = Integer.parseInt(pageNow);// 得到当前页
						if (pageNowOn > totalP) // 如果当前页大于总页数
							pageNowOn = totalP;// 设为最后一页
						ArrayList<Student> stu = Localutil.findStudentByPage(students, PAGE_SIZE, pageNowOn);// （学生集合，每页条数，当前页）得到分页后每页的集合
						if (json == null)
							json = new JSONObject();
						json.clear();
						json.put(totalP, stu);// 放入数据(以总页数为key)
						if (pw == null)
							pw = response.getWriter();// 得到printWriter
						pw.print(json.toString());// 以字符串的格式传给ajax
						pw.close();

					}
				} else if (myType == 2) {// 作课程查询
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					List<Course> myCourse = daoM.findCourseByMaster();// 查出所有课程
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(master.getName(), myCourse);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 3) {// 作学生详情展示
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					String stdNum = request.getParameter("stdN");
					if (stdNum == null)
						stdNum = (String) request.getAttribute("stdNum");
					ArrayList<Score> historyScore = (ArrayList<Score>) daoM.findScoreByStudentNum(stdNum);
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(stdNum, historyScore);
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 4) {// 查出所有教师
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					List<Teacher> myTeacher = daoM.findAllTeacher();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(master.getName(), myTeacher);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();

				} else if (myType == 5) {// 查出所有班主任
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					List<ClassWorker> myTeacher = daoM.findAllClassWorker();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(master.getName(), myTeacher);// 以教师的姓名或工号作为返回key
					if (pw == null)
						pw = response.getWriter();
					pw.print(json.toString());
					pw.close();

				} else if (myType == 6) {
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					List<Course> list = daoM.findCourseByMaster();
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put(master.getName(), list);// 以教师的姓名或工号作为返回key
					// if (pw == null)
					// pw = response.getWriter();
					PrintWriter pw = response.getWriter();
					pw.print(json.toString());
					pw.close();
				} else if (myType == 7) {
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化Dao
					List<String> list = daoM.findMyclassByMaster();// 查出所教班级的名称
					session.setAttribute("myClass", list);// 向session中放入班级
					if (json == null)
						json = new JSONObject();
					json.clear();
					json.put("返回班级名称", list);
					pw.print(json.toString());
					pw.close();
				} else if (myType == 8) {
					String name = request.getParameter("name");
					if (daoM == null)
						daoM = new MasterDaoImpl();// 实例化DeaneryDao
					List<Student> students = daoM.findstudentByName(name);
					if (students.size()==0) {
						if (json == null)
							json = new JSONObject();
						if (pw==null) {
							pw=response.getWriter();
						}
						json.clear();
						json.put("查询失败", "没有该生信息");
						pw.print(json.toString());
						pw.close();
					}else{
						if (json == null)
							json = new JSONObject();
						if (pw==null) {
							pw=response.getWriter();
						}
						json.clear();
						json.put("查询成功", students);
						pw.print(json.toString());
						pw.close();
					}
				}
			}
		}
	}
}
