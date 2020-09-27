package com.xw.crawer.weather.service;

import com.xw.crawer.weather.dao.WeatherEventMapper;
import com.xw.crawer.weather.pojo.WeatherEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * 天气大事件服务层
 *
 * @author adx
 * @date 2020/7/16 11:20
 */
@Service
public class WeatherEventService {
    @Autowired
    public WeatherEventMapper weatherEventMapper;
    public int save(WeatherEvent we) {
        WeatherEvent wee = new WeatherEvent();
        wee.setEventDate(we.getEventDate());
        List<WeatherEvent> weee = weatherEventMapper.selectByWeatherEvent(wee);
        if (weee == null || weee.size() <= 0){
            return weatherEventMapper.insertSelective(we);
        }else {
            we.setId(weee.get(0).getId());
            return weatherEventMapper.updateByPrimaryKeySelective(we);
        }
        //weatherEventMapper.deleteAll();
        //return weatherEventMapper.insertSelective(we);
    }

    public WeatherEvent selectByDate(Date date) {
        //将日期 改为 2020年9月27日
        String formatDate = DateFormat.getDateInstance(DateFormat.LONG).format(date);

        return weatherEventMapper.selectByDate(formatDate);
    }

    public static void main(String[] args) {
        Date date = new Date();
        String formatDate = DateFormat.getDateInstance(DateFormat.LONG).format(date);
        System.out.println(formatDate);
    }
}
