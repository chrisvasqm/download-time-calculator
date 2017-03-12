package com.christianv07.dev.speedy.introActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.christianv07.dev.speedy.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MyIntro extends AppIntro {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance(getString(R.string.slideTittle), getString(R.string.slideDescription), R.drawable.slidesdown, Color.parseColor("#F15455")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.slideTittle2), getString(R.string.slideDescription2), R.drawable.slidesdown4, Color.parseColor("#F15455")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.slideTittle3), getString(R.string.slideDescription3), R.drawable.slidesdown3, Color.parseColor("#F15455")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.slideTittle4), getString(R.string.slideDescription4), R.drawable.slidesdown2, Color.parseColor("#F15455")));


        // OPTIONAL METHODS

        // Override bar/separator color
        setBarColor(Color.parseColor("#F15455"));
        setSeparatorColor(Color.parseColor("#F15455"));

        // SHOW or HIDE the statusbar
        showStatusBar(false);

        setSkipText(getString(R.string.introSkip));
        setDoneText(getString(R.string.introDone));

        // Edit the color of the nav bar on Lollipop+ devices
//        setNavBarColor(R.color.colorPrimary);

        // Hide Skip/Done button
        showSkipButton(true);
        showDoneButton(true);


        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(false);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        finish();
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}
