package net.chensee.dao;

import net.chensee.entity.po.CompanyToLinePo;
import net.chensee.entity.po.company.*;
import net.chensee.entity.vo.company.CompDescByDayVo;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: CompanyDao
 * @date 2019/12/30 17:36
 */
public interface CompanyDao {

    /**
     * 周/月视图：统计每天每个公司换乘次数，拥挤度，消费次数
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompBasicStatisticsPo> getEachCompBasicStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图：每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompCardTypeConsumeStatisticsPo> getEachCompCardTypeConsumeStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司五个峰值的消费次数
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompFivePeakStatisticsPo> getEachCompFivePeakStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每条线路的消费次数(线路排名)
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompLineConsumeStatisticsPo> getEachCompLineConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    Object getEachCompStationOffStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    Object getEachCompStationTransferStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 日视图：每天每个公司每种卡类型的消费次数的统计（查询每种消费方式和全部消费的占比）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    List<EachCompCardTypeConsumeStatisticsPo> getEachCompCardTypeConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图：统计每天每个公司五个峰值的消费次数
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    List<EachCompFivePeakStatisticsPo> getEachCompFivePeakStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每条线路的消费次数(线路排名)
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    List<EachCompLineConsumeStatisticsPo> getEachCompLineConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）
     * @param deptNo
     * @param queryTime
     * @return
     */
    Object getEachCompStationOffStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）
     * @param deptNo
     * @param queryTime
     * @return
     */
    Object getEachCompStationTransferStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    List<EachCompStationConsumeStatisticsPo> getEachCompStationConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    CompDescByDayVo getDescByDayBusiness(String deptNo, Date queryTime);

    CompanyToLinePo getCompanyLines(String deptNo);

    List<CompanyToLinePo> getCompanys();
}
