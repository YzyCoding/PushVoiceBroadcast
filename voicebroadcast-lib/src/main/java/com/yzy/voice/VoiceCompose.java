package com.yzy.voice;

import android.text.TextUtils;

import com.yzy.voice.util.VoiceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 志尧
 * @date on 2018-01-12 15:25
 * @email 1417337180@qq.com
 * @describe 音频组合
 * @ideas
 */

public class VoiceCompose {

    /**
     * 音频组合
     *
     * @param voiceBean
     * @param isNum     支持数字 。默认false
     * @return
     */
    public static List<String> genVoiceList(VoiceBean voiceBean, boolean isNum) {
        if (voiceBean == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        String success = voiceBean.getmStartSuccess();
        String amount = voiceBean.getmAmount();
        String unit = voiceBean.getmUnit();

        /**
         * null处理
         */
        if (TextUtils.isEmpty(amount)) {
            return result;
        }

        if (!TextUtils.isEmpty(success)) {
            result.add(success);
        }

        if (!TextUtils.isEmpty(amount)) {
            if (isNum) {
                result.addAll(VoiceUtils.createReadableNumList(amount));
            } else {
                result.addAll(VoiceUtils.genReadableMoney(amount));
            }
        }

        if (!TextUtils.isEmpty(unit)) {
            result.add(unit);
        }

        return result;
    }
}
