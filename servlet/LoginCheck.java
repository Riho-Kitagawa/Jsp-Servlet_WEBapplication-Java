package jp.co.sss.crud.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.crud.bean.Emp;
import jp.co.sss.crud.dao.DBManager;
import jp.co.sss.crud.dao.EmpDao;
import jp.co.sss.crud.dao.LoginDao;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// PrintWriter out = response.getWriter();

		response.setContentType("text/html; charset=UTF-8");

		// パラメータからの取得
		String id = request.getParameter("empId");
		String pass = request.getParameter("empPass");

		try {
			if (id == "" && pass == "") {
				request.setAttribute("error", "社員ID、またはパスワードを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (id == "") {
				request.setAttribute("error", "社員IDを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (pass == "") {
				request.setAttribute("error", "パスワードを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (id.length() > 5) {
				request.setAttribute("error", "社員IDは99999までの整数値で入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (pass.length() > 17) {
				request.setAttribute("error", "パスワードは16文字以内で入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			}

			// 入力値をString型からint型に変換
			int idNum = Integer.parseInt(id);

		} catch (NumberFormatException e) {

			// int型への変換に失敗した場合は判定エラーと判定
			request.setAttribute("error", "IDは5文字以内の整数値で入力してください。");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}

		int idNum = Integer.parseInt(id);

		try {
			// DBへ接続
			connection = DBManager.getConnection();
			// SQL文を作成
			preparedStatement = connection
					.prepareStatement("SELECT emp_id, emp_pass from EMPLOYEE where emp_id = ? AND emp_pass =?");

			// バインド
			preparedStatement.setInt(1, idNum);
			preparedStatement.setString(2, pass);

			// 実行
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				HttpSession session = request.getSession();
				Emp empList = EmpDao.findById(idNum);

				session.setAttribute("loginName", empList);

				// empにidNumで探した権限１を代入
				int emp = LoginDao.findByIdForAuth(idNum);

				// empが１だったら一般ユーザ画面に遷移
				if (emp == 1) {
					request.getRequestDispatcher("/SelectAll2").forward(request, response);
				} else {
					request.getRequestDispatcher("/SelectAll").forward(request, response);
				}
			} else {
				request.setAttribute("error", "社員ID、またはパスワードが間違っています。");

				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		// PrintWriter out = response.getWriter();

		response.setContentType("text/html; charset=UTF-8");

		// パラメータからの取得
		String id = request.getParameter("empId");
		String pass = request.getParameter("empPass");

		try {
			if (id == "" && pass == "") {
				request.setAttribute("error", "社員ID、またはパスワードを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (id == "") {
				request.setAttribute("error", "社員IDを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (pass == "") {
				request.setAttribute("error", "パスワードを入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (id.length() > 5) {
				request.setAttribute("error", "社員IDは99999までの整数値で入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			} else if (pass.length() > 17) {
				request.setAttribute("error", "パスワードは16文字以内で入力してください。");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			}

			// 入力値をString型からint型に変換
			int idNum = Integer.parseInt(id);

		} catch (NumberFormatException e) {

			// int型への変換に失敗した場合は判定エラーと判定
			request.setAttribute("error", "IDは5文字以内の整数値で入力してください。");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}

		int idNum = Integer.parseInt(id);

		try {
			// DBへ接続
			connection = DBManager.getConnection();
			// SQL文を作成
			preparedStatement = connection
					.prepareStatement("SELECT emp_id, emp_pass from EMPLOYEE where emp_id = ? AND emp_pass =?");

			// バインド
			preparedStatement.setInt(1, idNum);
			preparedStatement.setString(2, pass);

			// 実行
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				HttpSession session = request.getSession();
				Emp empList = EmpDao.findById(idNum);

				session.setAttribute("loginName", empList);

				// empにidNumで探した権限１を代入
				int emp = LoginDao.findByIdForAuth(idNum);

				// empが１だったら一般ユーザ画面に遷移
				if (emp == 1) {
					request.getRequestDispatcher("/SelectAll2").forward(request, response);
				} else {
					request.getRequestDispatcher("/SelectAll").forward(request, response);
				}
			} else {
				request.setAttribute("error", "社員ID、またはパスワードが間違っています。");

				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
