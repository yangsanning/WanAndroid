package ysn.com.wanandroid.model.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ysn.com.wanandroid.model.bean.Article;

/**
 * @Author yangsanning
 * @ClassName RoomConversionFactory
 * @Description 数据库适配器
 * @Date 2020/5/30
 */
public class RoomConversionFactory {

    @TypeConverter
    public static String fromHashMapToString(HashMap<String, String> hashMap) {
        JSONArray jsonArray = new JSONArray();
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            try {
                Map.Entry<String, String> next = iterator.next();
                JSONObject jsonObject = new JSONObject();
                jsonArray.put(jsonObject.put(next.getKey(), next.getValue()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    @TypeConverter
    public static HashMap<String, String> fromStringToHashMap(String value) {
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                for (Iterator<String> iterator = jsonArray.getJSONObject(i).keys(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    hashMap.put(key, jsonArray.getJSONObject(i).getString(key));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }


    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String listToString(List<Article.TagsBean> objectList) {
        if (objectList == null) {
            return "";
        }
        return new Gson().toJson(objectList);
    }

    @TypeConverter
    public List<Article.TagsBean> stringToList(String objectString) {
        if (objectString == null) {
            return new ArrayList<>();
        }
        return new Gson().fromJson(objectString, new TypeToken<List<Article.TagsBean>>() {
        }.getType());
    }
}
