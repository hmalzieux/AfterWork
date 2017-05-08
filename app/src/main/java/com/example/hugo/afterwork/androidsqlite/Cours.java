package com.example.hugo.afterwork.androidsqlite;

public class Cours {

    //private variables
    private long idCours;
    private String libelle;
    private String typeCours;
    private String contenu;
    private long idMatiere;

    // Empty constructor
    public Cours(){

    }
    // constructor
    public Cours(long idCours, String libelle, String typeCours, String contenu, long idMatiere){
        this.idCours = idCours;
        this.libelle = libelle;
        this.typeCours = typeCours;
        this.contenu = contenu;
        this.idMatiere = idMatiere;
    }

    // constructor
    public Cours(String libelle){
        this.libelle = libelle;
    }

    // getting ID
    public long getID(){
        return this.idCours;
    }

    // setting id
    public void setID(long id){
        this.idCours = id;
    }

    // getting libelle
    public String getLibelle(){
        return this.libelle;
    }

    // setting libelle
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    // getting typeCours
    public String getTypeCours(){
        return this.typeCours;
    }

    // setting typeCours
    public void setTypeCours(String typeCours){
        this.typeCours = typeCours;
    }

     // getting contenu
    public String getContenu(){
        return this.contenu;
    }

    // setting contenu
    public void setContenu(String contenu){
        this.contenu = contenu;
    }

    // getting idMatiere
    public long getIdMatiere(){
        return this.idMatiere;
    }

    // setting idMatiere
    public void setIdMatiere(long idMatiere){
        this.idMatiere = idMatiere;
    }
}