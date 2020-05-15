package net.chensee.service.impl;

import net.chensee.common.DateUtil;
import net.chensee.common.DoubleHandleUtil;
import net.chensee.common.GCJ02_WGS84Utils;
import net.chensee.dao.GroupDao;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.company.EachCompBasicStatisticsPo;
import net.chensee.entity.po.company.EachCompFivePeakStatisticsPo;
import net.chensee.entity.po.company.EachCompStationConsumeStatisticsPo;
import net.chensee.entity.po.group.AvgStatisticsPo;
import net.chensee.entity.po.group.GroupBasicStatisticsPo;
import net.chensee.entity.po.group.GroupFivePeakStatisticsPo;
import net.chensee.entity.po.group.GroupStationConsumeStatisticsPo;
import net.chensee.entity.vo.LocateInfo;
import net.chensee.entity.vo.company.CompDescByDaysVo;
import net.chensee.entity.vo.group.GroupDescByDayVo;
import net.chensee.entity.vo.group.GroupDescByDaysVo;
import net.chensee.service.BusinessService;
import net.chensee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: GroupServiceImpl
 * @date 2019/12/30 17:36
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private BusinessService businessService;

    @Override
    public ObjectResponse getGroupBasicStatisticsPosByBusiness(Date startTime, Date endTime) {
        List<GroupBasicStatisticsPo> ebsps = groupDao.getGroupBasicStatisticsPosByBusiness(startTime, endTime);
        ConfigProperties configProperties = businessService.getConfigProperties(new Date());
        for (GroupBasicStatisticsPo ebsp : ebsps) {
            Integer total = businessService.getBusRunningNumberTotalByGroup(ebsp.getQueryTime());
            ebsp.setRatio((double) ebsp.getConsumeCount() / (total * configProperties.getRatedNumber()));
        }
        return ObjectResponse.ok(ebsps);
    }

    @Override
    public ObjectResponse getGroupCardTypeConsumeStatisticsPosByBusiness(Date startTime, Date endTime) {
        return ObjectResponse.ok(groupDao.getGroupCardTypeConsumeStatisticsPosByBusiness(startTime, endTime));
    }

    @Override
    public ObjectResponse getGroupFivePeakStatisticsPoByBusiness(Date startTime, Date endTime) {
        List<GroupFivePeakStatisticsPo> gfpspList = groupDao.getGroupFivePeakStatisticsPoByBusiness(startTime, endTime);
        if (gfpspList.size() == 0) {
            return ObjectResponse.ok();
        }
        List<List<Object>> lists = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add("time");
        List<String> nameList = new ArrayList<>();
        for (GroupFivePeakStatisticsPo efpsp : gfpspList) {
            if (!list.contains(efpsp.getQueryTimeStr())) {
                list.add(efpsp.getQueryTimeStr());
            }
            String scopeTimeName = efpsp.getScopeTimeName();
            if (!nameList.contains(scopeTimeName)) {
                nameList.add(scopeTimeName);
            }
        }
        lists.add(list);
        List<Object> list1;
        for (String name : nameList) {
            list1 = new ArrayList<>();
            for (GroupFivePeakStatisticsPo efpsp : gfpspList) {
                String scopeTimeName = efpsp.getScopeTimeName();
                if (!name.equals(scopeTimeName)) {
                    continue;
                }
                if (!list1.contains(scopeTimeName)) {
                    list1.add(scopeTimeName);
                }
                list1.add(efpsp.getConsumeCount());
            }
            lists.add(list1);
        }
        return ObjectResponse.ok(lists);
    }

    @Override
    public ObjectResponse getGroupLineConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        return ObjectResponse.ok(groupDao.getGroupLineConsumeStatisticsPoByBusinessLimit(startTime, endTime));
    }

    @Override
    public ObjectResponse getGroupStationConsumeStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        return ObjectResponse.ok(groupDao.getGroupStationConsumeStatisticsPoByBusinessLimit(startTime, endTime));
    }

    @Override
    public ObjectResponse getGroupStationOffStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        return ObjectResponse.ok(groupDao.getGroupStationOffStatisticsPoByBusinessLimit(startTime, endTime));
    }

    @Override
    public ObjectResponse getGroupStationTransferStatisticsPoByBusinessLimit(Date startTime, Date endTime) {
        return ObjectResponse.ok(groupDao.getGroupStationTransferStatisticsPoByBusinessLimit(startTime, endTime));
    }

    @Override
    public ObjectResponse getGroupStationConsumeStatisticsPoByBusiness(Date startTime, Date endTime) {
        List<GroupStationConsumeStatisticsPo> gscsps = groupDao.getGroupStationConsumeStatisticsPoByBusiness(startTime, endTime);
        for (GroupStationConsumeStatisticsPo gscsp : gscsps) {
            LocateInfo locateInfo = GCJ02_WGS84Utils.wgs84_To_Gcj02(gscsp.getLng(), gscsp.getLat());
            if (locateInfo.isChina()) {
                gscsp.setLat(locateInfo.getLat());
                gscsp.setLng(locateInfo.getLng());
            }
        }
        return ObjectResponse.ok(gscsps);
    }

    @Override
    public ObjectResponse getAvgStatisticsPoByBusiness(Date startTime, Date endTime) {
        List<AvgStatisticsPo> aspbs = groupDao.getAvgStatisticsPoByBusiness(startTime, endTime);
        AvgStatisticsPo aspb = new AvgStatisticsPo();
        aspb.setGetOnStationTotal(0L);
        aspb.setOnTimeTotal(0L);
        aspb.setSuccessCalculateGetOffTotal(0L);
        aspb.setPersonPayRecordCount(0L);
        if (aspbs == null || aspbs.size() == 0) {
            return ObjectResponse.ok(aspb);
        }
        for (AvgStatisticsPo asp : aspbs) {
            aspb.setGetOnStationTotal(aspb.getGetOnStationTotal() + asp.getGetOnStationTotal());
            aspb.setOnTimeTotal(aspb.getOnTimeTotal() + asp.getOnTimeTotal());
            aspb.setSuccessCalculateGetOffTotal(aspb.getSuccessCalculateGetOffTotal() + asp.getSuccessCalculateGetOffTotal());
            aspb.setPersonPayRecordCount(aspb.getPersonPayRecordCount() + asp.getPersonPayRecordCount());
        }
        double avgGetOnStationCount = (double) aspb.getGetOnStationTotal() / aspb.getSuccessCalculateGetOffTotal();
        double avgOnTime = (double) aspb.getOnTimeTotal() / aspb.getSuccessCalculateGetOffTotal();
        double avgRideCount = (double) aspb.getSuccessCalculateGetOffTotal() / aspb.getPersonPayRecordCount();
        aspb.setAvgGetOnStationCount(DoubleHandleUtil.convertTo3Decimal(avgGetOnStationCount));
        aspb.setAvgOnTime(DoubleHandleUtil.convertTo3Decimal(avgOnTime));
        aspb.setAvgRideCount(DoubleHandleUtil.convertTo3Decimal(avgRideCount));
        return ObjectResponse.ok(aspb);
    }

    @Override
    public ObjectResponse getGroupCardTypeConsumeStatisticsPoByDayBusiness(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupCardTypeConsumeStatisticsPoByDayBusiness(queryTime));
    }

    @Override
    public ObjectResponse getGroupFivePeakStatisticsPoByDayBusiness(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupFivePeakStatisticsPoByDayBusiness(queryTime));
    }

    @Override
    public ObjectResponse getGroupLineConsumeStatisticsPoByDayBusiness(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupLineConsumeStatisticsPoByDayBusiness(queryTime));
    }

    @Override
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayBusinessLimit(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupStationConsumeStatisticsPoByDayBusinessLimit(queryTime));
    }

    @Override
    public ObjectResponse getGroupStationOffStatisticsPoByDayBusinessLimit(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupStationOffStatisticsPoByDayBusinessLimit(queryTime));
    }

    @Override
    public ObjectResponse getGroupStationTransferStatisticsPoByDayBusinessLimit(Date queryTime) {
        return ObjectResponse.ok(groupDao.getGroupStationTransferStatisticsPoByDayBusinessLimit(queryTime));
    }

    @Override
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayBusiness(Date queryTime) {
        List<GroupStationConsumeStatisticsPo> gscsps = groupDao.getGroupStationConsumeStatisticsPoByDayBusiness(queryTime);
        for (GroupStationConsumeStatisticsPo gscsp : gscsps) {
            LocateInfo locateInfo = GCJ02_WGS84Utils.wgs84_To_Gcj02(gscsp.getLng(), gscsp.getLat());
            if (locateInfo.isChina()) {
                gscsp.setLat(locateInfo.getLat());
                gscsp.setLng(locateInfo.getLng());
            }
        }
        return ObjectResponse.ok(gscsps);
    }

    @Override
    public ObjectResponse getAvgStatisticsPoByDayBusiness(Date queryTime) {
        return ObjectResponse.ok(groupDao.getAvgStatisticsPoByDayBusiness(queryTime));
    }

    @Override
    public ObjectResponse getGroupLineConsumeStatisticsPoByDayHourBusiness(Date queryTime, Date hourTime) {
        return ObjectResponse.ok(groupDao.getGroupLineConsumeStatisticsPoByDayHourBusiness(queryTime,hourTime));
    }

    @Override
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        return ObjectResponse.ok(groupDao.getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(queryTime,hourTime));
    }

    @Override
    public ObjectResponse getGroupStationOffStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        return ObjectResponse.ok(groupDao.getGroupStationOffStatisticsPoByDayHourBusinessLimit(queryTime,hourTime));
    }

    @Override
    public ObjectResponse getGroupStationTransferStatisticsPoByDayHourBusinessLimit(Date queryTime, Date hourTime) {
        return ObjectResponse.ok(groupDao.getGroupStationTransferStatisticsPoByDayHourBusinessLimit(queryTime,hourTime));
    }

    @Override
    public ObjectResponse getDescByDayBusiness(Date queryTime) {
        GroupDescByDayVo descByDayBusiness = groupDao.getDescByDayBusiness(queryTime);
        ConfigProperties configProperties = businessService.getConfigProperties(new Date());
        Integer total = businessService.getBusRunningNumberTotalByGroup(queryTime);
        descByDayBusiness.setRatio(DoubleHandleUtil.convertTo3Decimal((double) descByDayBusiness.getConsumeCount() / (total * configProperties.getRatedNumber())));
        return ObjectResponse.ok(descByDayBusiness);
    }

    @Override
    public ObjectResponse getDescByDaysBusiness(Date startTime, Date endTime) {
        List<GroupBasicStatisticsPo> gbspList = (List<GroupBasicStatisticsPo>) this.getGroupBasicStatisticsPosByBusiness(startTime, endTime).getData();
        if (gbspList.size() == 0) {
            return ObjectResponse.ok();
        }
        Integer consumeCount = 0;
        Integer transferCount = 0;
        Double ratio = 0.0;
        for (GroupBasicStatisticsPo gbsp : gbspList) {
            consumeCount += gbsp.getConsumeCount();
            transferCount += gbsp.getTransferCount();
            ratio += gbsp.getRatio();
        }
        GroupDescByDaysVo groupDescByDaysVo = new GroupDescByDaysVo();
        groupDescByDaysVo.setStartTime(DateUtil.getNYRDateStr(startTime));
        groupDescByDaysVo.setEndTime(DateUtil.getNYRDateStr(endTime));
        groupDescByDaysVo.setConsumeCount(consumeCount);
        groupDescByDaysVo.setTransferCount(transferCount);
        if (ratio != 0.0) {
            ratio = DoubleHandleUtil.convertTo3Decimal(ratio / gbspList.size());
        }
        groupDescByDaysVo.setRatio(ratio);
        return ObjectResponse.ok(groupDescByDaysVo);
    }
}
