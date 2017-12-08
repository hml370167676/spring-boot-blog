package learn.common.utils;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/28 9:59
 */
public class ThreadTest extends Thread {

  private int count = 5;

  @Override
  public synchronized void run() {
    count --;
    System.out.println(currentThread().getName() + "count = " + count);
  }

  public static void main(String[] args) {
    ThreadTest tt = new ThreadTest();
    Thread t1 = new Thread(tt, "t1");
    Thread t2 = new Thread(tt, "t2");
    Thread t3= new Thread(tt, "t3");
    Thread t4 = new Thread(tt, "t4");
    Thread t5= new Thread(tt, "t5");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();

  }
}
