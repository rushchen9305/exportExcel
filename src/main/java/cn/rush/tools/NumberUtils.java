package cn.rush.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    public static final String FIX_ONE = "#.0";
    public static final String FIX_TWO = "#.00";

    public static Double format(Double value,String format){
        if (value == null) return null;
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return Double.valueOf(decimalFormat.format(value));
    }

    public static Double round(Double value,String[] s){
      return round(value,Integer.valueOf(s[0]));
    }

    public static Double round(Double value,int s){
        BigDecimal bd = new BigDecimal(value);
        BigDecimal bd2 = bd.setScale(s,2);
        return bd2.doubleValue();
    }


    public static void main(String[] args) {
//        System.out.println(format(2513.4156,FIX_TWO));
        System.out.println(round(2513.4156,new String[]{"2"}));


    }
}
