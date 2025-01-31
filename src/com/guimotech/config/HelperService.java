package com.guimotech.config;

import com.guimotech.gui.swing.FrmAccueil;

import javax.swing.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class HelperService {

    public static Timestamp invalideDate = new Timestamp(0);
    public static java.util.Date invalideJDate = new java.util.Date(0);

//    public static String dateToString(Date date) {
//        SimpleDateFormat sdf  = new SimpleDateFormat("YYYY-MM-DD");
//        return sdf.format(date);
//    }

    public static Date stringToDateJU(String date) throws Exception {
        return Date.valueOf(date);
    }

    public static void showMessage(String message, MessageType type){
        if (type == MessageType.INFORMATION) {
            JOptionPane.showMessageDialog(FrmAccueil.getInstance(), message, "School Management",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (type == MessageType.ERROR) {
            JOptionPane.showMessageDialog(FrmAccueil.getInstance(), message, "School Management",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (type == MessageType.WARNING) {
            JOptionPane.showMessageDialog(FrmAccueil.getInstance(), message, "School Management",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    public static void showMessage(Exception e){
        e.printStackTrace();
        showMessage(e.getMessage(), MessageType.ERROR);
    }

    public static void showMessageError(String message){
        showMessage(message, MessageType.ERROR);
    }

    public static String dateToStringForBD(Timestamp tsp){
        if(tsp == null || tsp.equals(invalideDate)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd " + "HH:mm:ss.000");
        String str = sdf.format(tsp);
        return str;
    }

    //*
    public static String dateToString(Timestamp tsp){
        String str = "";
        if(tsp == null || tsp.equals(invalideDate)) return str;
        DateFormat sdf = DateFormat.getDateTimeInstance();
        str = sdf.format(tsp);
        return str;
    }

    public static String dateToString(Date date){
        String str = "";
        if(date==null) return str;
        DateFormat sdf = DateFormat.getDateTimeInstance();
        str = sdf.format(date);
        return str;
    }

    public static String dateToStringPreForm(Timestamp tsp){
        String str = "";
        if(tsp == null || tsp.equals(invalideDate)) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy " + "HH:mm:ss");
        //DateFormat sdf = DateFormat.getDateInstance();
        str = sdf.format(tsp);
        return str.replace("00:00:00", "");
    }

    public static String dateTimeToShortStringPreForm(Timestamp tsp){
        String str = "";
        if(tsp == null || tsp.equals(invalideDate)) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        str = sdf.format(tsp);
        return str.replace("00:00:00", "");
    }

    public static String dateTimeToShortStringPreForm(Date date){
        String str="";
        if(date==null) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        str = sdf.format(date);
        return str.replace("00:00:00", "");
    }

    public static String dateToStringPreForm(java.util.Date tsp){
        String str = "";
        if(tsp == null || tsp.equals(invalideJDate)) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        str = sdf.format(tsp);

        return str.replace("00:00:00", "");
    }

    public static String dateToShortStringPreForm(Timestamp tsp){
        String str="";
        if(tsp==null || tsp.equals(invalideDate)) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT);
        str = sdf.format(tsp);
        return str;
    }

    public static String dateToShortStringPreForm(Date date){
        String str="";
        if(date==null) return str;
        //DateFormat sdf = DateFormat.getDateInstance(DateFormat.SHORT);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        str = sdf.format(date);
        return str;
    }

    public static String dateToShortStringPreForm(java.util.Date date){
        String str="";
        if(date==null) return str;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        str = sdf.format(date);
        return str;
    }

    public static Timestamp stringToDate(String date){

        if (date == null || date == "") return invalideDate;
        String[] str;
        String res = "";
        str = date.split("[^0-9]+");
        int len = str.length;
        //System.out.println(len);
        //for(int i=0; i<len; i++) System.out.print(str[i] +", ");
        //System.out.println();
        if(date.contains("-")){
            try {
                return Timestamp.valueOf(date);
            } catch (Exception e) {
                return invalideDate;
            }
        }
        else if(len >= 3)
        {
            if(len == 3)
                res += str[2]+"-"+str[1]+"-"+str[0]+ " 00:00:00";
            else if(len == 4)
                res += str[2]+"-"+str[1]+"-"+str[0]+" "+str[3]+":00:00";
            else if(len == 5)
                res += str[2]+"-"+str[1]+"-"+str[0]+" "+str[3]+":"+str[4]+":00";
            else if(len == 6)
                res += str[2]+"-"+str[1]+"-"+str[0]+" "+str[3]+":"+str[4]+":"+str[5];
            else if(len == 7)
                res += str[2]+"-"+str[1]+"-"+str[0]+" "+str[3]+":"+str[4]+":"+str[5]+"."+str[6];
            else return invalideDate;

            try {
                return Timestamp.valueOf(res);
            } catch (Exception e) {
                return invalideDate;
            }
        }
        else if(len == 1 && date.length() == 8){
            res = date.substring(4)+"-"+date.substring(2, 4)+"-"+date.substring(0, 2)+ " 00:00:00";
            try {
                return Timestamp.valueOf(res);
            } catch (Exception e) {
                return invalideDate;
            }
        }
        else if(len == 1 && date.length() == 6){
            res = "20"+date.substring(4)+"-"+date.substring(2, 4)+"-"+date.substring(0, 2)+ " 00:00:00";
            try {
                return Timestamp.valueOf(res);
            } catch (Exception e) {
                return invalideDate;
            }
        }
        return invalideDate ;
    }

    public static boolean isDate(String date){
        Timestamp tsp = stringToDate(date);
        if(tsp.equals(invalideDate)) return false;
        return true;
    }

    public static boolean isValide(Timestamp tsp){
        if(tsp == null) return false;
        if(tsp.equals(invalideDate)) return false;
        return true;
    }

    public static boolean parseDate(String date, Timestamp tsp){
        tsp.setTime(stringToDate(date).getTime());
        if(tsp.equals(invalideDate)) return false;
        return true;
    }
}
