package com.funshion.dao.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.funshion.bean.log.LogInfo;
import com.funshion.utils.DBUtil;

public class LogInfoDao {

	public static List<LogInfo> getLogInfos() {
		List<LogInfo> logs = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		System.out.println("getLogInfos");
		try {
			Class.forName(DBUtil.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBUtil.DB_URL, DBUtil.USER, DBUtil.PASS);
			stmt = conn.createStatement();

			String sql = "select * from log order by count desc, date desc;";
			System.out.println("sql:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String romVer = rs.getString("rom_ver");
				String chipType = rs.getString("chip_type");
				String tag = rs.getString("tag");
				String process = rs.getString("process");
				String subject = rs.getString("subject");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int count = rs.getInt("count");
				int status = rs.getInt("status");
				int date = rs.getInt("date");

				LogInfo log = new LogInfo();
				log.setRomVer(romVer);
				log.setChipType(chipType);
				log.setTag(tag);
				log.setProcess(process);
				log.setSubject(subject);
				log.setTitle(title);
				log.setContent(content);
				log.setCount(count);
				log.setStatus(status);
				log.setDate(date);
				logs.add(log);
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
		return logs;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getLogInfos());
	}

}
