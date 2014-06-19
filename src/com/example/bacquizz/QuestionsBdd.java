package com.example.bacquizz;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class QuestionsBdd {
	
	private static final int VERSION_BDD = 4;
	private static final String NOM_BDD = "quizz.db";
 
	private static final String TABLE_QUIZZ = "quizz";
	private static final String COL_IDQ = "id";
	private static final int NUM_COL_IDQ = 0;
	private static final String COL_QUESTION = "question";
	private static final int NUM_COL_QUESTION = 1;
	
	private static final String COL_REP1 = "rep1";
	private static final int NUM_COL_REP1 = 2;
	
	private static final String COL_REP2 = "rep2";
	private static final int NUM_COL_REP2 = 3;
	private static final String COL_REP3= "rep3";
	private static final int NUM_COL_REP3 = 4;
	private static final String COL_REP4 = "rep4";
	private static final int NUM_COL_REP4 = 5;
	private static final String COL_ORDRE = "ordre";
	private static final int NUM_COL_ORDRE = 6;
	private static final String COL_ID_CHAPITRE = "idChapitre";
	private static final int NUM_COL_ID_CHAPITRE = 7;
 
	private SQLiteDatabase bdd;
 
	private MonQuizzSQLite maBaseSQLite;
 
	public QuestionsBdd(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new MonQuizzSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertQuestion4(Questions question){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_IDQ, question.getId());
		values.put(COL_QUESTION, question.getQuestions());
		values.put(COL_REP1, question.getRep1());
		values.put(COL_REP2, question.getRep2());
		values.put(COL_REP3, question.getRep3());
		values.put(COL_REP4, question.getRep4());
		values.put(COL_ID_CHAPITRE, question.getIdChapitre());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insertWithOnConflict(TABLE_QUIZZ, null,
				  values,SQLiteDatabase.CONFLICT_REPLACE);
	
		//return bdd.insert(TABLE_FILIERE, null, values);
	}
	
	public Questions getQuestion(int i){
	//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
	Cursor c = bdd.query(TABLE_QUIZZ, new String[] {COL_IDQ,COL_QUESTION,COL_REP1,COL_REP2,COL_REP3,COL_REP4,COL_ID_CHAPITRE}, COL_IDQ+ " LIKE \"" + i +"\"", null, null, null, null);
	return cursorToQuestion(c);
}
	//Cette méthode permet de convertir un cursor en un livre
	private Questions cursorToQuestion(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé une questions
		Questions question = new Questions();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		question.setId(c.getInt(c.getColumnIndex(COL_IDQ)));
		question.setQuestions(c.getString(NUM_COL_QUESTION));
		question.setRep1(c.getString(NUM_COL_REP1));
		question.setRep2(c.getString(NUM_COL_REP2));
		question.setRep3(c.getString(NUM_COL_REP3));
		question.setRep4(c.getString(NUM_COL_REP4));
//		question.setIdChapitre(c.getInt(NUM_COL_ID_CHAPITRE));
		question.setIdChapitre(c.getInt(c.getColumnIndex(COL_ID_CHAPITRE)));
		//On ferme le cursor
		c.close();
 
		//On retourne la filiere
		return question;
	}
}
