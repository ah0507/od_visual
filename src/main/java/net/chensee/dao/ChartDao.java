package net.chensee.dao;

import net.chensee.entity.po.HolidayDataPo;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.WeatherDayPo;

import java.util.List;

/**
 * @author ah
 * @title: ChartDao
 * @date 2020/3/9 11:05
 */
public interface ChartDao {

    List<HolidayDataPo> getHolidaysData(String startTime, String endTime);

    List<WeatherDayPo> getWeatherDayData(String startTime, String endTime);
}
