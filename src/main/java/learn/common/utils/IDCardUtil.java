package learn.common.utils;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description:身份证工具类</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/17 15:17
 */
public class IDCardUtil {

  public static final String CARD_PATTERN = "\\d{15}|\\d{17}([0-9xX])";
  public static final String NEW_CARD_PATTERN = "\\d{17}([0-9xX])";
  public static final int OLD_CARD = 15;
  public static final int[] WEIGH = new int[17];
  public static final int _MOUDLE = 11;

  /**
   * 校验合法性
   */
  public static boolean isLawfulCardNo(String cardNo, boolean acceptOld) {
    if (acceptOld) {
      return Pattern.matches(CARD_PATTERN, cardNo);
    } else {
      return Pattern.matches(NEW_CARD_PATTERN, cardNo);
    }
  }

  public static boolean checkBirthday(String cardNo) {
    if (cardNo.length() == OLD_CARD) {
      cardNo = cardNo.substring(0, 6) + "19" + cardNo.substring(6);
    }
    int year = Integer.parseInt(cardNo.substring(6, 10));
    int currentYear = DateUtils.getCurrentYear();

    if (year < 1900 || year > currentYear) {
      return false;
    }

    int month = Integer.parseInt(cardNo.substring(10, 12));
    if (month < 1 || month > 12) {
      return false;
    }
    int day = Integer.parseInt(cardNo.substring(12, 14));

    switch (month) {
      case 2:
        return day > 0 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? day < 29
            : day <= 28;
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        return day > 0 && day <= 31;
      case 4:
      case 6:
      case 9:
      case 11:
        return day > 0 && day <= 30;

      default:
        return false;
    }
  }


  /**
   * 方法描述： 15位证件号补上世纪
   */
  public static String compleCardNo(String cardNo) {
    if (cardNo.length() == OLD_CARD) {
      if (Integer.valueOf(cardNo.substring(6, 8)) <= 16) {
        cardNo = cardNo.substring(0, 6) + "20" + cardNo.substring(6);
      } else {
        cardNo = cardNo.substring(0, 6) + "19" + cardNo.substring(6);
      }
    }
    return cardNo;
  }

  /**
   * 方法描述： 15位证件号转18位
   * @param cardNo
   * @return
   */
  public static String oldCardNo2New(String cardNo) {
    if (cardNo.length() == OLD_CARD) {
      if (Integer.valueOf(cardNo.substring(6, 8)) <= 16) {
        cardNo = cardNo.substring(0, 6) + "20" + cardNo.substring(6);
      } else {
        cardNo = cardNo.substring(0, 6) + "19" + cardNo.substring(6);
      }
      cardNo += getCheckCode(cardNo);
    }
    return cardNo;
  }

  /**
   * 方法描述： 初始化加权因子
   */
  public static void initWeigh() {
    for (int i = 0;i < WEIGH.length-1;i++) {
      WEIGH[i] = (int)Math.pow(2,WEIGH.length - i) % 11;
    }
  }

  /**
   * 方法描述： 获取校验码
   * @param cardNo
   * @return
   */
  public static String getCheckCode(String cardNo) {
    int sum = 0;
    initWeigh();
    for (int i = 0;i < WEIGH.length;i ++) {
      sum += WEIGH[i] * Integer.parseInt(cardNo.charAt(i)+ "");
    }
    int remain = 12 - sum % _MOUDLE;

    switch (remain) {
      case 12:
        return "1";
      case 11:
        return "0";
      case 10:
        return "x";
      default:
        return "" + remain;
    }
  }

  /**
   * 方法描述： 校验合法性
   * @param cardNo
   * @return
   */
  public static boolean isValidateCard(String cardNo) {
    if (StringUtils.isEmpty(cardNo)) {
      return false;
    }
    if (!isLawfulCardNo(cardNo, true)) {
      return false;
    }
    if (!checkBirthday(cardNo)) {
      return false;
    }
    if (cardNo.length() == OLD_CARD) {
      cardNo = compleCardNo(cardNo);
    }
    return getCheckCode(cardNo).equals(cardNo.toLowerCase().charAt(17) + "");
  }

  /**
   * 获取当前年龄
   * @param cardNo
   * @return
   */
  public static int getAge(String cardNo) {
    int nowyear = DateUtils.getCurrentYear();
    int nowMonthAndDay = Integer.parseInt(DateUtils.getMonthAndDay());
    int birthdayMonthAndDay = Integer.parseInt(cardNo.substring(10,14));
    if (cardNo.length() == OLD_CARD) {
      compleCardNo(cardNo);
      if (nowMonthAndDay > birthdayMonthAndDay) {
        return nowyear - Integer.parseInt(cardNo.substring(6,10));
      }
      return nowyear - Integer.parseInt(cardNo.substring(6,10)) - 1;
    }
    if (nowMonthAndDay > birthdayMonthAndDay) {
      return nowyear - Integer.parseInt(cardNo.substring(6,10));
    }
    return nowyear - Integer.parseInt(cardNo.substring(6,10)) - 1;
  }

/*  public static int getAge(String cardNo,int a)
  {
    Calendar c1 = Calendar.getInstance();
    long nowmillSeconds = c1.getTimeInMillis();
    Calendar c2 = Calendar.getInstance();
    Date date = DateUtils.getDateByPattern(cardNo.substring(6, 14), DateUtils.YYYY_MM_DD_PATTERN_1);
    c2.setTime(date);
    long birmillSeconds = c2.getTimeInMillis();
    Calendar c3 = Calendar.getInstance();
    long millis = nowmillSeconds - birmillSeconds;
    c3.setTimeInMillis(millis);
    int year1 = c3.get(Calendar.YEAR);
    return year1 - 1970;
  }*/

  public static void main(String[] args) {
//    System.out.println(getAge("410821199010180053",1));
    System.out.println(getAge("410821199010180053"));
  }



}
