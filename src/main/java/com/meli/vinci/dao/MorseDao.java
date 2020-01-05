package com.meli.vinci.dao;

import java.io.IOException;
import java.util.NoSuchElementException;

import com.meli.vinci.model.Morse;

public interface MorseDao {

	Morse getHumanFromBin(String bin) throws IOException;
    Morse getBin2Morse(String bin) throws IOException;
    Morse getHuman2Morse(String words) throws IllegalArgumentException, NoSuchElementException, IOException;
    Morse getMorse2Human(String morseCod) throws IllegalArgumentException, NoSuchElementException, IOException;
   
}
