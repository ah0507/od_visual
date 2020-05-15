package net.chensee.entity.vo.company;

import lombok.Data;
import net.chensee.entity.vo.line.LineDescBaseVo;

/**
 * @author ah
 * @title: ChartDescVo
 * @date 2020/3/16 10:01
 */
@Data
public class CompDescByDayVo extends CompDescBaseVo{

    /**
     * 查询时间
     */
    private String queryTimeStr;

}
