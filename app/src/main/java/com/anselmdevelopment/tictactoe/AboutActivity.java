package com.anselmdevelopment.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;

import static com.anselmdevelopment.tictactoe.PlayActivity.DARKMODE;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        checkTheme();
        setContentView(R.layout.activity_about);

        FrameLayout backArrow = findViewById(R.id.about_back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
