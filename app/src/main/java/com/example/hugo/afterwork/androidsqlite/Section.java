package com.example.hugo.afterwork.androidsqlite;

public class Section {

    //private variables
    private long idSection;
    private String libelle;

    // Empty constructor
    public Section(){

    }
    // constructor
    public Section(long idSection, String libelle){
        this.idSection = idSection;
        this.libelle = libelle;
    }

    // constructor
    public Section(String libelle){
        this.libelle = libelle;
    }

    // getting ID
    public long getID(){
        return this.idSection;
    }

    // setting id
    public void setID(long id){
        this.idSection = id;
    }

    // getting libelle
    public String getLibelle(){
        return this.libelle;
    }

    // setting libelle
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
}