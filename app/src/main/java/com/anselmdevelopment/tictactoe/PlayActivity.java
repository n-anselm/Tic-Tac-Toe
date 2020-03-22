package com.anselmdevelopment.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

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
    TextView startNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);
        startNewGame = findViewById(R.id.tv_start_new_game);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!i1 && !gameOver) {
                    if (xo.equals("o")) {
                        iv1.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i1 = true;
                        x1 = 1;
                        checkIfThreeInRow();
                    } else if (xo.equals("x")) {
                        iv1.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i1 = true;
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
                        iv2.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i2 = true;
                        x2 = 1;
                    } else if (xo.equals("x")) {
                        iv2.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i2 = true;
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
                        iv3.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i3 = true;
                        x3 = 1;
                    } else if (xo.equals("x")) {
                        iv3.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i3 = true;
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
                        iv4.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i4 = true;
                        x4 = 1;
                    } else if (xo.equals("x")) {
                        iv4.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i4 = true;
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
                        iv5.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i5 = true;
                        x5 = 1;
                    } else if (xo.equals("x")) {
                        iv5.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i5 = true;
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
                        iv6.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i6 = true;
                        x6 = 1;
                    } else if (xo.equals("x")) {
                        iv6.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i6 = true;
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
                        iv7.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i7 = true;
                        x7 = 1;
                    } else if (xo.equals("x")) {
                        iv7.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i7 = true;
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
                        iv8.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i8 = true;
                        x8 = 1;
                    } else if (xo.equals("x")) {
                        iv8.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i8 = true;
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
                        iv9.setImageResource(R.drawable.x_black);
                        xo = "x";
//                        i9 = true;
                        x9 = 1;
                    } else if (xo.equals("x")) {
                        iv9.setImageResource(R.drawable.o_black);
                        xo = "o";
//                        i9 = true;
                        x9 = 2;
                    }
                }
                i9 = true;
                checkIfThreeInRow();
            }
        });

        startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearXO();
            }
        });
    }

    public void clearXO() {
        xo = "o";
        gameOver = false;

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

    public void checkIfThreeInRow() {
        // Check if X is winning
        if (x1 == 1 && x2 == 1 && x3 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv2.setImageResource(R.drawable.x_red);
            iv3.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x4 == 1 && x5 == 1 && x6 == 1) {
            iv4.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv6.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x7 == 1 && x8 == 1 && x9 == 1) {
            iv7.setImageResource(R.drawable.x_red);
            iv8.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x1 == 1 && x4 == 1 && x7 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv4.setImageResource(R.drawable.x_red);
            iv7.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x2 == 1 && x5 == 1 && x8 == 1) {
            iv2.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv8.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x3 == 1 && x6 == 1 && x9 == 1) {
            iv3.setImageResource(R.drawable.x_red);
            iv6.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x1 == 1 && x5 == 1 && x9 == 1) {
            iv1.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv9.setImageResource(R.drawable.x_red);
            gameOver = true;
        } else if (x3 == 1 && x5 == 1 && x7 == 1) {
            iv3.setImageResource(R.drawable.x_red);
            iv5.setImageResource(R.drawable.x_red);
            iv7.setImageResource(R.drawable.x_red);
            gameOver = true;
        }
        // Check if O is winning
        else if (x1 == 2 && x2 == 2 && x3 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv2.setImageResource(R.drawable.o_red);
            iv3.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x4 == 2 && x5 == 2 && x6 == 2) {
            iv4.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv6.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x7 == 2 && x8 == 2 && x9 == 2) {
            iv7.setImageResource(R.drawable.o_red);
            iv8.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x1 == 2 && x4 == 2 && x7 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv4.setImageResource(R.drawable.o_red);
            iv7.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x2 == 2 && x5 == 2 && x8 == 2) {
            iv2.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv8.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x3 == 2 && x6 == 2 && x9 == 2) {
            iv3.setImageResource(R.drawable.o_red);
            iv6.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x1 == 2 && x5 == 2 && x9 == 2) {
            iv1.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv9.setImageResource(R.drawable.o_red);
            gameOver = true;
        } else if (x3 == 2 && x5 == 2 && x7 == 2) {
            iv3.setImageResource(R.drawable.o_red);
            iv5.setImageResource(R.drawable.o_red);
            iv7.setImageResource(R.drawable.o_red);
            gameOver = true;
        }
    }
}
