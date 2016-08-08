package com.anim.button;

import android.app.Application;

import com.github.mmin18.layoutcast.LayoutCast;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

/**
 * Created by jiangyue on 16/8/5.
 */
public class MineApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //有米
        AdManager.getInstance(this).init("5027a4067307a270", "db60237986719d51", false);
        SpotManager.getInstance(this).loadSpotAds();
        SpotManager.getInstance(this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
        SpotManager.getInstance(this).setAnimationType(SpotManager.ANIM_ADVANCE);

        LayoutCast.init(this);
    }
}