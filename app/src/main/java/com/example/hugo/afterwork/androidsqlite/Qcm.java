package com.example.hugo.afterwork.androidsqlite;

public class Qcm {

    //private variables
    private long idQcm;
    private String titre;

    // Empty constructor
    public Qcm(){

    }
    // constructor
    public Qcm(long idQcm, String titre){
        this.idQcm = idQcm;
        this.titre = titre;
    }

    // constructor
    public Qcm(String titre){
        this.titre = titre;
    }

    // getting ID
    public long getID(){
        return this.idQcm;
    }

    // setting id
    public void setID(long id){
        this.idQcm = id;
    }

    // getting titre
    public String getTitre(){
        return this.titre;
    }

    // setting titre
    public void setTitre(String titre){
        this.titre = titre;
    }
}