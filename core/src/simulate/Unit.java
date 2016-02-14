package simulate;

import render.GDXMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import util.Point;

public class Unit {
	
	public Point position;
	//in radians
	public float direction;
	public int id;
	
	public Unit(int i, float x,float y, float r)
	{
		position = new Point(x, y);
		direction = r;
		id = i;
	}
	
	public void move(float f)
	{
		position.x += Math.cos(direction) * f;
		position.y += Math.sin(direction) * f;
		
		//check for out of map
		if(position.x < 0 - (SwarmMain.MAP_SIZE / 2))
		{
			position.x = 0 - (SwarmMain.MAP_SIZE / 2);
			direction = (float) (Math.PI - direction);
		}
		else if(position.x > (SwarmMain.MAP_SIZE / 2))
		{
			position.x = (SwarmMain.MAP_SIZE / 2);
			direction = (float) (Math.PI - direction);
		}
		else if(position.y < 0 - (SwarmMain.MAP_SIZE / 2))
		{
			position.y = 0 - (SwarmMain.MAP_SIZE / 2);
			direction = 0 - direction;
		}
		else if(position.y > (SwarmMain.MAP_SIZE / 2))
		{
			position.y = (SwarmMain.MAP_SIZE / 2);
			direction = 0 - direction;
		}
	}
	
	public void turn(float f)
	{
		direction += f;
	}
	
	public void setDir(float f)
	{
		direction = f;
	}

}
