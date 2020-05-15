package net.chensee.dao;

import net.chensee.entity.po.ConfigProperties;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: BusinessService
 * @date 2020/1/2 10:52
 */
public interface BusinessDao {

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
     * 获得配置表
     * @param queryTime
     * @return
     */
    ConfigProperties getConfigProperties(Date queryTime);
}
