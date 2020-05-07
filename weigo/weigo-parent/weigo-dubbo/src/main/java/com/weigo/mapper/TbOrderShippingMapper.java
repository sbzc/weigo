package com.weigo.mapper;

import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderShippingMapper {
    long countByExample(TbOrderShippingExample example);

    int deleteByExample(TbOrderShippingExample example);

    int deleteByPrimaryKey(String orderItemId);

    int insert(TbOrderShipping record);

    int insertSelective(TbOrderShipping record);

    List<TbOrderShipping> selectByExample(TbOrderShippingExample example);

    TbOrderShipping selectByPrimaryKey(String orderItemId);

    int updateByExampleSelective(@Param("record") TbOrderShipping record, @Param("example") TbOrderShippingExample example);

    int updateByExample(@Param("record") TbOrderShipping record, @Param("example") TbOrderShippingExample example);

    int updateByPrimaryKeySelective(TbOrderShipping record);

    int updateByPrimaryKey(TbOrderShipping record);
}