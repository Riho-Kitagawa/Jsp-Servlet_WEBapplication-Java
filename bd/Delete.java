package jp.co.sss.crud.bd;

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
 * Servlet implementation class Delete
 * 削除するサーブレット
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Emp empLog = (Emp) session.getAttribute("loginName");

		if (session.getAttribute("loginName") == null || empLog.getAuthority() == 1) { // emp情報を格納したオブジェクト"loginName"を取得できず、
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // また、権限が１のときはログインページにリダイレクト
		} else {
			// パラメータから取得
			String empId = request.getParameter("empId");

			//empIdを数値に変換
			int idNum = Integer.parseInt(empId);

			// empIdでEmpDaoのdeleteメソッド
			EmpDao.delete(idNum);

			request.getRequestDispatcher("/jsp/delete/delete_complete.jsp").forward(request, response);
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
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // また、権限が１のときはログインページにリダイレクト
		} else {
			// パラメータから取得
			String empId = request.getParameter("empId");

			//empIdを数値に変換
			int idNum = Integer.parseInt(empId);

			// empIdでEmpDaoのdeleteメソッド
			EmpDao.delete(idNum);

			request.getRequestDispatcher("/jsp/delete/delete_complete.jsp").forward(request, response);
		}
	}

}
