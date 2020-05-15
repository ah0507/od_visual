package net.chensee.entity.po.line;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ah
 * @title: LineBasePo
 * @date 2019/12/26 14:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineBasePo {

    private String lineNo;

    private String direction;

    /**
     * 查询时间
     */
    private Date queryTime;

    private String queryTimeStr;

    /**
     * 创建时间
     */
    private Date createTime;
}
