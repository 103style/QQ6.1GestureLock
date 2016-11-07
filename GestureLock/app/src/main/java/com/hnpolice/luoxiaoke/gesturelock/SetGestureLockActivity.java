package com.hnpolice.luoxiaoke.gesturelock;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

//设置手势锁
public class SetGestureLockActivity extends Activity {

    // 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态
    public static final int POINT_STATE_SELECTED = 1; // 按下状态
    public static final int POINT_STATE_WRONG = 2; // 错误状态

    @ViewInject(R.id.lock_indicator)
    LockIndicator mLockIndicator;
    @ViewInject(R.id.text_tip)
    TextView mTextTip;
    @ViewInject(R.id.gesture_container)
    FrameLayout mGestureContainer;

    private GestureContentView mGestureContentView;
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gesture_lock);
        ViewUtils.inject(this);
        initParam();
    }

    private void initParam() {
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if (!isInputPassValidate(inputCode)) {
                    mTextTip.setText(Html.fromHtml("<font color='#ff0000'>最少链接4个点, 请重新输入</font>"));
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }
                if (mIsFirstInput) {
                    mFirstPassword = inputCode;
                    updateCodeList(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                } else {
                    if (inputCode.equals(mFirstPassword)) {
                        Toast.makeText(SetGestureLockActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        mGestureContentView.clearDrawlineState(0L);
                        MainActivity.password =mFirstPassword;
                        SetGestureLockActivity.this.finish();
                    } else {
                        mTextTip.setText(Html.fromHtml("<font color='#ff0000'>与上一次绘制不一致，请重新绘制</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(SetGestureLockActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1300L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail() {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
        updateCodeList("");
    }

    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

}
