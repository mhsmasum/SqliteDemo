package com.example.masum_pc.sqlitedemo2;


import java.io.Serializable;

public class Country  implements Serializable {

    private int ID;
    private String CountryName;
    private  String Capital;
    private  int Population;

    public Country() {

    }


    public void setID(int id){
        this.ID = id;
    }

    public  void setCountryName(String name){
        this.CountryName = name;
    }

    public void setCapital(String capital) {
        this.Capital = capital;
    }

    public void setPopulation(int population) {
        this.Population = population;
    }

    public int getID() {
        return this.ID;
    }

    public String getCountryName(){
        return  this.CountryName;
    }

    public String getCapital() {
        return this.Capital;
    }

    public int getPopulation() {
        return this.Population;
    }


    public Country(int id , String name, String capital, int population){
        this.ID = id;
        this.CountryName = name;
        this.Capital = capital;
        this.Population = population;
    }


}
