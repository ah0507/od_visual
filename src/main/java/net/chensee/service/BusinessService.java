package net.chensee.service;

import net.chensee.entity.po.ConfigProperties;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: BusinessService
 * @date 2020/1/2 11:14
 */
public interface BusinessService {


    /**
     * 获得线路的总运行次数
     * @param queryTime
     * @param lineNo
     * @param direction
     * @return
     */
    Integer getBusRunningNumberTotalByLine(Date queryTime, String lineNo, String direction);

    /**
     * 获得公司的总运行次数
     * @param queryTime
     * @param busNos
     * @return
     */
    Integer getBusRunningNumberTotalByComp(Date queryTime, List<String> busNos);

    /**
     * 获得集团的总运行次数
     * @param queryTime
     * @return
     */
    Integer getBusRunningNumberTotalByGroup(Date queryTime);

    /**
     * 得到配置表
     * @param queryTime
     * @return
     */
    ConfigProperties getConfigProperties(Date queryTime);


}
