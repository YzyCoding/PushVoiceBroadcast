# PushVoiceBroadcast
支付宝商家语音播报

![image](https://github.com/YzyCoding/PushVoiceBroadcast/blob/master/image/jianshu_0038.png)

1. gradle引入
```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

    dependencies {
        implementation 'com.github.YzyCoding:PushVoiceBroadcast:1.0.2'
    }
```

2. 一行代码
```
    VoicePlay.with(MainActivity.this).play(amount);
```

* 支持顺序播报
* 支持中文大写
* 支持全数字
* 替换音频文件导入源码

#容错处理
```aidl
    /**
     * 提取字符串中的 数字 带小数点 ，没有就返回""
     *
     * @param money
     * @return
     */
    public static String getMoney(String money) {
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+)");
        Matcher m = pattern.matcher(money);
        if (m.find()) {
            money = m.group(1) == null ? "" : m.group(1);
        } else {
            pattern = Pattern.compile("(\\d+)");
            m = pattern.matcher(money);
            if (m.find()) {
                money = m.group(1) == null ? "" : m.group(1);
            } else {
                money = "";
            }
        }

        return money;
    }
```

```aidl
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
    
    
    Log:
    money == 
    money1 == 0.01
    money1 == 1000.00
    money2 == 1000
    money3 == 999.99
    money4 == 999.99
    money5 == 1
```


[fir下载](https://fir.im/gl7q)

[简书](https://www.jianshu.com/p/62e6382c610b)


参考：
[KTools/Voice](https://github.com/jiangkang/KTools/blob/master/app/src/main/java/com/jiangkang/ktools/audio/VoiceSpeaker.java)

