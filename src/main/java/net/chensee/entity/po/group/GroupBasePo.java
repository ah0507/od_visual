package net.chensee.entity.po.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ah
 * @title: BasePo
 * @date 2019/12/25 11:12
 */
@Data
public class GroupBasePo {

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
