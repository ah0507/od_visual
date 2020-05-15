package net.chensee.service;

import net.chensee.entity.po.ObjectResponse;

import java.util.Date;

/**
 * @author ah
 * @title: 集团分析图
 * @date 2019/12/30 17:36
 */
public interface GroupService {
    /**
     * 周/月视图：统计每天换乘次数，拥挤度，消费人数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupBasicStatisticsPosByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupCardTypeConsumeStatisticsPosByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天五个峰值的消费次数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupFivePeakStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每条线路的消费次数(线路排名)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupLineConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的消费次数（站点消费排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupStationConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的下车人次（站点下车人次排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupStationOffStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupStationTransferStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getGroupStationConsumeStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     *  周/月视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getAvgStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     * 日视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupCardTypeConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图：统计集团每天五个峰值的消费次数
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupFivePeakStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每条线路的消费次数(线路排名)
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupLineConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的消费次数（站点消费排名图）
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupStationConsumeStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的下车次数（站点下车次数排名图）
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupStationOffStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupStationTransferStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param queryTime
     * @return
     */
    ObjectResponse getGroupStationConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     *  日视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数
     * @param queryTime
     * @return
     */
    ObjectResponse getAvgStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每条线路的消费次数(线路排名前20的线路)
     * @param queryTime
     * @param hourTime
     * @return
     */
    ObjectResponse getGroupLineConsumeStatisticsPoByDayHourBusiness(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的消费次数（站点消费排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    ObjectResponse getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的下车人次（站点下车人次排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    ObjectResponse getGroupStationOffStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的换乘次数（站点换乘次数排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    ObjectResponse getGroupStationTransferStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param queryTime
     * @return
     */
    ObjectResponse getDescByDayBusiness(Date queryTime);

    /**
     * 周视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getDescByDaysBusiness(Date startTime, Date endTime);
}
