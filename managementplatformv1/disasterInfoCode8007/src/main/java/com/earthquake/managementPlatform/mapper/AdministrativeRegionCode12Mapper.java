package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.Aid;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AdministrativeRegionCode12Mapper {
    @Select("SELECT code_short FROM earthquake.aid2018 WHERE name = #{province}")
    String getProvinceCode(String province);
    @Select("SELECT code_short FROM earthquake.aid2018 where name = #{city} and parent = #{provinceCode}")
    String getCityCode(@Param("city") String city, @Param("provinceCode") String provinceCode);
    @Select("SELECT code_short FROM earthquake.aid2018 where name = #{country} and parent = #{cityCode}")
    String getCountryCode(@Param("country") String country,@Param("cityCode") String cityCode);
    @Select("SELECT code_short FROM earthquake.aid2018 where name = #{town} and parent = #{countryCode}")
    String getTownCode(@Param("town") String town,@Param("countryCode") String countryCode);
    @Select("SELECT code_short FROM earthquake.aid2018 where name = #{village} and parent = #{townCode}")
    String getVillageCode(@Param("village") String village,@Param("townCode") String townCode);
    @Select("SELECT code,name FROM earthquake.aid2018 where level = 'province'")
    List<Aid> getProvinceAid();
    @Select("SELECT code,name FROM earthquake.aid2018 where level = 'city' and parent = #{provinceCode}")
    List<Aid> getCityAid(@Param("provinceCode") String provinceCode);
    @Select("SELECT code,name FROM earthquake.aid2018 where level = 'country' and parent = #{cityCode}")
    List<Aid> getCountryAid(@Param("cityCode") String cityCode);
    @Select("SELECT code,name FROM earthquake.aid2018 where level = 'town' and parent = #{countryCode}")
    List<Aid> getTownAid(@Param("countryCode") String countryCode);
    @Select("SELECT code,name FROM earthquake.aid2018 where level = 'village' and parent = #{townCode}")
    List<Aid> getVillageAid(@Param("townCode") String townCode);
}
