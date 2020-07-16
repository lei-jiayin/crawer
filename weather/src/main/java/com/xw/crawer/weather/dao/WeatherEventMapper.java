package com.xw.crawer.weather.dao;

import com.xw.crawer.weather.pojo.WeatherEvent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherEventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeatherEvent record);

    int insertSelective(WeatherEvent record);

    WeatherEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeatherEvent record);

    int updateByPrimaryKey(WeatherEvent record);

    List<WeatherEvent> selectByWeatherEvent(WeatherEvent wee);

    void deleteAll();
}