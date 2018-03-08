package com.krvang.lindved.cameraloginscammer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Test";
    private final int LENGTH_OF_CREDENTIAL = 4;
    private final int MAX_ATTEMPTS = 3;
    private final List<Integer> CREDENTIALS = new ArrayList<>(Arrays.asList(2, 6, 8, 4));

    private LinearLayout mLinearLayout;
    private TextView mMessageTextView;

    private List<Integer> mNumbers;
    private int mTries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumbers = new ArrayList<>();
        mTries = 0;
        mLinearLayout = findViewById(R.id.llDigits);
        createDigits();
    }

    private void createDigits(){
        mLinearLayout.removeAllViews();
        int number = 1;
        for(int i = 0; i < 3; i++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER);
            for(int j = 0; j < 3; j++){
                Digit digit = new Digit(this, number++);
                row.addView(digit);
            }
            mLinearLayout.addView(row);
        }
    }

    private void addNumber(int number){
        mNumbers.add(number);
        if(mNumbers.size() == LENGTH_OF_CREDENTIAL){
            mTries++;
            boolean match = checkCredentialMatch();


            if(!match && mTries == MAX_ATTEMPTS){
                //TODO RKL: Take picture here.
            }

            mTries = mTries == MAX_ATTEMPTS ? 0 : mTries;
            mNumbers = new ArrayList<>();
        }
    }

    private boolean checkCredentialMatch(){
        for(int i = 0; i < mNumbers.size(); i++){
            if(!Objects.equals(mNumbers.get(i), CREDENTIALS.get(i)))
                return false;
        }
        return true;
    }


    private class Digit extends android.support.v7.widget.AppCompatButton implements View.OnClickListener{

        private int mNumber;

        public Digit(Context context, int number) {
            super(context);
            mNumber = number;
            setText(mNumber + "");
            setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            addNumber(mNumber);
        }
    }
}
