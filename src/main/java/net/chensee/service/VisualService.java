package net.chensee.service;

import net.chensee.entity.po.BaseProcess;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.LoggingPo;

import java.util.List;

public interface VisualService {

    void addConfig(ConfigProperties configProperties) throws Exception;

    List<BaseProcess> getRoots(int pageNum, int pageSize);

    List<BaseProcess> getChildProcesses(String parentName, String dataTime);

    List<LoggingPo> getLogs(int pageNum, int pageSize);

    long getLogsCount();

    long getRootsCount();

}
