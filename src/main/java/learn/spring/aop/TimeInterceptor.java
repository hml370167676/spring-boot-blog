package learn.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Description:检测方法执行耗时的spring切面类</p> 使用@Aspect注解的类，Spring将会把它当做一个特殊的Bean,也就是不对这个类本身进行动态代理
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/6 11:00
 */
@Aspect
@Component
public class TimeInterceptor {

  private static Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

  //一分钟，即60000ms
  private static final long ONE_MINUTE = 60000;

  //service层的统计耗时切面，类型必须为 final String 类型的，主要里要使用的变量只能是静态常量类型的
  public static final String POINT = "execution (* )";

}
