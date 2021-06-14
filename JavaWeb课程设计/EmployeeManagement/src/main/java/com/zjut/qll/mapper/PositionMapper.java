package com.zjut.qll.mapper;

import com.zjut.qll.pojo.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PositionMapper {

    List<Position> queryAllPosition();

    Position queryPositionById(@Param("position_id") int id);

}
