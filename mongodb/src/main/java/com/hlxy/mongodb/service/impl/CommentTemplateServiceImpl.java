package com.hlxy.mongodb.service.impl;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.pojo.User;
import com.hlxy.mongodb.service.CommentTemplateService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CommentTemplateServiceImpl implements CommentTemplateService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Comment> findAll() {

        Query query = new Query();
        query.with(PageRequest.of(1,2));
        Sort sort = new Sort(Sort.Direction.DESC, "_id");
        query.with(sort);
        List<Comment> all = mongoTemplate.find(query,Comment.class);

         log.info("MongoTemplate数据：",all);
        return all;
    }

    @Override
    public List<Comment> findByQuery() {

        //Query query = Query.query(Criteria.where("users.id").regex("^.*"+0+".*$"));
        Criteria criteria1 = new Criteria();
        criteria1.and("users.id").is("001");
        Criteria criteria2 = new Criteria();
        criteria2.and("userid").is("1003");
        Criteria criteria = new Criteria();
        criteria.orOperator(criteria1,criteria2);
        Query query = new Query();
        query.addCriteria(criteria);

        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        log.info("findByQuerys数据",comments);
        return comments;
    }

    @Override
    public Comment add() {
        Query query = Query.query(Criteria.where("_id").is("5eec3048efe4502f201b7ea8"));
        User user = new User();
        user.setName("pull");
        user.setId("002");
        Update update = new Update();
        update.push("users",user);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
       // String s = updateResult.getUpsertedId().toString();
        System.out.println(updateResult.getMatchedCount());
        return null;
    }

    @Override
    public Comment updateComment() {
        Query query = Query.query(Criteria.where("users.id").is("001"));
        Update update = new Update();
        update.set("users.$.name","数据改了");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
        BsonValue upsertedId = updateResult.getUpsertedId();
        System.out.println("更新的id是："+upsertedId);
        return null;
    }

    @Override
    public long delectComment() {
        Query query = Query.query(Criteria.where("_id").is("5eec3048efe4502f201b7ea8"));
        User user = new User();
        user.setId("002");
        Update update = new Update();
        update.pull("users",user);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
        // String s = updateResult.getUpsertedId().toString();
        System.out.println(updateResult.getMatchedCount());
        return 0;
    }
}
