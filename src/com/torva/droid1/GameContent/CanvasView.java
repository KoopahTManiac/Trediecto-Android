package com.torva.droid1.GameContent;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.torva.droid1.GameActivity;

public class CanvasView extends View implements OnTouchListener
{
	Paint red = new Paint();
	Paint green = new Paint();
	Paint font1 = new Paint();
	int pointer1 = 0;
	int pointer2 = 1;
	Rect rect1 = new Rect();
	Rect Shoot = new Rect();
	boolean IsMoving = true;
	public CanvasView(Context context) 
	{
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(android.graphics.Canvas canvas) {
		
		try
		{
			red.setColor(Color.RED);
			red.setStyle(Paint.Style.FILL);
			green.setColor(Color.GREEN);
			green.setStyle(Paint.Style.FILL);
			green.setAlpha(100);
			for (int i = 0; i <GameActivity.players.size();i++)
			{
				canvas.save();
				canvas.rotate(
						(float)(
						GameActivity.players.get(i).Rotation*57.2957795d), 
						GameActivity.players.get(i).X, 
						GameActivity.players.get(i).Y
				);
				
				rect1.left = GameActivity.players.get(i).X-10;
				rect1.top = GameActivity.players.get(i).Y-10;
				rect1.right = GameActivity.players.get(i).X+10;
				rect1.bottom = GameActivity.players.get(i).Y+10;
				canvas.drawRect(rect1,red);
				canvas.restore();
			}
			for (int i = 0; i < GameActivity.Bullets.size(); i++) 
			{
				Shoot.left = (int)GameActivity.Bullets.get(i).X-2;
				Shoot.top = (int)GameActivity.Bullets.get(i).Y-2;
				Shoot.right = (int)GameActivity.Bullets.get(i).X+2;
				Shoot.bottom = (int)GameActivity.Bullets.get(i).Y+2;
				canvas.drawRect(Shoot, red);
			}
			canvas.drawCircle(GameActivity.Control1.X, GameActivity.Control1.Y, 10, red);
			canvas.drawCircle(GameActivity.Control2.X, GameActivity.Control2.Y, 10, green);
			canvas.drawCircle(GameActivity.Control1._X, GameActivity.Control1._Y, 10, red);
			canvas.drawCircle(GameActivity.Control2._X, GameActivity.Control2._Y, 10, green);
			canvas.drawLine(GameActivity.Control1.X, GameActivity.Control1.Y, GameActivity.Control1._X, GameActivity.Control1._Y, red);
			canvas.drawLine(GameActivity.Control2.X, GameActivity.Control2.Y, GameActivity.Control2._X, GameActivity.Control2._Y, red);
			
			font1.setColor(Color.RED);
			font1.setStyle(Paint.Style.FILL);
			font1.setTextSize(10);
			canvas.drawText(String.valueOf(GameActivity.Bullets.size()),0f,10f,font1);
			canvas.drawText(GameActivity.status,0f,20f,font1);
			super.onDraw(canvas);
		}
		catch (Exception e)
		{
			
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int actionMasked = event.getActionMasked();
		int pointerIndex = ((event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT);
		
		
	    if (pointer1 == event.getPointerId(pointerIndex)) 
	    {
	        switch (actionMasked)
	        {
	        	case (MotionEvent.ACTION_POINTER_DOWN):
		        case (MotionEvent.ACTION_DOWN):
		        {
		        	GameActivity.Control1.X = (int)event.getX(0);
		        	GameActivity.Control1.Y = (int)event.getY(0);
		        	GameActivity.Control1._X = (int)event.getX(0);
		        	GameActivity.Control1._Y = (int)event.getY(0);
		        	break;
		        }
		        case (MotionEvent.ACTION_POINTER_UP):
		        case (MotionEvent.ACTION_UP):
		        {
		        	GameActivity.Control1.X = 9999;
		        	GameActivity.Control1.Y = 9999;
		        	GameActivity.Control1._X = 9999;
		        	GameActivity.Control1._Y = 9999;
		        	break;
		        }
		        case (MotionEvent.ACTION_MOVE):
		        {
		        	GameActivity.Control1._X = (int)event.getX(0);
		        	GameActivity.Control1._Y = (int)event.getY(0);
		        	break;
		        }
	        }
	    }
	    if (pointer2 == event.getPointerId(pointerIndex))
	    {
	        switch (actionMasked)
	        {
	        	case (MotionEvent.ACTION_POINTER_DOWN):
		        case (MotionEvent.ACTION_DOWN):
		        {
		        	GameActivity.Control2.X = (int)event.getX(pointerIndex);
		        	GameActivity.Control2.Y = (int)event.getY(pointerIndex);
		        	GameActivity.Control2._X = (int)event.getX(pointerIndex);
		        	GameActivity.Control2._Y = (int)event.getY(pointerIndex);
		        	break;
		        }
		        case (MotionEvent.ACTION_POINTER_UP):
		        case (MotionEvent.ACTION_UP):
		        {
		        	GameActivity.Control2.X = 9999;
		        	GameActivity.Control2.Y = 9999;
		        	GameActivity.Control2._X = 9999;
		        	GameActivity.Control2._Y = 9999;
		        	break;
		        }
		        case (MotionEvent.ACTION_MOVE):
		        {
		        	GameActivity.Control2._X = (int)event.getX(pointerIndex);
		        	GameActivity.Control2._Y = (int)event.getY(pointerIndex);
		        	break;
		        }
	        }
	    }
	    switch (actionMasked) 
	    {
		    case (MotionEvent.ACTION_MOVE):
		    {
		    	if (event.getPointerCount() > 1)
		    	{
		    		GameActivity.Control2._X = (int)event.getX(1);
		        	GameActivity.Control2._Y = (int)event.getY(1);
		    	}
		    }
	    }
	    int temp1 = GameActivity.Control2._X-GameActivity.Control2.X;
	    int temp2 = GameActivity.Control2._Y-GameActivity.Control2.Y;
	    
	    
	    for (int i = 0; i < GameActivity.players.size(); i++) 
	    {
			if (GameActivity.players.get(i).Username.equals(GameActivity.Username))
			{
				try
				{
					if (GameActivity.con != null)
					{
						if(GameActivity.con.isConnected())
						{
							GameActivity.con.sendTextMessage(
									"Mouse;" + 
									(GameActivity.players.get(i).NewX + temp1) + ";" + 
									(GameActivity.players.get(i).NewY + temp2));
							if (calcDistance(
									GameActivity.Control2.X,
									GameActivity.Control2.Y,
									GameActivity.Control2._X,
									GameActivity.Control2._Y) > 15)
							{
								GameActivity.con.sendTextMessage("Shoot");
							}
							else
							{
								GameActivity.con.sendTextMessage("ShootC");
							}
							if (calcDistance(GameActivity.Control1.X,GameActivity.Control1.Y,GameActivity.Control1._X,GameActivity.Control1._Y) > 15)
							{
								int dir1 = GameActivity.Control1._X-GameActivity.Control1.X;
							    int dir2 = GameActivity.Control1.Y-GameActivity.Control1._Y;
								double angle = ((Math.atan2(dir1, dir2) * 180) / Math.PI);
								
								if (angle < 22.5d && angle >= -22.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "1");
								}
								if (angle >= 22.5d && angle < 67.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "2");
								}
								if (angle >= 67.5d && angle < 112.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "3");
								}
								if (angle >= 112.5d && angle < 157.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "4");
								}
								if (angle >= 157.5d || angle < -157.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "5");
								}
								if (angle <= -112.5d && angle > -157.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "6");
								}
								if (angle <= -67.5d && angle > -112.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "7");
								}
								if (angle <= -22.5d && angle > -67.5d)
								{
									GameActivity.con.sendTextMessage("Move;" + "8");
								}
								IsMoving = true;
							}
							else if (IsMoving)
							{
								GameActivity.con.sendTextMessage("Move;" + "0");
								IsMoving = false;
							}
						}
					}
				}
				catch (Exception e)
				{
					
				}
			}
		}
	    
	    
	    
		return true;
	}
	public double calcDistance(double x1, double y1, double x2, double y2){
	     return Math.sqrt(Math.abs(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
    }
}
