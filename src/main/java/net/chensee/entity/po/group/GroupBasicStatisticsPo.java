package net.chensee.entity.po.group;

import lombok.Data;

/**
 * @author ah
 * @title: 统计集团每天换乘次数，拥挤度，消费人数
 * @date 2019/12/25 10:58
 */
@Data
public class GroupBasicStatisticsPo extends GroupBasePo {

    /**
     * 拥挤度：消费次数/（运行次数*车数*每辆车的额定人数）
     */
    private Double ratio;

    /**
     * 换乘次数
     */
    private Integer transferCount;

    /**
     * 消费人数
     */
    private Integer consumeCount;

}
