package com.backbase.boardmembers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.backbase.boardmembers.R;
import com.backbase.boardmembers.ui.memberslist.MembersListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 17/02/17.
 */
public class SplashScreen extends AppCompatActivity {

    @Bind(R.id.imgLogo)
    ImageView imgLogo;

    ButterKnife binder ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        binder.bind(this);


        Animation objAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        objAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation anim) {

                Intent objIntent;
                objIntent = new Intent(getApplicationContext(),MembersListActivity.class);
                objIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(objIntent);
                finish();

            }
            public void onAnimationRepeat(Animation arg0) {}

            public void onAnimationStart(Animation arg0) {}
        });


        imgLogo.startAnimation(objAnimation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind(this);

    }
}