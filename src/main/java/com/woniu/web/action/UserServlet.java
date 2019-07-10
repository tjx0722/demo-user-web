package com.woniu.web.action;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniu.dao.impl.UserDAO;
import com.woniu.po.PageBean;
import com.woniu.po.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDAO ud = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method==null) {
			findAll(request,response);
		}else if (method.equals("addUser")) {
			addUser(request,response);
		}else if (method.equals("delUser")) {
			delUser(request,response);
		}else if (method.equals("editForm")) {
			editForm(request,response);
		}else if (method.equals("editUser")) {
			editUser(request,response);
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			String uname = request.getParameter("uname");
			Double umoney = Double.parseDouble(request.getParameter("umoney"));
			String  str = request.getParameter("ubirth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date ubirth = sdf.parse(str);
			User user = new User();
			user.setUid(uid);
			user.setUname(uname);
			user.setUmoney(umoney);
			user.setUbirth(ubirth);
//			System.out.println(user);
			ud.update(user);
			response.sendRedirect("user.do");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid = Integer.parseInt(request.getParameter("uid"));
		User user = ud.findOne(uid);
//		System.out.println("-------"+uid);
		request.setAttribute("user", user);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	private void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int uid = Integer.parseInt(request.getParameter("uid"));
		ud.del(uid);
		response.sendRedirect("user.do");
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String uname = request.getParameter("uname");
			Double umoney = Double.parseDouble(request.getParameter("umoney"));
			String  str = request.getParameter("ubirth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date ubirth = sdf.parse(str);
			User user = new User();
			user.setUname(uname);
			user.setUmoney(umoney);
			user.setUbirth(ubirth);
			ud.add(user);
			response.sendRedirect("user.do");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PageBean pb = new PageBean();
		String pageNum = request.getParameter("pageNum");
		int pNum = 1; 
		if (pageNum!=null) {
			pNum = Integer.parseInt(pageNum);
		}
		pb.setPageNum(pNum);
		
		int countRow = ud.findAll().size();
		int countPage = countRow%pb.getPageRow()==0?countRow/pb.getPageRow():countRow/pb.getPageRow()+1;
		pb.setCountPage(countPage);
		pb.setCountRow(countRow);
		
		request.setAttribute("pb", pb);
		request.setAttribute("pageNum", pageNum);
		List<User> list = ud.findByPage(pb);
		request.setAttribute("userList", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
