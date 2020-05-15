package net.chensee.dao.impl.rowMapper;

import net.chensee.entity.po.HolidayDataPo;
import net.chensee.entity.po.WeatherDayPo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ah
 * @title: WeatherDayDataRowMapper
 * @date 2020/3/9 14:10
 */
public class WeatherDayDataRowMapper implements RowMapper<WeatherDayPo> {
    @Nullable
    @Override
    public WeatherDayPo mapRow(ResultSet resultSet, int i) throws SQLException {
        WeatherDayPo weatherDayPo = new WeatherDayPo();
        weatherDayPo.setQueryTime(resultSet.getString(1));
        weatherDayPo.setTMax(resultSet.getString(2));
        weatherDayPo.setTMin(resultSet.getString(3));
        weatherDayPo.setState(resultSet.getString(4));
        weatherDayPo.setWind(resultSet.getString(5));
        weatherDayPo.setAir(resultSet.getString(6));
        return weatherDayPo;
    }
}