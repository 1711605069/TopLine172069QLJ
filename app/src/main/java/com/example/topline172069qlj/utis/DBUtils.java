package com.example.topline172069qlj.utis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.topline172069qlj.sqlite.SQLiteHelper;

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;

    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }

    public boolean userIsExist(String username) {
        boolean result = false;
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + "  WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor.getCount() > 0) {
            result = true;
        }
        return result;
    }

    /*
    用户注册
     */
    public boolean userRegister(String username, String password) {
        boolean result = false;
        ContentValues cv = new ContentValues();
        cv.put("userName", username);
        cv.put("password", password);
        long id = db.insert(SQLiteHelper.U_USERINFO, null, cv);
        if (id > 0) {
            result = true;
        }
        return result;
    }

    /*
    用户登陆
     */
    public boolean userLogin(String username, String password) {
        boolean result = false;
        String sql = "select * from "+ SQLiteHelper.U_USERINFO +" where username = ? and password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if (cursor.getCount() > 0) {
            result = true;
        }
        return result;
    }


}
