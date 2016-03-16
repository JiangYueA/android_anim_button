package com.anim.button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.anim.button.R;
import com.anim.button.widget.AnimImageView;


public class MainActivity extends Activity {

    private AnimImageView animImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        animImageView = (AnimImageView) findViewById(R.id.img_anim_button);
        animImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animImageView.startAnimShow();
            }
        });
    }
}
