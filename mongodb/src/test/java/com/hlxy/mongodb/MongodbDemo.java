package com.hlxy.mongodb;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.pojo.User;
import com.hlxy.mongodb.service.MongoDBTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbDemo {
    @Autowired
    private MongoDBTest mongoDBTest;

    @Test
    public void add(){
        Comment comment = new Comment();
        comment.setId("1");
        comment.setUserid("100");
        comment.setContent("我是简单的增加");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setLikenum(10);
        comment.setParentid("1003");
        comment.setArticleid("100");
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("1");
        user.setName("小明");
        users.add(user);
        comment.setUsers(users);
        Comment insert = mongoDBTest.insert(comment);
        System.out.println(insert);
    }
    @Test
    public void removeObj(){
        Long aLong = mongoDBTest.removeObj();
        System.out.println(aLong);
    }
    @Test
    public void  removeQuery(){
        Long aLong = mongoDBTest.removeQuery();
        System.out.println(aLong);
    }

    @Test
    public void findAll(){
        List<Comment> all = mongoDBTest.findAll();
        System.out.println(all);
    }
    @Test
    public void updateFirstComment(){
         mongoDBTest.updateFirstComment();

    }
    @Test
    public void updateMultiComment(){
        Long aLong = mongoDBTest.updateMultiComment();
        System.out.println(aLong);
    }
    @Test
    public void findQueryComment(){
        List<Comment> queryComment = mongoDBTest.findQueryComment();
        System.out.println(queryComment);
    }
}
