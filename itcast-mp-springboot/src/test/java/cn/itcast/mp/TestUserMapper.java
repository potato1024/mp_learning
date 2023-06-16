package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setMail("2@itcast.cn");
        user.setAge(15);
        user.setName("汤姆1");
        user.setUserName("tom1");
        user.setPassword("123456");
        user.setAddress("beijing");
        int count = this.userMapper.insert(user);
        System.out.println("count=>" + count);
        System.out.println("id=>" + user.getId());
    }

    @Test
    public void testSelectByID() {
        User user = this.userMapper.selectById(3L);
        System.out.println(user);
    }

    @Test
    public void testUpdateByID() {
        User user = new User();
        user.setId(1L);
        user.setAge(19);
        user.setPassword("666666");
        int result = this.userMapper.updateById(user);
        System.out.println("result=>" + result);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(20);
        user.setPassword("888888");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "zhangsan");
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result=>" + result);

    }

    @Test
    public void testUpdate2() {

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", 21).set("password", "999999")
                .eq("user_name", "zhangsan");
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result=>" + result);

    }

    @Test
    public void testDeleteById() {
        int result = this.userMapper.deleteById(3L);
        System.out.println("result=>" + result);
    }

    @Test
    public void testDeleteByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "zhangsan");
        map.put("password", "999999");
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result=>" + result);
    }

    @Test
    public void testDelete() {
/*        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","tom1")
                .eq("password","123456");*/

        User user = new User();
        user.setUserName("tom");
        user.setPassword("123456");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = this.userMapper.delete(wrapper);
        System.out.println("result=>" + result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = this.userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
        System.out.println("result=>" + result);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 100L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456");

        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count=>" + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "itcast");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "itcast");
        Page<User> page = new Page<>(2, 2);
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("总条数" + iPage.getTotal());
        System.out.println("总页数" + iPage.getPages());
        System.out.println("当前页数" + iPage.getCurrent());
        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }

    }

    @Test
    public void testFindById() {
        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "曹操");
        params.put("age", "20");
        params.put("password", null);

        //wrapper.allEq(params);
        //wrapper.allEq(params,false);
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")), params);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.likeLeft("name","五");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("name","王五").or().eq("age",21);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("name","王五").or().eq("age",21)
                .select("id","name","age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAll(){

        List<User> users = this.userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
