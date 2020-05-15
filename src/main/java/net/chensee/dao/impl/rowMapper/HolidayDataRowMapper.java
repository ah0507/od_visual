package net.chensee.dao.impl.rowMapper;

import net.chensee.entity.po.HolidayDataPo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author ah
 * @title: HolidayDataRowMapper
 * @date 2020/3/9 11:16
 */
public class HolidayDataRowMapper implements RowMapper<HolidayDataPo> {
    @Nullable
    @Override
    public HolidayDataPo mapRow(ResultSet resultSet, int i) throws SQLException {
        HolidayDataPo holidayDataPo = new HolidayDataPo();
        holidayDataPo.setQueryTime(resultSet.getString(1));
        holidayDataPo.setWeek(resultSet.getInt(2));
        holidayDataPo.setIsWorkDay(resultSet.getInt(3));
        holidayDataPo.setNote(resultSet.getString(4));
        return holidayDataPo;
    }
}