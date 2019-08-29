package jp.co.sss.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.crud.bean.Dept;
import jp.co.sss.crud.bean.Emp;

/**
 * @author Riho
 * EmpのDAOクラス
 *
 */
public class EmpDao {
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	private static Emp emp = null;

	/**
	 * 全件検索するメソッド
	 *
	 * @param id
	 * @param pass
	 * @return employee
	 */
	public static List<Emp> findAll() {
		List<Emp> empList = new ArrayList<Emp>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");
			// JDBCドライバのロード
			connection = DBManager.getConnection();

			String sql = "SELECT * from DEPARTMENT d INNER JOIN Employee e ON e.DEPT_ID = d.DEPT_ID order by e.emp_id asc";

			// SQL文を生成
			preparedStatement = connection.prepareStatement(sql);

			// SQLを実行
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				emp = new Emp();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setEmpPass(resultSet.getString("emp_pass"));
				emp.setEmpName(resultSet.getString("emp_name"));
				emp.setGender(resultSet.getInt("gender"));
				emp.setAddress(resultSet.getString("address"));

				//simple date formatクラスでYYYY/MM/DDにフォーマット
				String birthday = simpleDateFormat.format(resultSet.getDate("birthday"));
				emp.setDate(birthday);
				emp.setAuthority(resultSet.getInt("authority"));

				Dept dept = new Dept();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setDeptName(resultSet.getString("dept_name"));
				emp.setDept(dept);
				empList.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(preparedStatement, connection);
		}
		return empList;
	}

	/**
	 * 社員名で検索するメソッド
	 *
	 * @param empName
	 * @return empList
	 */
	public static List<Emp> findByName(String empName) {
		List<Emp> empList = new ArrayList<Emp>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");

			connection = DBManager.getConnection();

			String sql = "SELECT * from DEPARTMENT d INNER JOIN Employee e ON e.DEPT_ID = d.DEPT_ID where emp_name LIKE ?";

			preparedStatement = connection.prepareStatement(sql);

			// バインド
			preparedStatement.setString(1, "%" + empName + "%");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				emp = new Emp();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setEmpPass(resultSet.getString("emp_pass"));
				emp.setEmpName(resultSet.getString("emp_name"));
				emp.setGender(resultSet.getInt("gender"));
				emp.setAddress(resultSet.getString("address"));

				//simple date formatクラスでYYYY/MM/DDにフォーマット
				String birthday = simpleDateFormat.format(resultSet.getDate("birthday"));
				emp.setDate(birthday);

				emp.setAuthority(resultSet.getInt("authority"));

				Dept dept = new Dept();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setDeptName(resultSet.getString("dept_name"));
				emp.setDept(dept);
				empList.add(emp);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(preparedStatement, connection);
		}
		return empList;
	}

	/**
	 * 部署IDで検索するメソッド
	 *
	 * @param empName
	 * @return empList
	 */
	public static List<Emp> findByDeptName(Integer deptId) {
		List<Emp> empList = new ArrayList<Emp>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");

			connection = DBManager.getConnection();

			String sql = "SELECT * from DEPARTMENT d INNER JOIN Employee e ON d.DEPT_ID = e.DEPT_ID where d.DEPT_ID = ?";

			preparedStatement = connection.prepareStatement(sql);

			// バインド
			preparedStatement.setInt(1, deptId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				emp = new Emp();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setEmpPass(resultSet.getString("emp_pass"));
				emp.setEmpName(resultSet.getString("emp_name"));
				emp.setGender(resultSet.getInt("gender"));
				emp.setAddress(resultSet.getString("address"));

				//simple date formatクラスでYYYY/MM/DDにフォーマット
				String birthday = simpleDateFormat.format(resultSet.getDate("birthday"));
				emp.setDate(birthday);

				emp.setAuthority(resultSet.getInt("authority"));

				Dept dept = new Dept();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setDeptName(resultSet.getString("dept_name"));
				emp.setDept(dept);
				empList.add(emp);
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBManager.close(preparedStatement, connection);
		}
		return empList;
	}

	/**
	 * 社員IDで検索するメソッド
	 *
	 * @param empId
	 * @return emp
	 */
	public static Emp findById(Integer empId) {
		Emp emp = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd");

			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT * from DEPARTMENT d INNER JOIN Employee e ON e.DEPT_ID = d.DEPT_ID where e.emp_id=?");
			preparedStatement.setInt(1, empId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				emp = new Emp();
				emp.setEmpId(resultSet.getInt("emp_id"));
				emp.setEmpPass(resultSet.getString("emp_pass"));
				emp.setEmpName(resultSet.getString("emp_name"));
				emp.setGender(resultSet.getInt("gender"));
				emp.setAddress(resultSet.getString("address"));

				//simple date formatクラスでYYYY/MM/DDにフォーマット
				String birthday = simpleDateFormat.format(resultSet.getDate("birthday"));
				emp.setDate(birthday);

				emp.setAuthority(resultSet.getInt("authority"));

				Dept dept = new Dept();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setDeptName(resultSet.getString("dept_name"));
				emp.setDept(dept);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBManager.close(preparedStatement, connection);
		}
		return emp;
	}


	/**
	 * 登録するメソッド
	 *
	 * @param emp
	 */
	public static void insert(Emp emp) {

		try {
			connection = DBManager.getConnection();

			String sql = "INSERT INTO employee VALUES(seq_emp.nextval, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, emp.getEmpPass());
			preparedStatement.setString(2, emp.getEmpName());
			preparedStatement.setInt(3, emp.getGender());
			preparedStatement.setString(4, emp.getAddress());
			preparedStatement.setString(5, emp.getDate());
			preparedStatement.setInt(6, emp.getAuthority());
			preparedStatement.setInt(7, emp.getDept().getDeptId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			DBManager.close(preparedStatement, connection);
		}
	}

	/**
	 * 更新するメソッド
	 *
	 * @param emp
	 */
	public static void update(Emp emp) {

		try {
			connection = DBManager.getConnection();

			String sql = "UPDATE employee SET emp_pass =?, emp_name = ?, gender = ?, address = ?, birthday = ?, authority = ?, dept_id=? where emp_id =?";

			preparedStatement = connection.prepareStatement(sql);

			//empから値をバインド
			preparedStatement.setString(1, emp.getEmpPass());
			preparedStatement.setString(2, emp.getEmpName());
			preparedStatement.setInt(3, emp.getGender());
			preparedStatement.setString(4, emp.getAddress());
			preparedStatement.setString(5, emp.getDate());
			preparedStatement.setInt(6, emp.getAuthority());
			preparedStatement.setInt(7, emp.getDept().getDeptId());
			preparedStatement.setInt(8, emp.getEmpId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBManager.close(preparedStatement, connection);
		}
	}

	/**
	 * 削除するメソッド
	 *
	 * @param empId
	 */
	public static void delete(Integer empId) {

		try {
			connection = DBManager.getConnection();

			String sql = "DELETE from employee where emp_id = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, empId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(preparedStatement, connection);
		}
	}

	/**
	 * 一般ユーザで更新するメソッド
	 *
	 * @param emp
	 */
	public static void updateUser(Emp emp) {

		try {
			connection = DBManager.getConnection();

			String sql = "UPDATE employee SET emp_pass =?, emp_name = ?, gender = ?, address = ?, birthday = ?, dept_id=? where emp_id =?";

			preparedStatement = connection.prepareStatement(sql);

			//empから値をバインド
			preparedStatement.setString(1, emp.getEmpPass());
			preparedStatement.setString(2, emp.getEmpName());
			preparedStatement.setInt(3, emp.getGender());
			preparedStatement.setString(4, emp.getAddress());
			preparedStatement.setString(5, emp.getDate());
			preparedStatement.setInt(6, emp.getDept().getDeptId());
			preparedStatement.setInt(7, emp.getEmpId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBManager.close(preparedStatement, connection);
		}
	}


}
