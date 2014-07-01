package com.example.bacquizz;

public class Questions {
	
	private int id;
	private String questions;
	private String rep1;
	private String rep2;
	private String rep3; 
	private String rep4;
	private String ordre;
	private int idChapitre;
	
	// Juste 4 questions
	public Questions(){
		
	}
	// Juste 3 questions
	public Questions( String questions, String rep1, String rep2,
			String rep3, int idChapitre) {
		super();
	
		this.questions = questions;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.idChapitre = idChapitre;
	}

	// Juste 4 Questions
	public Questions(int id, String questions, String rep1, String rep2,
			String rep3, String rep4, int idChapitre) {
		super();
		this.id=id;
		this.questions = questions;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.rep4 = rep4;
		this.idChapitre = idChapitre;
	}
	
	//4 questions sans id
		public Questions( String questions, String rep1, String rep2,
				String rep3, String rep4, int idChapitre) {
			super();
			this.questions = questions;
			this.rep1 = rep1;
			this.rep2 = rep2;
			this.rep3 = rep3;
			this.rep4 = rep4;
			this.idChapitre = idChapitre;
		}
	// 4 questions+ordre de réponse 
	public Questions( String questions, String rep1, String rep2,
			String rep3, String rep4, String ordre, int idChapitre) {
		super();
		
		this.questions = questions;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
		this.rep4 = rep4;
		this.ordre = ordre;
		this.idChapitre = idChapitre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getRep1() {
		return rep1;
	}
	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}
	public String getRep2() {
		return rep2;
	}
	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}
	public String getRep3() {
		return rep3;
	}
	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}
	public String getRep4() {
		return rep4;
	}
	public void setRep4(String rep4) {
		this.rep4 = rep4;
	}
	public String getOrdre() {
		return ordre;
	}
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
	
	public int getIdChapitre() {
		return idChapitre;
	}
	public void setIdChapitre(int idChapitre) {
		this.idChapitre = idChapitre;
	}
	
	public String getUneReponse(int nb)
	{
		String rep;
		if(nb == 1)
			rep = this.rep1;
		else
			if(nb == 2)
				rep = this.rep2;
			else
				if(nb == 3)
					rep = this.rep3;
				else
					rep = this.rep4;
		return rep;
	}
	
	public String toString()
	{
		String s = "";
		s= this.questions;
		
		return s;
	}

}
