package net.chensee.entity.po;

import lombok.Data;

/**
 * @author ah
 * @title: WeatherDayPo
 * @date 2020/3/9 10:35
 */
@Data
public class WeatherDayPo {

    /**
     * 查询时间
     */
    private String queryTime;

    /**
     * 最高温度
     */
    private String tMax;

    /**
     * 最低温度
     */
    private String tMin;

    /**
     * 天气情况
     */
    private String state;

    /**
     * 风级
     */
    private String wind;

    /**
     * 空气质量
     */
    private String air;
}
