package jp.co.sss.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Riho
 *	DBに接続するクラス
 */
public class DBManager {

	/** インスタンス化を禁止 */
	private DBManager(){}

	/**
	 * DBに切断する
	 * @return connection
	 * alt + shift+ J
	 */
	public static Connection getConnection(){
		try {
			// ドライバークラスのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// データベースに接続
			Connection connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "servlet_training_user",
					"systemsss");

			return connection;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * @param connection
	 * DB接続を切断する
	 */
	public static void close(Connection connection){
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param preparedStatement
	 * @param connection
	 * DB接続を切断する
	 */
	public static void close(PreparedStatement preparedStatement, Connection connection){
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * DB接続を切断する
	 *
	 * @param resultSet
	 *            SQL検索結果 クラッド処理をしている処理
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
