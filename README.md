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
    implementation 'com.github.YzyCoding:PushVoiceBroadcast:1.0.0'
}
```

2. 收到推送时调用
```
        VoicePlay.getInstance()
                .with(MainActivity.this)
                .setmMoney(amount)
                .play();

```

* 支持顺序播报
* 支持中文大写
* 支持全数字


[fir下载](https://fir.im/gl7q)

[简书](https://www.jianshu.com/p/62e6382c610b)
