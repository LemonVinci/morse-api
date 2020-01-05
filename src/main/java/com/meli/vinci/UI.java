package com.meli.vinci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.meli.vinci.dao.MorseDao;
import com.meli.vinci.dao.impl.MorseDaoImpl;
import com.meli.vinci.model.Morse;

public class UI {
	
	private static final int DOT = 200, DASH = DOT * 3, FREQ = 800;
	static Integer timeToStop = 0;
	static boolean cleanTime=false;
	static Timer timer0;
	static Timer timer1;
	static JTextField tfMorse;
	static JTextField tfHuman;
	
	
	public static void main (String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		int timerDelay0 = 500;
		int timerDelay1 = 60;
		URL soundName = UI.class.getClass().getResource("/beep.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundName);

		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		
		
		Morse morse = new Morse();
		MorseDao morseDao = new MorseDaoImpl();
		
		JButton b=new JButton("Click");
		b.setBounds(50, 100, 100, 40);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		final JTextField tfBinary = new JTextField();
		tfBinary.setBounds(50,150,150,20);
		
		tfMorse = new JTextField();
		tfMorse.setBounds(50,200,150,20);
		
		tfHuman = new JTextField();
		tfHuman.setBounds(50,250,150,20);
		
		timer0 = new Timer(timerDelay0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				tfBinary.setText(tfBinary.getText() + "0");
				timeToStop++;
				if(clip.isRunning()) clip.stop();
				if(timeToStop > 12){
					timer0.stop();timer1.stop();
					timeToStop=0;
					cleanTime=true;
					try {
						tfMorse.setText( morseDao.getBin2Morse(tfBinary.getText()).getMorse());
						tfHuman.setText( morseDao.getMorse2Human(tfMorse.getText()).getHuman());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		timer1 = new Timer(timerDelay1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tfBinary.setText(tfBinary.getText() + "1");
				clip.loop(1);
				
				timeToStop=0;
				if(cleanTime){
					tfBinary.setText("");
					tfMorse.setText("");
					tfHuman.setText("");
					cleanTime=false;
				}
				
			}
		});
		final ButtonModel bModel = b.getModel();
		bModel.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
			
				if(bModel.isPressed() && !timer1.isRunning()) {
					
					timer1.start();
					timer0.stop();
				}else if (!bModel.isPressed() && timer1.isRunning()) {
					timer0.start();
					timer1.stop();
				}
			}
		});
		
		JFrame f=new JFrame();
		f.add(b);
		f.add(tfBinary);
		f.add(tfMorse);
		f.add(tfHuman);
		
		f.setSize(400,800);
		f.setLayout(null);
		f.setVisible(true);
	}

}
