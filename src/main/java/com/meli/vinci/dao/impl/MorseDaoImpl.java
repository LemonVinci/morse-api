package com.meli.vinci.dao.impl;

import java.io.IOException;
import java.util.NoSuchElementException;

import com.meli.vinci.dao.MorseDao;
import com.meli.vinci.enums.HumanMorseEnum;
import com.meli.vinci.model.Morse;

public class MorseDaoImpl implements MorseDao {

	private Morse morse;

    public MorseDaoImpl() {
    	morse = new Morse();
    }

	@Override
	public Morse getHumanFromBin(String bin) throws IOException {
		morse = getBin2Morse(bin);
		morse = getMorse2Human(morse.getMorse());
		
		return morse;
	}

	@Override
	public Morse getBin2Morse(String bin) throws IOException {
		 morse.setBinary(bin);
	        String code = new String();
	        char lastChar='0';
	        Integer contador=0;
	        //obtengo linea completa
	        	    for (char c : bin.toCharArray()) {
		            	if(lastChar == c) {
		            		contador++;
		            	}else {
		            		//break de la cadena homogenea
		            		if(lastChar=='1') {
		            			code+=(contador > 3 ? "-" : ".");
		            		}else {
		            			
		            			if(contador > 3 && contador < 8) code+=" ";
		            			if(contador >= 8) code+="  ";
		            			
		            		}
		            		contador=1;
		            	}
		            	lastChar=c;
		            }
	        	    morse.setMorse( morse.getMorse() + code);
	        	    code = new String();
	                System.out.print("\n\n>>> ");
	      
		return morse;
	}

	@Override
	public Morse getHuman2Morse(String words) throws IllegalArgumentException, NoSuchElementException, IOException {
		//obtengo linea completa
		morse.setHuman(words);	
        	 for (String word : words.split(" ")) {
	        	//tomo caracteres
	            for (char c : word.toUpperCase().toCharArray()) {
	            	//obtengo la letra para ubicarla en el enum "morse"
	            	for (char note : HumanMorseEnum.byLetra(Character.toString(c)).toCharArray()) {
	                    System.out.print(note == ' ' ? "\n" : note);
	                    morse.setMorse(morse.getMorse() + (note == ' ' ? ' ' :  note));
	            	}
	            	
	            	morse.setMorse(morse.getMorse() + ' ');
	            }
	            morse.setMorse(morse.getMorse() + "  ");
        	}
		return morse;
	}

	@Override
	public Morse getMorse2Human(String morseCod) throws IllegalArgumentException, NoSuchElementException, IOException {
		morse.setMorse(morseCod);	
        //obtengo linea completa
        //tomo las palabras divididas por tabulacion
            for (String word : morseCod.split("  ")) {
	        	//tomo las letras divididas por tabulacion
	        	for (String code : word.split(" ")) {
	            	//obtengo la letra para ubicarla en el enum "morse"
		        	if(!code.isEmpty()) {
		        		String decoded = HumanMorseEnum.byCodigo(code);
		        		morse.setHuman(morse.getHuman() + (decoded.equals(' ') ? ' ' :  decoded));
		            }
	            }
	        	morse.setHuman(morse.getHuman()+ ' ');
	        }
		return morse;
	}

}