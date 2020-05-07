package com.weigo.mapper;

import com.weigo.pojo.TbVisitor;
import com.weigo.pojo.TbVisitorExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbVisitorMapper {
    long countByExample(TbVisitorExample example);
    
   List<Map<String,Object>> selectByGroup(TbVisitorExample example);
    
    int deleteByExample(TbVisitorExample example);

    int insert(TbVisitor record);

    int insertSelective(TbVisitor record);

    List<TbVisitor> selectByExample(TbVisitorExample example);

    int updateByExampleSelective(@Param("record") TbVisitor record, @Param("example") TbVisitorExample example);

    int updateByExample(@Param("record") TbVisitor record, @Param("example") TbVisitorExample example);
}