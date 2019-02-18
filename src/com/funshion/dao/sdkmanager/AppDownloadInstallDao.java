package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.sdkmanager.AppDownloadInstallInfo;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class AppDownloadInstallDao {

	public static List<AppDownloadInstallInfo> getAppInstallInfos(String r_romVer, String r_sdkVer, String r_app,
			String r_date) {
		List<AppDownloadInstallInfo> installInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getAppInstallInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();
			// select app, (select sum(download_start) from sdk_download_install as a where
			// a.date <= b.date and a.app='com.avc_mr.datacollectionandroid_1000803003') as
			// download_start, date from sdk_download_install as b where
			// app='com.avc_mr.datacollectionandroid_1000803003';
			int queryDate = DateUtil.getCurrentTime(r_date);

			String appendApp = "";
			String appendDate = "";
			if (r_app != null && r_app.trim().length() > 0)
				appendApp = (r_app.endsWith("%") ? " and app like '" : " and app='") + r_app + "'";
			if (r_date != null && r_date.trim().length() > 0)
				appendDate = (r_date.endsWith("%") ? " and app like '" : " and app='") + r_app + "'";

			String sql = "select app, "
					+ "(select sum(download_start) from sdk_download_install as a where a.date <= b.date" + appendApp
					+ ") as download_start, "
					+ "(select sum(download_sucess) from sdk_download_install as a where a.date <= b.date " + appendApp
					+ ") as download_sucess, "
					+ "(select sum(install_sucess) from sdk_download_install as a where a.date <= b.date " + appendApp
					+ ") as install_sucess, " + "date from sdk_download_install as b where date <= " + queryDate
					+ appendApp + " group by date;";

			sql = "select sum(download_start) as download_start, sum(download_sucess) as download_sucess, sum(install_sucess) as install_sucess, date from sdk_download_install where date <= "
					+ queryDate + appendApp + " group by date order by date asc;";

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// String app = rs.getString("app");
				int downloadStart = rs.getInt("download_start");
				int downloadSucess = rs.getInt("download_sucess");
				int installSucess = rs.getInt("install_sucess");
				int date = rs.getInt("date");

				AppDownloadInstallInfo installInfo = new AppDownloadInstallInfo();
				// installInfo.setApp(app);
				installInfo.setDownloadStart(downloadStart);
				installInfo.setDownloadSucess(downloadSucess);
				installInfo.setInstallSucess(installSucess);
				installInfo.setDate(date);
				installInfos.add(installInfo);
				System.out.println(installInfos);
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
		return installInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getAppInstallInfos(null, null, null, null));
	}
}
