package com.weigo.mapper;

import com.weigo.pojo.TbUserLogin;
import com.weigo.pojo.TbUserLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserLoginMapper {
    long countByExample(TbUserLoginExample example);

    int deleteByExample(TbUserLoginExample example);

    int insert(TbUserLogin record);

    int insertSelective(TbUserLogin record);

    List<TbUserLogin> selectByExample(TbUserLoginExample example);

    int updateByExampleSelective(@Param("record") TbUserLogin record, @Param("example") TbUserLoginExample example);

    int updateByExample(@Param("record") TbUserLogin record, @Param("example") TbUserLoginExample example);
}