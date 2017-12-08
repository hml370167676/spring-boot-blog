package learn.common.utils;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/28 11:34
 */
public class LockTest {

  private Lock lock = new ReentrantLock();//锁对象

  /**
   * 区别：
   *需要注意的是，用sychronized修饰的方法或者语句块在代码执行完之后锁自动释放，
   *而是用Lock需要我们手动释放锁，所以为了保证锁最终被释放(发生异常情况)，要把互斥区放在try内，释放锁放在finally内！！
   * @param name
   */
  public void output(String name) {
    lock.lock();//得到锁
    try {
      for (int i = 0;i < name.length();i ++) {
        System.out.println(name.charAt(i));
      }
    } finally {
      lock.unlock();//释放锁
    }
  }

  static class syncData {
    private int data;//共享数据

    public synchronized void set(int data) {
      System.out.println(Thread.currentThread().getName() + "准备写入数据");
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      this.data = data;
      System.out.println(Thread.currentThread().getName() + "写入" + this.data);
    }

    public synchronized void get() {
      System.out.println(Thread.currentThread().getName() + "准备读取数据");
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "读取" + this.data);
    }

  }

  public static void main(String[] args) {
    final syncData data = new syncData();

    //写入
    for (int i = 0;i < 3;i++) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int j = 0; j < 5;j++) {
            data.set(new Random().nextInt(30));
          }
        }
      });
      t.setName("Thread-w" + i);
      t.start();
    }
    //读取
    for (int i = 0; i < 3;i++) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int j = 0; j < 5; j++) {
            data.get();
          }
        }
      });
      t.setName("Thread-R" + i);
      t.start();
    }
  }




}
