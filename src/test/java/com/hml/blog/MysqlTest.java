package com.hml.blog;

import com.hml.blog.config.JpaConfiguration;
import com.hml.blog.entity.Role;
import com.hml.blog.entity.User;
import com.hml.blog.repository.RoleRepository;
import com.hml.blog.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        Role role = new Role();
        role.setName("admins");
        roleRepository.save(role);
        Assert.notNull(role.getId(),"roleId is null");

        User user = new User();
        user.setName("user");
        user.setCreatdate(new Date());

        List<Role> roles = roleRepository.findAll();
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId(),"userId is null");
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page,"page is null");
    }
}
