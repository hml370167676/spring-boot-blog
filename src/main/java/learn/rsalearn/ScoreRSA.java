package learn.rsalearn;

/**
 * <p>Description:学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2018/1/17 15:05
 */
public class ScoreRSA {

  public String scoreConvertor(float score, boolean round) {

    int scorea = 0;

    if (round) {
      //使用包装类四舍五入
      scorea = Float.floatToIntBits(score);
      //使用工具类四舍五入
      scorea = Math.round(score);
    } else {
      //强制转换
      scorea = (int) score;
    }
    return (scorea >= 90) ? "A" : (scorea >= 60 ? "B" : "C");

  }

  /**
   * 最大公约数
   */

  public int maxCommonDivisor(int m, int n) {

    //保证m > n
    if (m < n) {
      int temp = m;
      m = n;
      n = m;
    }
    if (m % n == 0) {
      return n;
    } else {
      return maxCommonDivisor(n, m % n);
    }
  }

  /**
   * 最小公倍数
   */
  public int minCommonMultiple(int m, int n) {

    return m * n / maxCommonDivisor(m, n);
  }


}
