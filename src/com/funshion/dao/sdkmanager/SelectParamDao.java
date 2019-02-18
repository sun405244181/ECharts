package com.funshion.dao.sdkmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.sdkmanager.SelectParam;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class SelectParamDao {

	public static List<SelectParam> getSelectParams(String ptype) {
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

			int queryDate = DateUtil.getCurrentTime(null);

			if (ptype.equals("p2prr_sdkmanager")) {
				sql = "select sdk_ver as param, sum(count) as count, date from sdk_p2p_error where (error_code != 0 and error_code != 1) and date="
						+ queryDate + " group by sdk_ver order by count desc;";
			} else if (ptype.equals("p2prr_addtask")) {
				sql = "select app as param, sum(count) as count, date from sdk_p2p_error where (error_code != 0 and error_code != 1) and date="
						+ queryDate + " group by app order by count desc;";
			} else if (ptype.equals("install_fail_rom")) {
				sql = "select rom_ver as param, sum(count) as count, date from sdk_install_fail where (error_code != 1 and error_code != -25) and date="
						+ queryDate + " group by rom_ver order by count desc;";
			} else if (ptype.equals("install_fail_sdkmanager")) {
				sql = "select sdk_ver as param, sum(count) as count, date from sdk_install_fail where (error_code != 1 and error_code != -25) and date="
						+ queryDate + " group by sdk_ver order by count desc;";
			} else if (ptype.equals("install_fail_task")) {
				sql = "select app as param, sum(count) as count, date from sdk_install_fail where (error_code != 1 and error_code != -25) and date="
						+ queryDate + " group by app order by count desc;";
			}
			int topDate = 0;

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String var = rs.getString("param");
				int date = rs.getInt("date");
				if (topDate == 0) {
					topDate = date;
				} else {
					if (topDate != date)
						break;
				}

				SelectParam param = new SelectParam();
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
		System.out.println(getSelectParams("sdkmanager"));
	}

}
