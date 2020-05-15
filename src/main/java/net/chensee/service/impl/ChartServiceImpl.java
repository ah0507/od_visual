package net.chensee.service.impl;

import net.chensee.dao.ChartDao;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ah
 * @title: ChartServiceImpl
 * @date 2020/3/9 11:03
 */
@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartDao chartDao;

    @Override
    public ObjectResponse getHolidaysData(String startTime, String endTime) {
        return ObjectResponse.ok(chartDao.getHolidaysData(startTime, endTime));
    }

    @Override
    public ObjectResponse getWeatherDayData(String startTime, String endTime) {
        return ObjectResponse.ok(chartDao.getWeatherDayData(startTime, endTime));
    }
}
