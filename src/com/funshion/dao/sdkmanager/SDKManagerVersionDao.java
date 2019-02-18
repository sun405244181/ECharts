package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.funshion.bean.sdkmanager.SDKManagerVersion;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class SDKManagerVersionDao {

	public static List<SDKManagerVersion> getSDKManagerVersionInfos(String r_romVer, String r_sdkVer, String r_date) {
		List<SDKManagerVersion> versionInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getAppInstallFailInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			int queryDate = DateUtil.getCurrentTime(r_date);
			String sql = "select app_ver, sum(app_count) as count, date from sdk_upgrade_info where date=" + queryDate
					+ " group by app_ver order by count desc";

			// 显示已有记录最新的
			sql = "select app_ver, sum(app_count) as count, date from sdk_upgrade_info group by app_ver, date order by date desc, count desc;";
			int topDate = 0;

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Map versionCountMap = new HashMap();
			while (rs.next()) {
				String sdkVer = String.valueOf(rs.getInt("app_ver"));
				int count = rs.getInt("count");
				int date = rs.getInt("date");
				if (topDate == 0) {
					topDate = date;
				} else {
					if (topDate != date)
						break;
				}

				sdkVer = sdkVer.length() > 3 ? sdkVer.replace('0', '.').substring(0, 3) : sdkVer;
				if (sdkVer != null && sdkVer.equals("0"))
					sdkVer = "null";
				SDKManagerVersion vresionInfo = (SDKManagerVersion) versionCountMap.get(sdkVer);
				if (vresionInfo != null) {
					vresionInfo.setCount(count + vresionInfo.getCount());
				} else {
					vresionInfo = new SDKManagerVersion();
					vresionInfo.setSdkVer(sdkVer);
					vresionInfo.setCount(count);
					vresionInfo.setDate(date);
					versionCountMap.put(sdkVer, vresionInfo);
					versionInfos.add(vresionInfo);
				}
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
		return versionInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getSDKManagerVersionInfos(null, null, null));
	}

}
