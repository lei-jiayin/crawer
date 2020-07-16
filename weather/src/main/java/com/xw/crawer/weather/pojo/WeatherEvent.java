package com.xw.crawer.weather.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class WeatherEvent {
    private Integer id;

    private String eventDate;

    private String eventImg;

    private String title;

    private Date createTime;

    private String description;

    private String url;
}