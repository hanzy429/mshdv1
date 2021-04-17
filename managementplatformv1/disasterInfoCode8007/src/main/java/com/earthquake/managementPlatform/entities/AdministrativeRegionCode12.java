package com.earthquake.managementPlatform.entities;

import com.earthquake.managementPlatform.mapper.AdministrativeRegionCode12Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class AdministrativeRegionCode12 implements AdministrativeRegionCode{
    private String province;
    private String city;
    private String country;
    private String town;
    private String village;

    @Resource
    AdministrativeRegionCode12Mapper administrativeRegionCode12Mapper;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public void setAdministrativeRegionCode12(String province, String city, String country, String town, String village) {
        this.province = province;
        this.city = city;
        this.country = country;
        this.town = town;
        this.village = village;
    }

    @Override
    public String codeForAdministrativeRegion() {
        if(province!=null){
            province = administrativeRegionCode12Mapper.getProvinceCode(province);
//            log.info(province);

        }
        else{
            province = "000000000000";
            return province;
        }
        if(city!=null){
            String provinceCode = province +"0000000000";
            city = administrativeRegionCode12Mapper.getCityCode(city,provinceCode);
//            log.info(city);
        }
        else{
            city = "0000000000";
            return province+city;
        }
        if(country!=null){
            String cityCode = city+"00000000";
            country = administrativeRegionCode12Mapper.getCountryCode(country,cityCode);
//            log.info(country);
        }
        else{
            country = "00000000";
            return city+country;
        }
        if(town!=null){
            String countryCode = country+"000000";
            town = administrativeRegionCode12Mapper.getTownCode(town,countryCode);
//            log.info(town);
        }
        else{
            town = "000000";
            return country+town;
        }
        if(village!=null){
            String townCode = town+"000";
            village = administrativeRegionCode12Mapper.getVillageCode(village,townCode);
        }
        else{
            village = "000";
            return town+village;
        }
        return village;
    }


}
