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
import jp.co.sss.crud.dao.LoginDao;

/**
 * Servlet implementation class ToUpdate
 */
@WebServlet("/ToUpdate")
public class ToUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginName") == null) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // ログインページにリダイレクト
		} else {

			// パラメータから取得
			String empId = request.getParameter("empId");
			int empNum = Integer.parseInt(empId);

			// empIdでID検索
			Emp emp = EmpDao.findById(empNum);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			// empにidNumで探した権限１を代入
			int empNum2 = LoginDao.findByIdForAuth(empNum);

			// empが１だったら一般ユーザ画面に遷移
			if (empNum2 == 1) {
				request.getRequestDispatcher("/jsp/update/update_input_user.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/jsp/update/update_input.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginName") == null) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // ログインページにリダイレクト
		} else {
			// パラメータから取得
			String empId = request.getParameter("empId");
			int empNum = Integer.parseInt(empId);

			// empIdでID検索
			Emp emp = EmpDao.findById(empNum);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("/jsp/update/update_input.jsp").forward(request, response);
		}
	}

}
