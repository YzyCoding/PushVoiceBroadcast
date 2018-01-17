package com.yzy.voice;

import android.text.TextUtils;

import com.yzy.voice.constant.VoiceConstants;
import com.yzy.voice.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 志尧
 * @date on 2018-01-12 15:25
 * @email 1417337180@qq.com
 * @describe 音频组合
 * @ideas
 */

public class VoiceTextTemplate {

    /**
     * 音频组合
     *
     * @param voiceBean
     * @return
     */
    public static List<String> genVoiceList(VoiceBuilder voiceBean) {
        List<String> result = new ArrayList<>();
        String start = voiceBean.getStart();
        String money = voiceBean.getMoney();
        String unit = voiceBean.getUnit();
        boolean checkNum = voiceBean.isCheckNum();

        if (TextUtils.isEmpty(money)) {
            return result;
        }

        if (!TextUtils.isEmpty(start)) {
            result.add(start);
        }

        if (!TextUtils.isEmpty(money)) {
            if (checkNum) {
                result.addAll(createReadableNumList(money));
            } else {
                result.addAll(genReadableMoney(money));
            }
        }

        if (!TextUtils.isEmpty(unit)) {
            result.add(unit);
        }

        return result;
    }


    /**
     * 全转成 中文 RMB
     *
     * @param numString
     * @return
     */
    private static List<String> genReadableMoney(String numString) {
        List<String> result = new ArrayList<>();
        if (!TextUtils.isEmpty(numString)) {
            if (numString.contains(VoiceConstants.DOT_POINT)) {
                String integerPart = numString.split("\\.")[0];
                String decimalPart = numString.split("\\.")[1];
                List<String> intList = readIntPart(integerPart);
                List<String> decimalList = readDecimalPart(decimalPart);
                result.addAll(intList);
                if (!decimalList.isEmpty()) {
                    result.add(VoiceConstants.DOT);
                    result.addAll(decimalList);
                }
            } else {
                result.addAll(readIntPart(numString));
            }
        }
        return result;
    }

    private static List<String> readDecimalPart(String decimalPart) {
        List<String> result = new ArrayList<>();
        if (!"00".equals(decimalPart)) {
            char[] chars = decimalPart.toCharArray();
            for (char ch : chars) {
                result.add(String.valueOf(ch));
            }
        }
        return result;
    }


    /**
     * 全转成数字
     *
     * @param numString
     * @return
     */
    private static List<String> createReadableNumList(String numString) {
        List<String> result = new ArrayList<>();
        if (!TextUtils.isEmpty(numString)) {
            int len = numString.length();
            for (int i = 0; i < len; i++) {
                if ('.' == numString.charAt(i)) {
                    result.add(VoiceConstants.DOT);
                } else {
                    result.add(String.valueOf(numString.charAt(i)));
                }
            }
        }
        return result;
    }

    /**
     * 返回数字对应的音频
     *
     * @param integerPart
     * @return
     */
    private static List<String> readIntPart(String integerPart) {
        List<String> result = new ArrayList<>();
        String intString = MoneyUtils.readInt(Integer.parseInt(integerPart));
        int len = intString.length();
        for (int i = 0; i < len; i++) {
            char current = intString.charAt(i);
            if (current == '拾') {
                result.add(VoiceConstants.TEN);
            } else if (current == '佰') {
                result.add(VoiceConstants.HUNDRED);
            } else if (current == '仟') {
                result.add(VoiceConstants.THOUSAND);
            } else if (current == '万') {
                result.add(VoiceConstants.TEN_THOUSAND);
            } else if (current == '亿') {
                result.add(VoiceConstants.TEN_MILLION);
            } else {
                result.add(String.valueOf(current));
            }
        }
        return result;
    }
}
