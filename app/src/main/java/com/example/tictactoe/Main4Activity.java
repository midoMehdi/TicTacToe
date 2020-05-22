package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[8][8];
    private boolean player1Turn = true;
    private boolean player2Turn = true;
    private int countRound = 0;
    private int player1Points = 0;
    private int player2Points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPLayer2;
    MediaPlayer musicNCS;
    private FloatingActionButton floatingActionButtonReset;
    private FloatingActionButton floatingActionButtonBack;
    private FloatingActionButton floatingActionButtonMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playMusic();
        setContentView(R.layout.activity_main4);
        floatingActionButtonReset = findViewById(R.id.floating_action_button_Reset_five_for_five);
        floatingActionButtonBack = findViewById(R.id.floating_action_button_Back_five_for_five);
        floatingActionButtonMusic = findViewById(R.id.floating_action_button_Music_five_for_five);
        textViewPlayer1 = findViewById(R.id.textViewP1);
        textViewPLayer2 = findViewById(R.id.textViewP2);
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                String buttonId = "button_"+i + j;
                int resId = getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        floatingActionButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewPlayer1.setText("Player1 : "+0);
                textViewPLayer2.setText("Player2 : "+0);
                resetBoard();
            }
        });
        floatingActionButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFirstActivity();
            }
        });
        floatingActionButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
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
            for(int j = 0 ; j < 4 ; j++){
                if (filed[i][j].equals(filed[i+1][j+1])
                        && filed[i+1][j+1].equals(filed[i+2][j+2])
                        && filed[i+2][j+2].equals(filed[i+3][j+3])
                        && filed[i+3][j+3].equals(filed[i+4][j+4])
                        && !filed[i][j].equals("")){

                    return true;
                }
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
    public void playMusic(){
        if (musicNCS == null){
            musicNCS = MediaPlayer.create(this,R.raw.song);
        }
        musicNCS.start();
    }

    public void stopMusic() {
        if (musicNCS != null){
            musicNCS.stop();
            musicNCS = null;
        }
        else {
            musicNCS = MediaPlayer.create(this,R.raw.song);
            musicNCS.start();
        }

    }

    public void BackFirstActivity() {
        if (musicNCS != null){
            musicNCS.stop();
        }
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);

    }


    public void resetBoard() {
        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                buttons[i][j].setText("");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (musicNCS != null){
            musicNCS.stop();
            musicNCS = null;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        playMusic();
    }
}
