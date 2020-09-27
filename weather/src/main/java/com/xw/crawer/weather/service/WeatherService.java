package com.xw.crawer.weather.service;

import com.xw.crawer.weather.dao.WeatherInfoMapper;
import com.xw.crawer.weather.pojo.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adv
 * @date 2020/9/27 13:36
 */
@Service
public class WeatherService {

    @Autowired
    private WeatherInfoMapper weatherInfoMapper;

    public WeatherInfo findWeatherInfoByDate(){
        return weatherInfoMapper.selectFirstWeather();
    }
}
