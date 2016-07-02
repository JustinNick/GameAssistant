package com.example.johncheng.gameassistant.module.mygame.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;

import java.util.ArrayList;

public class MygameActivity extends BaseActivity implements View.OnClickListener {


    private AllgameFragment allgameFragment;
    private LastgameFragment lastgameFragment;
    private NotplayFragment notplayFragment;
    private ArrayList<Fragment> mlistGames;
    private ViewPager mvpMygames;
    private ImageView mivMygameBack;
    private RadioGroup mRGMygame;

    @Override
    protected int setViewId() {
        return R.layout.activity_mygame;
    }

    @Override
    protected void findViews() {
        mvpMygames = (ViewPager) findViewById(R.id.vpmygames);
        mivMygameBack = (ImageView) findViewById(R.id.iv_mygame_back);
        mRGMygame = (RadioGroup) findViewById(R.id.rg_mygame);

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
        mvpMygames.setAdapter(new MygameAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void initEvents() {
        mivMygameBack.setOnClickListener(this);
        mRGMygame.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_allgame:
                      mvpMygames.setCurrentItem(0);
                        break;
                    case R.id.rb_notplay:
                        mvpMygames.setCurrentItem(1);
                        break;
                    case R.id.rb_lastgame:
                        mvpMygames.setCurrentItem(2);
                        break;
                }
            }
        });

        mvpMygames.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mRGMygame.check(R.id.rb_allgame);
                        break;
                    case 1:
                        mRGMygame.check(R.id.rb_notplay);
                        break;
                    case 2:
                        mRGMygame.check(R.id.rb_lastgame);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
