package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DistributionCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface DistributionCodeMapper {
    @Select("select * from distributioncode")
    Collection<DistributionCode> getAllDistributionCodes();
    @Select("select * from distributioncode where ID = #{id} for update")
    DistributionCode getDistributionCodeById(String id);
    @Insert("insert into distributioncode(ID,number)\n" +
            "        values (#{id},#{number})")
    void save(DistributionCode distributionCode);
    @Update("update distributioncode set number = #{number}\n" +
            "        where ID = #{id}")
    void update(DistributionCode distributionCode);
}