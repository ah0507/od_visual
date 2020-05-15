package net.chensee.service.impl;

import net.chensee.dao.BusinessDao;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: BusinessService
 * @date 2020/1/2 11:14
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    public Integer getBusRunningNumberTotalByLine(Date queryTime, String lineNo, String direction) {
        return businessDao.getBusRunningNumberTotalByLine(queryTime,lineNo,direction);
    }

    @Override
    public Integer getBusRunningNumberTotalByComp(Date queryTime, List<String> busNos) {
        return businessDao.getBusRunningNumberTotalByComp(queryTime,busNos);
    }

    @Override
    public Integer getBusRunningNumberTotalByGroup(Date queryTime) {
        return businessDao.getBusRunningNumberTotalByGroup(queryTime);
    }

    @Override
    public ConfigProperties getConfigProperties(Date queryTime) {
        return businessDao.getConfigProperties(queryTime);
    }
}
