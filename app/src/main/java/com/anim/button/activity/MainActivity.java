package com.anim.button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.anim.button.R;
import com.anim.button.util.DisplayUtil;
import com.anim.button.widget.AnimImageView;
import com.anim.button.widget.FadePopImageView;
import com.anim.button.widget.FillPopImageView;
import com.anim.button.widget.RippleImageView;
import com.anim.button.widget.TransitionImageView;

/**
 * 按钮显示主Activity
 */
public class MainActivity extends Activity {

    private AnimImageView animImageView;
    private FillPopImageView fillPopImageView;
    private RippleImageView rippleImageView;
    private FadePopImageView fadeImageView;
    private TransitionImageView transitionImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayUtil.init(this);

        initView();
    }

    private void initView() {
        //缩放btn
        animImageView = (AnimImageView) findViewById(R.id.img_anim_button);
        animImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animImageView.startAnimShow();
            }
        });
        //波纹btn
        rippleImageView = (RippleImageView) findViewById(R.id.img_ripple_button);
        rippleImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rippleImageView.startRippleAnimation();
                return false;
            }
        });
        //渐引btn
        fillPopImageView = (FillPopImageView) findViewById(R.id.img_fill_button);
        fillPopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillPopImageView.startAnimShow();
            }
        });
        //渐变btn
        fadeImageView = (FadePopImageView) findViewById(R.id.img_fade_button);
        fadeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeImageView.startFadeAnimationClick();
            }
        });
        //路径btn
        transitionImageView = (TransitionImageView) findViewById(R.id.img_transition_button);
        transitionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionImageView.simulateProgress();
            }
        });
        //btn
    }
}
