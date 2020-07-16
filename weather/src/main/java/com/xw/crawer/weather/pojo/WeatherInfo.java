package com.xw.crawer.weather.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class WeatherInfo {
    private String id;

    private String weatherDate;

    private String weatherName;

    private Integer tMax;

    private Integer tMin;

    private String winDirection;

    private String winLevel;

    private String url;

    private Date createTime;
}