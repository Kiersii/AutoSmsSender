package com.example.autosmssender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MyAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sp = context.getSharedPreferences("opt2", Context.MODE_PRIVATE);
        System.out.println("weszło w reveivera");

        String number = sp.getString("number","48606738816");

        sendMessage(number);
    }

    public void sendMessage(String number){

        String [] messages={"take a pill","take a chill pill","weź pigułke",
                "\uD83D\uDC8A","\uD83C\uDF46","\uD83C\uDF64",
                "\uD83C\uDF79","my \uD83C\uDF79","mój \uD83D\uDC2C",
                "moja \uD83D\uDC37","i'm gonna \uD83D\uDC45 you","\uD83D\uDC44",
                "\uD83D\uDC59","\uD83D\uDC7D","\uD83D\uDC96",
                "\uD83D\uDC98","\uD83D\uDC9D","\uD83D\uDCA9",
                "\uD83D\uDD1E","\uD83D\uDD50","\uD83D\uDE07",
                "\uD83D\uDE08","\uD83D\uDEB7","\uD83D\uDEB8",};

        final int i=messages.length;//
        final int random = new Random().nextInt(i);

        SmsManager myManager= SmsManager.getDefault();
        myManager.sendTextMessage(number,null,messages[random], null,null) ;

    }
}
