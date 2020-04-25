package com.boardgamebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DiceRollerActivity extends AppCompatActivity {

    private Button plusDiceFour;
    private Button minusDiceFour;
    private Button plusDiceSix;
    private Button minusDiceSix;
    private Button plusDiceEight;
    private Button minusDiceEight;
    private Button plusDiceTen;
    private Button minusDiceTen;
    private Button plusDiceTwelve;
    private Button minusDiceTwelve;
    private Button plusDiceTwenty;
    private Button minusDiceTwenty;
    private TextView diceFourTotal;
    private TextView diceSixTotal;
    private TextView diceEightTotal;
    private TextView diceTenTotal;
    private TextView diceTwelveTotal;
    private TextView diceTwentyTotal;
    private ImageButton rollTheDice;
    private Integer total;
    private TextView finalResult;
    private TextView viewTotalAddNumber;
    private Button addNumber;
    private Button substractNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller);

        plusDiceFour = findViewById(R.id.d4BtnAdd);
        minusDiceFour = findViewById(R.id.d4BtnMinus);
        plusDiceSix = findViewById(R.id.d6BtnAdd);
        minusDiceSix  = findViewById(R.id.d6BtnMinus);
        plusDiceEight = findViewById(R.id.d8BtnAdd);
        minusDiceEight = findViewById(R.id.d8BtnMinus);
        plusDiceTen = findViewById(R.id.d10BtnAdd);
        minusDiceTen = findViewById(R.id.d10BtnMinus);
        plusDiceTwelve = findViewById(R.id.d12BtnAdd);
        minusDiceTwelve = findViewById(R.id.d12BtnMinus);
        plusDiceTwenty = findViewById(R.id.d20BtnAdd);
        minusDiceTwenty = findViewById(R.id.d20BtnMinus);
        diceFourTotal = findViewById(R.id.d4ViewTotal);
        diceSixTotal = findViewById(R.id.d6ViewTotal);
        diceEightTotal = findViewById(R.id.d8ViewTotal);
        diceTenTotal = findViewById(R.id.d10ViewTotal);
        diceTwelveTotal = findViewById(R.id.d12ViewTotal);
        diceTwentyTotal = findViewById(R.id.d20ViewTotal);
        rollTheDice = findViewById(R.id.rollDiceBtn);
        finalResult = findViewById(R.id.finalResult);
        viewTotalAddNumber = findViewById(R.id.dcViewTotal);
        addNumber = findViewById(R.id.cdBtnAdd);
        substractNumber = findViewById(R.id.cdBtnMius);

        //Adding 1 for Dice4
        plusDiceFour.setOnClickListener(v -> addToTotal(diceFourTotal));
        //Adding 1 for Dice6
        plusDiceSix.setOnClickListener(v -> addToTotal(diceSixTotal));
        //Adding 1 for Dice8
        plusDiceEight.setOnClickListener(v -> addToTotal(diceEightTotal));
        //Adding 1 for Dice10
        plusDiceTen.setOnClickListener(v -> addToTotal(diceTenTotal));
        //Adding 1 for Dice12
        plusDiceTwelve.setOnClickListener(v -> addToTotal(diceTwelveTotal));
        //Adding 1 for Dice20
        plusDiceTwenty.setOnClickListener(v -> addToTotal(diceTwentyTotal));
        //Adding 1 for Total Number
        addNumber.setOnClickListener(v -> addToTotal(viewTotalAddNumber));



        //Subtracting 1 for Dice4
        minusDiceFour.setOnClickListener(v -> subtractFromTotal(diceFourTotal));
        //Subtracting 1 for Dice6
        minusDiceSix.setOnClickListener(v -> subtractFromTotal(diceSixTotal));
        //Subtracting 1 for Dice8
        minusDiceEight.setOnClickListener(v -> subtractFromTotal(diceEightTotal));
        //Subtracting 1 for Dice10
        minusDiceTen.setOnClickListener(v -> subtractFromTotal(diceTenTotal));
        //Subtracting 1 for Dice12
        minusDiceTwelve.setOnClickListener(v -> subtractFromTotal(diceTwelveTotal));
        //Subtracting 1 for Dice20
        minusDiceTwenty.setOnClickListener(v -> subtractFromTotal(diceTwentyTotal));
        //Substracting 1 for Total Number
        substractNumber.setOnClickListener(v -> subtractFromTotal(viewTotalAddNumber));



        rollTheDice.setOnClickListener(v -> {
            final Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
            rollTheDice.startAnimation(shake);
            total = 0;
            finalResult.setText("Total result: ");
            if (integerValue(diceFourTotal)>0){
                addToResult(diceFourTotal,4);
            }
            if (integerValue(diceSixTotal)>0){
                addToResult(diceSixTotal,6);
            }
            if (integerValue(diceEightTotal)>0){
                addToResult(diceEightTotal,8);
            }
            if (integerValue(diceTenTotal)>0){
                addToResult(diceTenTotal,10);
            }
            if (integerValue(diceTwelveTotal)>0){
                addToResult(diceTwelveTotal,12);
            }
            if (integerValue(diceTwentyTotal)>0){
                addToResult(diceTwentyTotal,20);
            }
            total += integerValue(viewTotalAddNumber);

            finalResult.append(String.valueOf(total));
        });
    }
    //Function for adding to total
    private void addToTotal (TextView textView){
        String updatedValue = String.valueOf(Integer.parseInt((String) textView.getText()) + 1);
        textView.setText(updatedValue);
    }
    //Function for subtracting from total
    private void subtractFromTotal (TextView textView){
        String updatedValue = String.valueOf(Integer.parseInt((String) textView.getText()) - 1);
        if (Integer.parseInt(updatedValue)>0){
            textView.setText(updatedValue);
        }
        else {
            textView.setText("0");
        }
    }

    //Function to generate random number and add it to total result
    private void addToResult (TextView textView, int numberOfSides){
        Random random = new Random();
        for (int i = 0; i < integerValue(textView);i++){
            total += random.nextInt(numberOfSides) + 1;
        }
    }

    //Function to get integer number from TextView
    private Integer integerValue (TextView textView){
       return Integer.parseInt(String.valueOf(textView.getText()));
    }

}
