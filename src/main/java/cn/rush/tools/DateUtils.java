package cn.rush.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String YYYYMMDD = "yyyyMMdd";

    public static String parse(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String parse(Date date,String[] format){
        return parse(date,format[0]);
    }

    public static void main(String[] args) {
        System.out.println(parse(new Date(),YYYYMMDD));
    }

}
