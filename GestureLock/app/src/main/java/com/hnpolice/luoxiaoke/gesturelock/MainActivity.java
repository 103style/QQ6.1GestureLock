package com.hnpolice.luoxiaoke.gesturelock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }

    @OnClick({R.id.bt, R.id.btCheck})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                startActivity(new Intent(MainActivity.this, SetGestureLockActivity.class));
                break;
            case R.id.btCheck:
                if (password == null){
                    Toast.makeText(MainActivity.this,"请先设置手势锁",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }
                break;
        }
    }
}
