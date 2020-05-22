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

import java.util.Random;
public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private boolean player2Turn = true;
    private int countRound = 0;
    private int player1Points = 0;
    private int player2Points = 0;
    private TextView textViewPlayer1;
    private TextView textViewPLayer2;
    Bundle bundle;
    int codePlayerVsPlayer = 10; //initialisation différente du vrai code.
    int codePlayerVsComputer = 11;//initialisation différente du vrai code.
    MediaPlayer musicNCS;
    FloatingActionButton floatingActionButtonReset;
    FloatingActionButton floatingActionButtonMusic;
    FloatingActionButton floatingActionButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        playMusic();
        textViewPlayer1 = findViewById(R.id.textViewP1);
        textViewPLayer2 = findViewById(R.id.textViewP2);
        floatingActionButtonReset = findViewById(R.id.floating_action_button_Reset);
        floatingActionButtonMusic = findViewById(R.id.floating_action_button_Music);
        floatingActionButtonBack = findViewById(R.id.floating_action_button_Back);
        bundle = getIntent().getExtras();
        codePlayerVsPlayer = bundle.getInt("playerVsPlayer");
        codePlayerVsComputer = bundle.getInt("playerVsComputer");
        if (codePlayerVsPlayer != 2){
            textViewPLayer2.setText("Computer : "+0);
        }
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
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
                if (codePlayerVsPlayer != 2){
                    textViewPLayer2.setText("Computer : "+0);
                }
                else {
                    textViewPLayer2.setText("Player2 : "+0);
                }
                player1Points = 0;
                player2Points = 0;
                resetBoard();
            }
        });
        floatingActionButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();

            }
        });
        floatingActionButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFirstActivity();
            }
        });
    }
    private void playerVSplayer(View v){
        if (player1Turn){

            ((Button) v).setText("X");
            //((Button) v).setTextColor(Color.parseColor("#ffb74d"));

        }
        else {

            ((Button) v).setText("O");
            //((Button) v).setTextColor(Color.parseColor("#ffffff"));
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
        else if (countRound == 9){
            draw();
        }
        else {
            player1Turn =! player1Turn;
        }
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().equals("")){
            return;
        }
        if (codePlayerVsPlayer == 2){
            playerVSplayer(v);

        } else {
            if (player1Turn){
                ((Button) v).setText("X");
                countRound++;
                if (checkForWin()){
                    player1Win();
                }
            }
            if ( player2Turn && countRound !=9){
                artificielInteligence();
                countRound++;
                if (checkForWin()){
                    player2Wins();
                }
            }

            if (countRound == 9){
                draw();
            }
        }
    }
    private void artificielInteligence() {
        String[][] filed = new String[3][3];
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                filed[i][j] =  buttons[i][j].getText().toString();
            }
        }
        Random random = new Random();
        int ii;
        int jj;
        do {
            ii = random.nextInt(3);
            jj = random.nextInt(3);
        }while (!buttons[ii][jj].getText().equals(""));
        for (int i = 0 ; i < 3 ; i++){

            /** Partie Defense pour ne pas perdre pour le deuxieme joueur
             * */


            /**
             * Check horizentalement
             * */
            if (buttons[i][0].getText().equals("X") && filed[i][0].equals(filed[i][1]) && buttons[i][2].getText().equals("")) {
                ii = i;
                jj = 2;
            }
            else if (buttons[i][0].getText().equals("X") && filed[i][0].equals(filed[i][2]) && buttons[i][1].getText().equals("")){
                ii = i;
                jj = 1;
            }
            else if (buttons[i][1].getText().equals("X") && filed[i][1].equals(filed[i][2]) && buttons[i][0].getText().equals("")){
                ii = i;
                jj = 0;
            }
            /**
             * Check verticalement
             * */
            if (buttons[0][i].getText().equals("X") && filed[0][i].equals(filed[1][i]) && buttons[2][i].getText().equals("")) {
                ii = 2;
                jj = i;
            }
            else if (buttons[0][i].getText().equals("X") && filed[0][i].equals(filed[2][i]) && buttons[1][i].getText().equals("")){
                ii = 1;
                jj = i;
            }
            else if (buttons[1][i].getText().equals("X") && filed[1][i].equals(filed[2][i]) && buttons[0][i].getText().equals("")){
                ii = 0;
                jj = i;
            }
            /**
             * Check diagonalement  première forme"\"
             * */
            if (buttons[0][0].getText().equals("X") && filed[0][0].equals(filed[1][1]) && buttons[2][2].getText().equals("")){
                ii = 2;
                jj = 2;
            }
            else if (buttons[0][0].getText().equals("X") && filed[0][0].equals(filed[2][2]) && buttons[1][1].getText().equals("")){
                ii = 1;
                jj = 1;
            }
            else if (buttons[1][1].getText().equals("X") && filed[1][1].equals(filed[2][2]) && buttons[0][0].getText().equals("")){
                ii = 0;
                jj = 0;
            }
            /**
             * Check diagonalement  deuxieme forme"/"
             * */
            if (buttons[0][2].getText().equals("X") && filed[0][2].equals(filed[1][1]) && buttons[2][0].getText().equals("")){
                ii = 2;
                jj = 0;
            }
            else if (buttons[0][2].getText().equals("X") && filed[0][2].equals(filed[2][0]) && buttons[1][1].getText().equals("")){
                ii = 1;
                jj = 1;
            }
            else if (buttons[1][1].getText().equals("X") && filed[1][1].equals(filed[2][0]) && buttons[0][2].getText().equals("")){
                ii = 0;
                jj = 2;
            }
        }

        /**
         *
         * Partie Attaque pour gagner ppour le deuxieme joueur
         * */


        for (int i = 0 ; i < 3 ; i++){
            if (buttons[i][0].getText().equals("O") && filed[i][0].equals(filed[i][1]) && buttons[i][2].getText().equals("")) {
                ii = i;
                jj = 2;
            }
            else if (buttons[i][0].getText().equals("O") && filed[i][0].equals(filed[i][2]) && buttons[i][1].getText().equals("")){
                ii = i;
                jj = 1;
            }
            else if (buttons[i][1].getText().equals("O") && filed[i][1].equals(filed[i][2]) && buttons[i][0].getText().equals("")){
                ii = i;
                jj = 0;
            }
            /**
             * Check verticalement
             * */
            if (buttons[0][i].getText().equals("O") && filed[0][i].equals(filed[1][i]) && buttons[2][i].getText().equals("")) {
                ii = 2;
                jj = i;
            }
            else if (buttons[0][i].getText().equals("O") && filed[0][i].equals(filed[2][i]) && buttons[1][i].getText().equals("")){
                ii = 1;
                jj = i;
            }
            else if (buttons[1][i].getText().equals("O") && filed[1][i].equals(filed[2][i]) && buttons[0][i].getText().equals("")){
                ii = 0;
                jj = i;
            }
            /**
             * Check diagonalement  première forme"\"
             * */
            if (buttons[0][0].getText().equals("O") && filed[0][0].equals(filed[1][1]) && buttons[2][2].getText().equals("")){
                ii = 2;
                jj = 2;
            }
            else if (buttons[0][0].getText().equals("O") && filed[0][0].equals(filed[2][2]) && buttons[1][1].getText().equals("")){
                ii = 1;
                jj = 1;
            }
            else if (buttons[1][1].getText().equals("O") && filed[1][1].equals(filed[2][2]) && buttons[0][0].getText().equals("")){
                ii = 0;
                jj = 0;
            }
            /**
             * Check diagonalement  deuxieme forme"/"
             * */
            if (buttons[0][2].getText().equals("O") && filed[0][2].equals(filed[1][1]) && buttons[2][0].getText().equals("")){
                ii = 2;
                jj = 0;
            }
            else if (buttons[0][2].getText().equals("O") && filed[0][2].equals(filed[2][0]) && buttons[1][1].getText().equals("")){
                ii = 1;
                jj = 1;
            }
            else if (buttons[1][1].getText().equals("O") && filed[1][1].equals(filed[2][0]) && buttons[0][2].getText().equals("")){
                ii = 0;
                jj = 2;
            }
        }
        System.out.println(ii+""+jj);
        buttons[ii][jj].setText("O");
    }
    private boolean checkForWin(){
        String[][] filed = new String[3][3];
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                filed[i][j] =  buttons[i][j].getText().toString();
            }
        }
        for (int i = 0 ; i < 3 ; i++){
            if (filed[i][0].equals(filed[i][1])
                    && filed[i][0].equals(filed[i][2])
                    && !filed[i][0].equals("")){

                return true;
            }
        }
        for (int j = 0 ; j < 3 ; j++){
            if (filed[0][j].equals(filed[1][j])
                    && filed[0][j].equals(filed[2][j])
                    && !filed[0][j].equals("")){

                return true;
            }
        }
        if (filed[0][0].equals(filed[1][1])
                && filed[0][0].equals(filed[2][2])
                && !filed[0][0].equals("")){

            return true;
        }
        if (filed[2][0].equals(filed[1][1])
                && filed[2][0].equals(filed[0][2])
                && !filed[2][0].equals("")){

            return true;
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

    private void draw(){
        Toast.makeText(this,"Draw ! ",Toast.LENGTH_LONG).show();
        resetBoard();
        init();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player1 : "+player1Points);
        if (codePlayerVsPlayer != 2){
            textViewPLayer2.setText("Computer : "+player2Points);
        }
        else {
            textViewPLayer2.setText("Player2 : "+player2Points);
        }

    }

    private void resetBoard(){
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                buttons[i][j].setText("");
            }
        }
    }

    private void init(){
        countRound = 0;
        player1Turn = true;
        player2Turn = true;
    }

    public void BackFirstActivity() {
        if (musicNCS != null){
            musicNCS.stop();
        }
        Intent myIntent = new Intent(this, Main2Activity.class);
        startActivity(myIntent);
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
