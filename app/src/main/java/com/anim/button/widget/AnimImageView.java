package com.anim.button.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.anim.button.R;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by jiangyue on 16/3/8.
 */
public class AnimImageView extends ImageView {
    private static final int DEFAULT_DURATION_TIME = 300;

    private Drawable firstDraw;
    private Drawable secondDraw;

    private float firstScale;
    private float secondScale;

    private boolean firstShow = false;
    private boolean secondShow = false;

    private ObjectAnimator firstShowAnimator;
    private ObjectAnimator firstUnShowAnimator;
    private ObjectAnimator secondShowAnimator;
    private ObjectAnimator secondUnShowAnimator;

    public AnimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AnimImageView);
        firstDraw = array.getDrawable(R.styleable.AnimImageView_firstDraw);
        secondDraw = array.getDrawable(R.styleable.AnimImageView_secondDraw);
        //设置动画
        firstShowAnimator = ObjectAnimator.ofFloat(this, "firstScale", 0, 1).setDuration(DEFAULT_DURATION_TIME);
        firstShowAnimator.setInterpolator(new DecelerateInterpolator());
        firstUnShowAnimator = ObjectAnimator.ofFloat(this, "firstScale", 1, 0).setDuration(DEFAULT_DURATION_TIME);
        firstUnShowAnimator.setInterpolator(new DecelerateInterpolator());
        secondShowAnimator = ObjectAnimator.ofFloat(this, "secondScale", 0, 1).setDuration(DEFAULT_DURATION_TIME);
        secondShowAnimator.setInterpolator(new DecelerateInterpolator());
        secondUnShowAnimator = ObjectAnimator.ofFloat(this, "secondScale", 1, 0).setDuration(DEFAULT_DURATION_TIME);
        secondUnShowAnimator.setInterpolator(new DecelerateInterpolator());
        //初始化动画
        startAnimShow();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        firstDraw.setBounds(0, 0, right, bottom);
        secondDraw.setBounds(0, 0, right, bottom);
    }

    public void startAnimShow() {
        if (firstShow) {
            setSecondAnimShow();
        } else {
            setFirstAnimShow();
        }
    }

    /* 设置First动画显示 */
    public void setFirstAnimShow() {
        if (!firstShow) {
            firstShow = true;
            secondShow = false;
            firstShowAnimator.start();
            secondUnShowAnimator.start();
        }
    }

    /* 设置First动画显示 */
    public void setFirstShow() {
        if (!firstShow) {
            firstShow = true;
            secondShow = false;
            setFirstScale(1.0f);
            setSecondScale(0.0f);
        }
    }

    /* 设置Second动画显示 */
    public void setSecondAnimShow() {
        if (!secondShow) {
            firstShow = false;
            secondShow = true;
            firstUnShowAnimator.start();
            secondShowAnimator.start();
        }
    }

    /* 设置Second显示 */
    public void setSecondShow() {
        if (!secondShow) {
            firstShow = false;
            secondShow = true;
            setFirstScale(0.0f);
            setSecondScale(1.0f);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getHeight() != 0) {
            //绘制第一个draw
            canvas.save();
            canvas.scale(firstScale, firstScale, getHeight() / 2, getHeight() / 2);
            firstDraw.draw(canvas);
            canvas.restore();
            //绘制第二个draw
            canvas.save();
            canvas.scale(secondScale, secondScale, getHeight() / 2, getHeight() / 2);
            secondDraw.draw(canvas);
            canvas.restore();
        }
    }

    /* set and get */
    public float getFirstScale() {
        return firstScale;
    }

    public void setFirstScale(float firstScale) {
        this.firstScale = firstScale;
        invalidate();
    }

    public float getSecondScale() {
        return secondScale;
    }

    public void setSecondScale(float secondScale) {
        this.secondScale = secondScale;
        invalidate();
    }

}
