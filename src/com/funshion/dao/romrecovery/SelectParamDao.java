package com.funshion.dao.romrecovery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.romrecovery.SelectParam;
import com.funshion.utils.DBUtil;

public class SelectParamDao {

	public static synchronized List<SelectParam> getSelectParams(String ptype) {
		List<SelectParam> params = new ArrayList<>();
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

			if (ptype.equals("rom_ver")) {
				sql = "select rom_ver as param, sum(count) as count from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode group by rom_ver order by count desc;";
			} else if (ptype.equals("chip_type")) {
				sql = "select chipType as param, sum(count) as count from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode group by chipType order by count desc;";
			} else if (ptype.equals("brand")) {
				sql = "select brand as param, sum(count) as count from rom_recovery left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on rom_recovery.h_mode = zhaojm.mode group by brand order by count desc;";
			}

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String var = rs.getString("param");
				SelectParam param = new SelectParam();
				param.setType(ptype);
				param.setParam(var);
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
