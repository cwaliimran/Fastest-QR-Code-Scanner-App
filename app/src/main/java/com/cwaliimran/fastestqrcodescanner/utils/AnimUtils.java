package com.cwaliimran.fastestqrcodescanner.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cwaliimran.fastestqrcodescanner.R;


public class AnimUtils {

    public static void fadeInAnimation(View view) {
        view.setVisibility(View.VISIBLE);
        AnimatorSet mAnimationSet1;
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", .0f, 1f);
        fadeIn.setDuration(400);
        mAnimationSet1 = new AnimatorSet();
        mAnimationSet1.play(fadeIn);
        mAnimationSet1.start();
        mAnimationSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
             }
            @Override
            public void onAnimationEnd(Animator animation){ }
            @Override
            public void onAnimationCancel(Animator animation) { }
            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
    }

    public static void fadeOutAnimation(final View view,int duration) {
        AnimatorSet mAnimationSet1;
        ObjectAnimator fadeout = ObjectAnimator.ofFloat(view, "alpha", 1f, .0f);
        fadeout.setDuration(duration);
        mAnimationSet1 = new AnimatorSet();
        mAnimationSet1.play(fadeout);
        mAnimationSet1.start();
        view.setEnabled(false);
        view.setClickable(false);
        view.setFocusable(false);
        mAnimationSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
               }
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setEnabled(true);
                view.setClickable(true);
                view.setFocusable(true);
                view.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationCancel(Animator animation) { }
            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }},1000);
    }

    public static void scanner_animation(final View view, Context context) {
        view.setVisibility(View.VISIBLE);
       Animation anim1 = AnimationUtils.loadAnimation(context, R.anim.scanner_anim);
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            };

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(anim1);
    }
}
