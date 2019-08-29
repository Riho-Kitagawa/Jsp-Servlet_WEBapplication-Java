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
@WebServlet("/SelectByDeptUser")
public class SelectByDeptUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("loginName") == null) { // emp情報を格納したオブジェクト"loginName"を取得できなければ
			response.sendRedirect("/servlet_crud/jsp/index.jsp"); // ログインページにリダイレクト
		} else {

			//deptIdを取得
			String deptId = request.getParameter("deptId");

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// deptIdで検索
			List<Emp> empList = EmpDao.findByDeptName(deptIdNum);

			if (empList.size() != 0) {
				// empListが0じゃなかったら遷移
				request.setAttribute("empList", empList);
				request.getRequestDispatcher("/jsp/Select/select_by_dept_user.jsp").forward(request, response);

			} else {
				// empListが0だったら該当なしページへ遷移
				request.getRequestDispatcher("/jsp/list/list_nomatch_user.jsp").forward(request, response);
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

			//deptIdを取得
			String deptId = request.getParameter("deptId");

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// deptIdで検索
			List<Emp> empList = EmpDao.findByDeptName(deptIdNum);

			if (empList.size() != 0) {
				// empListが0じゃなかったら遷移
				request.setAttribute("empList", empList);
				request.getRequestDispatcher("/jsp/Select/select_by_dept_user.jsp").forward(request, response);

			} else {
				// empListが0だったら該当なしページへ遷移
				request.getRequestDispatcher("/jsp/list/list_nomatch_user.jsp").forward(request, response);
			}
		}
	}

}
