package com.anim.button.widget.button;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by jiangyue on 16/4/5.
 * 渐变按钮
 */
public class FadePopImageView extends RelativeLayout {

    private static final int DEFAULT_DURATION_TIME = 500;

    private Paint paint;
    private AnimatorSet animatorSet;
    private ArrayList<AnimView> fadeViewList = new ArrayList<AnimView>();

    public FadePopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList animatorList = new ArrayList<Animator>();
        AnimView animView = new AnimView(getContext());
        addView(animView);
        fadeViewList.add(animView);
        final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(animView, "ScaleX", 0.0f, 1.0f);
//        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//        scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleXAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(scaleXAnimator);
        final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(animView, "ScaleY", 0.0f, 1.0f);
//        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//        scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleYAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(scaleYAnimator);
        final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(animView, "Alpha", 1.0f, 0.0f);
//        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//        alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
        alphaAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(alphaAnimator);
        animatorSet.playTogether(animatorList);
        //设置paint
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff1abc9c);
    }

    private class AnimView extends View {

        public AnimView(Context context) {
            super(context);
            this.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int radius = (Math.min(getWidth(), getHeight())) / 2;
            canvas.drawCircle(radius, radius, radius, paint);
        }
    }

    public void startFadeAnimationClick() {
        if (!animatorSet.isRunning()) {
            for (AnimView animView : fadeViewList) {
                animView.setVisibility(VISIBLE);
            }
            animatorSet.start();
        }
    }
}
