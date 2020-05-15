package net.chensee.entity.vo.company;

import lombok.Data;

/**
 * @author ah
 * @title: CompDescBaseVo
 * @date 2020/3/16 10:16
 */
@Data
public class CompDescBaseVo {

    private String deptNo;

    /**
     * 总消费
     */
    private Integer consumeCount;

    /**
     * 总换乘
     */
    private Integer transferCount;

    /**
     * 平均拥挤度
     */
    private Double ratio;
}
