package com.xw.crawer.job.task;


import com.xw.crawer.job.pojo.JobInfo;
import com.xw.crawer.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 处理数据 保存到数据库
 * @author xw
 * @date 2019/6/24 14:37
 */
@Component
public class SpringDataPipline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null){
          jobInfoService.save(jobInfo);
        }
    }
}
