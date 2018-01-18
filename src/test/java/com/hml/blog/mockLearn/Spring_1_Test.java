package com.hml.blog.mockLearn;

import static org.mockito.Mockito.when;

import java.util.Collections;
import learn.testEntity.Bar;
import learn.testEntity.Foo;
import learn.testEntity.FoolImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/13 10:48
 */
@ContextConfiguration(classes = FoolImpl.class)
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class Spring_1_Test extends AbstractTestNGSpringContextTests {

  @MockBean
  private Bar bar;

  @Autowired
  private Foo foo;

  @Test
  public void testCheckCodeDuplicate() {
    when(bar.getAllCodes()).thenReturn(Collections.singleton("123"));
    Assert.assertEquals(foo.checkCodeDuplicate("123"),true);
  }
}
