package com.example.johncheng.gameassistant.module.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;
import com.example.johncheng.gameassistant.common.widget.BottomMenu;
import com.example.johncheng.gameassistant.module.guess.ui.GuessFragment;
import com.example.johncheng.gameassistant.module.home.ui.HomeFragment;
import com.example.johncheng.gameassistant.module.me.ui.MeFragment;
import com.example.johncheng.gameassistant.module.money.ui.MoneyFragment;
import com.example.johncheng.gameassistant.module.shop.ui.ShopFragment;

public class MainActivity extends BaseActivity {

    BottomMenu mLastSelMenu = null;
    private View mMenuHome;
    private HomeFragment mhomeFragment;
    private MoneyFragment mmoneyFragment;
    private GuessFragment mguessFragment;
    private ShopFragment mshopFragment;
    private MeFragment mmeFragment;
    private Fragment mLastSelFragment;

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mLastSelMenu = (BottomMenu) findViewById(R.id.menu_home);
    }

    @Override
    protected void init() {
        mLastSelMenu.onSelect();

        mhomeFragment = new HomeFragment();
        mmoneyFragment = new MoneyFragment();
        mguessFragment = new GuessFragment();
        mshopFragment = new ShopFragment();
        mmeFragment = new MeFragment();
        mLastSelFragment = mhomeFragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mmoneyFragment);
        transaction.hide(mmoneyFragment);
        transaction.add(R.id.fragment_container, mguessFragment);
        transaction.hide(mguessFragment);
        transaction.add(R.id.fragment_container, mshopFragment);
        transaction.hide(mshopFragment);
        transaction.add(R.id.fragment_container, mmeFragment);
        transaction.hide(mmeFragment);
        transaction.add(R.id.fragment_container, mhomeFragment);
        transaction.commit();

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void loadData() {

    }

    public void choose(View view) {
        BottomMenu bottomMenu = (BottomMenu) view;
        bottomMenu.onSelect();
        if (!bottomMenu.equals(mLastSelMenu)) {
            mLastSelMenu.unSelect();
        }
        mLastSelMenu = bottomMenu;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.menu_home:
                if (!(mLastSelFragment instanceof HomeFragment)) {
                    transaction.hide(mLastSelFragment);
                }
                mLastSelFragment = mhomeFragment;
                transaction.show(mhomeFragment);
                break;
            case R.id.menu_money:
                if (!(mLastSelFragment instanceof MoneyFragment)) {
                    transaction.hide(mLastSelFragment);
                }
                mLastSelFragment = mmoneyFragment;
                transaction.show(mmoneyFragment);
                mmoneyFragment.showLoadDialog();
                break;
            case R.id.menu_guess:
                if (!(mLastSelFragment instanceof GuessFragment)) {
                    transaction.hide(mLastSelFragment);
                }
                mLastSelFragment = mguessFragment;
                transaction.show(mguessFragment);
                break;
            case R.id.menu_shop:
                if (!(mLastSelFragment instanceof ShopFragment)) {
                    transaction.hide(mLastSelFragment);
                }
                mLastSelFragment = mshopFragment;
                transaction.show(mshopFragment);
                break;
            case R.id.menu_me:
                if (!(mLastSelFragment instanceof MeFragment)) {
                    transaction.hide(mLastSelFragment);
                }
                mLastSelFragment = mmeFragment;
                transaction.show(mmeFragment);
                break;

        }
        transaction.commit();
    }

}











