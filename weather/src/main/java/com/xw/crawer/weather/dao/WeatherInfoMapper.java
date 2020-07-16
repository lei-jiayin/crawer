package com.xw.crawer.weather.dao;

import com.xw.crawer.weather.pojo.WeatherInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(WeatherInfo record);

    int insertSelective(WeatherInfo record);

    WeatherInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WeatherInfo record);

    int updateByPrimaryKey(WeatherInfo record);

    void deleteAll();

    WeatherInfo selectFirstWeather();

    void insertWeatherHistory(WeatherInfo weatherInfo);
}