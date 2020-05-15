package net.chensee.entity.vo.group;

import lombok.Data;
import net.chensee.entity.vo.line.LineDescBaseVo;

/**
 * @author ah
 * @title: GroupDescByDaysVo
 * @date 2020/3/16 10:01
 */
@Data
public class GroupDescByDaysVo extends GroupDescBaseVo {

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
