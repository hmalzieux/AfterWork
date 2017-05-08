package com.example.hugo.afterwork.androidsqlite;

public class ARepondu {

    //private variables
    private long idUtilisateur;
    private long idQcm;
    private float note;

    // Empty constructor
    public ARepondu(){

    }
    // constructor
    public ARepondu(long idUtilisateur, long idQcm, float note){
        this.idUtilisateur = idUtilisateur;
        this.idQcm = idQcm;
        this.note = note;
    }

    // constructor
    public ARepondu(float note){
        this.note = note;
    }

    // getting IdUtilisateur
    public long getIdUtilisateur(){
        return this.idUtilisateur;
    }

    // setting idUtilisateur
    public void setIdUtilisateur(long id){
        this.idUtilisateur = id;
    }

    // getting IdQcm
    public long getIdQcm(){
        return this.idQcm;
    }

    // setting idQcm
    public void setIdQcm(long id){
        this.idQcm = id;
    }

    // getting note
    public float getNote(){
        return this.note;
    }

    // setting note
    public void setNote(float note){
        this.note = note;
    }
}