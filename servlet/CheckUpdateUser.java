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
import jp.co.sss.crud.util.InputChecker;

/**
 * Servlet implementation class CheckUpdate
 */
@WebServlet("/CheckUpdateUser")
public class CheckUpdateUser extends HttpServlet {
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

			// パラメータから取得
			String empId = request.getParameter("empId");
			String pass = request.getParameter("empPass");
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String birth = request.getParameter("birthday");
			// String auth = request.getParameter("authority");
			String deptId = request.getParameter("deptId");

			if (pass == "" || empName == "" || gender == null || address == "" || birth == "") {
				request.setAttribute("error", "未入力の項目があります。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (pass == "") {
				request.setAttribute("error", "パスワードを入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (pass.length() >= 16 || pass.length() < 0) {
				request.setAttribute("error", "パスワードは1文字以上16文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (empName == "") {
				request.setAttribute("error", "社員名を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (empName.length() >= 30 || pass.length() < 0) {
				request.setAttribute("error", "社員名は1文字以上30文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (address == "") {
				request.setAttribute("error", "住所を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (address.length() >= 60 || pass.length() < 0) {
				request.setAttribute("error", "社員名は1文字以上60文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (InputChecker.checkDate(birth)) {
				request.setAttribute("error", "正しい日付を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);
			}

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// DAOでdeptIdを検索検索
			Dept dept = DeptDao.findbyId(deptIdNum);

			// empオブジェクトの作成
			Emp emp = new Emp();

			// empに値をセット
			emp.setEmpId(Integer.parseInt(empId));
			emp.setEmpPass(pass);
			emp.setEmpName(empName);
			emp.setGender(Integer.parseInt(gender));
			emp.setAddress(address);
			emp.setDate(birth);
			// emp.setAuthority(Integer.parseInt(auth));
			emp.setDept(dept);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("/jsp/update/update_check_user.jsp").forward(request, response);
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
			String pass = request.getParameter("empPass");
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String birth = request.getParameter("birthday");
			// String auth = request.getParameter("authority");
			String deptId = request.getParameter("deptId");

			if (pass == "" || empName == "" || gender == null || address == "" || birth == "") {
				request.setAttribute("error", "未入力の項目があります。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (pass == "") {
				request.setAttribute("error", "パスワードを入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (pass.length() >= 16 || pass.length() < 0) {
				request.setAttribute("error", "パスワードは1文字以上16文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (empName == "") {
				request.setAttribute("error", "社員名を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (empName.length() >= 30 || pass.length() < 0) {
				request.setAttribute("error", "社員名は1文字以上30文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (address == "") {
				request.setAttribute("error", "住所を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (address.length() >= 60 || pass.length() < 0) {
				request.setAttribute("error", "社員名は1文字以上60文字以下で入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);

			} else if (InputChecker.checkDate(birth)) {
				request.setAttribute("error", "正しい日付を入力してください。");
				request.getRequestDispatcher("/jsp/regist/regist_input.jsp").forward(request, response);
			}

			// deptIdをintegerに変更
			int deptIdNum = Integer.parseInt(deptId);

			// DAOでdeptIdを検索検索
			Dept dept = DeptDao.findbyId(deptIdNum);

			// empオブジェクトの作成
			Emp emp = new Emp();

			// empに値をセット
			emp.setEmpId(Integer.parseInt(empId));
			emp.setEmpPass(pass);
			emp.setEmpName(empName);
			emp.setGender(Integer.parseInt(gender));
			emp.setAddress(address);
			emp.setDate(birth);
			// emp.setAuthority(Integer.parseInt(auth));
			emp.setDept(dept);

			// リクエストスコープにempをセット
			request.setAttribute("emp", emp);

			request.getRequestDispatcher("/jsp/update/update_check_user.jsp").forward(request, response);
		}
	}

}
