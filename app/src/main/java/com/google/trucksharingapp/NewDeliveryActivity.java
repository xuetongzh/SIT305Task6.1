package com.google.trucksharingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.gzuliyujiang.wheelpicker.DatimePicker;
import com.github.gzuliyujiang.wheelpicker.annotation.DateMode;
import com.github.gzuliyujiang.wheelpicker.annotation.TimeMode;
import com.github.gzuliyujiang.wheelpicker.entity.DatimeEntity;
import com.github.gzuliyujiang.wheelpicker.widget.DatimeWheelLayout;
import com.google.trucksharingapp.bean.DeliveryBean;
import com.google.trucksharingapp.bean.UserBean;
import com.google.trucksharingapp.mmkv.KVConfigImpl;
import com.luck.picture.lib.utils.ToastUtils;

import org.litepal.LitePal;

public class NewDeliveryActivity extends AppCompatActivity implements View.OnClickListener {
    private UserBean userBean;
    private LinearLayout view1, view2;
    private EditText receiverName, pickUpLo;
    private TextView pickUpDate, pickUpTime, time1, time2, from, to;
    private Button next, callDriver;
    private DatimePicker picker;

    private String name, date, time, location;
    private EditText weight, type, width, height, length;
    private String weightS, typeS, widthS, heightS, lengthS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery);
        userBean = LitePal.where("userName = ?", KVConfigImpl.getKVConfigImpl().getString("userName", "")).find(UserBean.class).get(0);
        picker = new DatimePicker(this);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        receiverName = findViewById(R.id.receiverName);
        pickUpDate = findViewById(R.id.pickUpDate);
        pickUpDate.setOnClickListener(this);
        pickUpTime = findViewById(R.id.pickUpTime);
        pickUpTime.setOnClickListener(this);
        pickUpLo = findViewById(R.id.pickUpLo);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        time1 = findViewById(R.id.time);
        time2 = findViewById(R.id.time2);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        weight = findViewById(R.id.weight);
        type = findViewById(R.id.type);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        length = findViewById(R.id.length);
        callDriver = findViewById(R.id.callDriver);
        callDriver.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pickUpDate:
                pickUpDate();
                picker.setOnDatimePickedListener((year, month, day, hour, minute, second) -> {
                    pickUpDate.setText(year + "-" + month + "-" + day);
                });
                break;
            case R.id.pickUpTime:
                pickUpDate();
                picker.setOnDatimePickedListener((year, month, day, hour, minute, second) -> {
                    pickUpTime.setText(hour + "-" + minute);
                });
                break;
            case R.id.next:
                name = receiverName.getText().toString();
                date = pickUpDate.getText().toString();
                time = pickUpTime.getText().toString();
                location = pickUpLo.getText().toString();

                if (name.isEmpty() || date.isEmpty() || time.isEmpty() || location.isEmpty()) {
                    ToastUtils.showToast(NewDeliveryActivity.this, "Please enter all information");
                    return;
                }

                from.setText(userBean.getNickName());
                to.setText(userBean.getNickName());
                time1.setText(date + " " + time);
                time2.setText(date + " " + time);

                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                break;
            case R.id.callDriver:
                weightS = weight.getText().toString();
                typeS = weight.getText().toString();
                widthS = weight.getText().toString();
                heightS = weight.getText().toString();
                lengthS = weight.getText().toString();

                if (weightS.isEmpty() || typeS.isEmpty() || widthS.isEmpty() || heightS.isEmpty() || lengthS.isEmpty()) {
                    ToastUtils.showToast(NewDeliveryActivity.this, "Please enter all information");
                    return;
                }

                if (new DeliveryBean(name, date + " " + time, location, weightS, typeS, widthS, heightS, lengthS).save()) {
                    ToastUtils.showToast(NewDeliveryActivity.this, "Call successful");
                    finish();
                }
                break;
        }
    }

    private void pickUpDate() {
        final DatimeWheelLayout wheelLayout = picker.getWheelLayout();
        wheelLayout.setDateMode(DateMode.YEAR_MONTH_DAY);
        wheelLayout.setTimeMode(TimeMode.HOUR_24_NO_SECOND);
        wheelLayout.setRange(DatimeEntity.yearOnFuture(0), DatimeEntity.yearOnFuture(10));
        wheelLayout.setDateLabel("Year", "Month", "Day");
        wheelLayout.setTimeLabel("Hour", "Minute", "Second");
        picker.show();
    }
}