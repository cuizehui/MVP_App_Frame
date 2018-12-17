package com.nela.mvpdemo.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class MediaUtils {


    // IM通过Camera拍照存储的图片
    public static String createImagePath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
    }

    // IM通过Camera录像存储的视频
    public static String createVideoPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + System.currentTimeMillis() + ".mp4";
    }

    // IM通过AudioUtil存储的语音文件
    public static String createAudioPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + System.currentTimeMillis() + ".amr";
    }

    /**
     * 获取音视频长度
     */
    public static int getMediaDuring(String path) {
        if (!new File(path).exists())
            return 0;

        MediaPlayer player = new MediaPlayer();
        int seconds = 0;
        try {
            player.setDataSource(path);
            player.prepare();
            seconds = (player.getDuration() + 500) / 1000;
            if (seconds == 0) {
                seconds = 1;
            }
            player.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seconds;
    }

    public static String getSdcardPath(Context context) {
        String dir = null;
        String state = Environment.getExternalStorageState();
        boolean emulated = true;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            emulated = Environment.isExternalStorageEmulated();
        }
        if (Environment.MEDIA_MOUNTED.equals(state)
                && (emulated || !Environment.isExternalStorageRemovable())) {
            dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            dir = context.getFilesDir().getAbsolutePath();
        }
        return dir;
    }

}
