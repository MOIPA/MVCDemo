package com.dql.util;

import com.dql.I18.AppText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
    public static int calcAge(String birthDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    }

    public static String calcEndTime(Date startTime, String kind, int times) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(startTime);
        if (kind == AppText.QUARTERLY_FEES.toString()) {
            times *= 4;
        } else if (kind == AppText.YEARLY_FEES.toString()) {
            times *= 12;
        }
        instance.add(Calendar.MONTH, times);
        Date time = instance.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 计算总共缴纳多少钱 假定每个月60块
     *
     * @param kind
     * @param times
     * @return
     */
    public static String calcFamilyMoney(String member, String kind, int times) {
        int baseMoney = 10;
        if (member == AppText.PERSONAL.toString()) {
            baseMoney = 36;
        } else if (member == AppText.FAMILY.toString()) {
            baseMoney = 60;
        } else if (member == AppText.VISITOR.toString()) {
            // 游客只有次数 times*money
            return "you choosed visitor set,total money is: " + baseMoney * times;
        }
        if (kind == AppText.QUARTERLY_FEES.toString()) {
            times *= 4;
        } else if (kind == AppText.YEARLY_FEES.toString()) {
            times *= 12;
        }
        if (member == AppText.PERSONAL.toString()) {
            baseMoney = 36;
            return "you choosed personal set,total money is: "+baseMoney*times;
        } else if (member == AppText.FAMILY.toString()) {
            baseMoney = 60;
            return "you family personal set,total money is: "+baseMoney*times;
        }
        return "error member type";
    }

    /**
     * 验证大陆手机号是否正确
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
