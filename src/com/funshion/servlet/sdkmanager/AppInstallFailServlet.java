package com.funshion.servlet.sdkmanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.funshion.bean.sdkmanager.AppInstallFailInfo;
import com.funshion.dao.sdkmanager.AppInstallFailDao;

public class AppInstallFailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String romVer = req.getParameter("romVer");
		String sdkVer = req.getParameter("sdkVer");
		String app = req.getParameter("app");
		String date = req.getParameter("date");
		System.out.println("romVer:" + romVer + ";sdkVer:" + sdkVer + ";app:" + app + ";date:" + date);
		List<AppInstallFailInfo> installFailInfos = AppInstallFailDao.getAppInstallFailInfos(romVer, sdkVer, app, date);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(installFailInfos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
