package com.funshion.servlet.castscreen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.funshion.bean.castscreen.CastScreenErr;
import com.funshion.dao.castscreen.CastScreenErrDao;

public class CastScreenErrServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String app = req.getParameter("app");
		String brand = req.getParameter("brand");
		String chiptype = req.getParameter("chiptype");
		String appVer = req.getParameter("appVer");
		String date = req.getParameter("date");
		System.out.println("app:" + app + ";appVer:" + appVer + ";date:" + date);
		List<CastScreenErr> CastScreenErrInfos = CastScreenErrDao.getCastScreenErrInfos(app, brand, chiptype, appVer,
				date);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(CastScreenErrInfos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
