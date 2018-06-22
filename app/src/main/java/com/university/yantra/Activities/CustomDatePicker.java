package com.university.yantra.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Avishi Goyal on 14-08-2017.
 */

public class CustomDatePicker extends DatePickerDialog{
    int maxYear;
    int maxMonth;
    int maxDay;
    int year,  monthOfYear, dayOfMonth;

    public CustomDatePicker(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);

        this.year=year;
        this.monthOfYear=monthOfYear;
        this.dayOfMonth=dayOfMonth;


    }

    public void setMaxDate(long maxDate) {
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
          //  getDatePicker().setMaxDate(System.currentTimeMillis());
        //} else {
            final Calendar c = Calendar.getInstance();
            c.setTimeInMillis(maxDate);
            maxYear = c.get(Calendar.YEAR)-5;
            maxMonth = c.get(Calendar.MONTH);
            maxDay = c.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            if (year > maxYear) {
                view.updateDate(maxYear, maxMonth, maxDay);
                generateDialog();


            }
            if (monthOfYear > maxMonth && year == maxYear) {
                view.updateDate(maxYear, maxMonth, maxDay);
                generateDialog();
            }
            if (dayOfMonth > maxDay && year == maxYear && monthOfYear == maxMonth) {
                view.updateDate(maxYear, maxMonth, maxDay);
                generateDialog();
            }
    }
    //}

   public void generateDialog()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());



            alertDialogBuilder.setMessage("We provide analysis for child of age greater than 5 only.");
            alertDialogBuilder.setTitle("Sorry For Inconvenience!");

            alertDialogBuilder.setPositiveButton("Got It",
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Toast.makeText(getActivity(),"Resume",Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
