package com.example.quest1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvText);

        new Thread(new DisplayData()).start();
        new Thread(new DisplayData()).start();
    }

    class DisplayData implements Runnable {

        public void run() {
            final long id = Thread.currentThread().getId();

            for (int i = 0; i < 10; i++) {
                try {
                    sleep(1000);
                    final long startTime = System.nanoTime();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(textView.getText().toString() + id + ":" + TimeUnit.MICROSECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS) + "\n");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}