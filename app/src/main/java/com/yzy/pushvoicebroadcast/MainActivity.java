package com.yzy.pushvoicebroadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.voice.VoiceBuilder;
import com.yzy.voice.VoicePlay;
import com.yzy.voice.VoiceTextTemplate;

public class MainActivity extends AppCompatActivity {


    private boolean mCheckNum;

    private EditText editText;
    private Button btPlay;
    private Button btDel;
    private LinearLayout llMoneyList;
    private Switch switchView;

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
        switchView = findViewById(R.id.switch_view);
    }

    void initClick() {
        btPlay.setOnClickListener(view -> {
            String amount = editText.getText().toString().trim();
            if (TextUtils.isEmpty(amount)) {
                Toast.makeText(MainActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                return;
            }

            VoicePlay.with(MainActivity.this).play(amount, mCheckNum);

            llMoneyList.addView(getTextView(amount), 0);
            editText.setText("");
        });

        btDel.setOnClickListener(view -> llMoneyList.removeAllViews());

        switchView.setOnCheckedChangeListener((compoundButton, b) -> mCheckNum = b);
    }

    TextView getTextView(String amount) {
        VoiceBuilder voiceBuilder = new VoiceBuilder.Builder()
                .start("success")
                .money(amount)
                .unit("yuan")
                .checkNum(mCheckNum)
                .builder();

        StringBuffer text = new StringBuffer()
                .append("角标: ").append(llMoneyList.getChildCount())
                .append("\n")
                .append("输入金额: ").append(amount)
                .append("\n");
        if (mCheckNum) {
            text.append("全数字式: ").append(VoiceTextTemplate.genVoiceList(voiceBuilder).toString());
        } else {
            text.append("中文样式: ").append(VoiceTextTemplate.genVoiceList(voiceBuilder).toString());
        }

        TextView view = new TextView(MainActivity.this);
        view.setPadding(0, 8, 0, 0);
        view.setText(text.toString());
        return view;
    }
}
