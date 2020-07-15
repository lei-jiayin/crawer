package com.xw.crawer.job.dao;


import com.xw.crawer.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoDao extends JpaRepository<JobInfo,Long> {



}
