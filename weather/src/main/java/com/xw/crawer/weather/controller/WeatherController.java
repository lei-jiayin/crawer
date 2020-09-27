package com.xw.crawer.weather.controller;

import com.alibaba.fastjson.JSONObject;
import com.xw.crawer.weather.pojo.WeatherEvent;
import com.xw.crawer.weather.pojo.WeatherInfo;
import com.xw.crawer.weather.service.WeatherEventService;
import com.xw.crawer.weather.service.WeatherService;
import jdk.nashorn.internal.scripts.JS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 天气消息的提供者
 *
 * @author adv
 * @date 2020/9/27 11:56
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherEventService weatherEventService;

    @Autowired
    private Queue queue;

    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /*@Autowired
    private Topic topic;*/

    /*@RequestMapping("/sendTopic")
    public void sendTopic(){
        //获取天气信息
        WeatherInfo weatherInfoByDate = weatherService.findWeatherInfoByDate();
        //获取当天的事件文章
        Date date = new Date();
        WeatherEvent weatherEvent = weatherEventService.selectByDate(date);

        Map<String, Object> map = new HashMap<>();
        map.put("weatherInfo", weatherInfoByDate);
        map.put("weatherEvent", weatherEvent);
        jmsMessagingTemplate.convertAndSend(topic, map);
    }*/

    @RequestMapping("/send")
    public void send() {
        //获取天气信息
        WeatherInfo weatherInfoByDate = weatherService.findWeatherInfoByDate();
        //获取当天的事件文章
        Date date = new Date();
        // WeatherEvent weatherEvent = weatherEventService.selectByDate(date);

        Map<String, Object> map = new HashMap<>();
        map.put("weatherInfo", weatherInfoByDate);
        // map.put("weatherEvent", weatherEvent);
        String str = JSONObject.toJSONString(map);
        jmsMessagingTemplate.convertAndSend(queue, str);
    }

}
