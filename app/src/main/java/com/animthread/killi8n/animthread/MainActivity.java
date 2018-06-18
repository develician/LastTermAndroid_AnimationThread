package com.animthread.killi8n.animthread;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static ImageView imageView;
    public static ArrayList<Drawable> drawables = new ArrayList<>();
    public static Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        startAnimation();
    }

    private void startAnimation() {
        Resources res = getResources();
        drawables.add(res.getDrawable(R.drawable.ic_launcher_background));
        drawables.add(res.getDrawable(R.drawable.ic_arrow_drop_down_circle_black_24dp));
        drawables.add(res.getDrawable(R.drawable.ic_launcher_background));
        drawables.add(res.getDrawable(R.drawable.ic_arrow_drop_down_circle_black_24dp));
        drawables.add(res.getDrawable(R.drawable.ic_arrow_drop_down_circle_black_24dp));

        AnimThread animThread = new AnimThread();
        animThread.start();


    }

    class AnimThread extends Thread {
        @Override
        public void run() {
            super.run();
            int index = 0;
            for (int i = 0; i < 100; i++) {
                final Drawable drawable = drawables.get(index);
                index += 1;
                if(index > 4) {
                    index = 0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(500);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public synchronized void start() {
            super.start();
        }
    }

}
