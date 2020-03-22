package com.anselmdevelopment.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        RelativeLayout appBar = findViewById(R.id.app_bar);
        appBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playActivity = new Intent(IntroActivity.this, PlayActivity.class);
                startActivity(playActivity);
            }
        });

        /*RelativeLayout appBar = findViewById(R.id.app_bar);
        appBar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getBaseContext(), "Long click!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
    }
}
