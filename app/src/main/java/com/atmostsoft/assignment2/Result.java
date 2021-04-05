package com.atmostsoft.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Result extends AppCompatActivity {


    TextView tvBMIResult,tvClassification;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        init();
        String bmiLine = "";
        String bmi = intent.getDoubleExtra("bmiResult",0.0)+"";
        for (int i = 0;i<4;i++)
        {
            bmiLine += bmi.charAt(i);
        }
        System.out.println("BMI Result is "+bmi);
        tvBMIResult.setText(bmiLine);
        double weight = Double.parseDouble(intent.getStringExtra("weight"));
        if (Double.parseDouble(bmi) < 18.5)
            tvClassification.setText("Body mass deficit");
        else if (Double.parseDouble(bmi) >= 18.5 && Double.parseDouble(bmi) <= 24.9)
            tvClassification.setText("normal Body mass");
        else if (Double.parseDouble(bmi) >= 25.0 && Double.parseDouble(bmi) <= 29.9)
            tvClassification.setText("Excessive Body mass");
        else if (Double.parseDouble(bmi) >= 30.0 && Double.parseDouble(bmi) <= 34.9)
            tvClassification.setText("Obesity 1st degree");
        else if (Double.parseDouble(bmi) >= 35.0 && Double.parseDouble(bmi) <= 39.9)
            tvClassification.setText("Obesity 2nd degree");
        else if (Double.parseDouble(bmi) >= 40.0)
            tvClassification.setText("Obesity 3rd degree");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileWriter fileWriter = new FileWriter("“BMI_result.txt",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(intent.getStringExtra("name") + ","+intent.getStringExtra("gender")+","+intent.getStringExtra("age")
                            +","+intent.getStringExtra("height")+","+intent.getStringExtra("weight")+","+intent.getStringExtra("bmiResult"));

                    bufferedWriter.close();
                    fileWriter.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent1 = new Intent(com.atmostsoft.assignment2.Result.this,com.atmostsoft.assignment2.InputScreen.class);

                startActivity(intent1);
                finish();
            }

        });



    }

    public void init()
    {
        tvBMIResult = findViewById(R.id.tvBMIResult);
        tvClassification = findViewById(R.id.tvClassification);
        btnBack = findViewById(R.id.btnBack);
    }
}