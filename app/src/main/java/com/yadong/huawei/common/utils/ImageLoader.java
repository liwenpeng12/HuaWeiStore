package com.yadong.huawei.common.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * 图片加载帮助类
 * 不加 dontAnimate()，有的机型会出现图片变形的情况，先记下找到更好的方法再处理
 */
public final class ImageLoader {


    private ImageLoader() {
        throw new RuntimeException("ImageLoader cannot be initialized!");
    }


    public static void loadFit(Context context, String url, ImageView view, int defaultResId) {
        if (NetUtil.isWifiConnected(context) || NetUtil.isNetworkAvailable(context)) {
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(url).fitCenter().dontAnimate().placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        if (NetUtil.isWifiConnected(context) || NetUtil.isNetworkAvailable(context)) {
            Glide.with(context).load(url).centerCrop().dontAnimate().placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    public static void loadCenterCrop(Context context, String url, ImageView view) {
        if (NetUtil.isWifiConnected(context) || NetUtil.isNetworkAvailable(context)) {
            Glide.with(context).load(url).centerCrop().dontAnimate().into(view);
        }
    }

    public static void loadFitCenter(Context context, String url, ImageView view, int defaultResId) {
        if (NetUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).fitCenter().dontAnimate().placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    /**
     * 带监听处理
     */
    public static void loadFitCenter(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context).load(url).fitCenter().dontAnimate().listener(listener).into(view);
    }

    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context).load(url).centerCrop().dontAnimate().listener(listener).into(view);
    }

    /**
     * 设置图片大小处理
     */
    public static void loadFitOverride(Context context, String url, ImageView view, int defaultResId,
                                       int width, int height) {
        if (NetUtil.isWifiConnected(context)) {
            Glide.with(context).load(url).fitCenter().dontAnimate().override(width, height)
                    .placeholder(defaultResId).into(view);
        } else {
            view.setImageResource(defaultResId);
        }
    }

    /**
     * 计算图片分辨率
     */
    public static String calePhotoSize(Context context, String url) throws ExecutionException, InterruptedException {
        File file = Glide.with(context).load(url)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return options.outWidth + "*" + options.outHeight;
    }
}
