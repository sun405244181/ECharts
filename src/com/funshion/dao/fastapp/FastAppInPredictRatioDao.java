package com.funshion.dao.fastapp;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.funshion.bean.fastapp.FastAppPredictRatio;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FastAppInPredictRatioDao {

	public static List<FastAppPredictRatio> getFastAppPredictRatios() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("fast_app");
		MongoCollection<Document> appsInfo = database.getCollection("predict_ratio");
		FindIterable iterable = appsInfo.find();
		MongoCursor<Document> cursor = iterable.iterator();
		List<FastAppPredictRatio> infos = new ArrayList<FastAppPredictRatio>();
		while (cursor.hasNext()) {
			FastAppPredictRatio info = new FastAppPredictRatio();
			Document doc = cursor.next();
			info.setDate(doc.getString("date"));
			info.setAppOpenTimesRatio(doc.getDouble("app_open_times_ratio"));
			info.setTargetUserRatio(doc.getDouble("target_user_ratio"));
			info.setTargetUserAppRatio(doc.getDouble("target_user_app_ratio"));
			infos.add(info);
		}
		return infos;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getFastAppPredictRatios());
	}
}
