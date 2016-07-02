package com.example.johncheng.gameassistant.module.money.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseFragment;
import com.example.johncheng.gameassistant.base.ListviewCallback;
import com.example.johncheng.gameassistant.common.adapter.CommonAdapter;
import com.example.johncheng.gameassistant.common.adapter.ViewHolder;
import com.example.johncheng.gameassistant.module.money.adapter.MyMutilAdapter;
import com.example.johncheng.gameassistant.module.money.bean.TaskGameInfo;
import com.example.johncheng.gameassistant.module.money.dao.MoneyDao;
import com.example.johncheng.gameassistant.module.mygame.ui.MygameActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncheng on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private boolean mbLoad;
    private PopupWindow mpwLoad;
    private View mviewPW;
    private View mviewFragment;
    private ListView mlvTaskGame;
    private ArrayList<TaskGameInfo.InfoBean> mListGame;
    //    private CommonAdapter<TaskGameInfo.InfoBean> mAdapter;
    private MyMutilAdapter myMutilAdapter;
    private View headView;
    private View gametaskHeader;

    public void showLoadDialog() {
        if (!mbLoad && (mpwLoad == null)) {
            mpwLoad = new PopupWindow(mviewPW,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, false);
            mpwLoad.showAtLocation(mviewFragment, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    protected int setViewId() {
        return R.layout.layout_fragment_money;
    }

    @Override
    protected void findViews(View view) {
        mviewFragment = view;
        mlvTaskGame = (ListView) view.findViewById(R.id.lv_taskgame);
    }

    @Override
    protected void init() {
        mviewPW = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_pw_money, null);
//        headView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_task_header,null);
//        mlvTaskGame.addHeaderView(headView);

        mListGame = new ArrayList<>();
//        mAdapter = new CommonAdapter<TaskGameInfo.InfoBean>(getActivity(), mListGame, R.layout.layout_game_platform) {
//
//            @Override
//            public void convert(ViewHolder helper, int position, TaskGameInfo.InfoBean item) {
//                helper.setText(R.id.tv_gametitle,item.getPlatform_name());
//                helper.setImageByUrl(R.id.iv_gamelogo,item.getAd_img(),getActivity());
//                helper.setRating(R.id.rb_score,Integer.parseInt(item.getRank()));
//                helper.setText(R.id.tv_gamecontent,item.getDesc());
//                helper.setText(R.id.btn_gameaward,item.getReward());
//            }
//        };
//        mlvTaskGame.setAdapter(mAdapter);

        myMutilAdapter = new MyMutilAdapter(mListGame, this.getActivity());
        mlvTaskGame.setAdapter(myMutilAdapter);

    }

    @Override
    protected void initEvents() {
//        headView.setOnClickListener(this);
//        gametaskHeader = headView.findViewById(R.id.gametask_header);
//        gametaskHeader.setOnClickListener(this);

        mlvTaskGame.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        MoneyDao.getMoneyData(new ListviewCallback() {

            @Override
            public void updateListview(Object object) {
                List<TaskGameInfo.InfoBean> datas = (List<TaskGameInfo.InfoBean>) object;
                mListGame.addAll(datas);
//                mAdapter.notifyDataSetChanged();
                myMutilAdapter.notifyDataSetChanged();
                mbLoad = true;
                if (mpwLoad != null) {
                    mpwLoad.dismiss();
                    mpwLoad = null;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
//        startActivity(new Intent("tiaozhuandaoMygameactivity"));
        startActivity(new Intent(getActivity(), MygameActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 1:
                startActivity(new Intent(getActivity(), MygameActivity.class));
                break;
        }
    }
}
