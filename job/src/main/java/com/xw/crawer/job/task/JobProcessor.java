package com.xw.crawer.job.task;

import com.xw.crawer.commons.util.MathSalary;
import com.xw.crawer.commons.util.SubString;
import com.xw.crawer.job.pojo.JobInfo;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

/**
 * 任务管理
 * @author xw
 * @date 2019-06-23 17:29
 */
@Component
public class JobProcessor implements PageProcessor {

    @Autowired
    private SpringDataPipline springDataPipline;

    private String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java,2,1.html?" +
            "lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&" +
            "providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=" +
            "&specialarea=00&from=&welfare=";

    @Override
    public void process(Page page) {
        // 解析页面，获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        //判断获取到的集合是否为空，
        if (list.size() == 0){
            // 如果为空，表示这是招聘详情页,获取招聘详情信息，保存数据
            this.saveJobInfo(page);
        }else {
            //如果不为空，表示这是列表页，解析出详情页的url地址，放到任务队列中
            for (Selectable selectable : list){
                // 获取URL地址
                String jobInfoUrl = selectable.links().toString();
                // 把获取到的URL地址放到任务列表中
                page.addTargetRequest(jobInfoUrl);
                //System.out.println(jobInfoUrl);
            }

            // 获取下一页的URL
            String nextUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            // 放入队列中
            page.addTargetRequest(nextUrl);
            //System.out.println(nextUrl);
        }


        String html = page.getHtml().toString();
//        System.out.println(123);
//        System.out.println(html);
    }

    private void saveJobInfo(Page page) {
        // 创建招聘详情对象
        JobInfo jobInfo = new JobInfo();
        // 解析页面
        Html html = page.getHtml();
        // 封装成对象
        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        jobInfo.setCompanyAddr(SubString.subString(html.css("div.cn p.msg", "text").toString()).get(0));
        jobInfo.setCompanyInfo(html.css("div.tmsg","text").toString().trim());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        jobInfo.setJobAddr((Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).select("p.fp").text()).substring(5));
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());

        // 获取薪资
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());

        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);
        // 获取发布时间
        jobInfo.setTime(SubString.getPubTime(html.css("div.cn p.msg", "text").toString()));
        jobInfo.setCreateTime(new Date());

        //保存数据
        page.putField("jobInfo",jobInfo);
    }

    private Site site = Site.me()
            .setCharset("gbk")
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
     */
    @Scheduled(initialDelay = 1000,fixedDelay = 2*100*1000)
    public void prosess(){

        // 创建下载器
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        // 设置免费代理
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("124.205.155.152",9090)));

        Spider.create(new JobProcessor())
                .addUrl(url)
//                .setDownloader(httpClientDownloader)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100*1000)))
                .thread(20)
                .addPipeline(springDataPipline)
                .run();
    }
}
