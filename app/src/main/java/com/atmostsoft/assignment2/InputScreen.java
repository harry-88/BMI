package com.atmostsoft.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputScreen extends AppCompatActivity {

    EditText etName,etGender,etAge,etHeight,etWeight;
    Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
        init();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidity())
                {
                    Intent intent = new Intent(InputScreen.this,com.atmostsoft.assignment2.Result.class);
                    intent.putExtra("name",etName.getText().toString().trim());
                    intent.putExtra("gender",etGender.getText().toString().trim());
                    intent.putExtra("height",etHeight.getText().toString().trim());
                    intent.putExtra("age",etAge.getText().toString().trim());
                    intent.putExtra("weight",etWeight.getText().toString().trim());
                    intent.putExtra("bmiResult",calculate_BMI(etWeight.getText().toString().trim(),etHeight.getText().toString().trim()));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public double calculate_BMI(String weight,String height)
    {

        double heightInInches = Double.parseDouble(height);
        heightInInches = heightInInches*12;
        return 703*((Double.parseDouble(weight) * 2.20462)/(heightInInches*heightInInches));
    }
    public boolean checkValidity()
    {
        boolean flag = true;
        if (etName.getText().toString().isEmpty()) {
            etName.setError("Field is required");
            flag = false;
        }
        if(etAge.getText().toString().isEmpty()) {
            etAge.setError("Age is required");
            flag = false;
        }
        if (etGender.getText().toString().isEmpty()) {
            etGender.setError("age is required");
            flag = false;
        }
        else {
            if (!(etGender.getText().toString().equals("male") || etGender.getText().toString().equals("Female"))) {
                etGender.setError("enter carefully enter male or female");
                flag = false;
            }
        }
        if (etHeight.getText().toString().isEmpty()) {
            etHeight.setError("height is required");
            flag = false;
        }
        else
        {
            float age = Float.parseFloat(etHeight.getText().toString());
            if (age < 1) {
                etAge.setError("You entered an invalid age");
                flag = false;
            }
        }
        if (etWeight.getText().toString().isEmpty()) {
            etHeight.setError("weight is required");
            flag = false;
        }
        else
        {
            int weight = Integer.parseInt(etWeight.getText().toString());
            if (weight < 1) {
                etWeight.setError("you entered an invalid weight");
                flag = false;
            }
        }

        return flag;
    }
    public void init()
    {
        etName = findViewById(R.id.editTextTextPersonName);
        etGender = findViewById(R.id.editTextTextMale);
        etAge = findViewById(R.id.editTextAge);
        etHeight = findViewById(R.id.editTextHeight);
        etWeight = findViewById(R.id.editTextWeight);

        btnCalculate = findViewById(R.id.btnCalculate);
    }
}