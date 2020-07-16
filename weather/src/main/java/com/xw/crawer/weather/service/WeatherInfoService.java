package com.xw.crawer.weather.service;

import com.xw.crawer.weather.dao.WeatherInfoMapper;
import com.xw.crawer.weather.pojo.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 天气信息服务层处理
 *
 * @author adx
 * @date 2020/7/16 11:19
 */
@Service
public class WeatherInfoService {
    @Autowired
    public WeatherInfoMapper weatherInfoMapper;

    public int save(WeatherInfo wi) {
        return weatherInfoMapper.insertSelective(wi);
    }

    public void deleteAll() {
        weatherInfoMapper.deleteAll();
    }

    public WeatherInfo selectFirstWeather() {
        return weatherInfoMapper.selectFirstWeather();
    }

    public void insertWeatherHistory(WeatherInfo weatherInfo) {
        weatherInfoMapper.insertWeatherHistory(weatherInfo);
    }
}
