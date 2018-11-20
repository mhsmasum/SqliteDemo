package com.example.masum_pc.sqlitedemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText nameEditText , capitalEditText , populationEditText;
    Button updateBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

         int id = getIntent().getExtras().getInt("countryID");
         final int theID = id;

        final MyDatabaseHelper db = new MyDatabaseHelper(getApplicationContext());
        Country theCountry =  db.getCountry(id);

        nameEditText = findViewById(R.id.editTextCountryName);
        capitalEditText = findViewById(R.id.editTextCapital);
        populationEditText = findViewById(R.id.editTextPopulation);


        nameEditText.setText(theCountry.getCountryName());
        capitalEditText.setText(theCountry.getCapital());
        populationEditText.setText(String.valueOf(theCountry.getPopulation()));

        updateBtn = findViewById(R.id.buttonCountryUpdate);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = nameEditText.getText().toString();
                String capital = capitalEditText.getText().toString();
                int population = Integer.parseInt(populationEditText.getText().toString());
                Country aCountry =  new Country(theID , name , capital , population);
                boolean theUpdate = db.UpdateCountry(aCountry);

                Intent i  = new Intent(getApplicationContext() , TableActivity.class);
                startActivity(i);
            }
        });

    }
}
