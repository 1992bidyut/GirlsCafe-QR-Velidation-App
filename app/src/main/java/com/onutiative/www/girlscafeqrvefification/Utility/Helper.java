package com.onutiative.www.girlscafeqrvefification.Utility;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.onutiative.www.girlscafeqrvefification.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Helper {

    private Context context;

    public Helper(Context context) {
        this.context = context;
    }

    public  boolean isInternetAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }
    public void showSnakBar(View view,String text){
        Snackbar.make(view,text , Snackbar.LENGTH_SHORT)
                .show();
    }

    public String getDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
    public String getDateTime(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
    public String getMonthEarlier(){
        Date to = Calendar.getInstance().getTime();
        //this line is supposedly to get the date that is 30 days ago
        Date from = new Date(to.getTime()+(1000*60*60*24*30));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(from);
        return date;
    }

    public String getCalenderDate(final TextView setPosition){
        final Calendar calendar;
        int month, year, day;
        final String[] date = {""};
        //calender
        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                        calendar.set(i,i1,i2);
                        date[0] = sdf.format(calendar.getTime());
                        //Log.i(TAG,"At pick date: "+ date[0]);
                        setPosition.setText(date[0]);
                    }
                }, year, month, day);

        datePickerDialog.show();
        return date[0];
    }

    public String dateConversion(String date){
        String formatedate="";
        SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat svrFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date forDate=mdyFormat.parse(date);
            formatedate= svrFormat.format(forDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatedate;
    }

    public String makeUniqueID(){
        Date date= new Date();
        long time = date.getTime();
        return String.valueOf(time);
    }
}
