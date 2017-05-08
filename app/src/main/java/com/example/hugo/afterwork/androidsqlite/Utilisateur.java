package com.example.hugo.afterwork.androidsqlite;

public class Utilisateur {

    //private variables
    private long idUtilisateur;
    private String nom;
    private String prenom;
    private String mail;
    private String motDePasse;
    private long idSection;

    // Empty constructor
    public Utilisateur(){

    }
    // constructor
    public Utilisateur(long idUtilisateur, String nom, String prenom, String mail, String motDePasse, long idSection){
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.idSection = idSection;
    }

    // constructor
    public Utilisateur(String nom, String prenom, String mail, String motDePasse){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }
    // getting ID
    public long getID(){
        return this.idUtilisateur;
    }

    // setting id
    public void setID(long id){
        this.idUtilisateur = id;
    }

    // getting nom
    public String getNom(){
        return this.nom;
    }

    // setting nom
    public void setNom(String nom){
        this.nom = nom;
    }

    // getting prenom
    public String getPrenom(){
        return this.prenom;
    }

    // setting prenom
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    // getting mail
    public String getMail(){
        return this.mail;
    }

    // setting mail
    public void setMail(String mail){
        this.mail = mail;
    }

    // getting motDePasse
    public String getMotDePasse(){
        return this.motDePasse;
    }

    // setting motDePasse
    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
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