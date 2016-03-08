package com.ccdt.live.activity;

import android.content.Intent;
import android.view.View;


import com.ccdt.live.R;
import com.ccdt.live.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    //butterknife知识点
    @OnClick({R.id.button_go_rxjava,R.id.button_go_retrofit})
    public void goActivity(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.button_go_rxjava:{
                intent.setClass(getApplicationContext(),RxjavaActivity.class);
                break;
            }
            case R.id.button_go_retrofit:{
                intent.setClass(getApplicationContext(),RetrofitActivity.class);
                break;
            }

        }
        this.startActivity(intent);
    }



}
