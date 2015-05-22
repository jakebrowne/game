package game;
import java.awt.Color;

import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class smithMover extends Actor
{
	
	
	public Color getColor()	//redefines color so it is original color
	{
		return null;
	}
		
	public void act()
	{
		karelFaller karel = new karelFaller();
		Location loc = new Location((int)(Math.random()*2),(int)(Math.random()*5));
		karel.putSelfInGrid(getGrid(), loc);
	}
	  
}



