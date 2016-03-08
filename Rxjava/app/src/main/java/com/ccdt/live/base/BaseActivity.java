package com.ccdt.live.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.ccdt.live.port.RxjavaInterface;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by rain on 2016/1/4.
 */
public abstract class BaseActivity extends Activity implements RxjavaInterface{

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.context = this;
        /**加载根布局*/
        setContentView(getContentViewId());
        /**ButterKnife 绑定Activity*/
        ButterKnife.bind(this);

        /**初始化成员*/
        initAllMembers(savedInstanceState);

    }

    public void initAllMembers(Bundle savedInstanceState) {
    }


    public abstract int getContentViewId();


    @Override
    public void onRxjavaComplete(int requestCode) {

    }

    @Override
    public void onRxjavaError(Throwable exception) {

    }

    @Override
    public void onRxjavaSuccess(int requestCode, Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
