package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent myIntent;
    RadioGroup radioGroup;
    Button buttonGo;
    int radioButtonSelectedId;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.RadioGroup);
        buttonGo = findViewById(R.id.Go);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                radioButtonSelectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioButtonSelectedId);
                switch (radioButton.getId()){
                    case R.id.threethree:
                        toActivityTroisFoisTrois();
                        break;
                    case R.id.fivefive:
                        toActivityCinqFoisCinq();
                        break;
                }
            }
        });
    }

    public void toActivityTroisFoisTrois() {
        myIntent = new Intent(this,Main2Activity.class);
        startActivity(myIntent);

    }

    public void toActivityCinqFoisCinq() {
        myIntent = new Intent(this,Main4Activity.class);
        startActivity(myIntent);


    }
}
