package jp.co.sss.crud.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.crud.bean.Dept;
import jp.co.sss.crud.bean.Emp;
import jp.co.sss.crud.dao.DeptDao;

/**
 * Servlet implementation class BackToRegist
 */
@WebServlet("/BackToRegist")
public class BackToRegist extends HttpServlet {
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
			String pass = request.getParameter("empPass");
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String birth = request.getParameter("birthday");
			String auth = request.getParameter("authority");
			String deptId = request.getParameter("deptId");

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// DAOでdeptIdを検索検索
			Dept dept = DeptDao.findbyId(deptIdNum);

			// empオブジェクトの作成
			Emp emp = new Emp();

			// empに値をセット
			emp.setEmpPass(pass);
			emp.setEmpName(empName);
			emp.setGender(Integer.parseInt(gender));
			emp.setAddress(address);
			emp.setDate(birth);
			emp.setAuthority(Integer.parseInt(auth));
			emp.setDept(dept);

			// リクエストスコープにempを保管
			request.setAttribute("returnEmp", emp);

			request.getRequestDispatcher("/jsp/regist/regist_input2.jsp").forward(request, response);
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
			String pass = request.getParameter("empPass");
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String birth = request.getParameter("birthday");
			String auth = request.getParameter("authority");
			String deptId = request.getParameter("deptId");

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// DAOでdeptIdを検索検索
			Dept dept = DeptDao.findbyId(deptIdNum);

			// empオブジェクトの作成
			Emp emp = new Emp();

			// empに値をセット
			emp.setEmpPass(pass);
			emp.setEmpName(empName);
			emp.setGender(Integer.parseInt(gender));
			emp.setAddress(address);
			emp.setDate(birth);
			emp.setAuthority(Integer.parseInt(auth));
			emp.setDept(dept);

			// リクエストスコープにempを保管
			request.setAttribute("returnEmp", emp);

			request.getRequestDispatcher("/jsp/regist/regist_input2.jsp").forward(request, response);
		}
	}

}
