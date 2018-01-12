package com.yzy.voice;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.yzy.voice.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author 志尧
 * @date on 2018-01-12 15:12
 * @email 1417337180@qq.com
 * @describe 组合音频 实体类
 * @ideas  开头 + 金额 + 单位
 */

public class VoiceBean {

    private String mStartSuccess;
    private String mAmount;
    private String mUnit;

    public VoiceBean() {
    }

    public static VoiceBean getDefaultBean(String money) {
        return new VoiceBean()
                .setmStartSuccess(VoiceConstants.SUCCESS)
                .setmAmount(money)
                .setmUnit(VoiceConstants.YUAN);
    }

    public String getmStartSuccess() {
        return mStartSuccess;
    }

    public VoiceBean setmStartSuccess(String mStartSuccess) {
        this.mStartSuccess = mStartSuccess;
        return this;
    }

    public String getmAmount() {
        return mAmount;
    }

    public VoiceBean setmAmount(String mAmount) {
        this.mAmount = mAmount;
        return this;
    }

    public String getmUnit() {
        return mUnit;
    }

    public VoiceBean setmUnit(String mUnit) {
        this.mUnit = mUnit;
        return this;
    }
}
