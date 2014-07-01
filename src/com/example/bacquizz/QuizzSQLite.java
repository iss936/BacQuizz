package com.example.bacquizz;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class QuizzSQLite extends SQLiteOpenHelper{

	
	//quizz 
	private static final String TABLE_QUIZZ = "quizz";
	private static final String COL_IDQ = "id";
	private static final String COL_QUESTION = "question";
	private static final String COL_REP1 = "rep1";
	private static final String COL_REP2 = "rep2";
	private static final String COL_REP3 = "rep3";
	private static final String COL_REP4 = "rep4";
	private static final String COL_ORDRE = "ordre";
	private static final String COL_ID_CHAPITRE = "idChapitre";
	//monQuizz
	private static final String TABLE_MON_QUIZZ = "monQuizz";
	private static final String COL_MIDQ = "id";
	private static final String COL_MQUESTION = "question";
	private static final String COL_MREP1 = "rep1";
	private static final String COL_MREP2 = "rep2";
	private static final String COL_MREP3 = "rep3";
	private static final String COL_MREP4 = "rep4";
	private static final String COL_ORDER = "ordre";
	private static final String COL_ID_FICHE = "idFiche";
			
	private static final String CREATE_BDD = 
			"CREATE TABLE " + TABLE_QUIZZ + " ("+ COL_IDQ + " INTEGER PRIMARY KEY , " + COL_QUESTION + " TEXT NOT NULL, " + 
	COL_REP1 + " TEXT NOT NULL, " + COL_REP2 + " TEXT NOT NULL, " + COL_REP3 + " TEXT NOT NULL, " + COL_REP4 + " TEXT NOT NULL, " + COL_ORDRE +
	 " TEXT , " + COL_ID_CHAPITRE + " TEXT NOT NULL); " ;
	private static final String CREATE_BDD2 ="CREATE TABLE " + TABLE_MON_QUIZZ + " ("+ COL_MIDQ + " INTEGER PRIMARY KEY autoincrement , " + COL_MQUESTION + " TEXT NOT NULL, " + 
		COL_MREP1 + " TEXT NOT NULL, " + COL_MREP2 + " TEXT NOT NULL, " + COL_MREP3 + " TEXT NOT NULL, " + COL_MREP4 + " TEXT NOT NULL, " + COL_ORDER +
		 " TEXT , " + COL_ID_FICHE + " TEXT NOT NULL); ";
	
	
	public QuizzSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_BDD);
		db.execSQL(CREATE_BDD2);
		
		}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE " + TABLE_QUIZZ);
		db.execSQL("DROP TABLE " + TABLE_MON_QUIZZ + ";");
		onCreate(db);
		
	}
//	public void addQuestions(Questions question) {
//	    SQLiteDatabase dbe = this.getWritableDatabase();
//	    
//	    
//	    ContentValues values = new ContentValues();
//	    values.put(COL_IDQ, question.getId());
//	    values.put(COL_QUESTION, question.getQuestions());
//		values.put(COL_REP1, question.getRep1());
//		values.put(COL_REP2, question.getRep2());
//		values.put(COL_REP3, question.getRep3());
//		values.put(COL_REP4, question.getRep4());
//		values.put(COL_ID_CHAPITRE, question.getIdChapitre());
//
//	    // Inserting Row
//	    dbe.insert(TABLE_QUIZZ, null, values);
//	    dbe.close(); // Closing database connection
//	}
//	
	 

}
