package com.xw.crawer.weather.task;

import com.xw.crawer.weather.pojo.WeatherEvent;
import com.xw.crawer.weather.pojo.WeatherInfo;
import com.xw.crawer.weather.service.WeatherEventService;
import com.xw.crawer.weather.service.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * 天气数据处理
 *
 * @author adx
 * @date 2020/7/16 11:32
 */
@Component
public class WeatherDataPipline implements Pipeline {
    @Autowired
    public WeatherInfoService weatherInfoService;
    @Autowired
    public WeatherEventService weatherEventService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<WeatherInfo> weatherInfo = resultItems.get("weatherInfos");
        List<WeatherEvent> weatherEvent = resultItems.get("weatherEvents");

        if (weatherEvent != null && weatherInfo != null) {
            for (WeatherEvent we :
                    weatherEvent) {
                weatherEventService.save(we);
            }
            weatherInfoService.deleteAll();
            for (WeatherInfo wi :
                    weatherInfo) {
                weatherInfoService.save(wi);
            }
        }
    }
}
