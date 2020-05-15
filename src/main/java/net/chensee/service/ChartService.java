package net.chensee.service;

import net.chensee.entity.po.ObjectResponse;

/**
 * @author ah
 * @title: ChartService
 * @date 2020/3/9 11:02
 */
public interface ChartService {

    ObjectResponse getHolidaysData(String startTime,String endTime);

    ObjectResponse getWeatherDayData(String startTime, String endTime);
}
