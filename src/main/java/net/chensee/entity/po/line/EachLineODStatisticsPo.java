package net.chensee.entity.po.line;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.chensee.entity.vo.line.RelationStation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author ah
 * @title: OD分析 每天每条线路每个方向的每个站点的上下车人数及其两辆站点间出行人数
 * @date 2019/12/26 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "od_l_od_statistics")
public class EachLineODStatisticsPo extends LineBasePo{

    private Integer stationNo;

    private String stationName;

    private List<RelationStation> relationStationList;

    private Integer getOnCount;

    private Integer getOffCount;
}



