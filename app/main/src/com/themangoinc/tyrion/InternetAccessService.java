package com.themangoinc.tyrion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class InternetAccessService extends ServiceBase {

	protected boolean isConnected = false;
	
/*	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
*/	
	public void setConnectionStatus(boolean status) {
		isConnected = status;
	}
	
	public boolean getConnectionStatus(){
		return isConnected;
	}

}
