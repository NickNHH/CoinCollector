package ch.hsr.appquest.coincollector;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationUtil {

    private static final String CHANNEL_ID = "CoinChannelId";
    private static final String CHANNEL_NAME = "CoinChannel";
    private static final String CHANNEL_DESCRIPTION = "Channel for sending coin found notifications";

    private Context context;
    private int notificationId = 0;

    NotificationUtil(Context context) { this.context = context; }

    public void sendNotificationToUser() {
        // TODO: Setze hier eine Notification ab. Nutze dazu NotificationCompat.Builder.
        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Coin Collector")
                .setContentText("Coin found")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Coin found"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);*/
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.coin), attributes);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

}
