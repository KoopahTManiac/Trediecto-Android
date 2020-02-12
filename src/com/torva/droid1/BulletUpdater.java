package com.torva.droid1;

import java.util.TimerTask;

public class BulletUpdater extends TimerTask
{
int speed =4;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < GameActivity.Bullets.size();i++)
		{
			GameActivity.Bullets.get(i).X += GameActivity.Bullets.get(i).VolX*speed;
			GameActivity.Bullets.get(i).Y += GameActivity.Bullets.get(i).VolY*speed;
		}
	}
}
