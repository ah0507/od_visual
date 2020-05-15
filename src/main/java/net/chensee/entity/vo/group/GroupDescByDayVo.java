package net.chensee.entity.vo.group;

import lombok.Data;
import net.chensee.entity.vo.line.LineDescBaseVo;

/**
 * @author ah
 * @title: GroupDescByDayVo
 * @date 2020/3/16 10:01
 */
@Data
public class GroupDescByDayVo extends GroupDescBaseVo{

    /**
     * 查询时间
     */
    private String queryTimeStr;

}
