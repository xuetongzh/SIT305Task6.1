package com.google.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.trucksharingapp.bean.TruckBean;
import com.google.trucksharingapp.bean.UserBean;
import com.google.trucksharingapp.mmkv.KVConfigImpl;
import com.luck.picture.lib.utils.ToastUtils;

import org.litepal.LitePal;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register, login;
    private EditText username, password;
    private String user, pwd;
    private UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if (LitePal.findAll(TruckBean.class).size() == 0) {
            initData();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                user = username.getText().toString();
                pwd = password.getText().toString();

                if (user.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (LitePal.where("userName = ?", user).find(UserBean.class).size() == 0) {
                    Toast.makeText(LoginActivity.this, "The account does not exist, please proceed to register", Toast.LENGTH_SHORT).show();
                    return;
                }

                userBean = LitePal.where("userName = ?", user).find(UserBean.class).get(0);
                if (userBean.getPassword().equals(pwd)) {
                    KVConfigImpl.getKVConfigImpl().setString("userName", userBean.getUserName());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, "Incorrect username or password");
                }
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void initData() {
        new TruckBean(R.drawable.truck_green, "22/2", "the weight(kg) is 100,the length of the goods is(cm):100,the width of the goods is(cm):100,the length of the goods is(cm):100").save();
        new TruckBean(R.drawable.truck_pink, "23/4", "the weight(kg) is 100,the length of the goods is(cm):100,the width of the goods is(cm):100,the length of the goods is(cm):100").save();
        new TruckBean(R.drawable.truck_blue, "22/9", "the weight(kg) is 100,the length of the goods is(cm):100,the width of the goods is(cm):100,the length of the goods is(cm):100").save();
        new TruckBean(R.drawable.truck_red, "22/4", "the weight(kg) is 100,the length of the goods is(cm):100,the width of the goods is(cm):100,the length of the goods is(cm):100").save();
        new TruckBean(R.drawable.truck_green, "22/8", "the weight(kg) is 100,the length of the goods is(cm):100,the width of the goods is(cm):100,the length of the goods is(cm):100").save();
    }
}