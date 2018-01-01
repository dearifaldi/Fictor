package com.example.asus.fictor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by ASUS on 19/12/2017.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(SampleSlide.newInstance(R.layout.intro_1));
        addSlide(SampleSlide.newInstance(R.layout.intro_2));
        addSlide(SampleSlide.newInstance(R.layout.intro_3));

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
//        addSlide(AppIntroFragment.newInstance
//                ("FinDoctor", getString(R.string.dokter_desc), R.drawable.doctor, getColor(R.color.light_blue_500
//                )));

        // OPTIONAL METHODS
        // Override bar/separator color.
//        setBarColor(Color.parseColor("#3F51B5"));
//        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
//        showSkipButton(false);
        setDepthAnimation();
//        setFadeAnimation();
//        setProgressButtonEnabled(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
//        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}
