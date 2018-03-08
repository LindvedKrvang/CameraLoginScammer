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

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Test";

    private LinearLayout mLinearLayout;
    private TextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Log.d(TAG, "onClick: " + mNumber + " button is clicked");
        }
    }
}
