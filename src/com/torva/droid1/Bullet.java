package com.torva.droid1;

public class Bullet 
{
	public int ID;
	public double X,Y,VolX,VolY;
	public Bullet(int ID, double X, double Y,double VolX, double VolY)
	{
		this.X = X;
		this.Y = Y;
		this.ID = ID;
		this.VolX = VolX;
		this.VolY = VolY;
	}
}
