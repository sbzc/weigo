package com.hlxy.mongodb;

import com.hlxy.mongodb.pojo.Comment;
import com.hlxy.mongodb.service.CommentService;
import com.hlxy.mongodb.service.CommentTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = {MongodbApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MongodbApplicationTests {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentTemplateService commentTemplateService;
    @Test
   public  void selectAll() {
        System.out.println("开始测试");
        List<Comment> comments = commentService.selectComment();
        System.out.println();
        System.out.println("数据："+comments);
    }

    @Test
    public void updateComment(){
        Comment comment = new Comment();
        comment.setArticleid("14");
        comment.setContent("我是新增的");
        comment.setCreatedatetime(LocalDateTime.now());
        Comment comment1 = commentService.updateCommnet(comment);
        System.out.println("comment:"+comment1);
    }
   @Test
    public void sele(){
       List<Comment> byUseridAndArticleid = commentService.findByUseridAndArticleid("1003", "1001");
       int size = byUseridAndArticleid.size();
       System.out.println(byUseridAndArticleid);
   }

   @Test
    public void MongoTemplatefindAll(){
       List<Comment> all = commentTemplateService.findAll();
       System.out.println("测试数据"+all);
   }
   @Test
   public void MongoTemplatefindByQuery(){
       List<Comment> byQuery = commentTemplateService.findByQuery();
       System.out.println(byQuery);
   }
    @Test
    public void add(){
       //Comment add = commentTemplateService.add();
       // System.out.println(add);
        commentTemplateService.delectComment();
    }
    @Test
    public void updateCommentTemplate(){
        Comment comment = commentTemplateService.updateComment();

    }
}
