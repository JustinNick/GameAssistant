package com.example.johncheng.gameassistant.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.base.BaseActivity;
import com.example.johncheng.gameassistant.common.constant.Constant;
import com.se7en.utils.SystemUtil;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity {

    private ViewPager mvpWelcome;
    private ArrayList<ImageView> mlistPics;
    private Button btnFeel;
    private int miCurVersion;
    private int miLastVersion;
    private ImageView mivTextLogo;
    private ImageView mivImageLogo;
    private TranslateAnimation translateAnimation;

    @Override
    protected void findViews() {
        mvpWelcome = (ViewPager) findViewById(R.id.vp_welcome);
        btnFeel = (Button) findViewById(R.id.btn_startfeel);
        mivTextLogo = (ImageView) findViewById(R.id.ivTextLogo);
        mivImageLogo = (ImageView) findViewById(R.id.ivImageLogo);
    }

    @Override
    protected void init() {
        miCurVersion = SystemUtil.getSystemVersionCode();
        miLastVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING, -1);
        if (miLastVersion == -1 || miCurVersion > miLastVersion) {
            mlistPics = new ArrayList<>();
            addImageView(R.mipmap.bg_guide_01);
            addImageView(R.mipmap.bg_guide_02);
            addImageView(R.mipmap.bg_guide_03);
            addImageView(R.mipmap.bg_guide_04);
            mvpWelcome.setAdapter(new PagerAdapter() {

                @Override
                public int getCount() {
                    return mlistPics.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                    container.addView(mlistPics.get(position));
                    return mlistPics.get(position);
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                    container.removeView(mlistPics.get(position));
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });
        } else {
            mvpWelcome.setVisibility(View.GONE);
            mivTextLogo.setVisibility(View.VISIBLE);
            mivImageLogo.setVisibility(View.VISIBLE);
            startTextTranslateAnim();
            startImageTranslateAnim();

        }

    }

    @Override
    protected void initEvents() {
        if (miLastVersion == -1 || miCurVersion > miLastVersion) {
            mvpWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == mlistPics.size() - 1) {
                        btnFeel.setVisibility(View.VISIBLE);
                        startFeel();
                    } else {
                        btnFeel.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    private void startFeel() {
        btnFeel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SystemUtil.setSharedInt(Constant.VERSION_STRING,miCurVersion);
                mvpWelcome.setVisibility(View.GONE);
                btnFeel.setVisibility(View.GONE);
                mivTextLogo.setVisibility(View.VISIBLE);
                startTextTranslateAnim();
                mivImageLogo.setVisibility(View.VISIBLE);
                startImageTranslateAnim();

            }
        });
    }

    private void startImageTranslateAnim() {
        translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new BounceInterpolator());
        mivImageLogo.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (SystemUtil.getSharedBoolean(Constant.LOGIN_FLAG,false)){
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                }else{
                    startActivity(new Intent(WelcomeActivity.this, RBActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startTextTranslateAnim() {
        translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        mivTextLogo.startAnimation(translateAnimation);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_welcome;
    }

    protected void addImageView(int iResId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(iResId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mlistPics.add(imageView);
    }
}
