package net.chensee.dao.impl;

import net.chensee.dao.CompanyDao;
import net.chensee.entity.po.CompanyToLinePo;
import net.chensee.entity.po.company.*;
import net.chensee.entity.vo.company.CompDescByDayVo;
import net.chensee.entity.vo.line.LineDescByDayVo;
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
 * @title: CompanyDaoImpl
 * @date 2019/12/30 17:36
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<EachCompBasicStatisticsPo> getEachCompBasicStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "queryTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.andOperator(
                Criteria.where("queryTime").gte(startTime),
                Criteria.where("queryTime").lte(endTime)
        );
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachCompBasicStatisticsPo.class);
    }

    @Override
    public List<EachCompCardTypeConsumeStatisticsPo> getEachCompCardTypeConsumeStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("cardType")
                        .first("cardType").as("cardType")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount")
        );
        AggregationResults<EachCompCardTypeConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_card_type_consume_statistics", EachCompCardTypeConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachCompFivePeakStatisticsPo> getEachCompFivePeakStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "startTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.andOperator(
                Criteria.where("queryTime").gte(startTime),
                Criteria.where("queryTime").lte(endTime)
        );
        query.addCriteria(criteria);
        query.with(sort);
        return mongoTemplate.find(query, EachCompFivePeakStatisticsPo.class);
    }

    @Override
    public List<EachCompLineConsumeStatisticsPo> getEachCompLineConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("lineNo")
                        .first("lineNo").as("lineNo")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)
        );
        AggregationResults<EachCompLineConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_line_consume_statistics", EachCompLineConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }


    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "consumeCount")),
                Aggregation.limit(20)
        );
        AggregationResults<EachCompStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", EachCompStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationOffStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("offCount").as("offCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "offCount")),
                Aggregation.limit(20)
                );
        AggregationResults<EachCompStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", EachCompStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationTransferStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("transferCount").as("transferCount"),
                Aggregation.sort(new Sort(Sort.Direction.DESC, "transferCount")),
                Aggregation.limit(20)
                );
        AggregationResults<EachCompStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", EachCompStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("deptNo").is(deptNo)
                                .andOperator(
                                        Criteria.where("queryTime").gte(startTime),
                                        Criteria.where("queryTime").lte(endTime)
                                )),
                Aggregation.group("stationName")
                        .first("stationName").as("stationName")
                        .first("lat").as("lat")
                        .first("lng").as("lng")
                        .first("deptNo").as("deptNo")
                        .first("queryTime").as("queryTime")
                        .first("queryTimeStr").as("queryTimeStr")
                        .first("createTime").as("createTime")
                        .sum("consumeCount").as("consumeCount")
                        .sum("offCount").as("offCount")
                        .sum("transferCount").as("transferCount")
        );
        AggregationResults<EachCompStationConsumeStatisticsPo> aggregate = mongoTemplate.aggregate(aggregation, "od_cp_station_consume_statistics", EachCompStationConsumeStatisticsPo.class);
        return aggregate.getMappedResults();
    }

    @Override
    public List<EachCompCardTypeConsumeStatisticsPo> getEachCompCardTypeConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachCompCardTypeConsumeStatisticsPo.class);
    }

    @Override
    public List<EachCompFivePeakStatisticsPo> getEachCompFivePeakStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.ASC, "startTime");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachCompFivePeakStatisticsPo.class);
    }

    @Override
    public List<EachCompLineConsumeStatisticsPo> getEachCompLineConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.DESC, "consumeCount");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        query.limit(20);
        return mongoTemplate.find(query, EachCompLineConsumeStatisticsPo.class);
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.DESC, "consumeCount");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        query.limit(20);
        return mongoTemplate.find(query, EachCompStationConsumeStatisticsPo.class);
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationOffStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.DESC, "offCount");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        query.limit(20);
        return mongoTemplate.find(query, EachCompStationConsumeStatisticsPo.class);
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationTransferStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        Sort sort = new Sort(Sort.Direction.DESC, "transferCount");
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.with(sort);
        query.addCriteria(criteria);
        query.limit(20);
        return mongoTemplate.find(query, EachCompStationConsumeStatisticsPo.class);
    }

    @Override
    public List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, EachCompStationConsumeStatisticsPo.class);
    }

    @Override
    public CompDescByDayVo getDescByDayBusiness(String deptNo, Date queryTime) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("deptNo").is(deptNo);
        criteria.and("queryTime").is(queryTime);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, CompDescByDayVo.class,"od_cp_basic_statistics");
    }

    @Override
    public CompanyToLinePo getCompanyLines(String deptNo) {
        Query query = new Query(Criteria.where("deptNo").is(deptNo));
        return mongoTemplate.findOne(query, CompanyToLinePo.class,"od_company_to_line");
    }

    @Override
    public List<CompanyToLinePo> getCompanys() {
        return mongoTemplate.findAll(CompanyToLinePo.class,"od_company_to_line");
    }

}
