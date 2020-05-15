package net.chensee.service;

import net.chensee.entity.po.ObjectResponse;

import java.util.Date;

/**
 * @author ah
 * @title: 公司分析图
 * @date 2019/12/30 17:36
 */
public interface CompanyService {

    /**
     * 周/月视图：统计每天每个公司换乘次数，拥挤度，消费人数
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompBasicStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图：每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompCardTypeConsumeStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司五个峰值的消费次数
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompFivePeakStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每条线路的消费次数(线路排名)
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompLineConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompStationConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);


    /**
     * 周/月视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompStationOffStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompStationTransferStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime);

    /**
     * 周/月视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getEachCompStationConsumeStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime);

    /**
     * 日视图：每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getEachCompCardTypeConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图：统计每天每个公司五个峰值的消费次数
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getEachCompFivePeakStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每条线路的消费次数(线路排名)
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getEachCompLineConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime);

    /**
     * 日视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）
     * @param deptNo
     * @param nyrDateByStr
     * @return
     */
    ObjectResponse getEachCompStationOffStatisticsPoByDayBusinessLimit(String deptNo, Date nyrDateByStr);

    /**
     * 日视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）
     * @param deptNo
     * @param nyrDateByStr
     * @return
     */
    ObjectResponse getEachCompStationTransferStatisticsPoByDayBusinessLimit(String deptNo, Date nyrDateByStr);

    /**
     * 日视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）
     *
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param deptNo
     * @param queryTime
     * @return
     */
    ObjectResponse getDescByDayBusiness(String deptNo, Date queryTime);

    /**
     * 日视图: 统计文字描述（换乘次数，消费次数,拥挤度）
     * @param deptNo
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getDescByDaysBusiness(String deptNo,Date startTime, Date endTime);

    /**
     * 通过公司得到该公司所有线路
     * @param deptNos
     * @return
     */
    ObjectResponse getCompanyLines(String deptNos);

    /**
     * 获得所有的公司
     * @return
     */
    ObjectResponse getCompanys();

}
