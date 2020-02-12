package com.torva.droid1;


public class Player 
{
	public String Username;
	public int ID;
	public int HP;
	public int HPMax;
	public int X;
	public int Y;
	public int NewX;
	public int NewY;
	public int Speed;
	public double Rotation = 0;
	

	public Player(String Username,int ID,int HP,int HPMax,int X, int Y, int NewX,int NewY,int Speed)
	{
		this.Username = Username;
		this.ID = ID;
		this.HP = HP;
		this.HPMax = HPMax;
		this.X =X;
		this.Y =Y;
		this.NewX = NewX;
		this.NewY = NewY;
		this.Speed = Speed;
	}
}