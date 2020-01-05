package com.meli.vinci.controller;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

import com.meli.vinci.enums.HumanMorseEnum;

public class Morse2Human {

    private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;
   
    public static void main(String[] args) throws IOException, LineUnavailableException, InterruptedException {
        boolean sound = !Arrays.asList(args).contains("-n");
        System.out.print("Hit enter to begin transmission.");
        String linea;
        //obtengo linea completa
        while ((linea = new BufferedReader(new InputStreamReader(System.in)).readLine()) != null) {
        	//tomo las palabras divididas por tabulacion
            for (String word : linea.split("  ")) {
	        	//tomo las letras divididas por tabulacion
	        	for (String code : word.split(" ")) {
	            	//obtengo la letra para ubicarla en el enum "morse"
		        	if(!code.isEmpty()) {
		        		  String decoded = HumanMorseEnum.byCodigo(code);
		                  System.out.print(decoded.equals(' ') ? "\n" : decoded);
		            }
	            }
	        	System.out.print(' ');
	        }
            System.out.print("\n\n>>> ");
        }
    }
}