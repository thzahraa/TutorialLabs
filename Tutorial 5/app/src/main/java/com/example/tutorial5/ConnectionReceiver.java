package com.example.tutorial5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("IntentA")){
            Toast.makeText(context,"Explicit: ", Toast.LENGTH_SHORT).show();
        }
        if (("android.net.conn.CONNECTIVITY_CHANGE").equals(action)){
            Toast.makeText(context, "Implicit: Connection Changed", Toast.LENGTH_SHORT).show();
        }
    }
}
