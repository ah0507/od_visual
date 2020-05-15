package net.chensee.service.impl;

import net.chensee.dao.VisualDao;
import net.chensee.entity.po.*;
import net.chensee.service.VisualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisualServiceImpl implements VisualService {

    @Autowired
    private VisualDao visualDao;

    @Override
    public void addConfig(ConfigProperties configProperties) throws Exception{
        visualDao.addConfig(configProperties);
    }

    @Override
    public List<BaseProcess> getRoots(int pageNum, int pageSize) {
        return visualDao.getRoots(pageNum,pageSize);
    }

    @Override
    public List<BaseProcess> getChildProcesses(String parentName, String dataTime) {
        return visualDao.getChildProcesses(parentName,dataTime);
    }

    @Override
    public List<LoggingPo> getLogs(int pageNum, int pageSize) {
        return visualDao.getLogs(pageNum,pageSize);
    }

    @Override
    public long getLogsCount() {
        return visualDao.getLogsCount();
    }

    @Override
    public long getRootsCount() {
        return visualDao.getRootsCount();
    }

}
