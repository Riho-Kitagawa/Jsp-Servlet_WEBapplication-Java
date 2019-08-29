package jp.co.sss.crud.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.crud.bean.Emp;
import jp.co.sss.crud.dao.EmpDao;

/**
 * Servlet implementation class CheckDelete
 */
@WebServlet("/CheckDelete")
public class CheckDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Emp empLog = (Emp) session.getAttribute("loginName");

		if (session.getAttribute("loginName") == null || empLog.getAuthority() == 1) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // ログインページにリダイレクト
		} else {
			// パラメータから取得
			String empId = request.getParameter("empId");
			int empNum = Integer.parseInt(empId);

			// empIdでID検索
			Emp emp = EmpDao.findById(empNum);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("/jsp/delete/delete_check.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Emp empLog = (Emp) session.getAttribute("loginName");

		if (session.getAttribute("loginName") == null || empLog.getAuthority() == 1) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // ログインページにリダイレクト
		} else {
			// パラメータから取得
			String empId = request.getParameter("empId");
			int empNum = Integer.parseInt(empId);

			// empIdでID検索
			Emp emp = EmpDao.findById(empNum);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("/jsp/delete/delete_check.jsp").forward(request, response);
		}
	}
}
