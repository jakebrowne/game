package game;
import java.awt.Color;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import java.applet.*;
import java.io.File;
import java.net.*;

public class gameRunner 
{
	public static void main(String[] args)
	{
		System.setProperty("info.gridworld.gui.selection", "hide");
		System.setProperty("info.gridworld.gui.frametitle", "Space Karel Invaders!"); 
		spaceWorld world = new spaceWorld(new BoundedGrid (5,5));
		smithMover spaceSmith = new smithMover();
		world.add(new Location(4,1), spaceSmith);
		world.show();
		while(true)
		{
			int x = world.updateNumLives();
			world.setMessage("Press H for Help. " + "Score: " + world.getScore() + " Lives: " + x);
			if(world.getNumLives()<=0)
			{
				JOptionPane.showMessageDialog(null, "You have lost the game.");
				/*File music = new File("whatchasay.WAV");
				playSound(music);*/
				System.exit(0);
			} 
			try  
			{  
				Thread.sleep(1200);   
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}   
	/*static void playSound(File sound)
	{
		try     
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e)
		{
			
		}
	}*/
} 
