package edu.fje.dam2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class M11_AnimacioRequadre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m11_animacio_requadre);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button boto = (Button) findViewById(R.id.boto);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int amplada = size.x;
        final int alcada = size.y;

        boto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animacio1 =
                        ObjectAnimator.ofFloat(boto, "x", 0, amplada-boto.getWidth());
                animacio1.setDuration(1000);
                ObjectAnimator animacio2 =
                        ObjectAnimator.ofFloat(boto, "y", 0, alcada-boto.getHeight());
                animacio1.setDuration(1000);
                ObjectAnimator animacio3 =
                        ObjectAnimator.ofFloat(boto, "x",  amplada-boto.getWidth(),0);
                animacio1.setDuration(1000);
                ObjectAnimator animacio4 =
                        ObjectAnimator.ofFloat(boto, "y", alcada-boto.getHeight(),0);
                animacio1.setDuration(1000);


                final AnimatorSet animacio = new AnimatorSet();
                animacio.playSequentially(animacio1, animacio2, animacio3, animacio4);


                animacio.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator a) {
                        super.onAnimationEnd(a);
                        animacio.start();
                    }

                });

                animacio.start();



            }
        });

    }
}
