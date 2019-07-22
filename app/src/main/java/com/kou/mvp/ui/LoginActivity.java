package com.kou.mvp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kou.mvp.base.BaseActivity;
import com.kou.mvp.bean.LoginResponBen;
import com.kou.mvp.mvp.contract.LoginContract;
import com.kou.mvp.mvp.presenter.LoginPresenterIm;
import com.kou.mvp.util.Commen;
import com.kou.mvp.util.PrefUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yaowei.mvp.R;

/**
 * Created by sq on 2018/6/23
 */
public class LoginActivity extends BaseActivity<LoginPresenterIm> implements LoginContract.LoginView {
    private String tag = "LoginActivity";
    @BindView(R.id.name1_mainlayout)
    EditText mName1Mainlayout;
    @BindView(R.id.pwd1_mainlayout)
    EditText mPwd1Mainlayout;
    @BindView(R.id.login_mainlayout)
    Button mLoginMainlayout;
    private ProgressDialog mDialog;
    private long exitTime = 0;
    private String mUsername;
    private String mUserpwd;
    private String mAccess_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_mainlayout)
    void loginClick() {
        mUsername = mName1Mainlayout.getText().toString().trim();
        mUserpwd = mPwd1Mainlayout.getText().toString().trim();
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在加载...");
        Map<String,Object> map = new HashMap<>();
        map.put("username",mUsername);
        map.put("password",mUserpwd);
        map.put("grant_type","password");
        mPresenter.getToken(map);

    }


    @Override
    protected int createBaseLayout() {
        return R.layout.loginlayout;
    }

    @Override
    protected LoginPresenterIm onCreatePresenter() {
        return new LoginPresenterIm(this);
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void onSucceed(LoginResponBen data) {
        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
        mAccess_token = data.getAccess_token();
        Log.e(tag, "mAccess_token" + mAccess_token);
        PrefUtils.setString(this, Commen.TOKEASSEN, mAccess_token);
        Intent intent = new Intent(LoginActivity.this, TestRetrofitActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    public void onFail(String err) {
        Log.e(tag, err);
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideDialog() {
        mDialog.dismiss();
    }


    //   按两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
