package learn.rsalearn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <p>Description:判断一个数 是否是质数（素数:只可以被自己和1整除的数。比1大但不是素数的数称为合数。1和0既非素数也非合数）</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2018/1/17 10:30
 */
@Component
public class PrimeNumberRSA {

  private static final Logger logger = LoggerFactory.getLogger(PrimeNumberRSA.class);


  /**
   * 质数取余的正负号取决于前面一个数是正数还是负数 对于整数，java的取余运算规则如下 a%b = a-(a/b)*b 5%3 = 5-(5/3)*3=2 5%-3 =
   * 5-(5/-3)*-3=2 -5%3 = -5-(-5/3)*3=-2 -5%-3 = -5-(-5/-3)*-3=-2
   *
   * 如果操作数中有浮点数则采用的规则为 a%b=a-(b*q),这里q=int(a/b) 5.2%3.1=5.2-1*3.1=2.1 5.2%-3.1=5.2-(-1)*(-3.1)=2.1
   * -5.2%3.1=-5.1-(-1)*3.1=-2.1 -5.2%-3.1=-5.1-(-1)*(-3.1)=-2.1
   */
  //最直接 最笨的办法
  public boolean isPrimeNumber1(int x) {

    if (x <= 1) {
      return false;
    }

    boolean isPN = true;

    for (int i = 2; i <= x - 1; i++) {
      if (x % i == 0) {
        isPN = false;
        break;
      }
    }
    return isPN;
  }

  /**
   * 对于一个正整数num而言，它对(num/2, num)范围内的正整数是必然不能够整除的, 因此，我们在判断num的时候，没有必要让 它除以该范围内的数
   */
  public boolean isPrimeNumber2(int x) {

    if (x <= 1) {
      return false;
    }

    boolean isPN = true;

    int ubound = x / 2 + 1;
    for (int i = 2; i <= ubound; i++) {
      if (x % i == 0) {
        isPN = false;
        break;
      }
    }

    return isPN;
  }

  /**
   * 对于一个小于num的正整数x，如果num不能整除x，则num必然不能整除num/x （num = num/x * x）。反之相同。 我们又知num =√num*√num。
   * 如果n除以大于√num的数，必得到小于√num的商，而小于√num的整数已经在2到√num的整 数试过了，因为就没有必要再试（√num, num）范围内的数了
   */

  public boolean isPrimeNumber3(int x) {

    if (x <= 1) {
      return false;
    }

    boolean isPN = true;
    int ubound = (int) Math.sqrt(x);
    for (int i = 2; i <= ubound; i++) {
      if (x % i == 0) {
        isPN = false;
        break;
      }
    }
    return isPN;
  }

  /**
   * 考虑到偶数的因素
   */

  public boolean isPrimeNumber4(int x) {

    if (x <= 1) {
      return false;
    }

    if (x % 2 == 0) {
      return false;
    } else {

      int ubound = (int) Math.sqrt(x);
      for (int i = 3; i <= ubound; i++) {

        if (x % i == 0) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * 埃拉托斯特尼筛选法
   *
   * @param lbound lower bound
   * @param ubound upper bound
   */
  public List primeNumberFilter(int lbound, int ubound) {
    Assert.isTrue(lbound >= 0, "下界不能小于0");
    Assert.isTrue(ubound >= 0, "上界不能小于0");
    Assert.isTrue(lbound <= ubound, "上界不能小于下界");

    boolean[] isprime = new boolean[ubound];
    List result = new ArrayList();

    for (int i = 0; i < ubound; i++) {
      if (i < 2) {
        isprime[i] = false;
      } else {
        isprime[i] = true;
      }
    }

    int ulimit = (int) Math.sqrt(ubound) + 1;
    for (int i = 2; i < ulimit; i++) {
      if (isprime[i]) {
        int repeat = ubound / i;
        for (int j = 2; j < repeat; j++) {
          isprime[i * j] = false;
        }
      }
    }

    for (int i = lbound; i < ubound; i++) {
      if (isprime[i]) {
        result.add(i);
      }
    }
    return result;
  }


}
