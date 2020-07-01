package com.hlxy.mongodb.service.impl;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.service.MongoDBTest;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MongoDBTestImpl implements MongoDBTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Comment insert(Comment comment) {
        log.info("简单的新增开始");
        Comment insert = mongoTemplate.insert(comment);
        mongoTemplate.insert(new ArrayList<>(),Comment.class);

        return insert;
    }

    @Override
    public Long removeObj() {
        log.info("removeObj()");
        Comment comment = new Comment();
        comment.setId("1");
        DeleteResult remove = mongoTemplate.remove(comment);
        long deletedCount = remove.getDeletedCount();

        return deletedCount;
    }

    @Override
    public Long removeQuery() {
        log.info("removeQuery");
        Query query = new Query();
        //把userid==100的删除
        Criteria criteria = Criteria.where("userid").is("100");
        query.addCriteria(criteria);
        DeleteResult remove = mongoTemplate.remove(query, Comment.class);
        return remove.getDeletedCount();
    }

    @Override
    public List<Comment> findAll() {
        //Comment.class实体类的class对象
        log.info("findAll");
        List<Comment> all = mongoTemplate.findAll(Comment.class);
        return all;
    }

    @Override
    public Long updateFirstComment() {
        log.info("updateFirstComment");
        Query query = Query.query(Criteria.where("userid").is("1003"));
        Update update = new Update();
        update.set("nickname", "updateFirstComment");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
        System.out.println(updateResult.getMatchedCount());
        return updateResult.getMatchedCount();
    }

    @Override
    public Long updateMultiComment() {
        log.info("updateMultiComment");
        Query query = Query.query(Criteria.where("userid").is("1003"));
        Update update = new Update();
        update.set("nickname", "updateMultiComment");
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Comment.class);
        System.out.println(updateResult.getMatchedCount());
        return updateResult.getMatchedCount();
    }

    @Override
    public List<Comment> findQueryComment() {

        Query query = new Query();
        Criteria criteria = new Criteria();
        //正则
        criteria.and("userid").regex("^.*"+0+".*$");
        query.addCriteria(criteria);
        List<Comment> comments = mongoTemplate.find(query, Comment.class);

        return comments;
    }


}
