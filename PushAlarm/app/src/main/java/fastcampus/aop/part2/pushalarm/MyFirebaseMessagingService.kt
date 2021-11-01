package fastcampus.aop.part2.pushalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import fastcampus.aop.part2.pushalarm.NotificationType.*

class MyFirebaseMessagingService: FirebaseMessagingService() {

    //라이브 서비스의 경우, 토큰이 갱신될 때마다 서버에 해당 토큰을 갱신
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        createNotificationChannel()

        //Get PushData
        val type = remoteMessage.data["type"]
            ?.let { valueOf(it) }
        val title = remoteMessage.data["title"]
        val message= remoteMessage.data["message"]

        type ?: return  //타입이 null일 경우 알림X

        //Create Notification & Notify
        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, message))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.description = CHANNEL_DESCRIPTION

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun createNotification(
        type: NotificationType,
        title: String?,
        message: String?
    ): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)   //기존 액티비티 갱신
        }
        val pendingIntent = PendingIntent.getActivity(this, type.id, intent, FLAG_UPDATE_CURRENT)


        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ico_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)    //클릭했을 때 자동으로 알림이 사라짐

        when(type) {
            NORMAL -> Unit
            EXPANDABLE -> {
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "😊😂🤣❤😍😒👌😘💕😁👍🙌🤦‍♀️🤦‍♂️🤷‍♀️🤷‍♂️✌🤞😉😎🎶😢💖😜👏💋🌹🎉🎂🤳🐱‍👤🐱‍💻🐱‍🐉🐱‍👓🐱‍🚀👀✔😃✨"
                        )
                )
            }
            CUSTOM -> {
                notificationBuilder
                    .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(
                        RemoteViews(
                            packageName,
                            R.layout.view_custom_notification
                        ).apply {
                            setTextViewText(R.id.title, title)
                            setTextViewText(R.id.message, message)
                        }
                    )
            }
        }

        return notificationBuilder.build()
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}