package com.xw.crawer.job.service.impl;

import com.xw.crawer.job.dao.JobInfoDao;
import com.xw.crawer.job.pojo.JobInfo;
import com.xw.crawer.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xw
 * @date 2019-06-23 16:56
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        // 查询原有的数据
        // 根据url 和 发布时间查询数据
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());

         // 判断数据库中是否有已存在的数据
        List<JobInfo> list = this.findJobInfo(param);

        // 如果已存在 执行更新
        if (list.size() == 0){
            jobInfoDao.saveAndFlush(jobInfo);

        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        //设置查询条件
        Example example = Example.of(jobInfo);

        List all = jobInfoDao.findAll(example);
        return all;
    }
}
