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

import com.funshion.bean.bt.BTPairInfo;
import com.funshion.utils.DBUtil;

public class BTPairDao {

	public static List<BTPairInfo> getBTPairInfos(String chiptype, String brand, String ctype, String distinct,
			String romver) {
		List<BTPairInfo> pairInfos = new ArrayList<>();
		Map<String, BTPairInfo> pairMap = new HashMap<String, BTPairInfo>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getBTPairInfos");
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

			String datesql = "select distinct date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " and distinct_mac=" + distinct + " order by date asc;";
			System.out.println("sql:" + datesql);
			ResultSet daters = stmt.executeQuery(datesql);
			while (daters.next()) {
				int date = daters.getInt("date");

				BTPairInfo pairInfo = (BTPairInfo) pairMap.get(String.valueOf(date));
				if (pairInfo != null) {
					pairInfo.setDate(date);
				} else {
					pairInfo = new BTPairInfo();
					pairInfo.setDate(date);
					pairInfos.add(pairInfo);
					pairMap.put(String.valueOf(date), pairInfo);
				}
			}
			daters.close();

			String sucesssql = "select sum(count) as count, date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where bt_pair_status=1 "
					+ andParams + " and distinct_mac=" + distinct + " group by date order by date asc;";
			System.out.println("sql:" + sucesssql);
			ResultSet sucessrs = stmt.executeQuery(sucesssql);
			while (sucessrs.next()) {
				int count = sucessrs.getInt("count");
				int date = sucessrs.getInt("date");

				BTPairInfo pairInfo = (BTPairInfo) pairMap.get(String.valueOf(date));
				if (pairInfo != null) {
					pairInfo.setSucess(count);
				} else {
					pairInfo = new BTPairInfo();
					pairInfo.setSucess(count);
					pairInfo.setDate(date);
					pairInfos.add(pairInfo);
					pairMap.put(String.valueOf(date), pairInfo);
				}
			}
			sucessrs.close();

			String failsql = "select sum(count) as count, date from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where bt_pair_status=0 "
					+ andParams + " and distinct_mac=" + distinct + " group by date order by date asc;";
			System.out.println("sql:" + failsql);
			ResultSet failrs = stmt.executeQuery(failsql);
			while (failrs.next()) {
				int count = failrs.getInt("count");
				int date = failrs.getInt("date");

				BTPairInfo pairInfo = (BTPairInfo) pairMap.get(String.valueOf(date));
				if (pairInfo != null) {
					pairInfo.setFail(count);
				} else {
					pairInfo = new BTPairInfo();
					pairInfo.setFail(count);
					pairInfo.setDate(date);
					pairInfos.add(pairInfo);
					pairMap.put(String.valueOf(date), pairInfo);
				}
			}
			failrs.close();
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
		return pairInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getBTPairInfos(null, null, null, "0", null));
	}

}
