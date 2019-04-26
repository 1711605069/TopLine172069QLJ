package com.example.topline172069qlj.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UtilsHelper {
    public static int getScreenWidth(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
