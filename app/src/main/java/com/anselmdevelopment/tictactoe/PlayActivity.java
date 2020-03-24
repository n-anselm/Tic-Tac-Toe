package com.anselmdevelopment.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.appizona.yehiahd.fastsave.FastSave;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;

public class PlayActivity extends AppCompatActivity {

    EditText mPlayer1, mPlayer2;
    TextView player1, vs, player2, score1, score2;
    String playerName1, playerName2;
    int player1Score, player2Score;
    int x1, x2, x3, x4, x5, x6, x7, x8, x9;
    boolean i1 = false;
    boolean i2 = false;
    boolean i3 = false;
    boolean i4 = false;
    boolean i5 = false;
    boolean i6 = false;
    boolean i7 = false;
    boolean i8 = false;
    boolean i9 = false;
    boolean gameOver = false;
    String xo = "o";
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9;
    TextView reset, newGame;
    private LayoutInflater inflater;
    RelativeLayout bottomBar;
    FrameLayout optionsArrowUp;
    boolean namesEmpty = false;
    private ShakeDetector shakeDetector;

    public static final String PLAYER1 = "player1name";
    public static final String PLAYER2 = "player2name";
    public static final String SCORE1 = "score1x";
    public static final String SCORE2 = "score2o";
    public static final String DARKMODE = "darkmode";
    public static final String RESTART = "restart";
    public static final String DISTRACTIONFREEMODE = "distractionfreemode";
    public static final String TIMESPLAYED = "timesplayed";
    public static final String SHAKE = "shake";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        checkTheme();
        setContentView(R.layout.activity_play);

        player1 = findViewById(R.id.tv_player1);
        player2 = findViewById(R.id.tv_player2);
        vs = findViewById(R.id.tv_vs);
        score1 = findViewById(R.id.tv_score1);
        score2 = findViewById(R.id.tv_score2);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);
        reset = findViewById(R.id.tv_reset);
        newGame = findViewById(R.id.tv_new_game);
        bottomBar = findViewById(R.id.rl_bottom_bar);
        optionsArrowUp = findViewById(R.id.fl_chevron_up);

        alertDialog(); // Show the alertdialog
        checkDistractionFreeMode();
        setPlayerNamesOnRestart();

        optionsArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create instance of PopupMenu
                Context wrapper = new ContextThemeWrapper(PlayActivity.this, R.style.PopupMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, optionsArrowUp);

                if (!FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
                    if (FastSave.getInstance().getBoolean(SHAKE, false)) {
                        // Inflate the popup menu using xml file
                        popupMenu.getMenuInflater().inflate(R.menu.menu_main1_0, popupMenu.getMenu());

                        // Register popup with OnMenuItemClickListener
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int id = item.getItemId();
                                if (id == R.id.action_setchange_player_names) { // Set/change player names
                                    alertDialog();
                                } else if (id == R.id.action_shake) {
                                    toggleShake();
                                } else if (id == R.id.action_dark_mode) { // Dark mode
                                    if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                                        FastSave.getInstance().saveBoolean(DARKMODE, false);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    } else {
                                        FastSave.getInstance().saveBoolean(DARKMODE, true);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    }
                                    Intent restart = new Intent(PlayActivity.this, PlayActivity.class);
                                    startActivity(restart);
                                } else if (id == R.id.action_normal_mode) { // Distraction free mode
                                    toggleDistractionFreeMode();
                                } else if (id == R.id.action_about) {
                                    Intent aboutActivity = new Intent(PlayActivity.this, AboutActivity.class);
                                    startActivity(aboutActivity);
                                }
                                return false;
                            }
                        });
                    } else {
                        popupMenu.getMenuInflater().inflate(R.menu.menu_main1_1, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int id = item.getItemId();
                                if (id == R.id.action_setchange_player_names) { // Set/change player names
                                    alertDialog();
                                } else if (id == R.id.action_shake) {
                                    toggleShake();
                                } else if (id == R.id.action_dark_mode) { // Dark mode
                                    if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                                        FastSave.getInstance().saveBoolean(DARKMODE, false);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    } else {
                                        FastSave.getInstance().saveBoolean(DARKMODE, true);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    }
                                    Intent restart = new Intent(PlayActivity.this, PlayActivity.class);
                                    startActivity(restart);
                                } else if (id == R.id.action_normal_mode) { // Distraction free mode
                                    toggleDistractionFreeMode();
                                } else if (id == R.id.action_about) {
                                    Intent aboutActivity = new Intent(PlayActivity.this, AboutActivity.class);
                                    startActivity(aboutActivity);
                                }
                                return false;
                            }
                        });
                    }
                } else {
                    if (FastSave.getInstance().getBoolean(SHAKE, false)) {
                        popupMenu.getMenuInflater().inflate(R.menu.menu_main2_0, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int id = item.getItemId();
                                if (id == R.id.action_setchange_player_names) { // Set/change player names
                                    alertDialog();
                                    return true;
                                } else if (id == R.id.action_shake) {
                                    toggleShake();
                                } else if (id == R.id.action_dark_mode) { // Dark mode
                                    if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                                        FastSave.getInstance().saveBoolean(DARKMODE, false);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    } else {
                                        FastSave.getInstance().saveBoolean(DARKMODE, true);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    }
                                    Intent restart = new Intent(PlayActivity.this, PlayActivity.class);
                                    startActivity(restart);
                                } else if (id == R.id.action_normal_mode) { // Distraction free mode
                                    toggleDistractionFreeMode();
                                } else if (id == R.id.action_reset) {
                                    reset();
                                } else if (id == R.id.action_new_game) {
                                    FastSave.getInstance().saveInt(SCORE1, 0);
                                    FastSave.getInstance().saveInt(SCORE2, 0);
                                    FastSave.getInstance().saveInt(TIMESPLAYED, 0);
                                    setNewGame();
                                }
                                return false;
                            }
                        });
                    } else {
                        popupMenu.getMenuInflater().inflate(R.menu.menu_main2_1, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                int id = item.getItemId();
                                if (id == R.id.action_setchange_player_names) { // Set/change player names
                                    alertDialog();
                                    return true;
                                } else if (id == R.id.action_shake) {
                                    toggleShake();
                                } else if (id == R.id.action_dark_mode) { // Dark mode
                                    if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                                        FastSave.getInstance().saveBoolean(DARKMODE, false);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    } else {
                                        FastSave.getInstance().saveBoolean(DARKMODE, true);
                                        FastSave.getInstance().saveBoolean(RESTART, true);
                                    }
                                    Intent restart = new Intent(PlayActivity.this, PlayActivity.class);
                                    startActivity(restart);
                                } else if (id == R.id.action_normal_mode) { // Distraction free mode
                                    toggleDistractionFreeMode();
                                } else if (id == R.id.action_reset) {
                                    reset();
                                } else if (id == R.id.action_new_game) {
                                    FastSave.getInstance().saveInt(SCORE1, 0);
                                    FastSave.getInstance().saveInt(SCORE2, 0);
                                    FastSave.getInstance().saveInt(TIMESPLAYED, 0);
                                    setNewGame();
                                }
                                return false;
                            }
                        });
                    }
                }
                popupMenu.show();
            }
        });

        // Settings for shake
        ShakeOptions shakeOptions = new ShakeOptions()
                .background(false)
                .interval(650)
                .shakeCount(2)
                .sensibility(2.0f);

        this.shakeDetector = new ShakeDetector(shakeOptions).start(this, new ShakeCallback() {
            @Override
            public void onShake() {
                if (FastSave.getInstance().getBoolean(SHAKE, false)) {
                    reset();
                }
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i1 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(1);
                        xo = "x";
                        x1 = 1;
                    } else if (xo.equals("x")) {
                        setO(1);
                        xo = "o";
                        x1 = 2;
                    }
                }
                i1 = true;
                checkIfThreeInRow();
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i2 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(2);
                        xo = "x";
                        x2 = 1;
                    } else if (xo.equals("x")) {
                        setO(2);
                        xo = "o";
                        x2 = 2;
                    }
                }
                i2 = true;
                checkIfThreeInRow();
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i3 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(3);
                        xo = "x";
                        x3 = 1;
                    } else if (xo.equals("x")) {
                        setO(3);
                        xo = "o";
                        x3 = 2;
                    }
                }
                i3 = true;
                checkIfThreeInRow();
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i4 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(4);
                        xo = "x";
                        x4 = 1;
                    } else if (xo.equals("x")) {
                        setO(4);
                        xo = "o";
                        x4 = 2;
                    }
                }
                i4 = true;
                checkIfThreeInRow();
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i5 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(5);
                        xo = "x";
                        x5 = 1;
                    } else if (xo.equals("x")) {
                        setO(5);
                        xo = "o";
                        x5 = 2;
                    }
                }
                i5 = true;
                checkIfThreeInRow();
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i6 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(6);
                        xo = "x";
                        x6 = 1;
                    } else if (xo.equals("x")) {
                        setO(6);
                        xo = "o";
                        x6 = 2;
                    }
                }
                i6 = true;
                checkIfThreeInRow();
            }
        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i7 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(7);
                        xo = "x";
                        x7 = 1;
                    } else if (xo.equals("x")) {
                        setO(7);
                        xo = "o";
                        x7 = 2;
                    }
                }
                i7 = true;
                checkIfThreeInRow();
            }
        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i8 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(8);
                        xo = "x";
                        x8 = 1;
                    } else if (xo.equals("x")) {
                        setO(8);
                        xo = "o";
                        x8 = 2;
                    }
                }
                i8 = true;
                checkIfThreeInRow();
            }
        });

        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i9 && !gameOver) {
                    if (xo.equals("o")) {
                        setX(9);
                        xo = "x";
                        x9 = 1;
                    } else if (xo.equals("x")) {
                        setO(9);
                        xo = "o";
                        x9 = 2;
                    }
                }
                i9 = true;
                checkIfThreeInRow();
            }
        });

        // Reset everything within the current playing session when reset is clicked
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        // Start a new game
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastSave.getInstance().saveInt(SCORE1, 0);
                FastSave.getInstance().saveInt(SCORE2, 0);
                FastSave.getInstance().saveInt(TIMESPLAYED, 0);
                setNewGame();
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

    public void alertDialog() {
        if (FastSave.getInstance().getBoolean(RESTART, false)) {
            FastSave.getInstance().saveBoolean(RESTART, false);
        } else {
            inflater = PlayActivity.this.getLayoutInflater();
            View v = inflater.inflate(R.layout.alertdialog, null);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(PlayActivity.this);
            mBuilder.setView(v);

            mPlayer1 = (EditText) v.findViewById(R.id.et_player1);
            mPlayer2 = (EditText) v.findViewById(R.id.et_player2);

            FrameLayout clearName1 = v.findViewById(R.id.fl_name1_clear);
            FrameLayout clearName2 = v.findViewById(R.id.fl_name2_clear);

            clearName1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayer1.setText(null);
                }
            });

            clearName2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayer2.setText(null);
                }
            });

            String p1 = FastSave.getInstance().getString(PLAYER1, "");
            String p2 = FastSave.getInstance().getString(PLAYER2, "");
            if (!p1.isEmpty() && !p2.isEmpty()) {
                mPlayer1.setText(p1);
                mPlayer2.setText(p2);
            }

            mBuilder.setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FastSave.getInstance().saveString(PLAYER1, "");
                    FastSave.getInstance().saveString(PLAYER2, "");
                    namesEmpty = true;
                }
            });

            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    playerName1 = mPlayer1.getText().toString();
                    playerName2 = mPlayer2.getText().toString();
                    FastSave.getInstance().saveString(PLAYER1, playerName1);
                    FastSave.getInstance().saveString(PLAYER2, playerName2);
                    if (!playerName1.isEmpty() && !playerName2.isEmpty() &&
                            !FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
                        player1.setText(playerName1 + " (X)");
                        player2.setText(playerName2 + " (O)");
                        vs.setVisibility(View.VISIBLE);
                        namesEmpty = false;
                    } else if (playerName1.isEmpty()) {
                        player1.setText(null);
                        player2.setText(null);
                        vs.setVisibility(View.GONE);
                        namesEmpty = true;
                    }
                    score1.setText(null);
                    score2.setText(null);
                }
            });
            mBuilder.show();
        }
    }

    /*
    Redisplay the player names if the activity is restarted to change to dark or light mode
     */
    public void setPlayerNamesOnRestart() {
        if (!FastSave.getInstance().getBoolean(RESTART, false) &&
                !FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            if (!FastSave.getInstance().getString(PLAYER1, "").isEmpty()) {
                player1.setText(FastSave.getInstance().getString(PLAYER1, "") + " (X)");
                player2.setText(FastSave.getInstance().getString(PLAYER2, "") + " (O)");
                vs.setVisibility(View.VISIBLE);
            }
            if (!(FastSave.getInstance().getInt(TIMESPLAYED, 0) == 0)) {
                setPlayerScoresText();
            }
        }
    }

    /*
    Toggles distraction free mode
     */
    public void toggleDistractionFreeMode() {
        if (!FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            player1.setText(null);
            player2.setText(null);
            vs.setVisibility(View.GONE);
            reset.setVisibility(View.GONE);
            newGame.setVisibility(View.GONE);
            score1.setVisibility(View.GONE);
            score2.setVisibility(View.GONE);
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                bottomBar.setBackgroundResource(android.R.color.black);
            } else {
                bottomBar.setBackgroundResource(android.R.color.white);
            }
            FastSave.getInstance().saveBoolean(DISTRACTIONFREEMODE, true);
        } else {
            if (namesEmpty) {
//                player1.setText(FastSave.getInstance().getString(PLAYER1, "") + " (X)");
//                player2.setText(FastSave.getInstance().getString(PLAYER2, "") + " (O)");
                player1.setVisibility(View.VISIBLE);
                player2.setVisibility(View.VISIBLE);
                vs.setVisibility(View.GONE);
            } else {
                player1.setText(FastSave.getInstance().getString(PLAYER1, "") + " (X)");
                player1.setVisibility(View.VISIBLE);
                vs.setVisibility(View.VISIBLE);
                player2.setText(FastSave.getInstance().getString(PLAYER2, "") + " (O)");
                player2.setVisibility(View.VISIBLE);
            }
//            player1.setVisibility(View.VISIBLE);
//            player2.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);
            newGame.setVisibility(View.VISIBLE);
            score1.setVisibility(View.VISIBLE);
            score2.setVisibility(View.VISIBLE);
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                bottomBar.setBackgroundResource(R.color.colorGrayDark);
            } else {
                bottomBar.setBackgroundResource(R.color.colorGrayLight);
            }
            FastSave.getInstance().saveBoolean(DISTRACTIONFREEMODE, false);
        }
    }

    private void toggleShake() {
        if (!FastSave.getInstance().getBoolean(SHAKE, false)) {
            FastSave.getInstance().saveBoolean(SHAKE, true);
        } else {
            FastSave.getInstance().saveBoolean(SHAKE, false);
        }
    }

    public void checkDistractionFreeMode() {
        if (FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            reset.setVisibility(View.GONE);
            newGame.setVisibility(View.GONE);
            score1.setVisibility(View.GONE);
            score2.setVisibility(View.GONE);
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                bottomBar.setBackgroundResource(android.R.color.black);
            } else {
                bottomBar.setBackgroundResource(android.R.color.white);
            }
        }
    }

    /*
    Resets the board and everything else except the current playing session
     */
    public void reset() {
        xo = "o";
        gameOver = false;

        if (player1.toString().contains("won")) {
            Intent i = new Intent(PlayActivity.this, PlayActivity.class);
            startActivity(i);
        }

        player1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        if (playerName1 == null && playerName2 == null || playerName1.isEmpty() && playerName2.isEmpty()) {
            player1.setText(null);
            player2.setText(null);
            player1.setVisibility(View.VISIBLE);
            player2.setVisibility(View.VISIBLE);
        } else if (FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            player1.setText(null);
            player2.setText(null);
            vs.setVisibility(View.GONE);
        } else {
            player1.setText(playerName1 + " (X)");
            player2.setText(playerName2 + " (O)");
            player1.setVisibility(View.VISIBLE);
            vs.setVisibility(View.VISIBLE);
            player2.setVisibility(View.VISIBLE);
        }

        iv1.setImageResource(android.R.color.transparent);
        iv2.setImageResource(android.R.color.transparent);
        iv3.setImageResource(android.R.color.transparent);
        iv4.setImageResource(android.R.color.transparent);
        iv5.setImageResource(android.R.color.transparent);
        iv6.setImageResource(android.R.color.transparent);
        iv7.setImageResource(android.R.color.transparent);
        iv8.setImageResource(android.R.color.transparent);
        iv9.setImageResource(android.R.color.transparent);

        i1 = false;
        i2 = false;
        i3 = false;
        i4 = false;
        i5 = false;
        i6 = false;
        i7 = false;
        i8 = false;
        i9 = false;

        x1 = 0;
        x2 = 0;
        x3 = 0;
        x4 = 0;
        x5 = 0;
        x6 = 0;
        x7 = 0;
        x8 = 0;
        x9 = 0;
    }

    public void setNewGame() {
        Intent newGame = new Intent(PlayActivity.this, PlayActivity.class);
        startActivity(newGame);
    }

    public void setX(int number) {
        if (number == 1) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv1.setImageResource(R.drawable.x_white);
            } else {
                iv1.setImageResource(R.drawable.x_black);
            }
        } else if (number == 2) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv2.setImageResource(R.drawable.x_white);
            } else {
                iv2.setImageResource(R.drawable.x_black);
            }
        } else if (number == 3) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv3.setImageResource(R.drawable.x_white);
            } else {
                iv3.setImageResource(R.drawable.x_black);
            }
        } else if (number == 4) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv4.setImageResource(R.drawable.x_white);
            } else {
                iv4.setImageResource(R.drawable.x_black);
            }
        } else if (number == 5) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv5.setImageResource(R.drawable.x_white);
            } else {
                iv5.setImageResource(R.drawable.x_black);
            }
        } else if (number == 6) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv6.setImageResource(R.drawable.x_white);
            } else {
                iv6.setImageResource(R.drawable.x_black);
            }
        } else if (number == 7) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv7.setImageResource(R.drawable.x_white);
            } else {
                iv7.setImageResource(R.drawable.x_black);
            }
        } else if (number == 8) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv8.setImageResource(R.drawable.x_white);
            } else {
                iv8.setImageResource(R.drawable.x_black);
            }
        } else if (number == 9) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv9.setImageResource(R.drawable.x_white);
            } else {
                iv9.setImageResource(R.drawable.x_black);
            }
        }
    }

    public void setO(int number) {
        if (number == 1) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv1.setImageResource(R.drawable.o_white);
            } else {
                iv1.setImageResource(R.drawable.o_black);
            }
        } else if (number == 2) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv2.setImageResource(R.drawable.o_white);
            } else {
                iv2.setImageResource(R.drawable.o_black);
            }
        } else if (number == 3) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv3.setImageResource(R.drawable.o_white);
            } else {
                iv3.setImageResource(R.drawable.o_black);
            }
        } else if (number == 4) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv4.setImageResource(R.drawable.o_white);
            } else {
                iv4.setImageResource(R.drawable.o_black);
            }
        } else if (number == 5) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv5.setImageResource(R.drawable.o_white);
            } else {
                iv5.setImageResource(R.drawable.o_black);
            }
        } else if (number == 6) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv6.setImageResource(R.drawable.o_white);
            } else {
                iv6.setImageResource(R.drawable.o_black);
            }
        } else if (number == 7) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv7.setImageResource(R.drawable.o_white);
            } else {
                iv7.setImageResource(R.drawable.o_black);
            }
        } else if (number == 8) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv8.setImageResource(R.drawable.o_white);
            } else {
                iv8.setImageResource(R.drawable.o_black);
            }
        } else if (number == 9) {
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                iv9.setImageResource(R.drawable.o_white);
            } else {
                iv9.setImageResource(R.drawable.o_black);
            }
        }
    }

    /*
    Sets text that displays to the user that player X won
     */
    public void setXWinnerText() {
        vs.setVisibility(View.GONE);
        player2.setVisibility(View.GONE);
        player1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        if (playerName1 == null && playerName2 == null || playerName1.isEmpty() && playerName2.isEmpty()) {
            player1.setText("Player X won!");
        } else {
            player1.setText(playerName1 + " won!");
        }
        player1Score = FastSave.getInstance().getInt(SCORE1, 0);
        player1Score = player1Score + 1;
        FastSave.getInstance().saveInt(SCORE1, player1Score);
        setPlayerScoresText();

        int timesPlayed = FastSave.getInstance().getInt(TIMESPLAYED, 0);
        timesPlayed = timesPlayed + 1;
        FastSave.getInstance().saveInt(TIMESPLAYED, timesPlayed);
    }

    /*
    Sets text that displays to the user that player X won
     */
    public void setOWinnerText() {
        player2.setVisibility(View.GONE);
        vs.setVisibility(View.GONE);
        player1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        if (playerName1 == null && playerName2 == null || playerName1.isEmpty() && playerName2.isEmpty()) {
            player1.setText("Player O won!");
        } else {
            player1.setText(playerName2 + " won!");
        }
        player2Score = FastSave.getInstance().getInt(SCORE2, 0);
        player2Score = player2Score + 1;
        FastSave.getInstance().saveInt(SCORE2, player2Score);
        setPlayerScoresText();

        int timesPlayed = FastSave.getInstance().getInt(TIMESPLAYED, 0);
        timesPlayed = timesPlayed + 1;
        FastSave.getInstance().saveInt(TIMESPLAYED, timesPlayed);
    }

    public void setDrawText() {
        player2.setVisibility(View.GONE);
        vs.setVisibility(View.GONE);
        player1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        player1.setText("Draw!");
        setPlayerScoresText();

        int timesPlayed = FastSave.getInstance().getInt(TIMESPLAYED, 0);
        timesPlayed = timesPlayed + 1;
        FastSave.getInstance().saveInt(TIMESPLAYED, timesPlayed);
    }

    public void setPlayerScoresText() {
        playerName1 = FastSave.getInstance().getString(PLAYER1, "");
        playerName2 = FastSave.getInstance().getString(PLAYER2, "");
        int x = FastSave.getInstance().getInt(SCORE1, 0);
        int o = FastSave.getInstance().getInt(SCORE2, 0);
        score1.setText(playerName1 + ": " + x + " points");
        score2.setText(playerName2 + ": " + o + " points");
    }

    /*
    Checks if X or O has three in a row anywhere
     */
    public void checkIfThreeInRow() {
        // Check if X is winning
        if (x1 == 1 && x2 == 1 && x3 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv2.setImageResource(R.drawable.x_red);
            iv3.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x4 == 1 && x5 == 1 && x6 == 1) {
            iv4.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv6.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x7 == 1 && x8 == 1 && x9 == 1) {
            iv7.setImageResource(R.drawable.x_red);
            iv8.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x1 == 1 && x4 == 1 && x7 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv4.setImageResource(R.drawable.x_red);
            iv7.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x2 == 1 && x5 == 1 && x8 == 1) {
            iv2.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv8.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x3 == 1 && x6 == 1 && x9 == 1) {
            iv3.setImageResource(R.drawable.x_red);
            iv6.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x1 == 1 && x5 == 1 && x9 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        } else if (x3 == 1 && x5 == 1 && x7 == 1) {
            iv3.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv7.setImageResource(R.drawable.x_red);
            gameOver = true;
            setXWinnerText();
        }
        // Check if O is winning
        else if (x1 == 2 && x2 == 2 && x3 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv2.setImageResource(R.drawable.o_red);
            iv3.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x4 == 2 && x5 == 2 && x6 == 2) {
            iv4.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv6.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x7 == 2 && x8 == 2 && x9 == 2) {
            iv7.setImageResource(R.drawable.o_red);
            iv8.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x1 == 2 && x4 == 2 && x7 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv4.setImageResource(R.drawable.o_red);
            iv7.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x2 == 2 && x5 == 2 && x8 == 2) {
            iv2.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv8.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x3 == 2 && x6 == 2 && x9 == 2) {
            iv3.setImageResource(R.drawable.o_red);
            iv6.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x1 == 2 && x5 == 2 && x9 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x3 == 2 && x5 == 2 && x7 == 2) {
            iv3.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv7.setImageResource(R.drawable.o_red);
            gameOver = true;
            setOWinnerText();
        } else if (x1 != 0 && x2 != 0 && x3 != 0 && x4 != 0 && x5 != 0 && x6 != 0 && x7 != 0 &&
                x8 != 0 && x9 != 0) {
            if (x1 == 1) {
                iv1.setImageResource(R.drawable.x_yellow);
            } else {
                iv1.setImageResource(R.drawable.o_yellow);
            }
            if (x2 == 1) {
                iv2.setImageResource(R.drawable.x_yellow);
            } else {
                iv2.setImageResource(R.drawable.o_yellow);
            }
            if (x3 == 1) {
                iv3.setImageResource(R.drawable.x_yellow);
            } else {
                iv3.setImageResource(R.drawable.o_yellow);
            }
            if (x4 == 1) {
                iv4.setImageResource(R.drawable.x_yellow);
            } else {
                iv4.setImageResource(R.drawable.o_yellow);
            }
            if (x5 == 1) {
                iv5.setImageResource(R.drawable.x_yellow);
            } else {
                iv5.setImageResource(R.drawable.o_yellow);
            }
            if (x6 == 1) {
                iv6.setImageResource(R.drawable.x_yellow);
            } else {
                iv6.setImageResource(R.drawable.o_yellow);
            }
            if (x7 == 1) {
                iv7.setImageResource(R.drawable.x_yellow);
            } else {
                iv7.setImageResource(R.drawable.o_yellow);
            }
            if (x8 == 1) {
                iv8.setImageResource(R.drawable.x_yellow);
            } else {
                iv8.setImageResource(R.drawable.o_yellow);
            }
            if (x9 == 1) {
                iv9.setImageResource(R.drawable.x_yellow);
            } else {
                iv9.setImageResource(R.drawable.o_yellow);
            }
            gameOver = true;
            if (!player1.toString().contains("won")) {
                setDrawText();
            }
        }
    }
}
