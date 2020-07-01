package com.hlxy.mongodb.dao;

import com.hlxy.mongodb.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment,String> {

    Page<Comment> findByParentid(String parentid, Pageable pageable);

    List<Comment> findByUseridAndArticleid(String userid, String articleid);
}
