package com.funshion.dao.bt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.funshion.bean.bt.BTVersion;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class BTVersionDao {

	public static List<BTVersion> getBTVersions(String chiptype, String brand, String ctype) {
		List<BTVersion> versions = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getAppInstallFailInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String andParams = "";
			if (chiptype != null && chiptype.length() > 0)
				andParams = " and chiptype='" + chiptype + "'";
			if (brand != null && brand.length() > 0)
				andParams += " and brand='" + brand + "'";
			if (ctype != null && ctype.length() > 0)
				andParams += " and ctype='" + ctype + "'";

			int queryDate = DateUtil.getCurrentTime(null);
			String sql = "select rom_ver, sum(count) as count, date from bt_rom_ver left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_rom_ver.h_mode = zhaojm.mode where 1=1 and date="
					+ queryDate + andParams + " group by rom_ver order by count desc;";
			System.out.println("sql:" + sql);
			Map<String, BTVersion> versionCounts = new HashMap<String, BTVersion>();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String romVer = rs.getString("rom_ver").substring(0, 3);
				int count = rs.getInt("count");

				BTVersion version = (BTVersion) versionCounts.get(romVer);
				if (version != null) {
					version.setCount(count + version.getCount());
				} else {
					version = new BTVersion();
					version.setBtVer(romVer);
					version.setCount(count);
					versions.add(version);
					versionCounts.put(romVer, version);
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
		return versions;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getBTVersions(null, null, null));
	}

}
