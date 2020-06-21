package com.prometheus.ledger.core.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.prometheus.ledger.service.common.session.model.SessionObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
    public static JSONArray toJsonArray(List<Object> list) {
        if (CollectionUtil.isEmpty(list)){
            return null;
        }

        JSONArray json = null;
        try {
            JSONParser parser = new JSONParser();
            json = (JSONArray) parser.parse(toJsonString(list));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONObject toJsonObject(Object object) {
        if (null == object){
            return null;
        }
        JSONObject json = null;
        try {
            JSONParser parser = new JSONParser();
            json = (JSONObject) parser.parse(toJsonString(object));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String toJsonString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static JSONObject convertStringToJSONObject(String jsonString){
        JSONObject obj = null;
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(jsonString);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return obj;
    }

}
