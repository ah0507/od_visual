package net.chensee.entity.po.group;

import lombok.Data;

/**
 * @author ah
 * @title: 集团每天每种卡类型的消费人数的统计（ 查询每种消费方式和全部消费的占比）
 * @date 2019/12/25 11:08
 */
@Data
public class GroupCardTypeConsumeStatisticsPo extends GroupBasePo {

    /**
     * 卡类型
     */
    private String cardType;

    /**
     * 消费人数
     */
    private Integer consumeCount;

}
