package net.chensee.entity.po.group;

import lombok.Data;

import java.util.Date;

/**
 * @author ah
 * @title: 统计集团每天五个峰值的消费数据
 * @date 2019/12/25 10:35
 */
@Data
public class GroupFivePeakStatisticsPo extends GroupBasePo {


    /**
     * 峰值名称
     */
    private String scopeTimeName;

    /**
     * 消费人数
     */
    private Integer consumeCount;

    private Date startTime;

    private Date endTime;

}
