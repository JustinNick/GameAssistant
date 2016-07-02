package com.example.johncheng.gameassistant.module.money.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.johncheng.gameassistant.R;
import com.example.johncheng.gameassistant.module.money.bean.TaskGameInfo;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncheng on 2016/7/1.
 */
public class MyMutilAdapter extends BaseAdapter {


    private final List<TaskGameInfo.InfoBean> mlistDatas;
    private final Context mContext;
    private int TYPE_TITLE = 0;
    private int TYPE_PLATFORM = 1;
    private Bitmap bitmap;

    public MyMutilAdapter(List<TaskGameInfo.InfoBean> listDatas, Context context) {
        mlistDatas = listDatas;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mlistDatas.size() + 3;
    }

    @Override
    public Object getItem(int position) {
        return mlistDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int iTYPE = getItemViewType(position);
        if (convertView == null) {
            if (iTYPE == TYPE_TITLE) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_game_title, null);
                TitleHolder titleHolder = new TitleHolder();
                titleHolder.mtvTitleName = (TextView) convertView.findViewById(R.id.tvtitle);
                titleHolder.mivTitleName = (ImageView) convertView.findViewById(R.id.ivtitlepic);
                if (position == 0) {
                    titleHolder.mtvTitleName.setText("官方推荐");
                    titleHolder.mivTitleName.setImageResource(R.drawable.ic_groom);
                } else {
                    titleHolder.mtvTitleName.setText("联盟任务");
                    titleHolder.mivTitleName.setImageResource(R.drawable.ic_lm);

                }
                convertView.setTag(titleHolder);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_game_platform, null);
                PlatformHolder platformHolder = new PlatformHolder();
                platformHolder.mtvPlatformTitle = (TextView) convertView.findViewById(R.id.tv_gametitle);
                platformHolder.mivPlatformPic = (ImageView) convertView.findViewById(R.id.iv_gamelogo);
                platformHolder.mtvPlatformcontent = (TextView) convertView.findViewById(R.id.tv_gamecontent);
                platformHolder.mtvPlatformAward = (TextView) convertView.findViewById(R.id.btn_gameaward);
                platformHolder.mrbPlatformScoce = (RatingBar) convertView.findViewById(R.id.rb_score);
                if (position == 1) {
                    platformHolder.mtvPlatformTitle.setText("游戏任务");
                    platformHolder.mivPlatformPic.setImageResource(R.drawable.ic_icon);
                } else {
                    platformHolder.mtvPlatformTitle.setText(mlistDatas.get(position - 3).getPlatform_name());
                    platformHolder.mtvPlatformcontent.setText(mlistDatas.get(position - 3).getDesc());
                    platformHolder.mtvPlatformAward.setText(mlistDatas.get(position - 3).getReward());
                    platformHolder.mrbPlatformScoce.setRating(Float.parseFloat(mlistDatas.get(position - 3).getRank()));
                    Picasso.with(mContext).load(mlistDatas.get(position - 3).getAd_img()).into(platformHolder.mivPlatformPic);
                }
                convertView.setTag(platformHolder);
            }
        } else {
            if (iTYPE == TYPE_TITLE) {
                TitleHolder titleHolder = (TitleHolder) convertView.getTag();
                if (position == 0) {
                    titleHolder.mtvTitleName.setText("官方推荐");
                    titleHolder.mivTitleName.setImageResource(R.drawable.ic_groom);

                } else {
                    titleHolder.mtvTitleName.setText("联盟任务");
                    titleHolder.mivTitleName.setImageResource(R.drawable.ic_lm);
                }
            } else {
                PlatformHolder platformHolder = (PlatformHolder) convertView.getTag();
                if (position == 1) {
                    platformHolder.mtvPlatformTitle.setText("游戏任务");
                    platformHolder.mivPlatformPic.setImageResource(R.drawable.ic_icon);
                } else {
                    platformHolder.mtvPlatformTitle.setText(mlistDatas.get(position - 3).getPlatform_name());
                    platformHolder.mtvPlatformcontent.setText(mlistDatas.get(position - 3).getDesc());
                    platformHolder.mtvPlatformAward.setText(mlistDatas.get(position - 3).getReward());
                    Picasso.with(mContext).load(mlistDatas.get(position - 3).getAd_img()).into(platformHolder.mivPlatformPic);
                }
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2) {
            return TYPE_TITLE;
        } else {
            return TYPE_PLATFORM;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class TitleHolder {

        ImageView mivTitleName;
        TextView mtvTitleName;
    }

    class PlatformHolder {

        ImageView mivPlatformPic;
        TextView mtvPlatformTitle;
        TextView mtvPlatformcontent;
        TextView mtvPlatformAward;
        RatingBar mrbPlatformScoce;
    }

}
