package learn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/17 15:24
 */
public class StringUtil {

  private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
  public static final String MOBILE_PATTERN = "1[345789]\\d{9}";

  /**
   * 获取字符串中的日期部分，如果不为日期时间格式，则返回原对象
   * @param dateTime
   * @return
   */
  public static String getDate (String dateTime) {
    return dateTime != null && dateTime.length() >= 10 ? dateTime.substring(0,10) :dateTime;
  }





}
