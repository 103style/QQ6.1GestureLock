package com.hnpolice.luoxiaoke.gesturelock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class CheckActivity extends AppCompatActivity {


    @ViewInject(R.id.text_tip)
    TextView mTextTip;
    @ViewInject(R.id.gesture_container)
    FrameLayout mGestureContainer;

    private GestureContentView mGestureContentView;
    //设置的手势密码
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ViewUtils.inject(this);
        Intent intent = getIntent();
        password = intent.getStringExtra("password");
        Log.e("Password", password);
        initViews();
    }

    private void initViews() {
        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, password,
                new GestureDrawline.GestureCallBack() {
                    @Override
                    public void onGestureCodeInput(String inputCode) {
                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
                        Toast.makeText(CheckActivity.this, "密码正确", 1000).show();
                        CheckActivity.this.finish();
                    }

                    @Override
                    public void checkedFail() {
                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);
                        mTextTip.setText(Html
                                .fromHtml("<font color='#ff0000'>密码错误</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(CheckActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

}
