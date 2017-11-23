package learn.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/20 14:50
 */
public class DateUtils {

  public static final String MM_PATTERN = "MM";
  public static final String YYYY_PATTERN = "yyyy";
  public static final String YYYY_MM_PATTERN = "yyyyMM";
  public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";
  public static final String YYYY_MM_DD_PATTERN_1 = "yyyyMMdd";
  public static final String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final String YYYY_MM_DD_HH_MM_SS_PATTERN_1 = "yyyyMMddHHmmss";
  public static final String YYYY_MM_DD_HH_MM_SS_SSSS_PATTERN = "yyyyMMddHHmmssSSSS";
  public static final String MM_DD_PATTERN = "MM-dd";
  public static final String MM_DD_PATTERN_1 = "MMdd";
  public static final String START_TIME = "00:00:00";
  public static final String END_TIME = "23:59:59";
  public static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
  public static final String HOUR_PATTERN = "15:00";
  public static final String YYYYMMDD_PATTERN = "yyyy年MM月dd日";
  public static final String TIME_PATTERN = "HH:mm:ss";
  public static final String ZGHOUR_TIME = "10:00:00";
  public static final String YYYYMMDD_HH_MM_SS_PATTERN = "yyyy年MM月dd日 HH:mm:ss";

  private static SimpleDateFormat sdf = new SimpleDateFormat();

  public static String getSerialNumber() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static int getYear(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.YEAR);
  }

  public static int getCurrentYear() {
    Calendar c = Calendar.getInstance();
    return c.get(Calendar.YEAR);
  }


  public static int getCurrentMonth() {
    Calendar c = Calendar.getInstance();
    return c.get(Calendar.MONTH) + 1;
  }

  public static String getMonthAndDay() {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat();
    sdf.applyPattern(MM_DD_PATTERN_1);
    return sdf.format(c.getTime());
  }

  public static int getCurrentDay() {
    Calendar c = Calendar.getInstance();
    return c.get(Calendar.DATE);
  }

  public static Date getDateByPattern(String date, String pattern) {
    sdf.applyPattern(pattern);
    try {
      return sdf.parse(date);
    } catch (ParseException e) {
      return null;
    }
  }

  public static void main(String[] args) {
    System.out.println(getCurrentDay());
    System.out.println(getCurrentMonth());
    System.out.println(getCurrentDay());
    System.out.println(getMonthAndDay());
    System.out.println(Calendar.getInstance().getTime());
  }


}
