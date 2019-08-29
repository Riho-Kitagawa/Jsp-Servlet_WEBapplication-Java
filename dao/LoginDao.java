package jp.co.sss.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Riho
 *ログイン用のDAOクラス
 */
public class LoginDao {
	public static Connection connection = null;
	public static ResultSet resultSet = null;
	public static PreparedStatement preparedStatement = null;

	/**
	 * IDで検索してauthorityを値を取得する
	 *
	 * @param empId
	 * @return emp
	 */
	public static int findByIdForAuth(Integer empId) {

		int emp = 0;
		try {

			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * from Employee where emp_id=?");
			preparedStatement.setInt(1, empId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				//カラムauthorityで探した結果をempにセット
				emp = resultSet.getInt("authority");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBManager.close(preparedStatement, connection);
		}
		return emp;
	}
}
