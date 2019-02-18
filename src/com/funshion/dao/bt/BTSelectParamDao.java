package com.funshion.dao.bt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.bt.BTSelectParam;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class BTSelectParamDao {

	public static synchronized List<BTSelectParam> getSelectParams(String ptype) {
		List<BTSelectParam> params = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getSelectParams");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String sql = null;

			if (ptype == null || ptype.trim().length() < 1)
				return params;

			int queryDate = DateUtil.getCurrentTime(null);
			if (ptype.equals("brand")) {
				sql = "select brand as param, sum(count) as count from bt_rom_ver left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_rom_ver.h_mode = zhaojm.mode where date="
						+ queryDate + " group by brand order by count desc;";
			} else if (ptype.equals("chiptype")) {
				sql = "select chiptype as param, sum(count) as count from bt_rom_ver left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_rom_ver.h_mode = zhaojm.mode where date="
						+ queryDate + " group by chiptype order by count desc;";
			} else if (ptype.equals("ctype")) {///////////// end
				sql = "select ctype as param, sum(count) as count from bt_rom_ver left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_rom_ver.h_mode = zhaojm.mode where date="
						+ queryDate + " group by ctype order by count desc;";
			} else if (ptype.equals("statusbrand")) {
				sql = "select brand as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode group by brand order by count desc;";
			} else if (ptype.equals("statuschiptype")) {
				sql = "select chiptype as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode group by chiptype order by count desc;";
			} else if (ptype.equals("statusctype")) {
				sql = "select ctype as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode group by ctype order by count desc;";
			} else if (ptype.equals("statusromver")) {
				sql = "select rom_ver as param, sum(count) as count from bt_pair_status where date=" + queryDate
						+ " and rom_ver like '%_s' and count>1000 group by rom_ver order by count desc;";
			} else if (ptype.equals("statusromver_3")) {/////////////////// end
				sql = "select rom_ver as param, sum(count) as count from bt_pair_status where bt_pair_ui=1 group by rom_ver order by count desc;";
			} else if (ptype.equals("pairbrand")) {
				sql = "select brand as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where (bt_pair_status=0 or bt_pair_status=1) group by brand order by count desc;";
			} else if (ptype.equals("pairchiptype")) {
				sql = "select chiptype as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where (bt_pair_status=0 or bt_pair_status=1) group by chiptype order by count desc;";
			} else if (ptype.equals("pairctype")) {
				sql = "select ctype as param, sum(count) as count from bt_pair_status left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on bt_pair_status.h_mode = zhaojm.mode where (bt_pair_status=0 or bt_pair_status=1) group by ctype order by count desc;";
			} else if (ptype.equals("pairromver")) {
				sql = "select rom_ver as param, sum(count) as count from bt_pair_status where (bt_pair_status=0 or bt_pair_status=1) group by rom_ver order by count desc;";
			}

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String select = rs.getString("param");
				if (select == null || select.equals("NULL"))
					continue;
				BTSelectParam param = new BTSelectParam();
				param.setType(ptype);
				param.setParam(select);
				params.add(param);
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
		return params;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getSelectParams("brand"));
	}

}
