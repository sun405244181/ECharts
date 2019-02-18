package com.funshion.dao.castscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.castscreen.CastScreenSelect;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class CastScreenSelectDao {

	public static List<CastScreenSelect> getCastScreenSelect(String ptype) {
		List<CastScreenSelect> castScreenSelects = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getCastScreenSelect ptype:" + ptype);
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String sql = "";
			if (ptype == null || ptype.trim().length() == 0)
				return castScreenSelects;
			int queryDate = DateUtil.getCurrentTime(null);
			if (ptype.equals("dau_brand")) {
				sql = "select brand as param, sum(count) as count from cast_screen left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen.h_mode = zhaojm.mode where date="
						+ queryDate + " group by brand order by count desc;";
			} else if (ptype.equals("dau_chiptype")) {
				sql = "select chiptype as param, sum(count) as count from cast_screen left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen.h_mode = zhaojm.mode where date="
						+ queryDate + " group by chiptype order by count desc;";
			} else if (ptype.equals("dau_airplay")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen where protocal_id='AIRPLAY' and count > 10 and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("dau_dlna")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen where protocal_id='DLNA' and count > 10 and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("dau_miracast")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen where protocal_id='MIRACAST' and count > 10 and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("status_dlna_ver")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen_status where protocal_id='DLNA' and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("status_airplay_ver")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen_status where protocal_id='MIRACAST' and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("err_dlna_ver")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen_err where protocal_id='DLNA' and date="
						+ queryDate + " group by app_ver order by count desc;";
			} else if (ptype.equals("err_airplay_ver")) {
				sql = "select app_ver as param, sum(count) as count from cast_screen_err where protocal_id='MIRACAST' and date="
						+ queryDate + " group by app_ver order by count desc;";
			}

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String param = rs.getString("param");
				CastScreenSelect castScreenSelect = new CastScreenSelect();
				castScreenSelect.setParam(param);
				castScreenSelects.add(castScreenSelect);
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
		return castScreenSelects;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCastScreenSelect("dau_brand"));
	}

}
