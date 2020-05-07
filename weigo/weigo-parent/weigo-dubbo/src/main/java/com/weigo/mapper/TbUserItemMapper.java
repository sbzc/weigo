package com.weigo.mapper;

import com.weigo.pojo.TbUserItem;
import com.weigo.pojo.TbUserItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserItemMapper {
    long countByExample(TbUserItemExample example);

    int deleteByExample(TbUserItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbUserItem record);

    int insertSelective(TbUserItem record);

    List<TbUserItem> selectByExample(TbUserItemExample example);

    TbUserItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbUserItem record, @Param("example") TbUserItemExample example);

    int updateByExample(@Param("record") TbUserItem record, @Param("example") TbUserItemExample example);

    int updateByPrimaryKeySelective(TbUserItem record);

    int updateByPrimaryKey(TbUserItem record);
}