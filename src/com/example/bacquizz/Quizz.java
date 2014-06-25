package com.example.bacquizz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quizz extends Activity implements OnClickListener{

	/** Called when the activity is first created. */
	private Button btnRep1;
	private Button btnRep2;
	private Button btnRep3;
	private Button btnRep4;
	private Button btnSuivant;
	private Questions questionFromBDD;
	private int idCurrentQuestion;
	private QuestionsBdd QuestionsBdd;
	private List<Integer> lesQuestions;
	private List<Integer> lesReponses;
	private int score; 
	private int idChapitre;
	private String exercice;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
	    setContentView(R.layout.activity_quizz);
	    // on récupère des paramètres 
	    Bundle nomBundle = this.getIntent().getExtras();
		idChapitre = nomBundle.getInt("idChapitre");
		exercice = nomBundle.getString("ex");
		
	    lesQuestions = randomQuestion();
	    
	    idCurrentQuestion = 0;
	    score = 0;
	    btnRep1 = (Button) findViewById(R.id.btnRep1);
		btnRep1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), "Fiche enregistrée !", Toast.LENGTH_SHORT).show();
				btnSuivant.setVisibility(View.VISIBLE);
				if(questionFromBDD.getRep1() != btnRep1.getText())
				{
					 // on rend les bouton non-cliquables
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
				
					 
					btnRep1.setBackgroundColor(Color.RED);
					Toast.makeText(getApplicationContext(), "Mauvaise Réponse !", Toast.LENGTH_SHORT).show();
					// on afficche tout de même la bonne reponse
					if(questionFromBDD.getRep1() == btnRep2.getText())
						btnRep2.setBackgroundColor(Color.GREEN);
					else
						if(questionFromBDD.getRep1() == btnRep3.getText())
							btnRep3.setBackgroundColor(Color.GREEN);
						else
							if(questionFromBDD.getRep1() == btnRep4.getText())
								btnRep4.setBackgroundColor(Color.GREEN);
				}
				else
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					 
					btnRep1.setBackgroundColor(Color.GREEN);
					Toast.makeText(getApplicationContext(), "Bonne Réponse !", Toast.LENGTH_SHORT).show();
					score++;
					
				}
			
			}
		});
		
		btnRep2 = (Button) findViewById(R.id.btnRep2);
		btnRep2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnSuivant.setVisibility(View.VISIBLE);
				if(questionFromBDD.getRep1() != btnRep2.getText())
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep2.setBackgroundColor(Color.RED);
					Toast.makeText(getApplicationContext(), "Mauvaise Réponse !", Toast.LENGTH_SHORT).show();
					// on afficche tout de même la bonne reponse
					if(questionFromBDD.getRep1() == btnRep1.getText())
						btnRep1.setBackgroundColor(Color.GREEN);
					else
						if(questionFromBDD.getRep1() == btnRep3.getText())
							btnRep3.setBackgroundColor(Color.GREEN);
						else
							if(questionFromBDD.getRep1() == btnRep4.getText())
								btnRep4.setBackgroundColor(Color.GREEN);
					
				}
				else
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep2.setBackgroundColor(Color.GREEN);
					Toast.makeText(getApplicationContext(), "Bonne Réponse !", Toast.LENGTH_SHORT).show();
					score++;
				}
				
			}
		});
		btnRep3 = (Button) findViewById(R.id.btnRep3);
		btnRep3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnSuivant.setVisibility(View.VISIBLE);
				if(questionFromBDD.getRep1() != btnRep3.getText())
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep3.setBackgroundColor(Color.RED);
					Toast.makeText(getApplicationContext(), "Mauvaise Réponse !", Toast.LENGTH_SHORT).show();
					// on affiche tout de même la bonne reponse
					if(questionFromBDD.getRep1() == btnRep1.getText())
						btnRep1.setBackgroundColor(Color.GREEN);
					else
						if(questionFromBDD.getRep1() == btnRep2.getText())
							btnRep2.setBackgroundColor(Color.GREEN);
						else
							if(questionFromBDD.getRep1() == btnRep4.getText())
								btnRep4.setBackgroundColor(Color.GREEN);
				
				}
				else
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep3.setBackgroundColor(Color.GREEN);
					Toast.makeText(getApplicationContext(), "Bonne Réponse !", Toast.LENGTH_SHORT).show();
					score++;
				}
			
			}
		});
		btnRep4 = (Button) findViewById(R.id.btnRep4);
		btnRep4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnSuivant.setVisibility(View.VISIBLE);
				if(questionFromBDD.getRep1() != btnRep4.getText())
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep4.setBackgroundColor(Color.RED);
					Toast.makeText(getApplicationContext(), "Mauvaise Réponse !", Toast.LENGTH_SHORT).show();
					// on affiche tout de même la bonne reponse
					if(questionFromBDD.getRep1() == btnRep1.getText())
						btnRep1.setBackgroundColor(Color.GREEN);
					else
						if(questionFromBDD.getRep1() == btnRep2.getText())
							btnRep2.setBackgroundColor(Color.GREEN);
						else
							if(questionFromBDD.getRep1() == btnRep3.getText())
								btnRep3.setBackgroundColor(Color.GREEN);
					
				}
				else
				{
					 btnRep1.setEnabled(false);
					 btnRep2.setEnabled(false);
					 btnRep3.setEnabled(false);
					 btnRep4.setEnabled(false);
					btnRep4.setBackgroundColor(Color.GREEN);
					Toast.makeText(getApplicationContext(), "Bonne Réponse !", Toast.LENGTH_SHORT).show();
					score++;
				}
				
			}
		});
		btnSuivant = (Button) findViewById(R.id.btnSuivant);
		btnSuivant.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 btnRep1.setEnabled(true);
				 btnRep2.setEnabled(true);
				 btnRep3.setEnabled(true);
				 btnRep4.setEnabled(true);
				
				idCurrentQuestion++;
				
				if(idCurrentQuestion <= 9)
				{
					int uneQuestion = lesQuestions.get(idCurrentQuestion);
					uneQuestion=lesQuestions.get(idCurrentQuestion);
					questionFromBDD = QuestionsBdd.getQuestion(uneQuestion);
			        TextView maTextView = (TextView) findViewById(R.id.txtQuestion);
			    	maTextView.setText(questionFromBDD.getQuestions());
			    	
			    	lesReponses = randomReponse();
			    	
			    	btnRep1.setText(questionFromBDD.getUneReponse(lesReponses.get(0)));
			    	btnRep2.setText(questionFromBDD.getUneReponse(lesReponses.get(1)));
			    	btnRep3.setText(questionFromBDD.getUneReponse(lesReponses.get(2)));
			    	btnRep4.setText(questionFromBDD.getUneReponse(lesReponses.get(3)));
			    	
			    	btnSuivant.setVisibility(View.INVISIBLE);
			    	btnRep1.setBackgroundResource(android.R.drawable.btn_default);
			    	btnRep2.setBackgroundResource(android.R.drawable.btn_default);
			    	btnRep3.setBackgroundResource(android.R.drawable.btn_default);
			    	btnRep4.setBackgroundResource(android.R.drawable.btn_default);
				}
				else
				{
					showScore(score);
				}
			  	
			}
		});
	 QuestionsBdd = new QuestionsBdd(this);
	 //déclaration des 10 questions
	 Questions question1=null;
	 Questions question2=null;Questions question3=null; Questions question4=null;
	 Questions question5=null; Questions question6=null;Questions question7=null;Questions question8=null; Questions question9=null ;Questions question10=null;
//   Création des différentes questions
	switch (idChapitre) {
	case 1:
	{
	      question1 = new Questions(1,"Combien d'habitants compte l'Asie?","2 Milliards","3 Milliards","4 Milliards","5 Milliards",idChapitre);
	      question2 = new Questions(2, "De combien était le taux d'urbanisation de l'asie en 2011?","45%", "40%", "35%","50%",idChapitre);
	      question3 = new Questions(3, "Quelle est la religion dominante au Japon?","Shintoïsme", "Boudhisme", "Confuciancisme"," ",idChapitre);
	      question4 = new Questions(4, "Quelle est le pourcentage de l'asie dans le PNB modiale?","20%","15%","30%","25%",1);
	      question5 = new Questions(5, "Quelle est le rang de l'asie dans le PNB mondial?","3ème","1er","2ème","4ème",idChapitre);
	      question6 = new Questions(6,"Que signifie NPIA?","Nouveaux Pays Industrialisés d'Asie","Nouveaux Pays Intermédiare d'Asie","Nouvelles Provinces Industrialisés d'Asie"," ",idChapitre);
	      question7 = new Questions(7, "Que signifie Anseac?","Association des nations du sud Est Asiatiques", "Association des nations du sud Est Américain", "Agglomérat des nations du sud Est Asiatiques"," ",idChapitre);
	      question8 = new Questions(8, "Quelle pays et régions asiatiques sont surnommé 4 dragons?","Corée du Sud,Hong-Kong,Singapour,Taïwan", "Corée du Nord,Pékin,Singapour,Taïwan", "Corée du Sud,Hong-Kong,Singapour,Tokyo","Corée du Sud,Hong-Kong,Pékin,Taïwan",idChapitre);
	      question9 = new Questions(9, "De combien d'habitants se compose la ville de Tokyo?","30 millions","40 millions","50 millions","60 millions",idChapitre);
	      question10 = new Questions(10, "Comment sont surnommées les NPIA?","Les nouveaux bébés tigres","Les nouveaux bébés dragons","Les nouveaux 9 fils du dragons","Les nouveaux bébés tigres de l'EST",idChapitre);
	      break;
	}
	case 2:
	{
		  question1 = new Questions(1,"À quel rang des puissances mondiales se situent les Etats-Unis ajourd'hui?","1er","2ème","5ème","7ème",idChapitre);
	      question2 = new Questions(2, "Par quoi se définit une puissance mondiale?","Sa taille, ses moyens et sa démographie", "Le nombre de guerres auxquelles elle a participé", "Le nombre de ses habitants","La date de création du pays",idChapitre);
	      question3 = new Questions(3, "Quel système prônent les Etats-Unis?","Le capitalisme", "Le fordisme", "Le communisme","La monarchie",idChapitre);
	      question4 = new Questions(4, "Quelle part du marché mondial représent les Etats-Unis?","17%","7%","27%","37%",idChapitre);
	      question5 = new Questions(5, "À l'origine, par combien de colonies britanniques étaient divisés les Etats-Unis?","13","4","12","51",idChapitre);
	      question6 = new Questions(6,"Qu'est-ce qui caractérise la population des Etats-Unis?","La pluri-culturalité","Les colons anglais","Les hispaniques","Les asiatiques",idChapitre);
	      question7 = new Questions(7, "Quelle est la première \" économie monde \" en 1914?","L'économie monde britannique", "L'économie monde américaine", "L'économie monde française","L'économie monde multipolaire",idChapitre);
	      question8 = new Questions(8, "Quel est le principe même du système économique capitaliste?","Le libre échange", "La non consommation", "Les échanges sont uniquement internes au pays","Le partage des richesses",idChapitre);
	      question9 = new Questions(9, "Comment appelle-t-on l'équivalent de la Silicon Valley en France?","Une technopole","Une mégalopole","Une métropole","Un centre de recherche",idChapitre);
	      question10 = new Questions(10, "Quelle crise marquera le déclin économique des Etats-Unis?","Le Jeudi Noir","La Grande Dépression","Les Subprimes","La Seconde Guerre Mondiale",idChapitre);
	      break;
	}
	case 3:
	{
		question1 = new Questions(1, "Comment s'appelle la période de forte croissance en France?", "30 Glorieuses", "30 Triomphales", "30 Grandes Années", "30 Victorieuses", idChapitre);
		question2 = new Questions(2, "Qu'est-ce que l'exode rurale?", "La fuite des campagnards vers les villes", "L'expansion des campagnes", "La fuite des citadins vers les campagnes", "La fuite des immigrés", idChapitre);
		question3 = new Questions(3, "Que signifie SMIC?", "Salaire Minimum Interprofessionnel de Croissance", "Salaire Minimum Interprofessionnel de Création", "Salaire Minimum Intermédiare de Croissance", "Salaire Moyen Interprofessionnel de Croissance", idChapitre);
		question4 = new Questions(4, "Durant cette période, le nombre de semaines de congés payés passe de 2 à:", "5 semaines", "6 semaines", "7 semaines", "8 semaines", idChapitre);
		question5 = new Questions(5, "Le nombre d'heure de travail hebdomadaire passe de 39h à:", "35h", "30h", "33h", "40h", idChapitre);
		question6 = new Questions(6, "Durant les 30 glorieuses, quel pourcentage représente la population paysanne dans la société française?", "5%", "15%", "20%", "30%", idChapitre);
		question7 = new Questions(7, "Que signifie RMI?", "Revenu Minimum d'Insertion", "Revenu Maximum d'Insertion", "Rendu Minimum d'Insertion", " ", idChapitre);
		question8 = new Questions(8, "Récemment le RMI fut remplacé par le...", "RSA", "RCA", "MSA", "TCA", idChapitre);
		question9 = new Questions(9, "En 2011, quel taux atteint le chômage en France?", "9,8%", "8,8%", "10,8%", "11,8%", idChapitre);
		question10 = new Questions(10, "Les pratiquants religieux passent de 60% de la population en 1945 à:", "10%", "30%", "20%", "5%", idChapitre);
		break;
	}
	case 4:
	{
		question1 = new Questions(1, "Quels pays composent l'Allience durant la Seconde Guerre Mondiale?", "La France et l'Angleterre", "La France et la Belgique", "La Pologne et la France", "L'Angleterre et les Etats-Unis", idChapitre);
		question2 = new Questions(2, "Quelle est la tactique utilitée par les allemands durant la Seconde Guerre Mondiale?", "Ils utilisent la stratégie de la \"Blitzkrieg\"", "Ils mettent en place la ligne Maginot", "Ils commencent par attaquer l'URSS", "Ils décident d'envahir la France le plus tôt possible", idChapitre);
		question3 = new Questions(3, "Quel est la date de la capitulation de la Pologne?", "Le 6 octobre 1939", "Le 11 novembre 1918", "Le 8 mai 1945", "Le 22 juin 1940", idChapitre);
		question4 = new Questions(4, "Quel est le but de l'opération qui s'est déroulée dans la \"poche de Dunkerque\" durant la Seconde Guerre Mondiale?", "Encercler et prendre au piège les Alliés", "Libérer une grande partie des troupes allemandes", "Préserver le contrôle naval de la France et de l'Angleterre", "Détruire le matériel des Alliés pour que l'Allemagne ne puisse pas le récupérer", idChapitre);
		question5 = new Questions(5, "En France, qui est placé à la présidence du conseil de la troisième République?", "Le maréchal Pétain", "Le général De Gaulle", "Winston Churchill", "Le général Gamelin", idChapitre);
		question6 = new Questions(6, "Où est installé le siège du nouveau régime en France pendant la Seconde Guerre mondiale", "À Vichy", "À Paris", "À Bruxelles", "À Lyon", idChapitre);
		question7 = new Questions(7, "Quel pays a mis en échec l'armée de Mussolini?", "La Grèce", "La Pologne", "L'Allemagne", "L'Espagne", idChapitre);
		question8 = new Questions(8, "Qui est à l'origine de l'attaque de Pearl Harbour le 7 décembre 1945?", "Le Japon", "L'Allemagne", "Les Alliés", "La Chine", idChapitre);
		question9 = new Questions(9, "Quelle est la date du débarquement en Normandie?", "Le 6 juin 1944", "Le 30 avril 1945", "Le 27 juin 1945", "Le 11 novembre 1943", idChapitre);
		question10 = new Questions(10, "Quelles sont les conséquences du refus de la capitulation japonaise?", "Les Etats-Unis largueront 2 bombes nucléaires sur le Japon", "Le Japon sera envahi par l'Allemagne", "Le Japon est envahi par les Etats-Unis", "La France déclare la guerre au Japon", idChapitre);
	}
	default:
		break;
	}


//      //On ouvre la base de données pour écrire dedans
      QuestionsBdd.open();
      //On insère les questions
      QuestionsBdd.insertQuestion4(question1);QuestionsBdd.insertQuestion4(question2);QuestionsBdd.insertQuestion4(question3);QuestionsBdd.insertQuestion4(question4);
      QuestionsBdd.insertQuestion4(question5);QuestionsBdd.insertQuestion4(question6);QuestionsBdd.insertQuestion4(question7);QuestionsBdd.insertQuestion4(question8);
      QuestionsBdd.insertQuestion4(question9);QuestionsBdd.insertQuestion4(question10);
      
      
      //Pour vérifier que l'on a bien créé notre question dans la BDD
	
    if(idCurrentQuestion == 0){
    	
    	questionFromBDD = QuestionsBdd.getQuestion(lesQuestions.get(0));
    	TextView maTextView = (TextView) findViewById(R.id.txtQuestion);
    	// on récupère une question
    	maTextView.setText(questionFromBDD.getQuestions());
    	
    	lesReponses = randomReponse();
    	btnRep1.setText(questionFromBDD.getUneReponse(lesReponses.get(0)));
    	btnRep2.setText(questionFromBDD.getUneReponse(lesReponses.get(1)));
    	btnRep3.setText(questionFromBDD.getUneReponse(lesReponses.get(2)));
    	btnRep4.setText(questionFromBDD.getUneReponse(lesReponses.get(3)));
    	
    	btnSuivant.setVisibility(View.INVISIBLE);
    	
    }
    
}
	
	public void showScore(int score2)
	{
		int a=1;
		a = score2;
		Bundle objetbunble = new Bundle();
		Intent intent = new Intent(Quizz.this, Score.class);
		objetbunble.putInt("score", a);
		objetbunble.putString("exercice", exercice);
		intent.putExtras(objetbunble);
		this.startActivity(intent);
	}

	private List<Integer> randomReponse()
	{
		
		java.util.List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Collections.shuffle(list);
		return list;
	}
	
	private List<Integer> randomQuestion()
	{
		java.util.List<Integer> list = Arrays.asList(1,2, 3, 4, 5, 6, 7, 8, 9, 10);
		Collections.shuffle(list);
		return list;
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	

	
		 
	}
	

}

