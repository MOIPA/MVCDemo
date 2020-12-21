package com.dql.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author tr
 * @date 2020/12/21 11:00
 */
public class Utils {
    /**
     * 计算到9月1日的年龄
     *
     * @param birthDay
     */
    public static int calcAge(String birthDay) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar birthDate = new GregorianCalendar();
            birthDate.setTime(dateFormat.parse(birthDay));
            Calendar current = new GregorianCalendar();
            // 今年9月日期
            Date currentNineMonth = dateFormat.parse(Calendar.getInstance().get(Calendar.YEAR) + "-09-01");
            Date currentTime = new Date();
            // 还没到9月按照现在时间算
            if (currentTime.before(currentNineMonth)) {
                current.setTime(currentTime);
            } else {
                current.setTime(currentNineMonth);
            }
            int diffYear = current.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
            int diffMonth = diffYear * 12 + current.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH);
            return diffMonth / 12;
        } catch (ParseException e) {
            System.out.println("ERROR: input date error");
            e.printStackTrace();
        }
        return -1;
    }
}
