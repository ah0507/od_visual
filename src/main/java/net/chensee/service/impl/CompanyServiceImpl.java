package net.chensee.service.impl;

import net.chensee.common.DateUtil;
import net.chensee.common.DoubleHandleUtil;
import net.chensee.common.GCJ02_WGS84Utils;
import net.chensee.dao.CompanyDao;
import net.chensee.entity.po.CompanyToLinePo;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.company.EachCompBasicStatisticsPo;
import net.chensee.entity.po.company.EachCompFivePeakStatisticsPo;
import net.chensee.entity.po.company.EachCompStationConsumeStatisticsPo;
import net.chensee.entity.po.line.EachLineBasicStatisticsPo;
import net.chensee.entity.vo.LocateInfo;
import net.chensee.entity.vo.company.CompDescByDaysVo;
import net.chensee.entity.vo.line.LineDescByDaysVo;
import net.chensee.service.BusinessService;
import net.chensee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ah
 * @title: CompanyServiceImpl
 * @date 2019/12/30 17:36
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private BusinessService businessService;

    @Override
    public ObjectResponse getEachCompBasicStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime) {
        List<EachCompBasicStatisticsPo> eachCompBasicStatisticsPosByBusiness = companyDao.getEachCompBasicStatisticsPosByBusiness(deptNo, startTime, endTime);
        ConfigProperties configProperties = businessService.getConfigProperties(new Date());
        for (EachCompBasicStatisticsPo ecbsp : eachCompBasicStatisticsPosByBusiness) {
            Set<String> busNos = ecbsp.getBusNos();
            Integer total = businessService.getBusRunningNumberTotalByComp(ecbsp.getQueryTime(), new ArrayList<>(busNos));
            ecbsp.setRatio((double) ecbsp.getConsumeCount() / (total * configProperties.getRatedNumber()));
        }
        return ObjectResponse.ok(eachCompBasicStatisticsPosByBusiness);
    }

    @Override
    public ObjectResponse getEachCompCardTypeConsumeStatisticsPosByBusiness(String deptNo, Date startTime, Date endTime) {
        return ObjectResponse.ok(companyDao.getEachCompCardTypeConsumeStatisticsPosByBusiness(deptNo, startTime, endTime));
    }

    @Override
    public ObjectResponse getEachCompFivePeakStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime) {
        List<EachCompFivePeakStatisticsPo> efpspList = companyDao.getEachCompFivePeakStatisticsPoByBusiness(deptNo, startTime, endTime);
        if (efpspList.size() == 0) {
            return ObjectResponse.ok();
        }
        List<List<Object>> lists = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add("time");
        List<String> nameList = new ArrayList<>();
        for (EachCompFivePeakStatisticsPo efpsp : efpspList) {
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
            for (EachCompFivePeakStatisticsPo efpsp : efpspList) {
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
    public ObjectResponse getEachCompLineConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        return ObjectResponse.ok(companyDao.getEachCompLineConsumeStatisticsPoByBusinessLimit(deptNo, startTime, endTime));
    }

    @Override
    public ObjectResponse getEachCompStationConsumeStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationConsumeStatisticsPoByBusinessLimit(deptNo, startTime, endTime));
    }

    @Override
    public ObjectResponse getEachCompStationOffStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationOffStatisticsPoByBusinessLimit(deptNo, startTime, endTime));
    }

    @Override
    public ObjectResponse getEachCompStationTransferStatisticsPoByBusinessLimit(String deptNo, Date startTime, Date endTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationTransferStatisticsPoByBusinessLimit(deptNo, startTime, endTime));
    }

    @Override
    public ObjectResponse getEachCompStationConsumeStatisticsPoByBusiness(String deptNo, Date startTime, Date endTime) {
        List<EachCompStationConsumeStatisticsPo> ecscsps = companyDao.getEachCompStationConsumeStatisticsPoByBusiness(deptNo, startTime, endTime);
        for (EachCompStationConsumeStatisticsPo ecscp : ecscsps) {
            LocateInfo locateInfo = GCJ02_WGS84Utils.wgs84_To_Gcj02(ecscp.getLng(), ecscp.getLat());
            if (locateInfo.isChina()) {
                ecscp.setLat(locateInfo.getLat());
                ecscp.setLng(locateInfo.getLng());
            }
        }
        return ObjectResponse.ok(ecscsps);
    }

    @Override
    public ObjectResponse getEachCompCardTypeConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompCardTypeConsumeStatisticsPoByDayBusiness(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompFivePeakStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompFivePeakStatisticsPoByDayBusiness(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompLineConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompLineConsumeStatisticsPoByDayBusiness(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationConsumeStatisticsPoByDayBusinessLimit(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompStationOffStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationOffStatisticsPoByDayBusinessLimit(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompStationTransferStatisticsPoByDayBusinessLimit(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getEachCompStationTransferStatisticsPoByDayBusinessLimit(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusiness(String deptNo, Date queryTime) {
        List<EachCompStationConsumeStatisticsPo> ecscps = companyDao.getEachCompStationConsumeStatisticsPoByDayBusiness(deptNo, queryTime);
        for (EachCompStationConsumeStatisticsPo ecscp : ecscps) {
            LocateInfo locateInfo = GCJ02_WGS84Utils.wgs84_To_Gcj02(ecscp.getLng(), ecscp.getLat());
            if (locateInfo.isChina()) {
                ecscp.setLat(locateInfo.getLat());
                ecscp.setLng(locateInfo.getLng());
            }
        }
        return ObjectResponse.ok(ecscps);
    }

    @Override
    public ObjectResponse getDescByDayBusiness(String deptNo, Date queryTime) {
        return ObjectResponse.ok(companyDao.getDescByDayBusiness(deptNo, queryTime));
    }

    @Override
    public ObjectResponse getDescByDaysBusiness(String deptNo, Date startTime, Date endTime) {
        List<EachCompBasicStatisticsPo> ecbspList = companyDao.getEachCompBasicStatisticsPosByBusiness(deptNo, startTime, endTime);
        if (ecbspList.size() == 0) {
            return ObjectResponse.ok();
        }
        Integer consumeCount = 0;
        Integer transferCount = 0;
        Double ratio = 0.0;
        for (EachCompBasicStatisticsPo ecbsp : ecbspList) {
            consumeCount += ecbsp.getConsumeCount();
            transferCount += ecbsp.getTransferCount();
            ratio += ecbsp.getRatio();
        }
        CompDescByDaysVo compDescByDaysVo = new CompDescByDaysVo();
        compDescByDaysVo.setStartTime(DateUtil.getNYRDateStr(startTime));
        compDescByDaysVo.setEndTime(DateUtil.getNYRDateStr(endTime));
        compDescByDaysVo.setDeptNo(deptNo);
        compDescByDaysVo.setConsumeCount(consumeCount);
        compDescByDaysVo.setTransferCount(transferCount);
        if (ratio != 0.0) {
            ratio = DoubleHandleUtil.convertTo3Decimal(ratio / ecbspList.size());
        }
        compDescByDaysVo.setRatio(ratio);
        return ObjectResponse.ok(compDescByDaysVo);
    }

    @Override
    public ObjectResponse getCompanyLines(String deptNos) {
        String[] depts = deptNos.split(",");
        Set<Integer> set = new HashSet<>();
        for (String deptNo : depts) {
            CompanyToLinePo companyToLinePo = companyDao.getCompanyLines(deptNo);
            List<String> lineNos = companyToLinePo.getLineNos();
            for (String lineNo : lineNos) {
                set.add(Integer.parseInt(lineNo));
            }
        }
        List<Integer> lineList = new ArrayList(set);
        Collections.sort(lineList);
        return ObjectResponse.ok(lineList);
    }

    @Override
    public ObjectResponse getCompanys() {
        List<CompanyToLinePo> companys = companyDao.getCompanys();
        List<String> depts = new ArrayList<>();
        for (CompanyToLinePo ctp : companys) {
            depts.add(ctp.getDeptNo());
        }
        return ObjectResponse.ok(depts);
    }

}
