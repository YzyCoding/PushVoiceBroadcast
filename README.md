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
    VoicePlay.with(MainActivity.this).play(amount, mCheckNum);
```

* 支持顺序播报
* 支持中文大写
* 支持全数字
* 替换音频文件导入源码


[fir下载](https://fir.im/gl7q)

[简书](https://www.jianshu.com/p/62e6382c610b)


参考：
[KTools/Voice](https://github.com/jiangkang/KTools/blob/master/app/src/main/java/com/jiangkang/ktools/audio/VoiceSpeaker.java)

