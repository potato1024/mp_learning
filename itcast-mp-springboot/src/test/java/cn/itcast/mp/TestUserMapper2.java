package cn.itcast.mp;


import cn.itcast.mp.enums.SexEnum;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById() {
        User user = new User();
        user.setId(2L);
        User user1 = user.selectById();
        System.out.println(user1);

    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("wsw");
        user.setPassword("123456");
        user.setAge(30);
        user.setName("魏");
        user.setMail("a@gmail.com");
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);
        boolean result = user.insert();
        System.out.println("result=>" + result);


    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(7L);
        user.setAge(31);
        boolean result = user.updateById();
        System.out.println("result=>" + result);
    }

    @Test
    public void testUpdateVersion(){
        User user = new User();
        user.setId(2L);
        User userVersion = user.selectById();
        user.setAge(22);
        user.setVersion(userVersion.getVersion());
        boolean result = user.updateById();
        System.out.println("result=>" + result);
    }
    @Test
    public void testUpdateAll(){
        User user = new User();
        user.setAge(31);
        boolean result = user.update(null);
        System.out.println("result=>" + result);
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setPassword("666666");
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("password","123456");
        boolean result = user.update(wrapper);
        System.out.println("result=>" + result);
    }
    @Test
    public void testDelete(){
        User user = new User();
        user.setId(7L);
        boolean result = user.deleteById();
        System.out.println("result=>" + result);
    }

    @Test
    public void testSelect(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.ge("age",30);
        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);

        }

    }

    @Test
    public void testSelectBySex(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.ge("sex",SexEnum.WOMAN);
        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);

        }

    }

}
