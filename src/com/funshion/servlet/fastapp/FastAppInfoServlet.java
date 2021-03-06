package com.funshion.servlet.fastapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.funshion.bean.fastapp.FastAppInfo;
import com.funshion.dao.fastapp.FastAppInfoDao;

public class FastAppInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<FastAppInfo> fastAppInfos = FastAppInfoDao.getFastAppInfos();
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(fastAppInfos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
