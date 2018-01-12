package com.yzy.pushvoicebroadcast;

import android.widget.EditText;

/**
 * @author 志尧
 * @date on 2018-01-12 17:38
 * @email 1417337180@qq.com
 * @describe
 * @ideas
 */

public class EditTextUtils {


    /**
     * 不可输入零。为小数时，保留后两位
     *
     * @param editText
     * @param s
     * @param start
     * @param before
     * @param count
     */
    public static void onNoZeroTextChanged(EditText editText, CharSequence s, int start, int before, int count) {
        if (editText == null) {
            return;
        }

        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }
}
