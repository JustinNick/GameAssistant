package com.example.johncheng.gameassistant.module.money.dao;

import com.example.johncheng.gameassistant.base.ListviewCallback;
import com.example.johncheng.gameassistant.common.constant.Constant;
import com.example.johncheng.gameassistant.module.money.bean.TaskGameInfo;
import com.google.gson.Gson;

/**
 * Created by johncheng on 2016/6/29.
 */
public class MoneyDao {

    public static void getMoneyData(final ListviewCallback listviewCallback){
        Gson gson = new Gson();
        TaskGameInfo taskGameInfo = gson.fromJson(Constant.GAME_TASK_LIST_JSON, TaskGameInfo.class);
        listviewCallback.updateListview(taskGameInfo.getInfo());
    }
}
