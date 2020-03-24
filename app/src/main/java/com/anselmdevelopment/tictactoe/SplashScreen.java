package com.anselmdevelopment.tictactoe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;

import static com.anselmdevelopment.tictactoe.PlayActivity.DARKMODE;
import static com.anselmdevelopment.tictactoe.PlayActivity.SCORE1;
import static com.anselmdevelopment.tictactoe.PlayActivity.SCORE2;
import static com.anselmdevelopment.tictactoe.PlayActivity.TIMESPLAYED;

public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        checkTheme();
        setContentView(R.layout.splashscreen);
        hideNavigationBar();

        //Splash Screen duration
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, PlayActivity.class));
                finish();
            }
        }, secondsDelayed * 1250);

        // Reset the player scores from the previous app launch
        FastSave.getInstance().saveInt(SCORE1, 0);
        FastSave.getInstance().saveInt(SCORE2, 0);
        FastSave.getInstance().saveInt(TIMESPLAYED, 0);
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

    public void hideNavigationBar() {
        if(Build.VERSION.SDK_INT < 19){
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            // For higher api versions
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
