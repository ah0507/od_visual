package net.chensee.service;

import net.chensee.entity.po.ObjectResponse;

import java.util.Date;

/**
 * @author ah
 * @title: 线路分析图
 * @date 2019/12/30 17:36
 */
public interface LineService {

    /**
     * 周/月视图：查询单条线路一定时间范围内的换乘次数，拥挤度，消费次数
     *
     * @param lineNo
     * @param direction
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getBasicPosByBusiness(String lineNo, String direction, Date startTime, Date endTime);

    /**
     * 周/月视图：查询单条线路一定时间范围内的五个峰值的消费次数
     *
     * @param lineNo
     * @param direction
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getFivePeakPosByBusiness(String lineNo, String direction, Date startTime, Date endTime);

    /**
     *  周/月视图：统计单条线一定时间范围内各站点的上下车、在车上、换乘次数
     * @param lineNo
     * @param direction
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getStationInfoPosByBusiness(String lineNo, String direction, Date startTime, Date endTime);

    /**
     * 日视图：查询单日单条线的五个峰值的消费次数
     *
     * @param lineNo
     * @param direction
     * @param queryTime
     * @return
     */
    ObjectResponse getFivePeakPosByDayBusiness(String lineNo, String direction, Date queryTime);

    /**
     *  日视图：查询单日单条线各站点每个站点的上下车、在车上、换乘次数
     * @param lineNo
     * @param direction
     * @param queryTime
     * @return
     */
    ObjectResponse getStationInfoPosByDayBusiness(String lineNo, String direction, Date queryTime);

    /**
     * 日视图：OD分析 每天每条线路每个方向的每个站点的上下车消费次数及其两辆站点间出行人数
     * @param lineNo
     * @param direction
     * @param queryTime
     * @return
     */
    ObjectResponse getODStatisticsPosByDayBusiness(String lineNo, String direction, Date queryTime);

    /**
     * 日视图: 统计每天每个时间段（每隔一小时）每条线路每个方向的上车消费次数、下车人次、换乘次数
     * @param lineNo
     * @param direction
     * @param queryTime
     * @return
     */
    ObjectResponse getEachTimeBasicPosByDayBusiness(String lineNo, String direction, Date queryTime);

    /**
     * 日视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param lineNo
     * @param direction
     * @param queryTime
     * @return
     */
    ObjectResponse getDescByDayBusiness(String lineNo, String direction, Date queryTime);

    /**
     * 日视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param lineNo
     * @param direction
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getDescByDaysBusiness(String lineNo, String direction, Date startTime, Date endTime);

    ObjectResponse getLineList();
}
