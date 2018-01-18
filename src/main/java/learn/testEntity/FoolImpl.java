package learn.testEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/12/12 14:38
 */
@Component
public class FoolImpl implements Foo {

  private Bar bar;

  @Override
  public boolean checkCodeDuplicate(String code) {
    return bar.getAllCodes().contains(code);
  }

  @Autowired
  public void setBar(Bar bar) {
    this.bar = bar;
  }
}
