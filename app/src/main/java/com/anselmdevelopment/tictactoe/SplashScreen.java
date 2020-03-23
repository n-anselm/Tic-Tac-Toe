package com.anselmdevelopment.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;

import static com.anselmdevelopment.tictactoe.PlayActivity.DARKMODE;

public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        checkTheme();
        setContentView(R.layout.splashscreen);

        //Splash Screen duration
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, PlayActivity.class));
                finish();
            }
        }, secondsDelayed * 1250);
    }

    public void checkTheme() {
        if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
            setTheme(R.style.AppThemeDark);
            FastSave.getInstance().saveBoolean(DARKMODE, true);
        } else {
            setTheme(R.style.AppTheme);
            FastSave.getInstance().saveBoolean(DARKMODE, false);
        }
    }
}
