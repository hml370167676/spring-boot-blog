package com.hml.blog;

import com.hml.blog.service.AuthenticationService;
import com.hml.blog.web.HelloController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

//配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@Rollback(true)
@Transactional
public class SpringBootBlogApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    private static Logger logger = LogManager.getLogger(SpringBootBlogApplicationTests.class);

    @Before //这个方法在每个方法执行之前都会执行一遍
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build(); //初始化MockMvc对象
    }

    @Test
    public void contextLoads() {
        logger.info("this is info log");
        logger.error("this is error log");
        logger.warn("this is warn log");
    }

    @Test
    public void helloShouldReturnDefaultMeaasage() throws Exception {

        this.mockMvc.perform(get("/hello"))//请求的url,请求的方法是get
                .andExpect(status().isOk())//返回的状态是200
                .andDo(print())//打印出请求和相应的内容
                .andExpect(content().string(containsString("Hello World!")));

        String stub = "except result";


    }

}
