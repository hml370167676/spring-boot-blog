package com.hml.blog.mockLearn;

import learn.rsalearn.PrimeNumberRSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2018/1/17 14:54
 */
@SpringBootTest(classes = PrimeNumberRSA.class)
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class PrimeNumberTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private PrimeNumberRSA primeNumberRSA;

  @Test
  public void test() {
    System.out.println(primeNumberRSA.primeNumberFilter(0, 120));
  }

}
