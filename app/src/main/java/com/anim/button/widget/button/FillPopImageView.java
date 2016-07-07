package com.anim.button.widget.button;

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
 * Created by jiangyue on 16/3/25.
 */
public class FillPopImageView extends ImageView {
    private static final int DEFAULT_DURATION_TIME = 300;

    private Drawable first;
    private Drawable second;

    private float firstAlpha;
    private float secondScale;

    private boolean firstShow = false;
    private boolean secondShow = false;

    private ObjectAnimator firstShowAnimator;
    private ObjectAnimator firstUnShowAnimator;
    private ObjectAnimator secondShowAnimator;
    private ObjectAnimator secondUnShowAnimator;

    public FillPopImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FillPopImageView);
        first = array.getDrawable(R.styleable.FillPopImageView_firstView);
        second = array.getDrawable(R.styleable.FillPopImageView_secondView);
        //设置动画
        firstShowAnimator = ObjectAnimator.ofFloat(this, "firstAlpha", 0, 1).setDuration(DEFAULT_DURATION_TIME);
        firstShowAnimator.setInterpolator(new DecelerateInterpolator());
        firstUnShowAnimator = ObjectAnimator.ofFloat(this, "firstAlpha", 1, 0).setDuration(DEFAULT_DURATION_TIME);
        firstUnShowAnimator.setInterpolator(new DecelerateInterpolator());
        secondShowAnimator = ObjectAnimator.ofFloat(this, "secondScale", 0, 1.5f, 1).setDuration(DEFAULT_DURATION_TIME);
        secondShowAnimator.setInterpolator(new DecelerateInterpolator());
        secondUnShowAnimator = ObjectAnimator.ofFloat(this, "secondScale", 1, 1.5f, 0).setDuration(DEFAULT_DURATION_TIME);
        secondUnShowAnimator.setInterpolator(new DecelerateInterpolator());
        //初始化动画
        startAnimShow();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        first.setBounds((int) (right * 0.15f), (int) (bottom * 0.15f), (int) (right * 0.85f), (int) (bottom * 0.85f));
        second.setBounds((int) (right * 0.15f), (int) (bottom * 0.15f), (int) (right * 0.85f), (int) (bottom * 0.85f));
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
            setFirstAlpha(1.0f);
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
            setFirstAlpha(0.0f);
            setSecondScale(1.0f);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getHeight() != 0) {
            //绘制第一个draw
            canvas.save();
            first.setAlpha((int) (firstAlpha * 255));
            first.draw(canvas);
            canvas.restore();
            //绘制第二个draw
            canvas.save();
            canvas.scale(secondScale, secondScale, getHeight() / 2, getHeight() / 2);
            second.draw(canvas);
            canvas.restore();
        }
    }

    /* set and get */
    public float getFirstAlpha() {
        return firstAlpha;
    }

    public void setFirstAlpha(float firstAlpha) {
        this.firstAlpha = firstAlpha;
    }

    public float getSecondScale() {
        return secondScale;
    }

    public void setSecondScale(float secondScale) {
        this.secondScale = secondScale;
        invalidate();
    }
}
