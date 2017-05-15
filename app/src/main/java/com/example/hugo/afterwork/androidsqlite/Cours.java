package com.example.hugo.afterwork.androidsqlite;

public class Cours {

    //private variables
    private long idCours;
    private String libelle;
    private String typeCours;
    private String cheminDoc;
    private String cheminVideo;
    private long idMatiere;

    // Empty constructor
    public Cours(){

    }
    // constructor
    public Cours(long idCours, String libelle, String typeCours, String cheminDoc, String cheminVideo, long idMatiere){
        this.idCours = idCours;
        this.libelle = libelle;
        this.typeCours = typeCours;
        this.cheminDoc = cheminDoc;
        this.cheminVideo = cheminVideo;
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
    public String getCheminDoc(){
        return this.cheminDoc;
    }

    // setting contenu
    public void setCheminDoc(String cheminDoc){
        this.cheminDoc = cheminDoc;
    }

    // getting contenu
    public String getCheminVideo(){
        return this.cheminVideo;
    }

    // setting contenu
    public void setCheminVideo(String cheminVideo){
        this.cheminVideo = cheminVideo;
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