package net.chensee.entity.po.group;

import lombok.Data;

/**
 * @author ah
 * @title: 统计公司每天每个站点的消费人数（站点消费排名图和热力图）
 * @date 2019/12/25 11:20
 */
@Data
public class GroupStationConsumeStatisticsPo extends GroupBasePo {

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 经度
     */
    private Double lat;

    /**
     * 纬度
     */
    private Double lng;

    /**
     * 消费人数
     */
    private Integer consumeCount;

    /**
     * 下车人数
     */
    private Integer offCount;

    /**
     * 换乘次数
     */
    private Integer transferCount;
}
