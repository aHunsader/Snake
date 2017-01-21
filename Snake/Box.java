package main;

import java.util.ArrayList;

public class Box {
	
	private int speedX;
	private int speedY;
	private ArrayList<Direction> directions;
	
	public Box(int speedX, int speedY){
		this.speedX = speedX;
		this.speedY = speedY;
		directions = new ArrayList<>();
	}
	public void setPosX(){
		speedX = 1;
		speedY = 0;
	}
	public void setNegX(){
		speedX = -1;
		speedY = 0;
	}
	public void setPosY(){
		speedY = -1;
		speedX = 0;
	}
	public void setNegY(){
		speedY = 1;
		speedX = 0;
	}
	public void addDirection(Direction d){
		directions.add(d);
	}
	public void deleteDirection(){
		directions.remove(0);
	}
	public int getSpeedX(){
		return speedX;
	}
	public int getSpeedY(){
		return speedY;
	}
	public ArrayList<Direction> getDir(){
		return directions;
	}
	public void setDir(int index, Direction d){
		directions.set(index, d);
	}
	public void removeDir(int index){
		directions.remove(index);
	}
	public void cutSpeedX(){
		speedX = 0;
	}
	public void cutSpeedY(){
		speedY = 0;
	}
}
