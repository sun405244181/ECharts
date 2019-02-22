package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.sdkmanager.DownloadTaskInfo;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class DebugDownloadTaskDao {

	public static List<DownloadTaskInfo> getDownloadTaskInfos() {
		List<DownloadTaskInfo> apks = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getDownloadTaskInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String sql = "select distinct app, date from debug_sdk_download_install where download_start > 0 and date="
					+ DateUtil.getCurrentTime(null) + " order by download_start desc;";
			int topDate = 0;

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String app = rs.getString("app");
				int date = rs.getInt("date");
				if (topDate == 0) {
					topDate = date;
				} else {
					if (topDate != date)
						break;
				}

				DownloadTaskInfo apk = new DownloadTaskInfo();
				apk.setTask(app);
				apks.add(apk);
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
		return apks;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getDownloadTaskInfos());
	}
}
