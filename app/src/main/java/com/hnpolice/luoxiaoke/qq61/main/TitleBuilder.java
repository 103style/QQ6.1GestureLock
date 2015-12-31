package com.hnpolice.luoxiaoke.qq61.main;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnpolice.luoxiaoke.qq61.R;

/**
 * @author luoxiaoke 2015-12-20 21:20
 * @describe   标题栏构造器
 */
public class TitleBuilder {
    private View titleView ;
    private ImageView titlebarIvLeft;
    private TextView titlebarTv;
    private ImageView titlebarIvRight;
    private TextView titlebarTvRight;
    private Button titlebarBtnRight;

    /**
     * Activity 中标题栏构造器
     * @param context
     */
    public TitleBuilder(Activity context) {
        titleView = context.findViewById(R.id.app_title_layout);
        titlebarIvLeft = (ImageView) titleView.findViewById(R.id.titlebar_iv_left);
        titlebarTv = (TextView) titleView.findViewById(R.id.titlebar_tv);
        titlebarIvRight = (ImageView) titleView.findViewById(R.id.titlebar_iv_right);
        titlebarTvRight = (TextView) titleView.findViewById(R.id.titlebar_tv_right);
        titlebarBtnRight = (Button) titleView.findViewById(R.id.titlebar_btn_right);
    }

    /**
     * Fragment 中标题栏构造器
     * @param context
     */
    public TitleBuilder(View context) {
        titleView = context.findViewById(R.id.app_title_layout);
        titlebarIvLeft = (ImageView) titleView.findViewById(R.id.titlebar_iv_left);
        titlebarTv = (TextView) titleView.findViewById(R.id.titlebar_tv);
        titlebarIvRight = (ImageView) titleView.findViewById(R.id.titlebar_iv_right);
        titlebarTvRight = (TextView) titleView.findViewById(R.id.titlebar_tv_right);
    }

    /**
     * 设置标题
     * @param text
     * @return
     */
    public TitleBuilder setTitleText(String text) {
        titlebarTv.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        titlebarTv.setText(text);
        return this;
    }

    // left
    /**
     * 设置默认的返回按钮
     * @return
     */
    public TitleBuilder setLeftImage() {
        titlebarIvLeft.setVisibility(View.VISIBLE);
        titlebarIvLeft.setImageResource(R.drawable.btn_back);
        return this;
    }

    /**
     * 设置左边自定义图片
     * @param resId
     * @return
     */
    public TitleBuilder setLeftImage(int resId) {
        titlebarIvLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titlebarIvLeft.setImageResource(resId);
        return this;
    }
    /**
     * 设置左边控件监听
     * @return
     */
    public TitleBuilder setLeftOnClickListener(View.OnClickListener listener) {
        if (titlebarIvLeft.getVisibility() == View.VISIBLE) {
            titlebarIvLeft.setOnClickListener(listener);
        }
        return this;
    }

    // right

    /**
     * 设置右边自定义图片
     * @param resId
     * @return
     */
    public TitleBuilder setRightImage(int resId) {
        titlebarIvRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titlebarIvRight.setImageResource(resId);
        return this;
    }

    /**
     * 设置右边自定义图片
     * @param resId
     * @return
     */
    public TitleBuilder setRightBackground(int resId) {
        titlebarIvRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titlebarIvRight.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置右边文字控件
     * @param text
     * @return
     */
    public TitleBuilder setRightText(String text) {
        titlebarTvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        titlebarTvRight.setText(text);
        return this;
    }

    /**
     * 设置右边文字背景
     * @param text
     * @return
     */
    public TitleBuilder setRighTButton(String text) {
        titlebarBtnRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        titlebarBtnRight.setText(text);
        return this;
    }

    /**
         * 设置右边Button控件监听
         * @return
         */
    public TitleBuilder setRightBtnOnClickListener(View.OnClickListener listener) {
        if (titlebarBtnRight.getVisibility() == View.VISIBLE) {
            titlebarBtnRight.setOnClickListener(listener);
        }
        return this;
    }


    /**
     * 设置左边控件监听
     * @return
     */
    public TitleBuilder setRightOnClickListener(View.OnClickListener listener) {
        if (titlebarIvRight.getVisibility() == View.VISIBLE) {
            titlebarIvRight.setOnClickListener(listener);
        } else if (titlebarTvRight.getVisibility() == View.VISIBLE) {
            titlebarTvRight.setOnClickListener(listener);
        }
        return this;
    }

}