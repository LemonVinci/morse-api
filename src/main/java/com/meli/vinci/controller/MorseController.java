package com.meli.vinci.controller;


import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.vinci.dao.MorseDao;
import com.meli.vinci.dao.impl.MorseDaoImpl;
import com.meli.vinci.model.Morse;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MorseController {

	  private static final String template = "Hello, %s!";
	  
	 
	  @RequestMapping("/translate2human")
	  public Morse humanFromBin(@RequestParam(value="binary", defaultValue="") String binaryCod) throws IllegalArgumentException, NoSuchElementException, IOException {
		 MorseDao morseDao = new MorseDaoImpl();
		 		 
		 return  morseDao.getHumanFromBin(binaryCod);
	  }	
	  
	  
	  @RequestMapping("/morse")
	  public Morse translate2Human(@RequestParam(value="code", defaultValue="") String morseCod) throws IllegalArgumentException, NoSuchElementException, IOException {
		 MorseDao morseDao = new MorseDaoImpl();
		 		 
		 return  morseDao.getMorse2Human(morseCod);
	  }	
	  
	  @RequestMapping("/human")
	  public Morse translate2Morse(@RequestParam(value="words", defaultValue="") String words) throws IllegalArgumentException, NoSuchElementException, IOException {
		 MorseDao morseDao = new MorseDaoImpl();
		 
		 return (morseDao.getHuman2Morse(words));
	  }	
}
