package com.hml.blog.mockLearn;

import static org.mockito.Mockito.when;

import java.util.Collections;
import learn.testEntity.Bar;
import learn.testEntity.FoolImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/12 17:04
 */

public class MockitoTest extends AbstractTestNGSpringContextTests{

  @Mock
  private Bar bar;

  @InjectMocks
  private FoolImpl foo;

  @BeforeMethod(alwaysRun = true)
  public void initMock() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCheckCodeDuplicate() {
    when(bar.getAllCodes()).thenReturn(Collections.singleton("123"));
    Assert.assertEquals(foo.checkCodeDuplicate("123"), true);
  }
}
