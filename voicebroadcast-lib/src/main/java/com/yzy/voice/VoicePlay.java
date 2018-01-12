package com.yzy.voice;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.yzy.voice.util.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 志尧
 * @date on 2018-01-12 15:09
 * @email 1417337180@qq.com
 * @describe 音频播放
 * @ideas
 */

public class VoicePlay {

    private ExecutorService mExecutorService;

    private VoicePlay() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    private volatile static VoicePlay mVoicePlay = null;

    /**
     * 单例
     *
     * @return
     */
    public static VoicePlay getInstance() {
        if (mVoicePlay == null) {
            synchronized (VoicePlay.class) {
                if (mVoicePlay == null) {
                    mVoicePlay = new VoicePlay();
                }
            }
        }
        return mVoicePlay;
    }


    private Context mContext;
    private String mMoney;
    private boolean mIsNum;

    public VoicePlay with(Context context) {
        this.mContext = context;
        return this;
    }

    public VoicePlay setmMoney(String mMoney) {
        this.mMoney = mMoney;
        return this;
    }

    public VoicePlay setmIsNum(boolean mIsNum) {
        this.mIsNum = mIsNum;
        return this;
    }

    public void play() {
        if (mExecutorService == null) {
            return;
        }

        List<String> voicePlay = VoiceCompose.genVoiceList(VoiceBean.getDefaultBean(mMoney), mIsNum);
        if (voicePlay == null || voicePlay.isEmpty()) {
            return;
        }

        mExecutorService.execute(() -> start(voicePlay));
    }

    /**
     * 开始播报
     * @param voicePlay
     */
    private void start(final List<String> voicePlay) {
        synchronized (VoicePlay.this) {

            MediaPlayer mMediaPlayer = new MediaPlayer();
            final CountDownLatch mCountDownLatch = new CountDownLatch(1);
            AssetFileDescriptor assetFileDescription = null;

            try {
                final int[] counter = {0};
                assetFileDescription = FileUtils.getAssetFileDescription(mContext,
                        String.format(VoiceConstants.FILE_PATH, voicePlay.get(counter[0])));
                mMediaPlayer.setDataSource(
                        assetFileDescription.getFileDescriptor(),
                        assetFileDescription.getStartOffset(),
                        assetFileDescription.getLength());
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(mediaPlayer -> mMediaPlayer.start());
                mMediaPlayer.setOnCompletionListener(mediaPlayer ->
                {
                    mediaPlayer.reset();
                    counter[0]++;

                    if (counter[0] < voicePlay.size()) {
                        try {
                            AssetFileDescriptor fileDescription2 = FileUtils.getAssetFileDescription(mContext,
                                    String.format(VoiceConstants.FILE_PATH, voicePlay.get(counter[0])));
                            mediaPlayer.setDataSource(
                                    fileDescription2.getFileDescriptor(),
                                    fileDescription2.getStartOffset(),
                                    fileDescription2.getLength());
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                            mCountDownLatch.countDown();
                        }
                    } else {
                        mediaPlayer.release();
                        mCountDownLatch.countDown();
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
                mCountDownLatch.countDown();
            } finally {
                if (assetFileDescription != null) {
                    try {
                        assetFileDescription.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                mCountDownLatch.await();
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
