package net.chensee.dao;

import net.chensee.entity.po.group.*;
import net.chensee.entity.vo.group.*;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: 集团数据需要合并公司数据
 * @date 2019/12/30 17:36
 */
public interface GroupDao {

    /**
     * 周/月视图：统计集团每天换乘次数，拥挤度，消费次数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupBasicStatisticsPo> getGroupBasicStatisticsPosByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupCardTypeConsumeStatisticsPo> getGroupCardTypeConsumeStatisticsPosByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天五个峰值的消费次数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupFivePeakStatisticsPo> getGroupFivePeakStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每条线路的消费次数(线路排名)
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupLineConsumeStatisticsPo> getGroupLineConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的消费次数（站点消费排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的下车人次（站点下车人次排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationOffStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationTransferStatisticsPoByBusinessLimit(Date startTime, Date endTime);

    /**
     * 周/月视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     *  周/月视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数
     * @param startTime
     * @param endTime
     * @return
     */
    List<AvgStatisticsPo> getAvgStatisticsPoByBusiness(Date startTime, Date endTime);

    /**
     * 日视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param queryTime
     * @return
     */
    List<GroupCardTypeConsumeStatisticsPo> getGroupCardTypeConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图：统计集团每天五个峰值的消费次数
     *
     * @param queryTime
     * @return
     */
    List<GroupFivePeakStatisticsPo> getGroupFivePeakStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每条线路的消费次数(线路排名)
     *
     * @param queryTime
     * @return
     */
    List<GroupLineConsumeStatisticsPo> getGroupLineConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的消费次数（站点消费排名图）
     *
     * @param queryTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的下车次数（站点下车次数排名图）
     *
     * @param queryTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationOffStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param queryTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationTransferStatisticsPoByDayBusinessLimit(Date queryTime);

    /**
     * 日视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param queryTime
     * @return
     */
    List<GroupStationConsumeStatisticsPo> getGroupStationConsumeStatisticsPoByDayBusiness(Date queryTime);

    /**
     *  日视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数
     * @param queryTime
     * @return
     */
    AvgStatisticsPo getAvgStatisticsPoByDayBusiness(Date queryTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每条线路的消费次数(线路排名前20的线路)
     *
     * @param hourTime
     * @param queryTime
     * @return
     */
    List<GroupTimeLineConsumeRankVo> getGroupLineConsumeStatisticsPoByDayHourBusiness(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的消费次数（站点消费排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    List<GroupTimeStationConsumeRankVo> getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的下车人次（站点下车人次排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    List<GroupTimeStationOffRankVo> getGroupStationOffStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    /**
     * 日视图: 统计集团每天每个时间段（1小时）每个站点的换乘次数（站点换乘次数排名图）
     * @param queryTime
     * @param hourTime
     * @return
     */
    List<GroupTimeStationTransferRankVo> getGroupStationTransferStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime);

    GroupDescByDayVo getDescByDayBusiness(Date queryTime);
}
