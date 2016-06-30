package com.example.johncheng.gameassistant.module.mygame.dao;

import com.example.johncheng.gameassistant.base.ListviewCallback;
import com.example.johncheng.gameassistant.base.NetCallback;
import com.example.johncheng.gameassistant.common.constant.Constant;
import com.example.johncheng.gameassistant.common.net.HttpNet;
import com.example.johncheng.gameassistant.module.mygame.bean.MygameInfo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by johncheng on 2016/6/30.
 */
public class MygameDao {

    private static HashMap<String, String> params;

    public static void getMyAllgameInfo(int iPage, final ListviewCallback listviewCallback){
        params = new HashMap<>();
        params.put("platform","2");
        params.put("page",iPage+"");

        HttpNet.doHttpRequest("POST", Constant.MONEY_URL, params, new NetCallback() {

            @Override
            public void success(String strResult) {
                Gson gson = new Gson();
                MygameInfo mygameInfo = gson.fromJson(strResult, MygameInfo.class);
                List<MygameInfo.InfoBean> datas = mygameInfo.getInfo();
                listviewCallback.updateListview(datas);
            }

            @Override
            public void fail(String strFail) {

            }
        });

    }

}
