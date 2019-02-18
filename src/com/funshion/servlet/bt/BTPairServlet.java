package com.funshion.servlet.bt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.funshion.bean.bt.BTPairInfo;
import com.funshion.dao.bt.BTPairDao;

public class BTPairServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chiptype = req.getParameter("chiptype");
		String brand = req.getParameter("brand");
		String ctype = req.getParameter("ctype");
		String distinct = req.getParameter("distinct");
		String romver = req.getParameter("romver");
		List<BTPairInfo> infos = BTPairDao.getBTPairInfos(chiptype, brand, ctype, distinct, romver);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(infos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
