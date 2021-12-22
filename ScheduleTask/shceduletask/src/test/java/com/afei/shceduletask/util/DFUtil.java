package com.afei.shceduletask.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DFUtil {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String format(Date date) {
        return sdf.format(date);
    }
}
