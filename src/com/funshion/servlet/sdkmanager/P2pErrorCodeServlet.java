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
import com.funshion.bean.sdkmanager.P2pErrorCode;
import com.funshion.dao.sdkmanager.P2pErrorCodeDao;

public class P2pErrorCodeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sdkVer = req.getParameter("sdkVer");
		String app = req.getParameter("app");
		String date = req.getParameter("date");
		List<P2pErrorCode> errorCodeInfos = P2pErrorCodeDao.getErrorCodeInfos(sdkVer, app, date);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(errorCodeInfos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
