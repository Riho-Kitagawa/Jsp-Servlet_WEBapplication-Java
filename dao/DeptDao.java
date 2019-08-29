package jp.co.sss.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.sss.crud.bean.Dept;

/**
 * @author edu
 * DeptのDAOクラス
 *
 */
public class DeptDao {
	public static Dept findbyId(int deptIdNum){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Dept dept =null;

		try {
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * from department where dept_id =?");

			preparedStatement.setInt(1, deptIdNum);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				dept = new Dept();
				dept.setDeptId(resultSet.getInt("dept_id"));
				dept.setDeptName(resultSet.getString("dept_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBManager.close(preparedStatement,connection);
		}
		return dept;

	}
}
