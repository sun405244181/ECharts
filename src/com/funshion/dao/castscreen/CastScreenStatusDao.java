package com.funshion.dao.castscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.funshion.bean.castscreen.CastScreenStatus;
import com.funshion.utils.DBUtil;

public class CastScreenStatusDao {

	public static List<CastScreenStatus> getCastScreenStatusInfos(String app, String brand, String chiptype,
			String version) {
		List<CastScreenStatus> castScreenStatusInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getCastScreenStatusInfos app:" + app + ";version:" + version + ";brand:" + brand
				+ ";chiptype:" + chiptype);
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String andParams = "";
			if (brand != null && brand.length() > 0) {
				andParams += " and brand='" + brand + "'";
			}
			if (chiptype != null && chiptype.length() > 0) {
				andParams += " and chiptype='" + chiptype + "'";
			}

			String sucessSql = "SELECT protocal_id, status, sum(count) as count, date FROM cast_screen_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen_status.h_mode = zhaojm.mode where status=1 ";
			sucessSql += andParams;
			if (app != null && !app.isEmpty())
				sucessSql += " and protocal_id='" + app + "'";
			else
				sucessSql += " and protocal_id='DLNA'";
			if (version != null && !version.isEmpty())
				sucessSql += " and app_ver='" + version + "'";
			sucessSql += " group by date;";

			String failSql = "SELECT protocal_id, status, sum(count) as count, date FROM cast_screen_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen_status.h_mode = zhaojm.mode where status=2 ";
			failSql += andParams;
			if (app != null && !app.isEmpty())
				failSql += " and protocal_id='" + app + "'";
			else
				failSql += " and protocal_id='DLNA'";
			if (version != null && !version.isEmpty())
				failSql += " and app_ver='" + version + "'";
			failSql += " group by date;";

			System.out.println("sucessSql:" + sucessSql);
			System.out.println("failSql:" + failSql);
			ResultSet sucessResultSet = stmt.executeQuery(sucessSql);
			Map sucessCounts = new HashMap();
			while (sucessResultSet.next()) {
				int sucessCount = sucessResultSet.getInt("count");
				int sucessDate = sucessResultSet.getInt("date");
				sucessCounts.put(String.valueOf(sucessDate), sucessCount);
			}
			sucessResultSet.close();

			ResultSet failResultSet = stmt.executeQuery(failSql);
			while (failResultSet.next()) {
				CastScreenStatus castScreenStatus = new CastScreenStatus();

				int failCount = failResultSet.getInt("count");
				int failDate = failResultSet.getInt("date");

				castScreenStatus.setSucessCount((int) sucessCounts.get(String.valueOf(failDate)));
				castScreenStatus.setFailCount(failCount);
				castScreenStatus.setDate(failDate);
				castScreenStatusInfos.add(castScreenStatus);
				System.out.println(castScreenStatus);
			}
			failResultSet.close();

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
		return castScreenStatusInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCastScreenStatusInfos("DLNA", "", "", ""));
	}

}
