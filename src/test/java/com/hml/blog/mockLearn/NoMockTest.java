package com.hml.blog.mockLearn;

import java.util.Collections;
import java.util.Set;
import learn.testEntity.Bar;
import learn.testEntity.FoolImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/12 16:35
 */
public class NoMockTest {

  @Test
  public void testCheckCodeDuplicate1() {

    FoolImpl foo = new FoolImpl();
    foo.setBar(() -> Collections.singleton("123"));
    Assert.assertEquals(foo.checkCodeDuplicate("123"), true);

  }

  @Test
  public void testCheckCodeDuplicate2() {

    FoolImpl foo = new FoolImpl();
    foo.setBar(new FakeBar(Collections.singleton("123")));
    Assert.assertEquals(foo.checkCodeDuplicate("123"),true);
  }

  public class FakeBar implements Bar {

    private final Set<String> codes;

    public FakeBar(Set<String> codes) {
      this.codes = codes;
    }

    @Override
    public Set<String> getAllCodes() {
      return codes;
    }
  }


}
