package net.chensee.dao;

import net.chensee.entity.po.BaseProcess;
import net.chensee.entity.po.CompanyToLinePo;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.LoggingPo;

import java.util.Date;
import java.util.List;

public interface VisualDao {

    void addConfig(ConfigProperties configProperties);

    List<BaseProcess> getRoots(int pageNum, int pageSize);

    List<BaseProcess> getChildProcesses(String parentName, String createTime);

    List<LoggingPo> getLogs(int pageNum, int pageSize);

    long getLogsCount();

    long getRootsCount();

}
