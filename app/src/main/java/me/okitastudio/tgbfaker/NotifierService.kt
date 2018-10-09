package me.okitastudio.tgbfaker

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class NotifierService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw NotImplementedError("not implemented: onBind")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.hasExtra("pid")) {
            val pid = intent.getStringExtra("pid")
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.action = TPatcher.ACTION_REPACTH
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            val builder = NotificationCompat.Builder(this, getString(R.string.app_name))
            builder.setContentTitle(getString(R.string.app_name))
                    .setContentText("PUBG PID: $pid, Click to Repatch and Reload PUBG")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(PendingIntent.getActivity(this, 0, mainIntent, 0))

            startForeground(1, builder.build())
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }
}
