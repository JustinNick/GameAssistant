package com.example.johncheng.gameassistant.module.mygame.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;

import java.util.ArrayList;

public class MygameActivity extends BaseActivity implements View.OnClickListener {


    private AllgameFragment allgameFragment;
    private LastgameFragment lastgameFragment;
    private NotplayFragment notplayFragment;
    private ArrayList<Fragment> mlistGames;
    private ViewPager vpMygames;
    private View mviewHeader;
    private ImageView mivMygameBack;

    @Override
    protected int setViewId() {
        return R.layout.activity_mygame;
    }

    @Override
    protected void findViews() {
        vpMygames = (ViewPager) findViewById(R.id.vpmygames);
        mivMygameBack = (ImageView) findViewById(R.id.iv_mygame_back);
    }

    @Override
    protected void init() {
        allgameFragment = new AllgameFragment();
        lastgameFragment = new LastgameFragment();
        notplayFragment = new NotplayFragment();
        mlistGames = new ArrayList<>();
        mlistGames.add(allgameFragment);
        mlistGames.add(notplayFragment);
        mlistGames.add(lastgameFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(allgameFragment);
        transaction.show(lastgameFragment);
        transaction.show(notplayFragment);
        vpMygames.setAdapter(new MygameAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initEvents() {
        mivMygameBack.setOnClickListener(this);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_mygame_back:
                finish();
                break;
        }
    }

    class MygameAdapter extends FragmentPagerAdapter{

        public MygameAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mlistGames.get(position);
        }

        @Override
        public int getCount() {
            return mlistGames.size();
        }
    }

}
