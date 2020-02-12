package com.torva.droid1;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.torva.droid1.GameContent.CanvasView;
import com.torva.droid1.GameContent.TouchPoint;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
public class GameActivity extends Activity {

	Bundle extras;
	public static String Username;
	String Password;
	public String addr= "ws://83.252.177.80:8181";
	public static WebSocketConnection con = new WebSocketConnection();
	public static List<Player> players = new ArrayList<Player>();
	public boolean Found = false;
	public int direction_last = 0;
	public static Player me = new Player("", 0, 0, 0, 0, 0, 0, 0, 0);
	public static TouchPoint Control1 = new TouchPoint(0, 0, 0, 0);
	public static TouchPoint Control2 = new TouchPoint(0, 0, 0, 0);
	public static List<Bullet> Bullets = new ArrayList<Bullet>();
	public CanvasView canvasView;
	public static String status="";
	public WebSocketHandler wsHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			extras = getIntent().getExtras();
			Username = extras.getString("Username");
			Password = extras.getString("Password");
			wsHandler = new WebSocketHandler()
			{
				@Override
				public void onOpen() 
				{
					con.sendTextMessage("Login;" + Username +";" + Password);
		            con.sendTextMessage("UPO;"+ Username);//Get Online Users
					status = "Open Socket";
				}
				@Override
				public void onClose(int code, String reason) 
				{
					
					// TODO Auto-generated method stub
					status = "ClosedSocket";
					super.onClose(code, reason);
				}
				@Override
				public void onTextMessage(String payload) 
				{
					String[] messages = payload.split(";");
					if (messages[0].equals("Msg")) //Msg received
					{
							
					}
					else if (messages[0].equals("Pos")) //Pos Update
					{
						if (players.size() > 0)
						{
							for (int i =0; i < players.size(); i++)
							{
								if (players.get(i).ID == Integer.parseInt(messages[1]))
								{
									players.get(i).NewX = Integer.parseInt(messages[2]);
									players.get(i).NewY = Integer.parseInt(messages[3]);
									players.get(i).Speed = Integer.parseInt("2");
									players.get(i).HP = Integer.parseInt(messages[4]);
									players.get(i).HPMax = Integer.parseInt(messages[5]);
								}
							}
						}
					}
					else if (messages[0].equals("Add"))
		            {
		                for (int i =0; i < players.size();i++)
		                {
		                    if (players.get(i).ID == Integer.parseInt(messages[1]))
		                    {
		                        Found = true;
		                    }
		                }
		                if (Found == false)
		                {
		                    players.add(new Player(messages[2],Integer.parseInt(messages[1]),100,100,Integer.parseInt(messages[3]) ,Integer.parseInt(messages[4]),Integer.parseInt(messages[3]),Integer.parseInt(messages[4]),2));
		                }
		                Found = false;
		            }
					else if (messages[0].equals("BulletA"))
		            {
						Bullets.add(new Bullet(Integer.parseInt(messages[1]),Double.parseDouble(messages[2]),Double.parseDouble(messages[3]),Double.parseDouble(messages[4]),Double.parseDouble(messages[5])));
		            }
					else if (messages[0].equals("BulletD"))
					{
						for (int i =0 ; i < Bullets.size(); i++)
						{
							if (Bullets.get(i).ID == Integer.parseInt(messages[1]))
							{
								Bullets.remove(i);
								status ="Removed Shoot: " + i;
							}
						}
					}
					else if (messages[0].equals("Rot"))
					{
						if (players.size() > 0)
						{
							for (int i =0; i < players.size(); i++)
							{
								if (Integer.parseInt(messages[1]) == players.get(i).ID)
								{
									players.get(i).Rotation = Double.parseDouble(messages[2]);
								}
							}
						}
					}
					else if (messages[0].equals("Del"))
					{
						for (int i =0; i < players.size(); i++)
						{
							if (players.get(i).ID == Integer.parseInt(messages[1]))
							{
								players.remove(i);
							}
						}
					}
					else if (messages[0].equals("GK"))
					{
						
					}
					
					
					
				}
				@Override
				public void onBinaryMessage(byte[] payload) 
				{
					// TODO Auto-generated method stub
					super.onBinaryMessage(payload);
				}
			};
			
		con.connect(addr, wsHandler);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer MovementUpdaterTimer = new Timer();
		MovementUpdaterTimer.schedule(new MoveUpdater(), 0,1000/60);
		Timer BulletUpdateTimer = new Timer();
		BulletUpdateTimer.schedule(new BulletUpdater(), 0,1000/60);
		Timer GameUpdaterTimer = new Timer();
		GameUpdaterTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (canvasView != null)
				{
					canvasView.postInvalidate();
				}
				
			}
		},0,1000/60);
		canvasView = new CanvasView(this);
		setContentView(canvasView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}
	@Override
	public void onBackPressed() 
	{
		android.os.Process.killProcess(android.os.Process.myPid());
	};
	public void InvaildateCanvas()
	{
		canvasView.invalidate();
	}
	
}

