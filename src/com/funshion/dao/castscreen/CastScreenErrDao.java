package com.funshion.dao.castscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.castscreen.CastScreenErr;
import com.funshion.utils.DBUtil;
import com.funshion.utils.DateUtil;

public class CastScreenErrDao {

	public static List<CastScreenErr> getCastScreenErrInfos(String protocal, String brand, String chiptype,
			String version, String date) {
		List<CastScreenErr> errorInfos = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getCastScreenErrInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String paramSql = "";
			if (brand != null && brand.length() > 0) {
				paramSql += " and brand='" + brand + "'";
			}
			if (chiptype != null && chiptype.length() > 0) {
				paramSql += " and chiptype='" + chiptype + "'";
			}

			int queryDate = DateUtil.getCurrentTime(date);
			if (protocal != null && protocal.trim().length() > 0)
				paramSql += " and protocal_id='" + protocal + "'";
			else
				paramSql += " and protocal_id='DLNA'";
			if (version != null && version.trim().length() > 0)
				paramSql += (version.endsWith("%") ? " and app_ver like '" : " and app_ver='") + version + "'";

			String sql = "select protocal_id, err, sum(count) as count, date from cast_screen_err left join (select distinct mode, chiptype, brand from mode_chiptype) as zhaojm on cast_screen_err.h_mode = zhaojm.mode where 1=1 "
					+ paramSql + " and date=" + queryDate + " group by err order by count desc;";

			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String errorType = rs.getString("err");
				if (errorType != null && errorType.equals("0"))
					errorType = "null";
				int errCount = rs.getInt("count");
				int errDate = rs.getInt("date");

				CastScreenErr errorInfo = new CastScreenErr();
				errorInfo.setErrType(errorType);
				errorInfo.setErrCount(errCount);
				errorInfo.setDate(errDate);
				errorInfos.add(errorInfo);
				System.out.println(errorInfo);
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
		return errorInfos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getCastScreenErrInfos(null, null, null, null, null);
	}

}
