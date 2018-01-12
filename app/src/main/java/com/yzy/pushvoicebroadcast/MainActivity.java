package com.yzy.pushvoicebroadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.voice.VoicePlay;
import com.yzy.voice.util.VoiceUtils;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private Button btPlay;
    private Button btDel;
    private LinearLayout llMoneyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    void initView() {
        editText = findViewById(R.id.edittext);
        btPlay = findViewById(R.id.bt_play);
        btDel = findViewById(R.id.bt_del);
        llMoneyList = findViewById(R.id.ll_money_list);
    }

    void initClick() {
        btPlay.setOnClickListener(view -> {
            String amount = editText.getText().toString().trim();
            if (TextUtils.isEmpty(amount)) {
                Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                return;
            }

            VoicePlay.getInstance()
                    .with(MainActivity.this)
                    .setmMoney(amount)
                    .play();

            llMoneyList.addView(getTextView(amount), 0);
            editText.setText("");
        });

        btDel.setOnClickListener(view -> llMoneyList.removeAllViews());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                EditTextUtils.onNoZeroTextChanged(editText, charSequence, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    TextView getTextView(String amount) {
        TextView view = new TextView(MainActivity.this);
        view.setText(llMoneyList.getChildCount() + " == " + amount + " == " + VoiceUtils.genReadableMoney(amount).toString());
        return view;
    }
}
