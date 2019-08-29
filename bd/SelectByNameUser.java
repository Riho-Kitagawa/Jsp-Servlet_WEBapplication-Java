package jp.co.sss.crud.bd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.crud.bean.Emp;
import jp.co.sss.crud.dao.EmpDao;

/**
 * Servlet implementation class SelectByDept
 */
@WebServlet("/SelectByNameUser")
public class SelectByNameUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginName") == null) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // または権限が１のときはログインページにリダイレクト
		} else {

			// パラメータで取得
			String empName = request.getParameter("empName");

			// 何も入力されなかったら、SelectAllへ遷移
			if (empName == "") {
				request.getRequestDispatcher("/SelectAll2").forward(request, response);
			}

			// empListにempNameで探したデータを入れる
			List<Emp> empList = EmpDao.findByName(empName);

			if (empList.size() != 0) {

				// empListが0じゃなかったら遷移
				request.setAttribute("empList", empList);
				request.getRequestDispatcher("/jsp/Select/select_by_name_user.jsp").forward(request, response);

			} else {
				// empListが0だったら該当なしページへ遷移
				request.getRequestDispatcher("/jsp/list/list_nomatch.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginName") == null) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp");// または権限が１のときはログインページにリダイレクト
		} else {

			// パラメータで取得
			String empName = request.getParameter("empName");

			// 何も入力されなかったら、SelectAllへ遷移
			if (empName == "") {
				request.getRequestDispatcher("/SelectAll2").forward(request, response);
			}

			// empListにempNameで探したデータを入れる
			List<Emp> empList = EmpDao.findByName(empName);

			if (empList.size() != 0) {

				// empListが0じゃなかったら遷移
				request.setAttribute("empList", empList);
				request.getRequestDispatcher("/jsp/Select/select_by_name_user.jsp").forward(request, response);

			} else {
				// empListが0だったら該当なしページへ遷移
				request.getRequestDispatcher("/jsp/list/list_nomatch.jsp").forward(request, response);
			}
		}
	}

}
