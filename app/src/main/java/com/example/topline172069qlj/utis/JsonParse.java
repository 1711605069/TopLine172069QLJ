package com.example.topline172069qlj.utis;

import com.example.topline172069qlj.bean.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {//p68 单例模式，只有一个对象 对象由构造方法产生
    private static JsonParse instance;
    private JsonParse(){  //构造方法私有化，类外部不能调用，拒绝创建对象
    }
    public static  JsonParse getInstance(){
        if (instance==null){
            instance=new JsonParse();
        }
        return instance;
    }
    //P95读数据
    public List<NewsBean> getAdList(String json){
        Gson gson=new Gson();
        Type ListType=new TypeToken<List<NewsBean>>(){}.getType();
        List<NewsBean> alList=gson.fromJson(json,ListType);
        return alList;

    }
    public List<NewsBean> getNewsList(String json){
        //使用gson库解析JSON数据
        Gson gson=new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType（）方法
        Type listType=new TypeToken<List<NewsBean>>(){
        }.getType();
        //把获取到的信息集合存到newsList中
        List<NewsBean> newsList=gson.fromJson(json,listType);
        return newsList;
    }
}
