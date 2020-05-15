package net.chensee.entity.vo.line;

import lombok.Data;

/**
 * @author ah
 * @title: ChartDescVo
 * @date 2020/3/16 10:01
 */
@Data
public class LineDescByDayVo  extends LineDescBaseVo{

    /**
     * 查询时间
     */
    private String queryTimeStr;

}
