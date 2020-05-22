package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main2Activity extends AppCompatActivity {
    Button playerVsPlayer;
    Button playerVsComputer;
    Intent myIntent;
    TextView logoText;
    Bundle bundle = new Bundle();
    FloatingActionButton floatingActionButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        logoText = findViewById(R.id.logoText);
        Shader shader = new LinearGradient(0, 0, 0, logoText.getTextSize(), Color.parseColor("#ffb74d"), Color.parseColor("#fff8e1"),
                Shader.TileMode.REPEAT);
        logoText.getPaint().setShader(shader);
        playerVsPlayer = findViewById(R.id.plyr1VsPlyr2);
        playerVsComputer = findViewById(R.id.plyr1Pc);
        floatingActionButtonBack = findViewById(R.id.floating_action_button_Back_Main2);
        floatingActionButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFirstActivity();
            }
        });
    }
    private void BackFirstActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
    public void OnePlayer(View view) {
        myIntent = new Intent(this, Main3Activity.class);
        bundle.putInt("playerVsComputer",1);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
    public void TwoPlayer(View view) {
        myIntent = new Intent(this, Main3Activity.class);
        bundle.putInt("playerVsPlayer",2);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
