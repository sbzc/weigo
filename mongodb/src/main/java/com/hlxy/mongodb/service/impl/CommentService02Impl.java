package com.hlxy.mongodb.service.impl;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.pojo.User;
import com.hlxy.mongodb.service.CommentService02;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CommentService02Impl implements CommentService02 {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Long pushUser() {
        Query q = Query.query(Criteria.where("_id").is("5eec3048efe4502f201b7ea8"));
        User user = new User();
        user.setId("003");
        user.setName("小米");
        Update update = new Update();
        //users是字段名 把user添加到users的集合中内嵌的数据
        update.push("users",user);
        UpdateResult updateResult = mongoTemplate.updateFirst(q, update, Comment.class);
        return updateResult.getMatchedCount();
    }

    @Override
    public Long pullUser() {
        Query q = Query.query(Criteria.where("_id").is("5eec3048efe4502f201b7ea8"));
        User user = new User();
        user.setId("1003");
        Update update = new Update();
        //users是字段名 users中查找匹配的user删除
        update.pull("users",user);
        UpdateResult updateResult = mongoTemplate.updateFirst(q, update, Comment.class);
        return updateResult.getMatchedCount();
    }

    @Override
    public Long updateComment() {
        Query query = Query.query(Criteria.where("_id").is("5eec3048efe4502f201b7ea8"));
        Update update = new Update();
        update.addToSet("code","添加字段");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
        return updateResult.getMatchedCount();
    }

    @Override
    public List<Comment> find() {
        Query query = Query.query(Criteria.where("users.id").is("003"));
        query.fields().exclude("users");
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        return comments;
    }

    @Override
    public List<Comment> advice() {
        log.info("advice");
        System.out.println("advice()");
        return null;
    }

}
