package net.chensee.entity.po.group;

import lombok.Data;

/**
 * @author ah
 * @title: 统计集团每天每条线路的消费人数(线路排名)
 * @date 2019/12/25 11:17
 */
@Data
public class GroupLineConsumeStatisticsPo extends GroupBasePo {

    /**
   * 线路号
     */
    private String lineNo;

    /**
     * 消费人数
     */
    private Integer consumeCount;
}
