package com.example.masum_pc.sqlitedemo2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText countryNameEditText, capitalEditText , populationEditText;
    private Button saveButton , showButton;

    MyDatabaseHelper myDatabaseHelper ; //object reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input);

        myDatabaseHelper = new MyDatabaseHelper(getApplicationContext());

        SQLiteDatabase countryDb = myDatabaseHelper.getWritableDatabase();

        //Ini View Elements

        countryNameEditText = findViewById(R.id.editTextCountryName);
        capitalEditText = findViewById(R.id.editTextCapital);
        populationEditText = findViewById(R.id.editTextPopulation);
        saveButton = findViewById(R.id.buttonSave);
        showButton =findViewById(R.id.buttonShow);





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Button Clicked", Toast.LENGTH_LONG).show();
                ///  Ini Values

                String name = countryNameEditText.getText().toString();
                String capital =capitalEditText.getText().toString();
                int population = Integer.parseInt(populationEditText.getText().toString());
               long rowId = myDatabaseHelper.insertData(name ,capital,population );

               if(rowId>0){
                   Toast.makeText(getApplicationContext(),"Insert Successful", Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(getApplicationContext(),"Insert Unsuccessful", Toast.LENGTH_LONG).show();
               }
                countryNameEditText.setText(null);
                capitalEditText.setText(null);
                populationEditText.setText(null);
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tableIntend = new Intent(getApplicationContext() , TableActivity.class);
                startActivity(tableIntend);
            }
        });




    }
}
