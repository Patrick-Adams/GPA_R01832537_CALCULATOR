package com.example.gpa_r01832537_calculator;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String key1 = "key1";
    private final String key2 = "key2";
    private final String key3 = "key3";
    private final String key4 = "key4";
    private final String key5 = "key5";
    private final String keyFinal = "keyFinal";
    private final String keyCompute = "keyCompute";



    private TextView finalGPA;
    private EditText grade1, grade2, grade3,
                    grade4, grade5;
    private Button computeGPA;

    int counter = 0; // keep track of onClick
    double total_score = 0.0; // all grades added together
    double avg_GPA = 0.0; // average of all grades divided by 5 (total classes)
    double g1, g2, g3, g4, g5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize each variable
        finalGPA = findViewById(R.id.finalGPA);

        grade1 = findViewById(R.id.course1);
        grade2 = findViewById(R.id.course2);
        grade3 = findViewById(R.id.course3);
        grade4 = findViewById(R.id.course4);
        grade5 = findViewById(R.id.course5);

        computeGPA = findViewById(R.id.computeGPA);

        computeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter % 2 != 1) { // the counter increments after each button click.
                                        // When 0, calculation will occur. When 1, fields will be reset

                    if (grade1.getText().toString().equals("") || grade2.getText().toString().equals("")
                            || grade3.getText().toString().equals("") || grade4.getText().toString().equals("")
                            || grade5.getText().toString().equals("")) { //Enforces all fields to be filled
                        finalGPA.setText("You must enter a Grade in all fields");

                    } else {
                        g1 = Double.parseDouble(grade1.getText().toString());
                        g2 = Double.parseDouble(grade2.getText().toString());
                        g3 = Double.parseDouble(grade3.getText().toString());
                        g4 = Double.parseDouble(grade4.getText().toString());
                        g5 = Double.parseDouble(grade5.getText().toString());
                        //This ensures the right data input
                        /*
                        g1 = Double.parseDouble(grade1.getText().toString());
                        if(g1 >= 0 && g1 <= 100) {
                            g2 = Double.parseDouble(grade2.getText().toString());
                            if (g2 >= 0 && g2 <= 100) {
                                g3 = Double.parseDouble(grade3.getText().toString());
                                if (g3 >= 0 && g3 <= 100) {
                                    g4 = Double.parseDouble(grade4.getText().toString());
                                    if(g4 >= 0 && g4 <= 100) {
                                        g5 = Double.parseDouble(grade5.getText().toString());
                                        if(g5 >= 0 && g5 <= 100){
                                            total_score = (g1 + g2 + g3 + g4 + g5);
                                            avg_GPA = (total_score / 5);
                                        }
                                        else
                                            grade5.setBackgroundColor(Color.RED);
                                    } else
                                        grade4.setBackgroundColor(Color.RED);
                                } else
                                    grade3.setBackgroundColor(Color.RED);
                            } else
                                grade2.setBackgroundColor(Color.RED);
                        } else
                            grade1.setBackgroundColor(Color.RED);

                         */

                        //Calculations to get average
                        total_score = (g1 + g2 + g3 + g4 + g5);
                        avg_GPA = (total_score / 5);


                        //sets finalGPA field to avgGPA, changes background color depending on score
                        if (avg_GPA < 60) {
                            finalGPA.setText(String.format("Your Final GPA: %s", avg_GPA));
                            finalGPA.setBackgroundColor(Color.RED);
                        } else if (avg_GPA >= 60 && avg_GPA < 80) {
                            finalGPA.setText(String.format("Your Final GPA: %s", avg_GPA));
                            finalGPA.setBackgroundColor(Color.YELLOW);
                        } else if (avg_GPA >= 80 && avg_GPA <= 100) {
                            finalGPA.setText(String.format("Your Final GPA: %s", avg_GPA));
                            finalGPA.setBackgroundColor(Color.GREEN);
                        }
                        //Increment counter to enable "clear" mode
                        counter++;
                        computeGPA.setText("CLEAR");;
                    }
                    //this is the "clear" mode, where counter = odd number
                } else {
                    grade1.setText("");
                    grade2.setText("");
                    grade3.setText("");
                    grade4.setText("");
                    grade5.setText("");
                    finalGPA.setText("");
                    computeGPA.setText("Compute GPA");
                    finalGPA.setBackgroundColor(Color.rgb(153,119,153));
                    counter++;
                }



            }
        });
    }

    //These two sections save the instance of the date when the screen rotates
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString(key1,grade1.getText().toString());
        outState.putString(key2,grade2.getText().toString());
        outState.putString(key3,grade3.getText().toString());
        outState.putString(key4,grade4.getText().toString());
        outState.putString(key5,grade5.getText().toString());
        outState.putString(keyFinal,finalGPA.getText().toString());
        outState.putString(keyCompute,computeGPA.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        grade1.setText(savedInstanceState.getString(key1));
        grade2.setText(savedInstanceState.getString(key2));
        grade3.setText(savedInstanceState.getString(key3));
        grade4.setText(savedInstanceState.getString(key4));
        grade5.setText(savedInstanceState.getString(key5));
        finalGPA.setText(savedInstanceState.getString(keyFinal));
        computeGPA.setText(savedInstanceState.getString(keyCompute));

        super.onRestoreInstanceState(savedInstanceState);
    }
}