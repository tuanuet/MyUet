package vnu.uet.tuan.myuet.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import vnu.uet.tuan.myuet.Service.MyService;


/**
 * Created by Admin on 15/9/2016.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent toService= new Intent(context,MyService.class);
        context.startService(toService);
    }
}