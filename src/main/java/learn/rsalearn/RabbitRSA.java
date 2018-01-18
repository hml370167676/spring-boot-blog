package learn.rsalearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description:有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第3个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ </p>
 * @Document 斐波拉契数列
 * @author minglu@toutoujinrong.com
 * @date 2018/1/17 9:56
 */
public class RabbitRSA {

  private static final Logger logger = LoggerFactory.getLogger(RabbitRSA.class);

  /**
   * 分析: 七月份的时候3月的新兔子在五月生的小兔子又生了一对兔子 关于斐波那契数列的兔子繁殖问题可以如下理解：
   * 实际月份  1    2    3    4    5    6    7     8
   * 幼仔对数  1    0    1    1    2    3    5     8
   * 成兔对数  0    1    1    2    3    5    8    13
   * 总体对数  1    1    2    3    5    8   13    21
   * 幼仔对数=前月成兔对数
   * 成兔对数=前月成兔对数+前月幼仔对数
   * 总体对数=本月成兔对数+本月幼仔对数
   * 可以看出幼仔对数、成兔对数、总体对数都构成了一个数列。这个数列有关十分明显的特点，那是：前面相邻两项之和，构成了后一项。
   */

  public static int rabbitRsa(int month) {
    if (month == 0 || month == 1) {
      return 1;
    } else {
      return rabbitRsa(month - 2) - rabbitRsa(month - 1);
    }
  }
    public static void main(String args[]){

    for (int i = 0; i <= 12; i++) {
      logger.info("第{}月的兔子总数为：{}",i,rabbitRsa(i));
    }
  }

}
