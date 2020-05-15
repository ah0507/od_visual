package net.chensee.dao.impl;

import net.chensee.dao.BusinessDao;
import net.chensee.entity.po.ConfigProperties;
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
import java.util.Map;

/**
 * @author ah
 * @title: BusinessService
 * @date 2020/1/2 10:52
 */
@Repository
public class BusinessDaoImpl implements BusinessDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer getBusRunningNumberTotalByLine(Date queryTime, String lineNo, String direction) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("lineNo").is(lineNo)
                                .and("direction").is(direction)),
                Aggregation.group("queryTime","lineNo","direction")
                        .sum("busRunningNumber").as("busRunningNumber")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "od_bus_running_number", Map.class);
        Map result = aggregationResults.getUniqueMappedResult();
        if (result == null) {
            return null;
        }else{
            return (Integer)result.get("busRunningNumber");
        }
    }

    @Override
    public Integer getBusRunningNumberTotalByComp(Date queryTime, List<String> busNos) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)
                                .and("busNo").in(busNos)),
                Aggregation.group("queryTime")
                        .sum("busRunningNumber").as("busRunningNumber")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "od_bus_running_number", Map.class);
        return (Integer) aggregationResults.getUniqueMappedResult().get("busRunningNumber");
    }

    @Override
    public Integer getBusRunningNumberTotalByGroup(Date queryTime) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(
                        Criteria.where("queryTime").is(queryTime)),
                Aggregation.group("queryTime")
                        .sum("busRunningNumber").as("busRunningNumber")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "od_bus_running_number", Map.class);
        return (Integer) aggregationResults.getUniqueMappedResult().get("busRunningNumber");
    }

    @Override
    public ConfigProperties getConfigProperties(Date queryTime) {
        Query query = new Query(Criteria.where("createTime").lte(queryTime))
                .with(new Sort(Sort.Direction.DESC, "createTime"))
                .limit(1);
        ConfigProperties configProperties = mongoTemplate.findOne(query, ConfigProperties.class);
        return configProperties;
    }
}
