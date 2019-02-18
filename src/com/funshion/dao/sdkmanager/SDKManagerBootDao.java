package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.funshion.bean.sdkmanager.SDKManagerBoot;
import com.funshion.utils.DBUtil;

public class SDKManagerBootDao {

	public static List<SDKManagerBoot> getSDKManagerBootInfos(String romVer, int sdkVer, int cdate) {
		System.out.println("getSDKManagerBootInfos");
		List<SDKManagerBoot> bootInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			int queryDate = getCurrentTime(cdate);
			String tvSql = "SELECT rom_ver, sum(count) as count, date FROM tv_upgrade_info where 1=1";
			if (romVer != null && !romVer.isEmpty())
				tvSql += " and rom_ver=" + romVer;
			tvSql += " and date<" + queryDate + " group by date;";

			String sdkSql = "SELECT rom_ver, app_ver, sum(app_count) as app_count, date FROM sdk_upgrade_info where 1=1";
			if (romVer != null && !romVer.isEmpty())
				sdkSql += " and rom_ver=" + romVer;
			if (sdkVer != -1)
				sdkSql += " and app_ver=" + sdkVer;
			sdkSql += " and date<" + queryDate + " group by date;";

			System.out.println("tvSql:" + tvSql);
			System.out.println("sdkSql:" + sdkSql);
			ResultSet tvResultSet = stmt.executeQuery(tvSql);
			Map tvCounts = new HashMap();
			while (tvResultSet.next()) {
				int tvCount = tvResultSet.getInt("count");
				int qDate = tvResultSet.getInt("date");
				tvCounts.put(String.valueOf(qDate), tvCount);
			}
			tvResultSet.close();

			ResultSet sdkResultSet = stmt.executeQuery(sdkSql);
			while (sdkResultSet.next()) {
				SDKManagerBoot bootInfo = new SDKManagerBoot();

				int appCount = sdkResultSet.getInt("app_count");
				int date = sdkResultSet.getInt("date");

				bootInfo.setTvCount((int) tvCounts.get(String.valueOf(date)));
				bootInfo.setAppCount(appCount);
				bootInfo.setDate(date);
				bootInfos.add(bootInfo);
				System.out.println(bootInfo);
			}
			sdkResultSet.close();
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
		return bootInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getSDKManagerBootInfos(null, -1, -1);
		// getCurrentTime(-1);
	}

	public static int getCurrentTime(int cdate) {
		if (cdate > -1)
			return cdate;
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String date = df.format(new Date());
			// System.out.println(date);
			return Integer.valueOf(date);
		} catch (Exception ex) {
		}
		return -1;
	}
}
