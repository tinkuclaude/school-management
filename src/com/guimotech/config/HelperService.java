package com.guimotech.config;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class HelperService {

    public static String dateToString(Date date) {
        SimpleDateFormat sdf  = new SimpleDateFormat("YYYY-MM-DD");
        return sdf.format(date);
    }

    public static Date stringToDate(String date) throws Exception {
        return Date.valueOf(date);
    }

    public static void showMessage(String message, MessageType type){
        if (type == MessageType.INFORMATION) {
            JOptionPane.showMessageDialog(null, message, "School Management",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (type == MessageType.ERROR) {
            JOptionPane.showMessageDialog(null, message, "School Management",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (type == MessageType.WARNING) {
            JOptionPane.showMessageDialog(null, message, "School Management",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    public static void showMessage(Exception e){
        showMessage(e.getMessage(), MessageType.ERROR);
    }

    public static void showMessageError(String message){
        showMessage(message, MessageType.ERROR);
    }
}
