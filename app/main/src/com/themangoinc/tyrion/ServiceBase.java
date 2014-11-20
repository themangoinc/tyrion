package com.themangoinc.tyrion;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class ServiceBase extends Service {

    protected Messenger mClientMessenger;
    protected Messenger mMsgHandler;

    public void doOnIncomingMsg(Message msg) {

    	switch (msg.what) {
    		case MessagesBase.SET_CLIENT_MSG : {
    			mClientMessenger = msg.replyTo;
    			break;
    			
    		}
    		default: {
    			break;
    		}
    	}	
    }
	
    protected class IncomingHandler extends Handler {
    	@Override
    	public void handleMessage(Message msg) {
    		doOnIncomingMsg(msg);
    	}    	
    }

    @Override
    public IBinder onBind(Intent arg0) {
    	// TODO Auto-generated method stub
    	mMsgHandler = new Messenger(new IncomingHandler());
    	return mMsgHandler.getBinder();
    }
    
	protected void sendClientDebugMsg(String str) {
		if(mClientMessenger != null) {
			Message msg = Message.obtain(null, MessagesBase.DBG_MSG, 0, 0);
			Bundle msgBundle = new Bundle(); 
			msgBundle.putString("debug", str);
			msg.setData(msgBundle);
			try {
				mClientMessenger.send(msg);
			} catch (RemoteException e) {
				
			}
		}
	}

}
