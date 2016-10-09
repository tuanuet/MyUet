package vnu.uet.tuan.myuet.MyNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import vnu.uet.tuan.myuet.MainActivity;

import vnu.uet.tuan.myuet.Models.Noti_data;

/**
 * Created by Admin on 16/9/2016.
 */
public class NotifiUlis {
    public static void init(Context context, Noti_data item,int id){
        String title = item.getTitle();
        String content = item.getContent();

        Intent intent =new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent= PendingIntent.getActivity(context,0,intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder buider = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            buider = new Notification.Builder(context)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setSound(sound)
                    .setContentText(content)
                    .setSmallIcon(android.R.drawable.ic_input_add)
                    .setContentIntent(pIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                manager.notify(id,buider.build());
            }
        }

    }
}
