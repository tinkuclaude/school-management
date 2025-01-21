package com.guimotech.config;

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
}
