package com.funshion.dao.fastapp;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.funshion.bean.fastapp.FastAppInfo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FastAppInfoDao {

	public static List<FastAppInfo> getFastAppInfos() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("fast_app");
		MongoCollection<Document> appsInfo = database.getCollection("apps_info");
		FindIterable iterable = appsInfo.find();
		MongoCursor<Document> cursor = iterable.iterator();
		List<FastAppInfo> infos = new ArrayList<FastAppInfo>();
		while (cursor.hasNext()) {
			FastAppInfo info = new FastAppInfo();
			Document doc = cursor.next();
			info.setDate(doc.getString("date"));
			info.setAppStartTimes(doc.getInteger("app_start_times"));
			info.setUserCountApps(doc.getInteger("user_count_apps"));
			info.setAppCounts(doc.getInteger("app_counts"));
			infos.add(info);
		}
		return infos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getFastAppInfos());
	}
}
