package com.example.johncheng.gameassistant.module.mygame.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseFragment;
import com.example.johncheng.gameassistant.base.ListviewCallback;
import com.example.johncheng.gameassistant.common.adapter.CommonAdapter;
import com.example.johncheng.gameassistant.common.adapter.ViewHolder;
import com.example.johncheng.gameassistant.module.mygame.bean.MygameInfo;
import com.example.johncheng.gameassistant.module.mygame.dao.MygameDao;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncheng on 2016/6/30.
 */
public class AllgameFragment extends BaseFragment {


    private PullToRefreshListView mrlvGames;
    private CommonAdapter<MygameInfo.InfoBean> commonAdapter;
    private ArrayList<MygameInfo.InfoBean> mlistGames;
    private int miCurPage =1;

    @Override
    protected int setViewId() {
        return R.layout.layout_fragment_allgame;
    }

    @Override
    protected void findViews(View view) {
        mrlvGames = (PullToRefreshListView) view.findViewById(R.id.ptr_listview);

    }

    @Override
    protected void init() {
        mrlvGames.setMode(PullToRefreshBase.Mode.BOTH);
        mlistGames = new ArrayList<>();
        commonAdapter = new CommonAdapter<MygameInfo.InfoBean>(this.getActivity(),mlistGames,R.layout.layout_mygame_view) {

            @Override
            public void convert(ViewHolder helper, int position, MygameInfo.InfoBean item) {

                helper.setText(R.id.tv_mygametitle,item.getName());
                helper.setImageByUrl(R.id.iv_mygamelogo,item.getIcon(),getActivity());
                helper.setText(R.id.tv_mygame_award,"奖"+item.getAll_prize()+"U币");
                helper.setRating(R.id.rb_myscore,Math.round(Float.parseFloat(item.getScore())));
                helper.setText(R.id.tv_mygamecontent,item.getCount_dl()+"人下载  "+item.getSize());
            }
        };
        mrlvGames.setAdapter(commonAdapter);
    }

    @Override
    protected void initEvents() {
mrlvGames.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        miCurPage=1;
        mlistGames.clear();
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        miCurPage++;
        loadData();
    }
});
    }

    @Override
    protected void loadData() {
        MygameDao.getMyAllgameInfo(miCurPage, new ListviewCallback() {

            @Override
            public void updateListview(Object object) {
                List<MygameInfo.InfoBean> datas= (List<MygameInfo.InfoBean>) object;
                mlistGames.addAll(datas);
                commonAdapter.notifyDataSetChanged();
                mrlvGames.onRefreshComplete();
            }
        });
    }

}
