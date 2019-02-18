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

import com.funshion.bean.castscreen.CastScreenInfo;
import com.funshion.utils.DBUtil;

public class CastScreenDao {

	public static List<CastScreenInfo> getCastScreenInfos(String brand, String chiptype, String airplay, String dlna,
			String miracast) {
		List<CastScreenInfo> castScreenInfos = new ArrayList<>();
		Map<String, CastScreenInfo> dauMap = new HashMap<String, CastScreenInfo>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getAppInstallFailInfos brand:" + brand + ";chiptype:" + chiptype);
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			boolean mergeOld = true;
			String andParams = "";
			String airplayParams = "";
			String dlnaParams = "";
			String miracastParams = "";
			if (brand != null && brand.length() > 0) {
				mergeOld = false;
				andParams += " and brand='" + brand + "'";
			}
			if (chiptype != null && chiptype.length() > 0) {
				mergeOld = false;
				andParams += " and chiptype='" + chiptype + "'";
			}
			if (airplay != null && airplay.length() > 0) {
				mergeOld = false;
				airplayParams = " and app_ver='" + airplay + "'";
			}
			if (dlna != null && dlna.length() > 0) {
				mergeOld = false;
				dlnaParams = " and app_ver='" + dlna + "'";
			}
			if (miracast != null && miracast.length() > 0) {
				mergeOld = false;
				miracastParams = " and app_ver='" + miracast + "'";
			}
			System.out.println(andParams);

			// 老表结构
			if (mergeOld) {
				String oldsql = "select sum(airplay_count) as airplay_count, sum(dlna_count) as dlna_count, sum(mirrorcast_count) as mirrorcast_count, date from cast_screen where date<20190121 group by date order by date asc;";
				System.out.println("oldsql:" + oldsql);
				ResultSet oldrs = stmt.executeQuery(oldsql);
				while (oldrs.next()) {
					int airplayCount = oldrs.getInt("airplay_count");
					int dlnaCount = oldrs.getInt("dlna_count");
					int mirrorcastCount = oldrs.getInt("mirrorcast_count");
					int date = oldrs.getInt("date");

					CastScreenInfo castScreenInfo = new CastScreenInfo();
					castScreenInfo.setAirplayCount(airplayCount);
					castScreenInfo.setDlnaCount(dlnaCount);
					castScreenInfo.setMirrorcastCount(mirrorcastCount);
					castScreenInfo.setDate(date);
					castScreenInfos.add(castScreenInfo);
				}
				oldrs.close();
			}

			// 新表结构
			String airplaysql = "select sum(count) as airplay_count, date from cast_screen left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen.h_mode = zhaojm.mode where date>=20190121 "
					+ andParams + airplayParams + " and protocal_id='AIRPLAY' group by date order by date asc;";
			System.out.println("airplaysql:" + airplaysql);
			ResultSet airplayrs = stmt.executeQuery(airplaysql);
			while (airplayrs.next()) {
				int airplayCount = airplayrs.getInt("airplay_count");
				int date = airplayrs.getInt("date");

				CastScreenInfo castScreenInfo = dauMap.get(String.valueOf(date));
				if (castScreenInfo == null) {
					castScreenInfo = new CastScreenInfo();
					castScreenInfo.setDate(date);
					castScreenInfos.add(castScreenInfo);
					dauMap.put(String.valueOf(date), castScreenInfo);
				}
				castScreenInfo.setAirplayCount(airplayCount);
			}
			airplayrs.close();

			String dlnasql = "select sum(count) as dlna_count, date from cast_screen left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen.h_mode = zhaojm.mode where date>=20190121 "
					+ andParams + dlnaParams + " and protocal_id='DLNA' group by date order by date asc;";
			System.out.println("dlnasql:" + dlnasql);
			ResultSet dlnars = stmt.executeQuery(dlnasql);
			while (dlnars.next()) {
				int dlnaCount = dlnars.getInt("dlna_count");
				int date = dlnars.getInt("date");

				CastScreenInfo castScreenInfo = dauMap.get(String.valueOf(date));
				if (castScreenInfo == null) {
					castScreenInfo = new CastScreenInfo();
					castScreenInfo.setDate(date);
					castScreenInfos.add(castScreenInfo);
					dauMap.put(String.valueOf(date), castScreenInfo);
				}
				castScreenInfo.setDlnaCount(dlnaCount);
			}
			dlnars.close();

			String mirrorcastsql = "select sum(count) as mirrorcast_count, date from cast_screen left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen.h_mode = zhaojm.mode where date>=20190121 "
					+ andParams + miracastParams + " and protocal_id='MIRACAST' group by date order by date asc;";
			System.out.println("mirrorcastsql:" + mirrorcastsql);
			ResultSet mirrorcastrs = stmt.executeQuery(mirrorcastsql);
			while (mirrorcastrs.next()) {
				int mirrorcastCount = mirrorcastrs.getInt("mirrorcast_count");
				int date = mirrorcastrs.getInt("date");

				CastScreenInfo castScreenInfo = dauMap.get(String.valueOf(date));
				if (castScreenInfo == null) {
					castScreenInfo = new CastScreenInfo();
					castScreenInfo.setDate(date);
					castScreenInfos.add(castScreenInfo);
					dauMap.put(String.valueOf(date), castScreenInfo);
				}
				castScreenInfo.setMirrorcastCount(mirrorcastCount);
			}
			mirrorcastrs.close();
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
		return castScreenInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCastScreenInfos(null, null, null, null, null));
	}

}
