package edu.calvin.andrew.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //number input objects
    private EditText input1;
    private EditText input2;

    //the spinner for operators
    private Spinner opSpinner;

    //the answer text
    private TextView answerTex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.numInput1);
        input2 = findViewById(R.id.numInput2);

        opSpinner = findViewById(R.id.operatorChoice);

        answerTex = findViewById(R.id.answerText);
    }

    public void calculate(View view) {
        //do calculations
        int num1;
        if(input1.getText().length() != 0) {
            num1 = Integer.parseInt(input1.getText().toString());
        }else{
            num1 = 0;
        }

        int num2;
        if(input2.getText().length() != 0) {
            num2 = Integer.parseInt(input2.getText().toString());
        }else {
            num2 = 0;
        }

        try{

            String op = opSpinner.getSelectedItem().toString();

            switch (op) {
                case "+":
                    displayAnswer(num1 + num2);
                    break;
                case "-":
                    displayAnswer(num1 - num2);
                    break;
                case "/":
                    displayAnswer( (float) num1 / (float)num2);
                    break;
                case "*":
                    displayAnswer( num1 * num2);
                    break;
                default:
                    displayAnswer("ERROR: operator selection problem");
                    break;
            }

        }catch(Exception e){
            displayAnswer("ERROR");
        }
    }

    private void displayAnswer(String s){
        answerTex.setText(s);
    }

    private void displayAnswer(int myInt){
        String ans = Integer.toString(myInt);
        answerTex.setText(ans);
    }

    private void displayAnswer(float myFloat){
        String ans = Float.toString(myFloat);
        answerTex.setText(ans);
    }
}
