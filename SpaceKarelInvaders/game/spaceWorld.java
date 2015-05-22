// The BallWorld class extends ActorWorld so that we can override the keyPressed() method.
// It demonstrates basic functionality to control what happens when pressing the arrow keys
package game;

import java.util.ArrayList;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import javax.swing.JOptionPane;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;


public class spaceWorld extends ActorWorld
{
	public spaceWorld(BoundedGrid b)
	{
		super(b);
		
	}
	private smithMover ball = null;
	private int score = 0;
	private int lives = 3;
	
	public int getScore()
	{
		return score;
	}
	
	public boolean gameIsLost()
	{
		if(lives<=0)
		{
			return true;
		}
		return false;
	}
	
	public int getNumLives()
	{
		return lives; 
	}
	
	public int updateNumLives()
	{
		ArrayList<Location> locs = getGrid().getOccupiedLocations();
		for(int i=0; i<locs.size(); i++)
		{
			if(locs.get(i).getRow()==3)
			{
				lives--;
				locs.remove(i);				
			}
		}
		return lives;
	}
	// The keyPressed() method is used to determine what happens when the arrow keys or the H key is pressed.
	// When the arrow key is pressed, we want to move the ball apprpriately.
	public boolean keyPressed(String description, Location loc)
	{
		Grid<Actor> gr = getGrid();
		loc = findLocationOfBall();
		
		if (ball == null)
			return true;
		
		if (description.equals("RIGHT"))        
		{
			//check to see if karel right first
			moveBall(gr, loc, 90);
		}
		else
		if (description.equals("LEFT"))        
		{	
			//check to see if karel left first
			moveBall(gr, loc, 270);
		}
		else
		if (description.equals("H") || description.equals("shift H")) //when user presses h or H, a dialog box appears
		{	
			JOptionPane.showMessageDialog(null, 
					"Hit the Karels before they hit earth! \n \n" 
					+"RIGHT arrow moves Mr.Smith right \n" 
					+"LEFT arrow moves Mr.Smith left \n"
					+"UP arrow shoots Karels \n"
					+"If you let 3 Karels by, the earth will implode!\n"
					); 
		} 
		else
		if(description.equals("UP"))
		{
			boolean cont = true;
			Location newLoc = new Location(3,loc.getCol());
			if(this.remove(newLoc)!=null)
			{
				this.remove(newLoc);
				cont = false;
				score++;
			}
			else
			{
				newLoc = new Location(2,loc.getCol());
			}
			if(this.remove(newLoc)!=null && cont==true)
			{
				this.remove(newLoc);
				cont=false;
				score++;
			}
			else
			{
				newLoc = new Location(1,loc.getCol());
			}
			if(this.remove(newLoc)!=null && cont==true)
			{
				this.remove(newLoc);
				cont=false;
				score++;
			}
			else
			{
				newLoc = new Location(0,loc.getCol());
			}
			if(this.remove(newLoc)!=null && cont==true)
			{
				this.remove(newLoc);
				cont=false;
				score++;
			}
		}
		return true;
	}
	
	// The findLocationOfBall() method is used to find the location of the ball.  It
	// loops through the grid to find the ball.
	public Location findLocationOfBall() 
	{
		Grid<Actor> gr = getGrid();
		Location loc = null;
		for (int i=0; i<getGrid().getNumRows(); i++)
		{
			for (int j=0; j<getGrid().getNumCols(); j++)
			{
				loc = new Location(i,j);
				Actor obj = gr.get(loc); //finds the location of an object in the grid
				if (obj instanceof smithMover) // check the object found to be a ball actor
				{
					ball = (smithMover) obj; 	// casts the object to be a ball
					return loc;			//returns the location of the ball
				}
			}
		}
		return loc;
	}

	// The moveBall() method moves the ball in the specified direction 
	public void moveBall(Grid<Actor> gr, Location loc, int dir)
	{
		Location newLoc = loc.getAdjacentLocation(dir);
		if (!gr.isValid(newLoc))
			newLoc = loc;
		ball.moveTo(newLoc);
		
	}	
}
