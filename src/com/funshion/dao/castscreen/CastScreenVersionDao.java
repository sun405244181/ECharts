package com.funshion.dao.castscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.castscreen.CastScreenVersion;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class CastScreenVersionDao {

	public static List<CastScreenVersion> getCastScreenVersions(String q_type, String q_date) {
		List<CastScreenVersion> castScreenVersions = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getCastScreenVersions");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String sql = "";
			if (q_type == null || q_type.trim().length() == 0)
				return castScreenVersions;
			int queryDate = DateUtil.getCurrentTime(q_date);
			if (q_type.equals("status_dlna")) {
				sql = "select distinct(app_ver) as app_ver, protocal_id from cast_screen_status where protocal_id='DLNA' and status = 2 and count > 0 and date = "
						+ queryDate + " order by app_ver desc;";
			} else if (q_type.equals("status_airplay")) {
				sql = "select distinct(app_ver) as app_ver, protocal_id from cast_screen_status where protocal_id='AIRPLAY' and status = 2 and count > 0 and date = "
						+ queryDate + " order by app_ver desc;";
			} else if (q_type.equals("err_dlna")) {
				sql = "select distinct(app_ver) as app_ver, protocal_id from cast_screen_err where protocal_id='DLNA' and date = "
						+ queryDate + " order by app_ver desc;";
			} else if (q_type.equals("err_airplay")) {
				sql = "select distinct(app_ver) as app_ver, protocal_id from cast_screen_err where protocal_id='AIRPLAY' and date = "
						+ queryDate + " order by app_ver desc;";
			}
			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String appVersion = rs.getString("app_ver");
				String protocalId = rs.getString("protocal_id");

				CastScreenVersion castScreenVersion = new CastScreenVersion();
				castScreenVersion.setAppVersion(appVersion);
				castScreenVersion.setProtocalId(protocalId);
				castScreenVersions.add(castScreenVersion);
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
		return castScreenVersions;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCastScreenVersions(null, null));
	}

}
