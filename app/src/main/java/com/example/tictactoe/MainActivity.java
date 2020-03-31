package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button playerVsPlayer;
    Button playerVsComputer;
    Intent myIntent;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerVsPlayer = findViewById(R.id.plyr1VsPlyr2);
        playerVsComputer = findViewById(R.id.plyr1Pc);

    }

    public void OnePlayer(View view) {
        myIntent = new Intent(this,Main2Activity.class);
        bundle.putInt("playerVsComputer",1);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }

    public void TwoPlayer(View view) {
        myIntent = new Intent(this,Main2Activity.class);
        bundle.putInt("playerVsPlayer",2);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
