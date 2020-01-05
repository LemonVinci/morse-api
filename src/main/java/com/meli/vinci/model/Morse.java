package com.meli.vinci.model;


public class Morse {

	 private String binary;
	 private String morse;
	 private String human;
	  
	public Morse(){
		binary = new String();
		morse = new String();
		human = new String();
	}
	  
	public String getBinary() {
		return binary;
	}
	public String getMorse() {
		return morse;
	}
	public String getHuman() {
		return human;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

	public void setMorse(String morse) {
		this.morse = morse;
	}

	public void setHuman(String human) {
		this.human = human;
	}

	 
}