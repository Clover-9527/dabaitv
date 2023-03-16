package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class D {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String register = sdf.format(date);
        System.out.println(register);
    }
}
