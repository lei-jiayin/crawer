package com.xw.crawer.weather.task;

import com.xw.crawer.commons.util.SnowFlake;
import com.xw.crawer.weather.pojo.WeatherEvent;
import com.xw.crawer.weather.pojo.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 爬取天气页面处理器
 *
 * @author adx
 * @date 2020/7/16 11:25
 */
@Component
public class WeatherProcessor implements PageProcessor {

    @Autowired
    public WeatherDataPipline weatherDataPipline;

    private final String url = "http://www.weather.com.cn/weather/101200101.shtml";

    @Override
    public void process(Page page) {
        // 解析页面 获取天气信息详情
        List<Selectable> list = page.getHtml().css("ul.t li.sky").nodes();
        List<Selectable> eventList = page.getHtml().css(".greatEvent ul li").nodes();
        if (list != null && list.size() > 0){
            saveWeatherInfo(page,list);
        }
        if (eventList != null && eventList.size() > 0){
            saveWeatherEvent(page,eventList);
        }
    }

    private void saveWeatherEvent(Page page,List<Selectable> list) {
        List<WeatherEvent> events = new ArrayList<>();
        Year now = Year.now();
        for (Selectable s:list) {
            WeatherEvent weatherEvent = new WeatherEvent();
            weatherEvent.setEventImg(s.css("img", "src").toString());
            weatherEvent.setDescription(s.css("div.article-box div p","text").toString());
            weatherEvent.setTitle(s.css("div.article-box div h2","text").toString());
            weatherEvent.setEventDate(now.toString() +"年"+ s.css("p.time","text").toString().trim());
            weatherEvent.setCreateTime(new Date());
            weatherEvent.setUrl(s.css("a","href").toString());
            events.add(weatherEvent);
        }
        page.putField("weatherEvents", events);
    }

    private void saveWeatherInfo(Page page, List<Selectable> list) {
        SnowFlake snowFlake = new SnowFlake(1,1);
        List<WeatherInfo> infos = new ArrayList<>();
        for (Selectable s: list) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setId(Long.toString(snowFlake.nextId()));
            weatherInfo.setWeatherDate(s.css("h1","text").toString().substring(0, s.css("h1","text").toString().length() - 4));
            weatherInfo.setWeatherName(s.css("p.wea","text").toString());
            weatherInfo.setTMax(Integer.valueOf(s.css("p.tem span","text").toString()));
            weatherInfo.setTMin(Integer.valueOf(s.css("p.tem i","text").toString().substring(0,s.css("p.tem i","text").toString().length() - 1)));
            List<Selectable> wins = s.css("p.win em span").nodes();
            weatherInfo.setWinDirection(wins.get(0).css("span","title") + "," + wins.get(1).css("span", "title").toString());
            weatherInfo.setWinLevel(s.css("p.win i","text").toString().replaceAll("&lt;","<").replaceAll("&gt;",">"));
            weatherInfo.setCreateTime(new Date());
            weatherInfo.setUrl(page.getUrl().toString());
            //weatherInfo.setWinDirection(s.css("p.win").css("em span").css(""));
            infos.add(weatherInfo);
        }
        page.putField("weatherInfos", infos);
    }
    private Site site = Site.me()
            .setCharset("utf8")
            .setTimeOut(100*10000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 推荐使用cron表达式管理定时任务
     * initialDelay 当任务启动时，等多久执行方法
     * fixedDelay每隔多久执行方法
     * 每天11点拉取天气数据
     */
    @Scheduled(cron = "0 0 11 * * ?")
    public void prosess(){

        // 创建下载器
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        // 设置免费代理
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("124.205.155.152",9090)));

        Spider.create(new WeatherProcessor())
                .addUrl(url)
//                .setDownloader(httpClientDownloader)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100*1000)))
                .thread(1)
                .addPipeline(weatherDataPipline)
                .run();
    }
}
