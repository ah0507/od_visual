package net.chensee.entity.vo.line;

import lombok.Data;

/**
 * @author ah
 * @title: LineDescBaseVo
 * @date 2020/3/16 10:16
 */
@Data
public class LineDescBaseVo {

    private String lineNo;

    private String direction;

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
