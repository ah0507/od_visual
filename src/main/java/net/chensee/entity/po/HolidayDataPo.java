package net.chensee.entity.po;

import lombok.Data;

/**
 * @author ah
 * @title: HolidayData
 * @date 2020/3/9 10:26
 */
@Data
public class HolidayDataPo {

    /**
     * 当天日期
     */
    private String queryTime;

    /**
     * 是否是工作日
     */
    private Integer isWorkDay;

    /**
     * 星期几
     */
    private Integer week;

    /**
     * 日期详细
     */
    private String note;

}
