package com.yzy.voice;

import android.util.Log;

import com.yzy.voice.util.StringUtils;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testMoney() {
        String money = StringUtils.getMoney("");
        System.out.println("money == " + money);

        String money1 = StringUtils.getMoney("收到影秀卡付款0.01元");
        System.out.println("money1 == " + money1);

        String money2 = StringUtils.getMoney("收到测试影秀卡付款1000.00元");
        System.out.println("money1 == " + money2);

        String money3 = StringUtils.getMoney("收到测试影秀卡付款1000元");
        System.out.println("money2 == " + money3);

        String money4 = StringUtils.getMoney("收到测试影秀卡付款999.99元");
        System.out.println("money3 == " + money4);

        String money5 = StringUtils.getMoney("999.99");
        System.out.println("money4 == " + money5);

        String money6 = StringUtils.getMoney("1");
        System.out.println("money5 == " + money6);
    }
}