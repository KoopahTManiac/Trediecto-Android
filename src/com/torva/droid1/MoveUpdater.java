package com.torva.droid1;

import java.util.TimerTask;

public class MoveUpdater extends TimerTask{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i =0; i < GameActivity.players.size(); i++)
		{
			if (GameActivity.players.get(i).X < GameActivity.players.get(i).NewX)
			{
				GameActivity.players.get(i).X +=GameActivity.players.get(i).Speed;
			}
			else if (GameActivity.players.get(i).X > GameActivity.players.get(i).NewX)
			{
				GameActivity.players.get(i).X -=GameActivity.players.get(i).Speed;
			}
			if (GameActivity.players.get(i).Y < GameActivity.players.get(i).NewY)
			{
				GameActivity.players.get(i).Y +=GameActivity.players.get(i).Speed;
			}
			else if (GameActivity.players.get(i).Y > GameActivity.players.get(i).NewY)
			{
				GameActivity.players.get(i).Y -=GameActivity.players.get(i).Speed;
			}
		}

	}

}
