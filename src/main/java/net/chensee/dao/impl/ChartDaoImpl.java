package net.chensee.dao.impl;

import net.chensee.dao.ChartDao;
import net.chensee.dao.impl.rowMapper.HolidayDataRowMapper;
import net.chensee.dao.impl.rowMapper.WeatherDayDataRowMapper;
import net.chensee.entity.po.HolidayDataPo;
import net.chensee.entity.po.WeatherDayPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ah
 * @title: ChartDaoImpl
 * @date 2020/3/9 11:05
 */
@Repository
public class ChartDaoImpl implements ChartDao {

    @Autowired
    private JdbcTemplate oracleJdbcTemplate;

    @Override
    public List<HolidayDataPo> getHolidaysData(String startTime, String endTime) {
        String sql = "select DATE_Y,WEEK,IS_WORK_DAY,NOTE from DA_WORKING_CALENDAR where DATE_Y between ? and ?";
        return oracleJdbcTemplate.query(sql, new Object[]{startTime,endTime}, new HolidayDataRowMapper());
    }

    @Override
    public List<WeatherDayPo> getWeatherDayData(String startTime, String endTime) {
        String sql = "SELECT DATE_Y,TMAX,TMIN,STATE,WIND,AIR FROM DA_WEATHER WHERE substr(DATE_Y,0,10) BETWEEN ? AND ?";
        return oracleJdbcTemplate.query(sql, new Object[]{startTime,endTime}, new WeatherDayDataRowMapper());
    }
}
