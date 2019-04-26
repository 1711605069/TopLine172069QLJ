package com.example.topline172069qlj.utils;

import com.example.topline172069qlj.bean.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse() {
    }
    public static JsonParse getInstance(){
        if (instance==null){
            instance=new JsonParse();
        }
        return instance;
    }
    public List<NewsBean> getAdList(String json){
        Gson gson=new Gson();//使用gson库解析JSON数据
        Type listType=new TypeToken<List<NewsBean>>(){
        }.getType();
        List<NewsBean> adList=gson.fromJson(json,listType);
        return  adList;
    }

}
