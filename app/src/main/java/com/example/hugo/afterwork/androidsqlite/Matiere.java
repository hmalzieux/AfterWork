package com.example.hugo.afterwork.androidsqlite;

public class Matiere {

    //private variables
    private long idMatiere;
    private String libelle;
    private long idSection;

    // Empty constructor
    public Matiere(){

    }
    // constructor
    public Matiere(long idMatiere, String libelle, long idSection){
        this.idMatiere = idMatiere;
        this.libelle = libelle;
        this.idSection = idSection;
    }

    // constructor
    public Matiere(String libelle){
        this.libelle = libelle;
    }

    // getting ID
    public long getID(){
        return this.idMatiere;
    }

    // setting id
    public void setID(long id){
        this.idMatiere = id;
    }

    // getting libelle
    public String getLibelle(){
        return this.libelle;
    }

    // setting libelle
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    // getting idSection
    public long getIdSection(){
        return this.idSection;
    }

    // setting idSection
    public void setIdSection(long idSection){
        this.idSection = idSection;
    }
}