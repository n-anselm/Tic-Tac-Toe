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

public class PlayActivity extends AppCompatActivity {

    EditText mPlayer1, mPlayer2;
    TextView player1, vs, player2;
    String playerName1, playerName2;
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
    RelativeLayout bottomBar, bottomBar2;
    FrameLayout optionsArrowUp, optionsArrowUp2;

    public static final String DARKMODE = "darkmode";
    public static final String RESTART = "restart";
    public static final String DISTRACTIONFREEMODE = "distractionfreemode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FastSave.init(getApplicationContext()); // Initialize FastSave - Reference: "https://github.com/yehiahd/FastSave-Android"
        checkTheme();
        setContentView(R.layout.activity_play);

        player1 = findViewById(R.id.tv_player1);
        player2 = findViewById(R.id.tv_player2);
        vs = findViewById(R.id.tv_vs);
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

        optionsArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create instance of PopupMenu
                Context wrapper = new ContextThemeWrapper(PlayActivity.this, R.style.PopupMenuStyle);
                final PopupMenu popupMenu = new PopupMenu(wrapper, optionsArrowUp);

                if (!FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
                    // Inflate the popup menu using xml file
                    popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                    // Register popup with OnMenuItemClickListener
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            if (id == R.id.action_setchange_player_names) { // Set/change player names
                                alertDialog();
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
                    // Inflate the popup menu using xml file
                    popupMenu.getMenuInflater().inflate(R.menu.menu_main2, popupMenu.getMenu());

                    // Register popup with OnMenuItemClickListener
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            if (id == R.id.action_setchange_player_names) { // Set/change player names
                                alertDialog();
                                return true;
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
                                setNewGame();
                            }
                            return false;
                        }
                    });
                }
                popupMenu.show();
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
                        checkIfThreeInRow();
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
            mBuilder.setView(v)
                    .setNeutralButton("Skip", null);

            mPlayer1 = (EditText) v.findViewById(R.id.et_player1);
            mPlayer2 = (EditText) v.findViewById(R.id.et_player2);

            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    playerName1 = mPlayer1.getText().toString();
                    playerName2 = mPlayer2.getText().toString();
                    if (!playerName1.isEmpty() && !playerName2.isEmpty()) {
                        player1.setText(playerName1 + " (X)");
                        player2.setText(playerName2 + " (O)");
                        vs.setVisibility(View.VISIBLE);
                    }
                }
            });
            mBuilder.show();
        }
    }

    /*
    Toggles distraction free mode
     */
    public void toggleDistractionFreeMode() {
        if (!FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            reset.setVisibility(View.GONE);
            newGame.setVisibility(View.GONE);
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                bottomBar.setBackgroundResource(android.R.color.black);
            } else {
                bottomBar.setBackgroundResource(android.R.color.white);
            }
            FastSave.getInstance().saveBoolean(DISTRACTIONFREEMODE, true);
        } else {
            reset.setVisibility(View.VISIBLE);
            newGame.setVisibility(View.VISIBLE);
            if (FastSave.getInstance().getBoolean(DARKMODE, false)) {
                bottomBar.setBackgroundResource(R.color.colorGrayDark);
            } else {
                bottomBar.setBackgroundResource(R.color.colorGrayLight);
            }
            FastSave.getInstance().saveBoolean(DISTRACTIONFREEMODE, false);
        }
    }

    public void checkDistractionFreeMode() {
        if (FastSave.getInstance().getBoolean(DISTRACTIONFREEMODE, false)) {
            reset.setVisibility(View.GONE);
            newGame.setVisibility(View.GONE);
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

        player1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        if (playerName1 == null && playerName2 == null || playerName1.isEmpty() && playerName2.isEmpty()) {
            player1.setText(null);
            player2.setText(null);
            player1.setVisibility(View.VISIBLE);
            player2.setVisibility(View.VISIBLE);
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
        }
    }
}
