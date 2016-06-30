package com.example.johncheng.gameassistant.module.money.ui;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseFragment;
import com.example.johncheng.gameassistant.base.ListviewCallback;
import com.example.johncheng.gameassistant.common.adapter.CommonAdapter;
import com.example.johncheng.gameassistant.common.adapter.ViewHolder;
import com.example.johncheng.gameassistant.module.money.bean.TaskGameInfo;
import com.example.johncheng.gameassistant.module.money.dao.MoneyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncheng on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment {

    private boolean mbLoad;
    private PopupWindow mpwLoad;
    private View mviewPW;
    private View mviewFragment;
    private ListView mlvTaskGame;
    private ArrayList<TaskGameInfo.InfoBean> mListGame;
    private CommonAdapter<TaskGameInfo.InfoBean> mAdapter;

    public void showLoadDialog(){
        if (!mbLoad&&(mpwLoad==null)){
            mpwLoad=new PopupWindow(mviewPW,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,false);
            mpwLoad.showAtLocation(mviewFragment,Gravity.CENTER,0,0);
        }
    }

    @Override
    protected int setViewId() {
        return R.layout.layout_fragment_money;
    }

    @Override
    protected void findViews(View view) {
        mviewFragment=view;
        mlvTaskGame = (ListView) view.findViewById(R.id.lv_taskgame);
    }

    @Override
    protected void init() {
        mviewPW = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_pw_money, null);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_task_header,null);
        mlvTaskGame.addHeaderView(headView);

        mListGame = new ArrayList<>();
        mAdapter = new CommonAdapter<TaskGameInfo.InfoBean>(getActivity(), mListGame, R.layout.layout_money_item) {

            @Override
            public void convert(ViewHolder helper, int position, TaskGameInfo.InfoBean item) {
                helper.setText(R.id.tv_gametitle,item.getPlatform_name());
                helper.setImageByUrl(R.id.iv_gamelogo,item.getAd_img(),getActivity());
                helper.setRating(R.id.rb_score,Integer.parseInt(item.getRank()));
                helper.setText(R.id.tv_gamecontent,item.getDesc());
                helper.setText(R.id.btn_gameaward,item.getReward());
            }
        };
        mlvTaskGame.setAdapter(mAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void loadData() {
        MoneyDao.getMoneyData(new ListviewCallback() {

            @Override
            public void updateListview(Object object) {
                List<TaskGameInfo.InfoBean> datas= (List<TaskGameInfo.InfoBean>) object;
                mListGame.addAll(datas);
                mAdapter.notifyDataSetChanged();
                mbLoad=true;
                if (mpwLoad!=null){
                    mpwLoad.dismiss();
                    mpwLoad=null;
                }
            }
        });
    }
}
