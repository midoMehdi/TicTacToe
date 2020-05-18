package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[8][8];
    private boolean player1Turn = true;
    private boolean player2Turn = true;
    private int countRound = 0;
    private int player1Points = 0;
    private int player2Points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPLayer2;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textViewPlayer1 = findViewById(R.id.textViewP1);
        textViewPLayer2 = findViewById(R.id.plyr1VsPlyr2);
        resetButton = findViewById(R.id.button_reset);
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                String buttonId = "button_"+i + j;
                int resId = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPlayer1.setText("Player1 : "+0);
                textViewPLayer2.setText("Player2 : "+0);
                resetBoard();
            }
        });
    }
    private void playerVsPLayer(View v){
        if (player1Turn){
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }
        countRound++;
        if (checkForWin()){
            if (player1Turn){
                player1Win();
            }
            else{
                player2Wins();
            }
        }
        else if (countRound == 64){
            draw();
        }
        else {
            player1Turn =! player1Turn;
        }
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().equals("")) {
            return;
        }
        playerVsPLayer(v);

    }
    private boolean checkForWin(){
        String[][] filed = new String[8][8];
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                filed[i][j] =  buttons[i][j].getText().toString();
            }
        }
        /**
         * Check for win horizentalement*/

        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                if (filed[i][j].equals(filed[i][j+1])
                        && filed[i][j+1].equals(filed[i][j+2])
                        && filed[i][j+2].equals(filed[i][j+3])
                        && filed[i][j+3].equals(filed[i][j+4])
                        && !filed[i][j].equals("")){

                    return true;
                }
            }
        }

        /**
         * Check for win verticalement */
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                if (filed[i][j].equals(filed[i+1][j])
                        && filed[i+1][j].equals(filed[i+2][j])
                        && filed[i+2][j].equals(filed[i+3][j])
                        && filed[i+3][j].equals(filed[i+4][j])
                        && !filed[i][j].equals("")){

                    return true;
                }
            }
        }

        /**
         * Check for diagonalement  première forme"\"
         * */
        for (int i = 0 ; i < 4 ; i++){
            if (filed[i][i].equals(filed[i+1][i+1])
                    && filed[i+1][i+1].equals(filed[i+2][i+2])
                    && filed[i+2][i+2].equals(filed[i+3][i+3])
                    && filed[i+3][i+3].equals(filed[i+4][i+4])
                    && !filed[i][i].equals("")){

                return true;
            }
        }

        /**
         * Check diagonalement  deuxieme forme"/"
         * */
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 7 ; j > 3 ; j--){
                if (filed[i][j].equals(filed[i+1][j-1])
                        && filed[i+1][j-1].equals(filed[i+2][j-2])
                        && filed[i+2][j-2].equals(filed[i+3][j-3])
                        && filed[i+3][j-3].equals(filed[i+4][j-4])
                        && !filed[i][j].equals("")){

                    return true;
                }
            }
        }
        return false;
    }

    private void player1Win(){
        player1Points++;
        Toast.makeText(this,"PLayer 1 wins",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
        init();
    }

    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"PLayer 2 wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
        init();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player1 : "+player1Points);
        textViewPLayer2.setText("Player2 : "+player2Points);
    }

    private void draw(){
        Toast.makeText(this,"Draw ! ",Toast.LENGTH_LONG).show();
        resetBoard();
        init();
    }


    private void init(){
        countRound = 0;
        player1Turn = true;
        player2Turn = true;
    }

    public void stopMusic(View view) {

    }

    public void BackFirstActivity(View view) {

    }


    public void resetBoard() {
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                buttons[i][j].setText("");
            }
        }
    }
}
