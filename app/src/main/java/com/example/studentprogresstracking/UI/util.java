package com.example.studentprogresstracking.UI;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.studentprogresstracking.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import java.util.Date;

public class util {

    public static String capitalize(String string){


      return  Arrays.stream(string.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }
    public static String  makeDateString(int year,int  month,int  date) {
        return getMonthString(month)+" "+date+" "+year;
    }
    public static String getMonthString(int month) {
        switch (month) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10:return "Oct";
            case 11:return "Nov";
            case 12:return "Dec";
            default:return "Invalid month";
        }

    }
    public static String DatetoNiceString(Date d){
    return new SimpleDateFormat("MMM dd yyyy").format(d);
    }

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean isValidPhoneNumber(String phone) {
        return (!TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches());
    }

    public static void createNotification(Context context, long triggerTime, String channelId, String channelName, String title, String content) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName,  NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.black)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true);
        notificationManager.notify((int) triggerTime, notificationBuilder.build());
    }


}
