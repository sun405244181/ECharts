package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.sdkmanager.P2pErrorCode;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class P2pErrorCodeDao {

	public static List<P2pErrorCode> getErrorCodeInfos(String r_sdkVer, String r_app, String r_date) {
		List<P2pErrorCode> errorInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getErrorCodeInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			int queryDate = DateUtil.getCurrentTime(r_date);
			String paramSql = "";
			if (r_sdkVer != null && r_sdkVer.trim().length() > 0)
				paramSql += " sdk_ver=" + r_sdkVer + " and ";
			if (r_app != null && r_app.trim().length() > 0)
				paramSql += (r_app.endsWith("%") ? "app like '" : " app='") + r_app + "' and ";

			String sql = "select error_code, sum(count) as count, date from sdk_p2p_error where " + paramSql + " date="
					+ queryDate + " group by error_code order by count desc;";
			// + " and (error_code != 0 and error_code != 1) group by error_code order by
			// count desc;";//包含成功次数

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int errorCode = rs.getInt("error_code");
				int count = rs.getInt("count");
				int date = rs.getInt("date");

				P2pErrorCode errorInfo = new P2pErrorCode();
				errorInfo.setErrorCode(errorCode);
				errorInfo.setCount(count);
				errorInfo.setDate(date);
				errorInfos.add(errorInfo);
				System.out.println(errorInfo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return errorInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getErrorCodeInfos(null, null, -1);
	}

}
