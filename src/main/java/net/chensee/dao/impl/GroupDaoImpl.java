package net.chensee.dao.impl;

import net.chensee.dao.GroupDao;
import net.chensee.entity.po.group.*;
import net.chensee.entity.vo.group.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: GroupDaoImpl
 * @date 2019/12/30 17:36
 */
@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<GroupBasicStatisticsPo> getGroupBasicStatisticsPosByBusiness(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("queryTime")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("transferCount").as("transferCount")
                        .sum("consumeCount").as("consumeCount")
                        .avg("ratio").as("ratio"),
                Aggregation.sort(new Sort(Sort.Direction.ASC, "queryTime"))
                );
        AggregationResults<GroupBasicStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_basic_statistics", GroupBasicStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupCardTypeConsumeStatisticsPo> getGroupCardTypeConsumeStatisticsPosByBusiness(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("cardType")
                        .first("cardType").as("cardType")
                        .sum("consumeCount").as("consumeCount")
        );
        AggregationResults<GroupCardTypeConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_card_type_consume_statistics", GroupCardTypeConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupFivePeakStatisticsPo> getGroupFivePeakStatisticsPoByBusiness(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("queryTime", "scopeTimeName")
                        .first("scopeTimeName").as("scopeTimeName")
                        .first("startTime").as("startTime")
                        .first("endTime").as("endTime")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.ASC, "queryTime","startTime"))
                );
        AggregationResults<GroupFivePeakStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_five_peak_statistics", GroupFivePeakStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupLineConsumeStatisticsPo> getGroupLineConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("lineNo")
                        .first("lineNo").as("lineNo")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupLineConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_line_consume_statistics", GroupLineConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationOffStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .sum("offCount").as("offCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "offCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationTransferStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .sum("transferCount").as("transferCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "transferCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByBusiness(Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().andOperator(
                                Criteria.where("queryTime").gte(startTime),
                                Criteria.where("queryTime").lte(endTime)
                        )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .sum("consumeCount").as("consumeCount")
                        .sum("offCount").as("offCount")
                        .sum("transferCount").as("transferCount")
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<AvgStatisticsPo> getAvgStatisticsPoByBusiness(Date startTime, Date endTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("queryTime").gte(startTime),
                Criteria.where("queryTime").lte(endTime)
        );
        query.addCriteria(criteria);
        return mongoTemplate.find(query, AvgStatisticsPo.class,"od_group_avg_statistics");
    }

    @Override
    public List<GroupCardTypeConsumeStatisticsPo> getGroupCardTypeConsumeStatisticsPoByDayBusiness(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("cardType")
                        .first("cardType").as("cardType")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount")
        );
        AggregationResults<GroupCardTypeConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_card_type_consume_statistics", GroupCardTypeConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupFivePeakStatisticsPo> getGroupFivePeakStatisticsPoByDayBusiness(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("queryTime", "scopeTimeName")
                        .first("scopeTimeName").as("scopeTimeName")
                        .first("startTime").as("startTime")
                        .first("endTime").as("endTime")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.ASC, "startTime"))
                );
        AggregationResults<GroupFivePeakStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_five_peak_statistics", GroupFivePeakStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupLineConsumeStatisticsPo> getGroupLineConsumeStatisticsPoByDayBusiness(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("lineNo")
                        .first("lineNo").as("lineNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)

        );
        AggregationResults<GroupLineConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_line_consume_statistics", GroupLineConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByDayBusinessLimit(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationOffStatisticsPoByDayBusinessLimit(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("offCount").as("offCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "offCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationTransferStatisticsPoByDayBusinessLimit(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("transferCount").as("transferCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "transferCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByDayBusiness(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount")
                        .sum("offCount").as("offCount")
                        .sum("transferCount").as("transferCount")
        );
        AggregationResults<GroupStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", GroupStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public AvgStatisticsPo getAvgStatisticsPoByDayBusiness(Date queryTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("queryTime").is(queryTime);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, AvgStatisticsPo.class,"od_group_avg_statistics");
    }

    @Override
    public List<GroupTimeLineConsumeRankVo> getGroupLineConsumeStatisticsPoByDayHourBusiness(Date queryTime, Date hourTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("currentTime").is(hourTime)),
                Aggregation.group("lineNo")
                        .first("lineNo").as("lineNo")
                        .first("currentTimeStr").as("currentTimeStr")
                        .sum("getOnCount").as("getOnCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "getOnCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupTimeLineConsumeRankVo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_each_time_basic_statistics", GroupTimeLineConsumeRankVo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupTimeStationConsumeRankVo> getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("currentTime").is(hourTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("currentTimeStr").as("currentTimeStr")
                        .sum("getOnCount").as("getOnCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "getOnCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupTimeStationConsumeRankVo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_each_time_basic_statistics", GroupTimeStationConsumeRankVo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupTimeStationOffRankVo> getGroupStationOffStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("currentTime").is(hourTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("currentTimeStr").as("currentTimeStr")
                        .sum("getOffCount").as("getOffCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "getOffCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupTimeStationOffRankVo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_each_time_basic_statistics", GroupTimeStationOffRankVo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<GroupTimeStationTransferRankVo> getGroupStationTransferStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("currentTime").is(hourTime)),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("currentTimeStr").as("currentTimeStr")
                        .sum("transferCount").as("transferCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "transferCount")),
                Aggregation.limit(20)
        );
        AggregationResults<GroupTimeStationTransferRankVo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_each_time_basic_statistics", GroupTimeStationTransferRankVo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public GroupDescByDayVo getDescByDayBusiness(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        new Criteria().and("queryTime").is(queryTime)),
                Aggregation.group("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .sum("transferCount").as("transferCount")
                        .sum("consumeCount").as("consumeCount")
        );
        AggregationResults<GroupDescByDayVo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_basic_statistics", GroupDescByDayVo.class);
        return aggregate.getUniqueMappedResult();
    }
}
