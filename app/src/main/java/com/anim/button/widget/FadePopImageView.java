package com.anim.button.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by jiangyue on 16/4/5.
 */
public class FadePopImageView extends ImageView {

    private static final int DEFAULT_DURATION_TIME = 3000;

    private float scaleX;
    private float scaleY;

    private Paint paint;

    public FadePopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ArrayList animatorList = new ArrayList<Animator>();
        AnimView animView = new AnimView(getContext());
        final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(animView, "ScaleX", 1.0f, scaleX);
        scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleXAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(scaleXAnimator);
        final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(animView, "ScaleY", 1.0f, scaleY);
        scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
        scaleYAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(scaleYAnimator);
        final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(animView, "Alpha", 1.0f, 0f);
        alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
        alphaAnimator.setDuration(DEFAULT_DURATION_TIME);
        animatorList.add(alphaAnimator);
        //设置paint
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(1, 1, 1, 1));
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

    @Override
    public float getScaleY() {
        return scaleY;
    }

    @Override
    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    @Override
    public float getScaleX() {
        return scaleX;
    }

    @Override
    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }
}
