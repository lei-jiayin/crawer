package com.xw.crawer.weather.task;

import com.alibaba.fastjson.JSONObject;
import com.xw.crawer.weather.pojo.WeatherInfo;
import com.xw.crawer.weather.service.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;

/**
 * 历史天气定时器
 *
 * @author adx
 * @date 2020/7/16 17:13
 */
@Component
public class WeatherTask {
    @Autowired
    public WeatherInfoService weatherInfoService;

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 每日12点保存当天的天气历史
     */
    @Scheduled(cron = "0 30 16 * * ?")
    // @Scheduled(cron = "0 0 12 * * ?")
    public void saveWeatherHistory(){
        WeatherInfo weatherInfo = weatherInfoService.selectFirstWeather();
        weatherInfo.setCreateTime(new Date());
        weatherInfoService.insertWeatherHistory(weatherInfo);
        String ms = JSONObject.toJSONString(weatherInfo);
        jmsMessagingTemplate.convertAndSend(queue, ms);
    }
}
