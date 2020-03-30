package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
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
        setContentView(R.layout.activity_main);
        textViewPlayer1 = findViewById(R.id.textViewP1);
        textViewPLayer2 = findViewById(R.id.textViewP2);
        resetButton = findViewById(R.id.button_reset);
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
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
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
            countRound++;
            if (checkForWin()){
                player1Win();
                player2Turn = false;
            }
        }
        if (player2Turn && countRound !=9){
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
    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"PLayer 2 wins!",Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw ! ",Toast.LENGTH_LONG).show();
        resetBoard();
    }
    private void updatePointsText() {
        textViewPlayer1.setText("Player1 : "+player1Points);
        textViewPLayer2.setText("Player2 : "+player2Points);
    }
    private void resetBoard(){
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                buttons[i][j].setText("");
            }
        }
        countRound = 0;
        player1Turn = true;
    }
}
