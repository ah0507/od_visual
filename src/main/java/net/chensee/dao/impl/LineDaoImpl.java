package net.chensee.dao.impl;

import net.chensee.dao.LineDao;
import net.chensee.entity.po.line.*;
import net.chensee.entity.vo.line.LineDescByDayVo;
import net.chensee.entity.vo.line.LineDescByDaysVo;
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
 * @title: LineDaoImpl
 * @date 2019/12/30 17:36
 */
@Repository
public class LineDaoImpl implements LineDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<EachLineBasicStatisticsPo> getBasicPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "queryTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.andOperator(
                Criteria.where("queryTime").gte(startTime),
                Criteria.where("queryTime").lte(endTime)
        );
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachLineBasicStatisticsPo.class);
    }

    @Override
    public List<EachLineFivePeakStatisticsPo> getFivePeakPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("lineNo").is(lineNo)
                                .and("direction").is(direction)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("queryTime", "scopeTimeName")
                        .first("lineNo").as("lineNo")
                        .first("direction").as("direction")
                        .first("scopeTimeName").as("scopeTimeName")
                        .first("startTime").as("startTime")
                        .first("endTime").as("endTime")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.ASC, "queryTime", "startTime"))
                );
        AggregationResults<EachLineFivePeakStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_five_peak_statistics", EachLineFivePeakStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachLineStationInfoStatisticsPo> getStationInfoPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("lineNo").is(lineNo)
                                .and("direction").is(direction)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group( "stationNo")
                        .first("lineNo").as("lineNo")
                        .first("direction").as("direction")
                        .first("stationNo").as("stationNo")
                        .first("stationName").as("stationName")
                        .first("stationUniqueKey").as("stationUniqueKey")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("getOnCount").as("getOnCount")
                        .sum("getOffCount").as("getOffCount")
                        .sum("peopleCount").as("peopleCount")
                        .sum("transferCount").as("transferCount"),
                Aggregation.sort(new Sort(Sort.Direction.ASC, "stationNo"))
                );
        AggregationResults<EachLineStationInfoStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_l_station_info_statistics", EachLineStationInfoStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachLineFivePeakStatisticsPo> getFivePeakPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "startTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachLineFivePeakStatisticsPo.class);
    }

    @Override
    public List<EachLineStationInfoStatisticsPo> getStationInfoPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "stationNo");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachLineStationInfoStatisticsPo.class);
    }

    @Override
    public List<EachLineODStatisticsPo> getODStatisticsPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "stationNo");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachLineODStatisticsPo.class);
    }

    @Override
    public List<EachLineEachTimeBasicStatisticsPo> getEachTimeBasicPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "currentTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachLineEachTimeBasicStatisticsPo.class);
    }

    @Override
    public LineDescByDayVo getDescByDayBusiness(String lineNo, String direction, Date queryTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("lineNo").is(lineNo);
        criteria.and("direction").is(direction);
        criteria.and("queryTime").is(queryTime);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, LineDescByDayVo.class,"od_l_basic_statistics");
    }

}
