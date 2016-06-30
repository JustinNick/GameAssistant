package com.example.johncheng.gameassistant.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johncheng.gameassistant.R;

/**
 * Created by johncheng on 2016/6/28.
 */
public class BottomMenu extends LinearLayout {

    private int miNormalPic;
    private int miPressPic;
    private View mviewMenu;
    private ImageView mivMenu;
    private TextView mtvMenu;
    private boolean bSelect;

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mviewMenu = LayoutInflater.from(context).inflate(R.layout.layout_bottommenu, this, true);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomMenu);
        String title = (String) typedArray.getText(R.styleable.BottomMenu_Text);
        miNormalPic = typedArray.getResourceId(R.styleable.BottomMenu_normalPic, -1);
        miPressPic = typedArray.getResourceId(R.styleable.BottomMenu_pressPic, -1);
        mivMenu = (ImageView) mviewMenu.findViewById(R.id.ivbottommenu);
        mtvMenu = (TextView) mviewMenu.findViewById(R.id.tvbottommenu);
        mtvMenu.setText(title);
        mivMenu.setImageResource(miNormalPic);
        typedArray.recycle();
    }
    public void onSelect(){
        if (bSelect){
            return;
        }
        bSelect=true;
        mivMenu.setImageResource(miPressPic);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0
                , Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1.2f);
        translateAnimation.setDuration(200);
        translateAnimation.setFillAfter(true);
        mtvMenu.startAnimation(translateAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.2f, 1, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.1f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        mivMenu.startAnimation(scaleAnimation);
    }
   public void unSelect(){
       bSelect=false;
       mivMenu.setImageResource(miNormalPic);
       TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
               Animation.RELATIVE_TO_SELF, 0
               , Animation.RELATIVE_TO_SELF, 1.2f,
               Animation.RELATIVE_TO_SELF, 0);
       translateAnimation.setDuration(200);
       mtvMenu.startAnimation(translateAnimation);

       ScaleAnimation scaleAnimation = new ScaleAnimation(1.1f, 1, 1.f, 1,
               Animation.RELATIVE_TO_SELF, 0.5f,
               Animation.RELATIVE_TO_SELF, 0);
       scaleAnimation.setDuration(200);
       mivMenu.startAnimation(scaleAnimation);
    }
}
