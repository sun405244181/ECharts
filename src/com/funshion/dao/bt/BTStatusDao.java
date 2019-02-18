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

import com.funshion.bean.bt.BTStatusInfo;
import com.funshion.utils.DBUtil;

public class BTStatusDao {

	public static List<BTStatusInfo> getBTStatusInfos(String chiptype, String brand, String ctype, String romver) {
		List<BTStatusInfo> statusInfos = new ArrayList<>();
		Map<String, BTStatusInfo> statusMap = new HashMap<String, BTStatusInfo>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getBTStatusInfos");
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
			if (romver != null && romver.length() > 0)
				andParams += " and rom_ver='" + romver + "'";

			String totalsql = "select sum(count) as count, date from bt_rom_ver left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_rom_ver.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " group by date order by date asc;";
			System.out.println("sql:" + totalsql);
			ResultSet totalrs = stmt.executeQuery(totalsql);
			while (totalrs.next()) {
				int count = totalrs.getInt("count");
				int date = totalrs.getInt("date");

				BTStatusInfo statusInfo = (BTStatusInfo) statusMap.get(String.valueOf(date));
				if (statusInfo != null) {
					statusInfo.setTotal(count);
				} else {
					statusInfo = new BTStatusInfo();
					statusInfo.setTotal(count);
					statusInfo.setDate(date);
					statusInfos.add(statusInfo);
					statusMap.put(String.valueOf(date), statusInfo);
				}
			}
			totalrs.close();

			String uisql = "select sum(count) as count, date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where bt_pair_ui=1 "
					+ andParams + " group by date order by date asc;";
			System.out.println("sql:" + uisql);
			ResultSet uirs = stmt.executeQuery(uisql);
			while (uirs.next()) {
				int count = uirs.getInt("count");
				int date = uirs.getInt("date");

				BTStatusInfo statusInfo = (BTStatusInfo) statusMap.get(String.valueOf(date));
				if (statusInfo != null) {
					statusInfo.setUi(count);
				} else {
					statusInfo = new BTStatusInfo();
					statusInfo.setUi(count);
					statusInfo.setDate(date);
					statusInfos.add(statusInfo);
					statusMap.put(String.valueOf(date), statusInfo);
				}
			}
			uirs.close();

			String pairsql = "select sum(count) as count, date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where (bt_pair_status=1 or bt_pair_status=0) "
					+ andParams + " group by date order by date asc;";
			System.out.println("sql:" + pairsql);
			ResultSet pairrs = stmt.executeQuery(pairsql);
			while (pairrs.next()) {
				int count = pairrs.getInt("count");
				int date = pairrs.getInt("date");

				BTStatusInfo statusInfo = (BTStatusInfo) statusMap.get(String.valueOf(date));
				if (statusInfo != null) {
					statusInfo.setPair(count);
				} else {
					statusInfo = new BTStatusInfo();
					statusInfo.setPair(count);
					statusInfo.setDate(date);
					statusInfos.add(statusInfo);
					statusMap.put(String.valueOf(date), statusInfo);
				}
			}
			pairrs.close();

			String callbacksql = "select sum(count) as count, date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where bt_callback=1 "
					+ andParams + " group by date order by date asc;";
			System.out.println("sql:" + callbacksql);
			ResultSet callbackrs = stmt.executeQuery(callbacksql);
			while (callbackrs.next()) {
				int count = callbackrs.getInt("count");
				int date = callbackrs.getInt("date");

				BTStatusInfo statusInfo = (BTStatusInfo) statusMap.get(String.valueOf(date));
				if (statusInfo != null) {
					statusInfo.setCallback(count);
				} else {
					statusInfo = new BTStatusInfo();
					statusInfo.setCallback(count);
					statusInfo.setDate(date);
					statusInfos.add(statusInfo);
					statusMap.put(String.valueOf(date), statusInfo);
				}
			}
			callbackrs.close();
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
		return statusInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getBTStatusInfos(null, null, null, null));
	}

}
