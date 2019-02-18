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
import com.funshion.bean.castscreen.CastScreenStatus;
import com.funshion.dao.castscreen.CastScreenStatusDao;

public class CastScreenStatusServlet extends HttpServlet {

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
		List<CastScreenStatus> infos = CastScreenStatusDao.getCastScreenStatusInfos(app, brand, chiptype, appVer);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(infos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
