package com.mentobile.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.mentobile.marriageties.Application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerDialogCustom extends DialogFragment implements DialogInterface.OnClickListener, android.app.DatePickerDialog.OnDateSetListener {

    private Activity activity;
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Application.DATE_PATTERN3, Locale.ENGLISH);

    GetPickerDailogDate getPickerDailogDate;

    public interface GetPickerDailogDate {
        void GetDate(String date);
    }

    public DatePickerDialogCustom(Activity activity, GetPickerDailogDate getPickerDailogDate) {
        this.activity = activity;
        this.getPickerDailogDate = getPickerDailogDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        android.app.DatePickerDialog dpd = new android.app.DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        //Get the DatePicker instance from DatePickerDialogCustom
        DatePicker dp = dpd.getDatePicker();
        //Set the DatePicker minimum date selection to current date
        // dp.setMinDate(c.getTimeInMillis());//get the current day
        //dp.setMinDate(System.currentTimeMillis() - 1000);// Alternate way to get the current day

        //Add 6 days with current date
        //  c.add(Calendar.DAY_OF_MONTH, 6);

        //Set the maximum date to select from DatePickerDialogCustom
        dp.setMaxDate(c.getTimeInMillis());

        return dpd; //Return the DatePickerDialogCustom in app window
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
//        Log.d("Date Set", ":::::On Click");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, monthOfYear, dayOfMonth);
        String strDataTime = simpleDateFormat.format(newDate.getTime());
        getPickerDailogDate.GetDate(strDataTime);
//        Log.d("Date Set", ":::::Year " + year + " Month " + monthOfYear + " Date " + dayOfMonth + " Date " + strDataTime);
    }
}
