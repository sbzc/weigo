package com.hlxy.mongodb.service.impl;

import com.hlxy.mongodb.dao.CommentRepository;
import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> selectComment() {
        Sort sort = new Sort(Sort.Direction.DESC, "userid");
        Comment comment = new Comment();
        comment.setUserid("1005");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("userid", ExampleMatcher.GenericPropertyMatchers.contains());

      Example<Comment> example = Example.of(comment, matcher);
        List<Comment> all = commentRepository.findAll(example,sort);
        log.info("all={}",all);
        return all;
    }

    @Override
    public Comment updateCommnet(Comment comment) {

        Comment save = commentRepository.save(comment);

        return save;
    }

    @Override
    public List<Comment> findByUseridAndArticleid(String userid, String articleid) {
        List<Comment> byUseridAndParentid = commentRepository.findByUseridAndArticleid(userid, articleid);

        return byUseridAndParentid;
    }

}
