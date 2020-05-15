package net.chensee.dao.impl;

import net.chensee.dao.VisualDao;
import net.chensee.entity.po.BaseProcess;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.LoggingPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisualDaoImpl implements VisualDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addConfig(ConfigProperties configProperties) {
        mongoTemplate.save(configProperties);
    }

    @Override
    public List<BaseProcess> getRoots(int pageNum, int pageSize) {
        Query query = new Query(Criteria.where("$where").is("this.taskName == this.taskGroupName"));
        query.skip((pageNum - 1) * pageSize).limit(pageSize);
        return mongoTemplate.find(query, BaseProcess.class);
    }

    @Override
    public List<BaseProcess> getChildProcesses(String parentName, String dataTime) {
        Query query = new Query(Criteria.where("taskGroupName").is(parentName)
                .and("dataTime").is(dataTime)
                .and("taskName").ne(parentName))
                .with(new Sort(Sort.Direction.ASC, "startTime"));
        return mongoTemplate.find(query, BaseProcess.class);
    }

    @Override
    public List<LoggingPo> getLogs(int pageNum, int pageSize) {
        Query query = new Query();
        query.skip((pageNum - 1) * pageSize).limit(pageSize);
        return mongoTemplate.find(query, LoggingPo.class);
    }

    @Override
    public long getLogsCount() {
        return mongoTemplate.count(new Query(), LoggingPo.class);
    }

    @Override
    public long getRootsCount() {
        Query query = new Query(Criteria.where("$where").is("this.taskName == this.taskGroupName"));
        return mongoTemplate.count(query,BaseProcess.class);
    }

}
