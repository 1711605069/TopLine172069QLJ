package com.example.topline172069qlj.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.topline172069qlj.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


}

//    //P94
//    private void getADData() {
//
//        Request request=new Request.Builder().url(Constant.WEB_SLTE+Constant.REQUEST_AD_URL).build();
//        Call call=okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                   String res=response.body().toString();
//                  // Log.i("AD",res);
//                Message msg=new Message();
//                msg.what=1;
//                msg.obj=res;
//                mHandle.sendMessage(msg);
//            }
//        });
//    }
//
//class Mhandle extends Handler {
//        @Override
//    public void dispatchMessage(Message msg) {
//            super.dispatchMessage(msg);
//                switch (msg.what) {
//                    case 1:
//                        String adResult = (String) msg.obj;
//                        // Toast.makeText(getContext(),adResult,Toast.LENGTH_SHORT).show();
//                        List<NewsBean> ad1 = JsonParse.getInstance().getAdList(adResult);
//                }
//            }




