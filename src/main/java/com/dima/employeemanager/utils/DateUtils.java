package com.dima.employeemanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDateAndTime(){
        //Creating a format for the date
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //By calling the date constructor, we get the current date
        Date today=new Date();
        //formatting the date to string
        String currentDateAndTime=dateFormat.format(today);

        return currentDateAndTime;
    }

    public static String getCurrentDate(){
        //creating a matching format for the date as it appears on the Database
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        //By calling the date constructor, we get the current date
        Date today=new Date();
        //formatting the date to string
        String currentDate=dateFormat.format(today);

        return currentDate;
    }

    public static boolean isDate1AfterDate2(String strDate1, String strDate2) {
        try{
            //creating a matching format for the date as it appears on the Database
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            //Parsing the dates from string to date type.
            Date date1=dateFormat.parse(strDate1);
            Date date2=dateFormat.parse(strDate2);
            //We check if date1 is before date2
            if (date1.after(date2)) {
                return true;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isCurrentDateAfterEndDate(String strEndDate){
        //We check if the coupon's end date is expired
        return isDate1AfterDate2(getCurrentDate(), strEndDate);
    }

}