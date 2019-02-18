package com.funshion.servlet.romrecovery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.funshion.bean.romrecovery.RomRecoveryInfo;
import com.funshion.dao.romrecovery.RomRecoveryDao;

public class RomRecoveryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String romVer = req.getParameter("romVer");
		String chipType = req.getParameter("chipType");
		String brand = req.getParameter("brand");
		String distinct = req.getParameter("distinct");

		List<RomRecoveryInfo> infos = RomRecoveryDao.getRomRecoveryInfos(romVer, chipType, brand, distinct);
		resp.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.parseArray(JSON.toJSONString(infos));
		PrintWriter out = resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
