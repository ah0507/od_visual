package net.chensee.entity.po.line;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ah
 * @title: 统计每天每条线路每个方向的换乘次数，拥挤度，消费人数
 * @date 2019/12/26 14:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "od_l_basic_statistics")
public class EachLineBasicStatisticsPo extends LineBasePo{

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
