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
import com.funshion.bean.castscreen.CastScreenInfo;
import com.funshion.dao.castscreen.CastScreenDao;

public class CastScreenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String brand = req.getParameter("brand");
		String chiptype = req.getParameter("chiptype");
		String airplay = req.getParameter("airplay");
		String dlna = req.getParameter("dlna");
		String miracast = req.getParameter("miracast");
		List<CastScreenInfo> castScreenInfos = CastScreenDao.getCastScreenInfos(brand, chiptype, airplay, dlna,
				miracast);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(castScreenInfos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
