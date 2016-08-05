package com.anim.button;

import android.app.Application;

import com.github.mmin18.layoutcast.LayoutCast;

/**
 * Created by jiangyue on 16/8/5.
 */
public class MineApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LayoutCast.init(this);
    }
}