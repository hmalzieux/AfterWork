package com.example.hugo.afterwork.androidsqlite;

public class Question {

    //private variables
    private long idQuestion;
    private long idQcm;
    private String titre;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String reponseJuste;

    // Empty constructor
    public Question(){

    }
    // constructor
    public Question(long idQuestion, long idQcm, String titre, String reponse1, String reponse2, String reponse3, String reponseJuste){
        this.idQuestion = idQuestion;
        this.idQcm = idQcm;
        this.titre = titre;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponseJuste = reponseJuste;
    }

    // constructor
    public Question(long idQcm, String titre, String reponse1, String reponse2, String reponse3, String reponseJuste){
        this.idQcm = idQcm;
        this.titre = titre;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponseJuste = reponseJuste;
    }
    // getting ID
    public long getID(){
        return this.idQuestion;
    }

    // setting id
    public void setID(long id){
        this.idQuestion = id;
    }

    // getting idQcm
    public long getIdQcm(){
        return this.idQcm;
    }

    // setting idQcm
    public void setIdQcm(long idQcm){
        this.idQcm = idQcm;
    }

    // getting titre
    public String getTitre(){
        return this.titre;
    }

    // setting titre
    public void setTitre(String titre){
        this.titre = titre;
    }

    // getting reponse1
    public String getReponse1(){
        return this.reponse1;
    }

    // setting reponse1
    public void setReponse1(String reponse1){
        this.reponse1 = reponse1;
    }

    // getting reponse2
    public String getReponse2(){
        return this.reponse2;
    }

    // setting reponse2
    public void setReponse2(String reponse2){
        this.reponse2 = reponse2;
    }

    // getting reponse3
    public String getReponse3(){
        return this.reponse3;
    }

    // setting reponse3
    public void setReponse3(String reponse3){
        this.reponse3 = reponse3;
    }

    // getting reponseJuste
    public String getReponseJuste(){
        return this.reponseJuste;
    }

    // setting reponseJuste
    public void setReponseJuste(String reponseJuste){
        this.reponseJuste = reponseJuste;
    }
}