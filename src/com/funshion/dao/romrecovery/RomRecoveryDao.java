package com.funshion.dao.romrecovery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.funshion.bean.romrecovery.RomRecoveryInfo;
import com.funshion.utils.DBUtil;

public class RomRecoveryDao {

	public static List<RomRecoveryInfo> getRomRecoveryInfos(String romVer, String chipType, String brand,
			String distinct) {
		List<RomRecoveryInfo> romRecoveryInfos = new ArrayList<>();
		Map<String, RomRecoveryInfo> romRecoveryMap = new HashMap<String, RomRecoveryInfo>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getRomRecoveryInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			// select recovery_info, sum(count) from rom_recovery left join mode_chiptype on
			// rom_recovery.h_mode = mode_chiptype.mode and chiptype='638' group by
			// recovery_info;
			String andParams = "";
			if (romVer != null && romVer.length() > 0)
				andParams = " and rom_ver='" + romVer + "'";
			if (chipType != null && chipType.length() > 0)
				andParams += " and chiptype='" + chipType + "'";
			if (brand != null && brand.length() > 0)
				andParams += " and brand='" + brand + "'";

			String dataWipeSql = "select recovery_info, sum(count) as count, date from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " and recovery_info='1' and distinct_mac=" + distinct + " group by date";
			System.out.println("dataWipeSql:" + dataWipeSql);
			ResultSet dataWipeRS = stmt.executeQuery(dataWipeSql);
			while (dataWipeRS.next()) {
				int count = dataWipeRS.getInt("count");
				int date = dataWipeRS.getInt("date");
				RomRecoveryInfo info = romRecoveryMap.get(String.valueOf(date));
				if (info == null) {
					info = new RomRecoveryInfo();
					info.setDataWipeCount(count);
					info.setDate(date);
					romRecoveryMap.put(String.valueOf(date), info);
					romRecoveryInfos.add(info);
				} else {
					info.setDataWipeCount(count);
				}

			}
			dataWipeRS.close();

			String cacheWipeSql = "select recovery_info, sum(count) as count, date from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " and recovery_info='2' and distinct_mac=" + distinct + " group by date";
			System.out.println("cacheWipeSql:" + cacheWipeSql);
			ResultSet cacheWipeRS = stmt.executeQuery(cacheWipeSql);
			while (cacheWipeRS.next()) {
				int count = cacheWipeRS.getInt("count");
				int date = cacheWipeRS.getInt("date");
				RomRecoveryInfo info = romRecoveryMap.get(String.valueOf(date));
				if (info == null) {
					info = new RomRecoveryInfo();
					info.setCacheWipeCount(count);
					info.setDate(date);
					romRecoveryMap.put(String.valueOf(date), info);
					romRecoveryInfos.add(info);
				} else {
					info.setCacheWipeCount(count);
				}
			}
			cacheWipeRS.close();

			String facWipeSql = "select recovery_info, sum(count) as count, date from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " and recovery_info='3' and distinct_mac=" + distinct + " group by date";
			System.out.println("facWipeSql:" + facWipeSql);
			ResultSet facWipeRS = stmt.executeQuery(facWipeSql);
			while (facWipeRS.next()) {
				int count = facWipeRS.getInt("count");
				int date = facWipeRS.getInt("date");
				RomRecoveryInfo info = romRecoveryMap.get(String.valueOf(date));
				if (info == null) {
					info = new RomRecoveryInfo();
					info.setFacWipeCount(count);
					info.setDate(date);
					romRecoveryMap.put(String.valueOf(date), info);
					romRecoveryInfos.add(info);
				} else {
					info.setFacWipeCount(count);
				}
			}
			facWipeRS.close();

			String forceWipeSql = "select recovery_info, sum(count) as count, date from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode where 1=1 "
					+ andParams + " and recovery_info='5' and distinct_mac=" + distinct + " group by date;";
			System.out.println("forceWipeSql:" + forceWipeSql);
			ResultSet forceWipeRS = stmt.executeQuery(forceWipeSql);
			while (forceWipeRS.next()) {
				int count = forceWipeRS.getInt("count");
				int date = forceWipeRS.getInt("date");
				RomRecoveryInfo info = romRecoveryMap.get(String.valueOf(date));
				if (info == null) {
					info = new RomRecoveryInfo();
					info.setForceWipeCount(count);
					info.setDate(date);
					romRecoveryMap.put(String.valueOf(date), info);
					romRecoveryInfos.add(info);
				} else {
					info.setForceWipeCount(count);
				}
			}
			forceWipeRS.close();
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
		return romRecoveryInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getRomRecoveryInfos(null, "638", "funshion", "0"));
	}

}
