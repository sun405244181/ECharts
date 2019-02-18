package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.sdkmanager.AppInstallFailInfo;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class AppInstallFailDao {

	public static List<AppInstallFailInfo> getAppInstallFailInfos(String r_romVer, String r_sdkVer, String r_app,
			String r_date) {
		List<AppInstallFailInfo> installFailInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getAppInstallFailInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			int queryDate = DateUtil.getCurrentTime(r_date);
			String paramSql = "";
			if (r_romVer != null && r_romVer.trim().length() > 0)
				paramSql += (r_romVer.endsWith("%") ? "rom_ver like '" : " rom_ver='") + r_romVer + "' and ";
			if (r_sdkVer != null && r_sdkVer.trim().length() > 0)
				paramSql += " sdk_ver=" + r_sdkVer + " and ";
			if (r_app != null && r_app.trim().length() > 0)
				paramSql += (r_app.endsWith("%") ? "app like '" : " app='") + r_app + "' and ";

			String sql = "select error_code, sum(count) as count, date from sdk_install_fail where " + paramSql
					+ " date=" + queryDate + " group by error_code order by count desc;";
			// + " and (error_code != 1 and error_code != -25) group by error_code order by
			// count desc;";

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int errorCode = rs.getInt("error_code");
				int count = rs.getInt("count");
				int date = rs.getInt("date");

				AppInstallFailInfo installFailInfo = new AppInstallFailInfo();
				installFailInfo.setErrorCode(errorCode);
				installFailInfo.setCount(count);
				installFailInfo.setDate(date);
				installFailInfos.add(installFailInfo);
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
		return installFailInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getAppInstallFailInfos(null, null, null, null));
	}
}
