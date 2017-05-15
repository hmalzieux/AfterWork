package com.example.hugo.afterwork.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "coursManager";

    // tables name
    private static final String TABLE_SECTION = "Section";
    private static final String TABLE_MATIERE = "Matiere";
    private static final String TABLE_COURS = "Cours";
    private static final String TABLE_UTILISATEUR = "Utilisateur";
    private static final String TABLE_QCM = "Qcm";
    private static final String TABLE_QUESTION = "Question";
    private static final String TABLE_AREPONDU = "ARepondu";

    // Tables Columns names
    private static final String KEY_IDSECTION = "idSection";
    private static final String KEY_LIBELLESECTION = "libelleSection";
    private static final String KEY_IDMATIERE = "idMatiere";
    private static final String KEY_LIBELLEMATIERE = "libelleMatiere";
    private static final String KEY_IDCOURS = "idCours";
    private static final String KEY_LIBELLECOURS = "libelleCours";
    private static final String KEY_TYPECOURS = "typeCours";
    private static final String KEY_PATHDOCUMENT = "cheminDoc";
    private static final String KEY_PATHVIDEO = "cheminVideo";
    private static final String KEY_IDUTILISATEUR = "idUtilisateur";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_MAIL = "mail";
    private static final String KEY_MOTDEPASSE = "motDePasse";
    private static final String KEY_IDQCM = "idQcm";
    private static final String KEY_TITREQCM = "titreQcm";
    private static final String KEY_NOTE = "note";
    private static final String KEY_IDQUESTION = "idQuestion";
    private static final String KEY_TITREQUESTION = "titreQuestion";
    private static final String KEY_REPONSE1 = "reponse1";
    private static final String KEY_REPONSE2 = "reponse2";
    private static final String KEY_REPONSE3 = "reponse3";
    private static final String KEY_REPONSEJUSTE = "reponseJuste";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    public void onCreate(SQLiteDatabase db) {

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        dropDB(db);
        // Create tables again
        onCreate(db);
    }

    public void dropDB(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATIERE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UTILISATEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QCM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AREPONDU);
        Log.d("Tables","Droped!");
    }

    public void createTables(SQLiteDatabase db){
        String CREATE_SECTION_TABLE = "CREATE TABLE " + TABLE_SECTION + "("
                + KEY_IDSECTION + " INTEGER PRIMARY KEY," + KEY_LIBELLESECTION + " TEXT" + ")";
        db.execSQL(CREATE_SECTION_TABLE);

        String CREATE_MATIERE_TABLE = "CREATE TABLE " + TABLE_MATIERE + "("
                + KEY_IDMATIERE + " INTEGER PRIMARY KEY," + KEY_LIBELLEMATIERE + " TEXT,"
                + KEY_IDSECTION + " INTEGER NOT NULL, FOREIGN KEY ("+KEY_IDSECTION+") REFERENCES " + TABLE_SECTION + " ("+KEY_IDSECTION+"));" + ")";
        db.execSQL(CREATE_MATIERE_TABLE);

        String CREATE_COURS_TABLE = "CREATE TABLE " + TABLE_COURS + "("
                + KEY_IDCOURS + " INTEGER PRIMARY KEY," + KEY_LIBELLECOURS + " TEXT, "+ KEY_TYPECOURS +" TEXT, "
                + KEY_PATHDOCUMENT + " TEXT, "+KEY_PATHVIDEO + " TEXT, "
                + KEY_IDMATIERE + " INTEGER NOT NULL, FOREIGN KEY ("+KEY_IDMATIERE+") REFERENCES " + TABLE_MATIERE + " ("+KEY_IDMATIERE+"));" + ")";
        db.execSQL(CREATE_COURS_TABLE);

        String CREATE_UTILISATEUR_TABLE = "CREATE TABLE " + TABLE_UTILISATEUR + "("
                + KEY_IDUTILISATEUR + " INTEGER PRIMARY KEY," + KEY_NOM + " TEXT," + KEY_PRENOM + " TEXT," + KEY_MAIL + " TEXT," + KEY_MOTDEPASSE + " TEXT,"
                + KEY_IDSECTION + " INTEGER NOT NULL, FOREIGN KEY ("+KEY_IDSECTION+") REFERENCES " + TABLE_SECTION + "("+KEY_IDSECTION+"));" + ")";
        db.execSQL(CREATE_UTILISATEUR_TABLE);

        String CREATE_QCM_TABLE = "CREATE TABLE " + TABLE_QCM + "("
                + KEY_IDQCM + " INTEGER PRIMARY KEY," + KEY_TITREQCM + " TEXT" + ")";
        db.execSQL(CREATE_QCM_TABLE);

        String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
                + KEY_IDQUESTION + " INTEGER, "
                + KEY_IDQCM + " INTEGER NOT NULL, "
                + KEY_TITREQUESTION + " TEXT, " + KEY_REPONSE1 + " TEXT, " + KEY_REPONSE2 + " TEXT, "+ KEY_REPONSE3 + " TEXT, "
                + KEY_REPONSEJUSTE + " TEXT, "
                +"PRIMARY KEY("+KEY_IDQUESTION+"), "
                + "FOREIGN KEY ("+KEY_IDQCM+") REFERENCES " + TABLE_QCM + "("+KEY_IDQCM+")"
                +");";
        Log.d("CREATE_QUESTION_TABLE",CREATE_QUESTION_TABLE);
        db.execSQL(CREATE_QUESTION_TABLE);

        String CREATE_AREPONDU_TABLE = "CREATE TABLE " + TABLE_AREPONDU + "("
                + KEY_IDUTILISATEUR + " INTEGER, "
                + KEY_IDQCM + " INTEGER, "
                + KEY_NOTE + " REAL NOT NULL, "
                +"PRIMARY KEY("+KEY_IDUTILISATEUR+","+KEY_IDQCM+"), "
                +"FOREIGN KEY ("+KEY_IDUTILISATEUR+") REFERENCES " + TABLE_UTILISATEUR + " ("+KEY_IDUTILISATEUR+"), "
                +"FOREIGN KEY ("+KEY_IDQCM+") REFERENCES " + TABLE_QCM + " ("+KEY_IDQCM+")"
                +");";
        db.execSQL(CREATE_AREPONDU_TABLE);

        Log.d("Tables : ","Created!");
    }

    public void defaultData(){
        // Insertion des sections
        Log.d("Insert: ", "sections ..");
        this.addSection(new Section(1, "AIGLE"));
        this.addSection(new Section(2, "IMAGINA"));
        this.addSection(new Section(3, "DECOL"));
        this.addSection(new Section(4, "MIT"));

        // Insertion des matieres
        Log.d("Insert: ", "matieres ..");
        this.addMatiere(new Matiere(1, "Architecture N-Tiers", 1));
        this.addMatiere(new Matiere(2, "Extraction des Connaissances dans les données", 1));
        this.addMatiere(new Matiere(3, "Programmation Mobile", 1));
        this.addMatiere(new Matiere(4, "Spécification Formelles, Vérification, Validation", 1));
        this.addMatiere(new Matiere(5, "Gestion de Projet", 1));
        this.addMatiere(new Matiere(6, "Base de Données Avancées", 1));
        this.addMatiere(new Matiere(7, "Web Sémantique", 2));

        // Insertion des cours
        Log.d("Insert: ", "cours ..");
        this.addCours(new Cours(1, "TP1 : les termites", "TPs", "", "", 4));
        this.addCours(new Cours(2, "TP3 : WebService", "TPs", "", "", 1));
        this.addCours(new Cours(3, "Video Ted conférence", "Cours", "", "", 5));
        this.addCours(new Cours(4, "TD3 : la base de données sqlite", "TDs", "tp_sgbd2.pdf", "bac_sport", 6));
        this.addCours(new Cours(5, "TD1 : les tables", "TDs", "tp_sgbd1.pdf", "", 6));
        this.addCours(new Cours(1, "Introduction", "Cours", "", 1));
        this.addCours(new Cours(1, "Patrons classiques", "Cours", "", 1));
        this.addCours(new Cours(1, "Cours RMI", "Cours", "", 1));
        this.addCours(new Cours(1, "Intro .Net", "Cours", "", 1));
        this.addCours(new Cours(1, "Intro C#", "Cours", "", 1));
        this.addCours(new Cours(1, "Cours WS", "Cours", "", 1));
        this.addCours(new Cours(1, "Cours WCF", "Cours", "", 1));

        this.addCours(new Cours(1, "Cours1 : Recherche d'information", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 2: Introduction au KDD", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 3: Classification (Apprentissage supervisé)", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 4: Clustering", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 5 - Extraction de motifs - les règles d'associations", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 6: Text Mining ", "Cours", "", 2));
        this.addCours(new Cours(1, "Cours 7: Ontology Learning and Named Entity Recognition", "Cours", "", 2));

        this.addCours(new Cours(1, "TD RMI", "TDs", "", 1));
        this.addCours(new Cours(1, "TP RMI", "TPs", "", 1));
        this.addCours(new Cours(1, "TP WCF", "TPs", "", 1));

        this.addCours(new Cours(1, "TP1 : exercices d'initiation à Weka", "TPs", "", 2));



        // Insertion des utilisateurs
        Log.d("Insert: ", "utilisateurs ..");
        this.addUtilisateur(new Utilisateur(1, "Dupond", "Pierre", "pierre.dupond@umontpellier.fr", "azerty", 1));
        this.addUtilisateur(new Utilisateur(2, "Calude", "Jean", "jean.calude@umontpellier.fr", "azerty", 2));
        this.addUtilisateur(new Utilisateur(3, "Durand", "Gérard", "gerard.durand@umontpellier.fr", "azerty", 3));
        this.addUtilisateur(new Utilisateur(4, "Baudelaire", "Charles", "charles.baudelaire@umontpellier.fr", "azerty", 4));
        this.addUtilisateur(new Utilisateur(5, "Admin", "admin", "@umontpellier.fr", "aaaaaa", 1));

        // Insertion des qcm
        Log.d("Insert: ", "qcm ..");
        this.addQcm(new Qcm(1, "Java RMI"));
        this.addQcm(new Qcm(2, "les basiques de l'android"));
        this.addQcm(new Qcm(3, "Prog agent, pour aller plus loin"));
        this.addQcm(new Qcm(4, "test connaissances sur Weka"));

        // Insertion des questions
        Log.d("Insert: ", "questions ..");/**/
        this.addQuestion(new Question(1, 1, "En RMI, un objet distribué", "est un objet qui hérite de RemoteObject", "est un objet qui implémente une interface de méthode distante ", "est un objet qui encapsule un objet qui assure la communication ", "est un objet qui implémente une interface de méthode distante "));
        this.addQuestion(new Question(2, 1, "L'adaptateur en Java RMI est un process qui s'exécute en dehors de toute JVM ", "Oui", "Non", "Pas toujours", "Non"));
        this.addQuestion(new Question(3, 1, "L’instruction lookup : ", "retourne un stub", "retourne un skeleton", "retourne un objet distribué", "retourne un stub"));
        this.addQuestion(new Question(4, 1, "L’instruction bind : ", "permet de créer un objet distribué", "permet d'enregistrer un objet distribué dans un adaptateur ", "alloue un port de communication pour l'objet distribué ", "permet d'enregistrer un objet distribué dans un adaptateur "));
        this.addQuestion(new Question(5, 1, "Un objet distribué est un serveur de socket dont le port est déterminé par défaut par la classe UnicastRemoteObject ", "Oui", "Non", "Cela dépend", "Oui"));
        this.addQuestion(new Question(6, 1, "L'interface d'un objet distribué définit", "les prototypes de toutes les méthodes de l'objet distribué ", "les prototypes des méthodes distantes de l'objet distribué ", "ni l'un ni l'autre", "les prototypes des méthodes distantes de l'objet distribué "));

        this.addQuestion(new Question(3, 2, "Comment faire du multi langage ?", "Fichiers string différents", "Developper une appli par langue", "L'appli s'adapte suivant le pays", "Fichiers string différents"));
        this.addQuestion(new Question(4, 2, "Comment s'appelle la bd propre à android ?'", "Androidbd", "Magento", "SQLite", "SQLite"));
        this.addQuestion(new Question(5, 2, "Comment simuler l'action d'un bouton ?", "Intent", "Fragment", "Capteur", "Intent"));
        this.addQuestion(new Question(6, 3, "Lapins VS loups, qui gagne ?", "lapins", "loups", "aucun", "aucun"));
        this.addQuestion(new Question(7, 4, "Quel est l'algo le plus rapide ?'", "J48", "NaiveBayes", "NBtree", "NaiveBayes"));
        this.addQuestion(new Question(8, 4, "Quel prétraitement est le moins efficace ?", "Verbe", "Adjectif", "Stop words", "Verbe"));

        // Insertion des a_repondu
        Log.d("Insert: ", "a_repondu ..");
      /*  this.addARepondu(new ARepondu(5,1,8));
        this.addARepondu(new ARepondu(5,4,3));
        this.addARepondu(new ARepondu(5,3,10));*/
    }
    public void addSection(Section section) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIBELLESECTION, section.getLibelle());

        // Inserting Row
        db.insert(TABLE_SECTION, null, values);
    }

    public void addMatiere(Matiere matiere) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIBELLEMATIERE, matiere.getLibelle());
        values.put(KEY_IDSECTION, matiere.getIdSection());

        // Inserting Row
        db.insert(TABLE_MATIERE, null, values);
        db.close(); // Closing database connection
    }

    public void addCours(Cours cours) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIBELLECOURS, cours.getLibelle());
        values.put(KEY_TYPECOURS,cours.getTypeCours());
        values.put(KEY_PATHDOCUMENT, cours.getCheminDoc());
        values.put(KEY_PATHVIDEO, cours.getCheminVideo());
        values.put(KEY_IDMATIERE, cours.getIdMatiere());

        // Inserting Row
        db.insert(TABLE_COURS, null, values);
        db.close(); // Closing database connection
    }

    public void addUtilisateur(Utilisateur utilisateur) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOM, utilisateur.getNom());
        values.put(KEY_PRENOM, utilisateur.getPrenom());
        values.put(KEY_MAIL, utilisateur.getMail());
        values.put(KEY_MOTDEPASSE, utilisateur.getMotDePasse());
        values.put(KEY_IDSECTION, utilisateur.getIdSection());

        // Inserting Row
        db.insert(TABLE_UTILISATEUR, null, values);
        db.close(); // Closing database connection
    }

    public void addQcm(Qcm qcm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITREQCM, qcm.getTitre());

        // Inserting Row
        db.insert(TABLE_QCM, null, values);
        db.close(); // Closing database connection
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDQCM, question.getIdQcm());
        values.put(KEY_TITREQUESTION, question.getTitre());
        values.put(KEY_REPONSE1, question.getReponse1());
        values.put(KEY_REPONSE2, question.getReponse2());
        values.put(KEY_REPONSE3, question.getReponse3());
        values.put(KEY_REPONSEJUSTE, question.getReponseJuste());
        // Inserting Row
        db.insert(TABLE_QUESTION, null, values);
        db.close(); // Closing database connection
    }

    public void addARepondu(ARepondu arepondu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IDUTILISATEUR, arepondu.getIdUtilisateur());
        values.put(KEY_IDQCM, arepondu.getIdQcm());
        values.put(KEY_NOTE, arepondu.getNote());

        // Inserting Row
        db.insert(TABLE_AREPONDU, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<String> getCours(String id) {
        String selectQuery = "SELECT "+KEY_LIBELLECOURS+", "+KEY_PATHDOCUMENT+", "+KEY_PATHVIDEO+
                " FROM " + TABLE_COURS +" WHERE " + KEY_IDCOURS +" = "+ id;


        Log.v("selectQuery",selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> cours = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                cours.add(0,cursor.getString(0));
                cours.add(1,cursor.getString(1));
                cours.add(2,cursor.getString(2));
                break;
            } while (cursor.moveToNext());
        }
        return cours;
    }


    public ArrayList<String[]> getQuestion(String idQcm) {
        ArrayList<String[]> res = new ArrayList<String[]>();

        String selectQuery = "SELECT "+KEY_TITREQUESTION+", "+KEY_REPONSE1+", "+KEY_REPONSE2+", "+KEY_REPONSE3+", "+KEY_REPONSEJUSTE+" FROM " + TABLE_QUESTION +" WHERE " + KEY_IDQCM +" = "+ idQcm;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                res.add(new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)});
            } while (cursor.moveToNext());
        }

        // return question list
        return res;
    }

    public int getConnection(String email, String password) {

        String selectQuery = "SELECT "+ KEY_IDUTILISATEUR +" FROM " + TABLE_UTILISATEUR +" WHERE " + KEY_MAIL +" = \""+ email +"\" AND " + KEY_MOTDEPASSE +" = \""+ password + "\";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int res = -1;
        if (cursor.moveToFirst()) {
            do {
                res = Integer.parseInt(cursor.getString(0));
                break;
            } while (cursor.moveToNext());
        }
        return res;
    }

    public ArrayList<String> getNamesMatiere(int idUser){

        String selectQuery = "SELECT "+TABLE_MATIERE+"."+KEY_LIBELLEMATIERE+" FROM " + TABLE_MATIERE +", "+ TABLE_UTILISATEUR
                +"  WHERE " + TABLE_MATIERE+"."+KEY_IDSECTION+" = "+ TABLE_UTILISATEUR+"."+KEY_IDSECTION
                +" AND "+TABLE_UTILISATEUR+"."+KEY_IDUTILISATEUR+"= \""+ idUser +"\" ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> res= new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                res.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return res;
    }

    public String[] getInfoUser(int idUser){
        String[] res = new String[4];
        String selectQuery = "SELECT "+KEY_NOM+", "+KEY_PRENOM+", "+KEY_MAIL+", "+KEY_LIBELLESECTION+" FROM "+ TABLE_UTILISATEUR +", "+TABLE_SECTION
                +"  WHERE " + TABLE_SECTION+"."+KEY_IDSECTION+" = "+ TABLE_UTILISATEUR+"."+KEY_IDSECTION
                +"  AND "+TABLE_UTILISATEUR+"."+KEY_IDUTILISATEUR+" = "+ idUser +" ;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                res[0] = cursor.getString(0);
                res[1] = cursor.getString(1);
                res[2] = cursor.getString(2);
                res[3] = cursor.getString(3);
                break;
            } while (cursor.moveToNext());
        }
        return res;
    }

    public ArrayList<String[]> getListCours(int idUser, String filtre){
        String filtre_bd = "";
        if (!filtre.equals("")) {
            if (filtre.contains(", ")) {
                String[] parts = filtre.split(", ");
                String part1 = parts[0];
                String part2 = parts[1];
                filtre_bd = " AND "+TABLE_MATIERE+"."+KEY_LIBELLEMATIERE+" = \""+part1+"\" AND "+TABLE_COURS+"."+ KEY_TYPECOURS +" = \""+part2+"\"";
            } else {
                filtre_bd = " AND "+TABLE_MATIERE+"."+KEY_LIBELLEMATIERE+" = \""+filtre+"\"";
            }
        }
        String selectQuery = "SELECT "+TABLE_COURS+"."+KEY_LIBELLECOURS+", "+TABLE_MATIERE+"."+KEY_LIBELLEMATIERE+", "+TABLE_COURS+"."+ KEY_TYPECOURS +", "+ TABLE_COURS+"."+KEY_IDCOURS+" FROM "+ TABLE_COURS +", "+TABLE_UTILISATEUR +", "+TABLE_MATIERE
                +" WHERE " + TABLE_MATIERE+"."+KEY_IDSECTION+" = "+ TABLE_UTILISATEUR+"."+KEY_IDSECTION
                +" AND " + TABLE_MATIERE+"."+KEY_IDMATIERE+" = "+ TABLE_COURS+"."+KEY_IDMATIERE
                +filtre_bd
                +"  AND "+TABLE_UTILISATEUR+"."+KEY_IDUTILISATEUR+" = "+ idUser +" ;";
        Log.d("query List Cours", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String[]> res= new ArrayList<String[]>();
        if (cursor.moveToFirst()) {
            do {
                res.add(new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)});
            } while (cursor.moveToNext());
        }
        return res;
    }

    public ArrayList<String[]> getListQcmDone(int idUser){
        String selectQuery = "SELECT "+TABLE_QCM+"."+KEY_TITREQCM+", "+TABLE_AREPONDU+"."+KEY_NOTE
                +" FROM "+ TABLE_QCM +", "+TABLE_UTILISATEUR +", "+TABLE_AREPONDU
                +" WHERE " + TABLE_QCM+"."+KEY_IDQCM+" = "+ TABLE_AREPONDU+"."+KEY_IDQCM
                +" AND " + TABLE_AREPONDU+"."+KEY_IDUTILISATEUR+" = "+ TABLE_UTILISATEUR+"."+KEY_IDUTILISATEUR
                +" AND "+TABLE_UTILISATEUR+"."+KEY_IDUTILISATEUR+" = "+ idUser +" ;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String[]> res= new ArrayList<String[]>();
        if (cursor.moveToFirst()) {
            do {
                res.add(new String[]{cursor.getString(0),cursor.getString(1)});
            } while (cursor.moveToNext());
        }
        return res;
    }

    public ArrayList<String[]> getListQcmNew(int idUser){
        String selectTotal = "SELECT "+TABLE_QCM+"."+KEY_IDQCM
                +" FROM "+ TABLE_QCM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorTotal = db.rawQuery(selectTotal, null);

        String selectRep = "SELECT "+TABLE_AREPONDU+"."+KEY_IDQCM
                +" FROM "+ TABLE_AREPONDU
                +" WHERE " +TABLE_AREPONDU+"."+KEY_IDUTILISATEUR+" = "+ idUser ;
        Cursor cursorRep = db.rawQuery(selectRep, null);
        String[] idQcm = new String[cursorTotal.getCount()];
        if (cursorRep.moveToFirst()) {
            do {
                idQcm[Integer.parseInt(cursorRep.getString(0))-1] = cursorRep.getString(0);
            } while (cursorRep.moveToNext());
        }

        String selectQcm = "SELECT "+TABLE_QCM+"."+KEY_TITREQCM+", "+TABLE_QCM+"."+KEY_IDQCM+" FROM "+ TABLE_QCM ;
        Cursor cursor = db.rawQuery(selectQcm, null);
        ArrayList<String[]> res= new ArrayList<String[]>();
        if (cursor.moveToFirst()) {
            do {
                if(!cursor.getString(1).equals(idQcm[Integer.parseInt(cursor.getString(1))-1])) {
                    res.add(new String[]{cursor.getString(0), cursor.getString(1)});
                }
            } while (cursor.moveToNext());
        }
        return res;
    }
}
