package com.weigo.mapper;

import com.weigo.pojo.TbEvaluate;
import com.weigo.pojo.TbEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbEvaluateMapper {
    long countByExample(TbEvaluateExample example);

    int deleteByExample(TbEvaluateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbEvaluate record);

    int insertSelective(TbEvaluate record);

    List<TbEvaluate> selectByExample(TbEvaluateExample example);

    TbEvaluate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbEvaluate record, @Param("example") TbEvaluateExample example);

    int updateByExample(@Param("record") TbEvaluate record, @Param("example") TbEvaluateExample example);

    int updateByPrimaryKeySelective(TbEvaluate record);

    int updateByPrimaryKey(TbEvaluate record);
}