package net.chensee.entity.vo.group;

import lombok.Data;

/**
 * @author ah
 * @title:
 * @date 2020/1/17 13:15
 */
@Data
public class GroupTimeStationConsumeRankVo {

    private String stationName;

    private String currentTimeStr;

    private Integer getOnCount;
}
