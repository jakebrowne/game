package game;

import java.awt.Color;

import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;

public class karelFaller extends Actor
{
	public Color getColor()	//redefines color so it is original color
	{
		return null;
	}
	public void act()
	{
		Location aLoc = getLocation();		//get this actor???s current location
		Grid<Actor> aGrid = getGrid();		//get this actor???s grid

//		setDirection(Location.SOUTH);
		if (aLoc.getRow() == aGrid.getNumRows()-2)
		{
			removeSelfFromGrid();
		}
		else
		{
			moveTo(new Location(aLoc.getRow()+1, aLoc.getCol()));
		}
	}
}