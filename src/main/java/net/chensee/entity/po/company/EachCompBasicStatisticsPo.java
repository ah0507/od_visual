package net.chensee.entity.po.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @author ah
 * @title: 统计每天每个公司换乘次数，拥挤度，消费人数
 * @date 2019/12/25 10:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "od_cp_basic_statistics")
public class EachCompBasicStatisticsPo extends CompanyBasePo {

    /**
     * 跑这个公司的车辆号集合
     */
    private Set<String> busNos;

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
