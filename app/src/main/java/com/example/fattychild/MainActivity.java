package com.example.fattychild;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    /**
     * From the subject of health and science
     *
     *The app can teach kids to calculate BMI by providing them with information about
     *  what BMI is, how it's calculated, and what the different ranges of BMI
     *  mean for their health.
     */
    public EditText num;
    public  EditText heightED;

    public  EditText weightED;
    public Spinner mySpinner;
    public TextView reselt;
    public TextView Error;
    public Button Done ;

    public  TextView f;

    ArrayAdapter<String> list;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         heightED=findViewById(R.id.h);
         weightED=findViewById(R.id.w);
         mySpinner =findViewById(R.id.spinner);
         Error=findViewById(R.id.ShowError);
         Done=findViewById(R.id.button);
         f=findViewById(R.id.BMIresult);
         reselt=findViewById(R.id.resF);
        final double[] height = {0};
        final double[] weight = {0};
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (heightED.getText().toString().equals("") || weightED.getText().toString().equals("")) {
                    Error.setVisibility(View.VISIBLE);
                    Error.setText("height or weight is null");
                } else {
                    height[0] = Double.parseDouble(heightED.getText().toString());
                    weight[0] = Double.parseDouble(weightED.getText().toString());

                    // Update the BMI value after height and weight have been entered
                    Person person = new Person();
                    String showB = Double.toString(person.calculateBMI(height[0], weight[0]));
                    f.setVisibility(View.VISIBLE);
                    f.setText("BMI="+showB);
                }
            }
        });
        Person person=new Person();
        String showB=Double.toString(person.calculateBMI(height[0], weight[0]));

        f.setText(showB);
        BMI bmi=new BMI();
        ArrayAdapter<String> list = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, bmi.getRanges());
        mySpinner.setAdapter(list);
        // mySpinner.setAdapter();



        mySpinner.setAdapter(list);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int selectedIndex = position;

                  if (selectedIndex == 0) {
                    reselt.setText("Underweight");
                } else if (selectedIndex == 1) {
                    reselt.setText("Normal weight Good");
                } else if (selectedIndex == 2) {
                    reselt.setText("Overweight");
                }else if (selectedIndex == 3) {
                    reselt.setText("Obesity");}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                reselt.setVisibility(View.VISIBLE);

            }
        });
    }
}










