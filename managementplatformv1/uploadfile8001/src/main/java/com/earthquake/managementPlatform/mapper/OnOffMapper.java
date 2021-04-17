package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnOffMapper {
    boolean getReadingFiles();

    int StartReadingFiles();

    int StopReadingFiles();

}
