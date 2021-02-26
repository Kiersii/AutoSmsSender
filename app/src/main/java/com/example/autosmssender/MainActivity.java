package com.example.autosmssender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MyTag";
   private SharedPreferences sp;
   private EditText etNumber, etHour, etMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumber=findViewById(R.id.editNumber);
        etHour=findViewById(R.id.editHour);
        etMinute=findViewById(R.id.editMinute);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        sp=getSharedPreferences("opt2",Context.MODE_PRIVATE);

        Log.i(TAG,"kuwra");
   }

    @Override
    protected void onStart() {
        super.onStart();
        String number = sp.getString("number","48606738816");
        etNumber.setText(number);
        int hour = sp.getInt("hour",13);
        etHour.setText(Integer.toString(hour));
        int minute = sp.getInt("minute", 30);
        etMinute.setText(Integer.toString(minute));
    }

    private void setAlarm(long timeinmillis){
        System.out.println("weszloe w setalarm");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent  = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(AlarmManager.RTC,timeinmillis,AlarmManager.INTERVAL_DAY ,pendingIntent);
    }


    public void clickButton(View view){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("number", etNumber.getText().toString());
        editor.putInt("hour", Integer.parseInt(etHour.getText().toString()));
        editor.putInt("minute", Integer.parseInt(etMinute.getText().toString()));
        editor.apply();


        int hour = sp.getInt("hour",13);
        int minute = sp.getInt("minute", 30);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);
        if(calendar.getTimeInMillis()<System.currentTimeMillis()){
            calendar.set(Calendar.HOUR_OF_DAY,hour+24);
        }
        System.out.println(calendar.getTime());
        setAlarm(calendar.getTimeInMillis());
    }
}
