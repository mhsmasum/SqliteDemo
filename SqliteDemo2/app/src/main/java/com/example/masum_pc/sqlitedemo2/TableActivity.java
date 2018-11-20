package com.example.masum_pc.sqlitedemo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableActivity extends AppCompatActivity {

    private static final String[][] SPACESHIPS = {{ "Casini", "Chemical", "NASA", "Jupiter" },
    { "Spitzer", "Nuclear", "NASA", "Alpha Centauri" } };

    private ArrayList<Country> arrayList = new ArrayList<Country>();
        public static final String [] TABLE_HEADER= {"ID", "NAME","CAPITAL","POPULATION"};

        public int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


        String[][] data ;

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this.getApplicationContext());

        arrayList = myDatabaseHelper.GetAllCountry();
        ArrayList<Country> arrayList2 = arrayList;

        if(!arrayList2.isEmpty()){
            data = setDataForTable(arrayList2);

        }
        else{
            data = SPACESHIPS;
        }


        TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.tableView);
        tableView.setColumnCount(4);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this , TABLE_HEADER));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, data));



//        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
//            @Override
//            public void onDataClicked(int rowIndex, String[] clickedData) {
//
//
//                String [] theData = clickedData;
//               //
//                int id = Integer.parseInt(theData[0]);
//                String name = theData[1];
//                String capital = theData[2];
//                int population = Integer.parseInt(theData[3]);
//                Country aCountry = new Country(id,name,capital , population);
//                Toast.makeText(getApplicationContext() , " Single Click " , Toast.LENGTH_LONG).show();
//                Intent edit = new Intent(getApplicationContext() , EditActivity.class);
//              //  edit.putExtra("Data", (Serializable) aCountry);
//
//                startActivity(edit);
//            }
//        });

        tableView.addDataLongClickListener(new TableDataLongClickListener<String[]>() {
            @Override
            public boolean onDataLongClicked(int rowIndex, String[] clickedData) {

               // Toast.makeText(getApplicationContext() , " Long Click " , Toast.LENGTH_LONG).show();

                String [] data = clickedData;
                  id = Integer.parseInt(clickedData[0]);
                AlertDialog.Builder  aBuilder = new AlertDialog.Builder(TableActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.edit_delete_button , null);
                 Button edit = mView.findViewById(R.id.button_edit);
                 Button deletebtn = mView.findViewById(R.id.button_delete);



                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent edit = new Intent(getApplicationContext() , EditActivity.class);
                        edit.putExtra("countryID",id );
                        startActivity(edit);
                    }
                });

                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext() , "Del" , Toast.LENGTH_LONG).show();
                        MyDatabaseHelper myDatabaseHelper22 = new MyDatabaseHelper(getApplicationContext());
                       int a =  myDatabaseHelper22.deleteCountry(String.valueOf(id));
                        finish();
                        startActivity(getIntent());
                    }
                });

                aBuilder.setView(mView);
                AlertDialog alertDialog = aBuilder.create();
                alertDialog.show();


                return true;

            }
        });

    }




    public static String[][] setDataForTable(ArrayList<Country> arrayList) {
       String [][] result = new String[arrayList.size()][4];

        for (int i = 0 ; i<arrayList.size() ; i++){
            Country country = arrayList.get(i);

            result[i][0] = String.valueOf(country.getID());
            result[i][1] = String.valueOf(country.getCountryName());
            result[i][2] = String.valueOf(country.getCapital());
            result[i][3] = String.valueOf(country.getPopulation());
        }

        return  result;
    }
}
