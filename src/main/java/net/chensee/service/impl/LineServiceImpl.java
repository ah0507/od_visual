package net.chensee.service.impl;

import net.chensee.common.DateUtil;
import net.chensee.common.DoubleHandleUtil;
import net.chensee.dao.CompanyDao;
import net.chensee.dao.LineDao;
import net.chensee.entity.po.CompanyToLinePo;
import net.chensee.entity.po.ConfigProperties;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.entity.po.group.GroupFivePeakStatisticsPo;
import net.chensee.entity.po.line.EachLineBasicStatisticsPo;
import net.chensee.entity.po.line.EachLineFivePeakStatisticsPo;
import net.chensee.entity.po.line.EachLineODStatisticsPo;
import net.chensee.entity.vo.line.LineDescByDaysVo;
import net.chensee.entity.vo.line.RelationStation;
import net.chensee.service.BusinessService;
import net.chensee.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ah
 * @title: LineServiceImpl
 * @date 2019/12/30 17:36
 */
@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineDao lineDao;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CompanyDao companyDao;

    @Override
    public ObjectResponse getBasicPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        List<EachLineBasicStatisticsPo> basicPosByBusiness = lineDao.getBasicPosByBusiness(lineNo, direction, startTime, endTime);
        ConfigProperties configProperties = businessService.getConfigProperties(new Date());
        for (EachLineBasicStatisticsPo ebsp : basicPosByBusiness) {
            Integer total = businessService.getBusRunningNumberTotalByLine(ebsp.getQueryTime(), ebsp.getLineNo(), ebsp.getDirection());
            if (total == null) {
                ebsp.setRatio(0.0);
            }else{
                ebsp.setRatio((double)ebsp.getConsumeCount() / (total * configProperties.getRatedNumber()));
            }
        }
        return ObjectResponse.ok(basicPosByBusiness);
    }

    @Override
    public ObjectResponse getFivePeakPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        List<EachLineFivePeakStatisticsPo> fppList = lineDao.getFivePeakPosByBusiness(lineNo, direction, startTime, endTime);
        if (fppList.size() == 0) {
            return ObjectResponse.ok();
        }
        List<List<Object>> lists = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add("time");
        List<String> nameList = new ArrayList<>();
        for (EachLineFivePeakStatisticsPo efpsp : fppList) {
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
            for (EachLineFivePeakStatisticsPo efpsp : fppList) {
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
    public ObjectResponse getStationInfoPosByBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        return ObjectResponse.ok(lineDao.getStationInfoPosByBusiness(lineNo, direction, startTime, endTime));
    }

    @Override
    public ObjectResponse getFivePeakPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        return ObjectResponse.ok(lineDao.getFivePeakPosByDayBusiness(lineNo, direction, queryTime));
    }

    @Override
    public ObjectResponse getStationInfoPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        return ObjectResponse.ok(lineDao.getStationInfoPosByDayBusiness(lineNo, direction, queryTime));
    }

    @Override
    public ObjectResponse getODStatisticsPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        List<EachLineODStatisticsPo> odStatisticsPosByDayBusiness = lineDao.getODStatisticsPosByDayBusiness(lineNo, direction, queryTime);
        return ObjectResponse.ok(this.handleODStatistics(odStatisticsPosByDayBusiness));
    }

    @Override
    public ObjectResponse getEachTimeBasicPosByDayBusiness(String lineNo, String direction, Date queryTime) {
        return ObjectResponse.ok(lineDao.getEachTimeBasicPosByDayBusiness(lineNo, direction, queryTime));
    }

    @Override
    public ObjectResponse getDescByDayBusiness(String lineNo, String direction, Date queryTime) {
        return ObjectResponse.ok(lineDao.getDescByDayBusiness(lineNo, direction, queryTime));
    }

    @Override
    public ObjectResponse getDescByDaysBusiness(String lineNo, String direction, Date startTime, Date endTime) {
        List<EachLineBasicStatisticsPo> elbspList = lineDao.getBasicPosByBusiness(lineNo, direction, startTime, endTime);
        if (elbspList.size() == 0) {
            return ObjectResponse.ok();
        }
        Integer consumeCount = 0;
        Integer transferCount = 0;
        Double ratio = 0.0;
        for (EachLineBasicStatisticsPo elbsp : elbspList) {
            consumeCount += elbsp.getConsumeCount();
            transferCount += elbsp.getTransferCount();
            ratio += elbsp.getRatio();
        }
        LineDescByDaysVo lineDescByDaysVo = new LineDescByDaysVo();
        lineDescByDaysVo.setStartTime(DateUtil.getNYRDateStr(startTime));
        lineDescByDaysVo.setEndTime(DateUtil.getNYRDateStr(endTime));
        lineDescByDaysVo.setLineNo(lineNo);
        lineDescByDaysVo.setDirection(direction);
        lineDescByDaysVo.setConsumeCount(consumeCount);
        lineDescByDaysVo.setTransferCount(transferCount);
        if (ratio != 0.0) {
            ratio = DoubleHandleUtil.convertTo3Decimal(ratio / elbspList.size());
        }
        lineDescByDaysVo.setRatio(ratio);
        return ObjectResponse.ok(lineDescByDaysVo);
    }

    private Map<String, List<Map>> handleODStatistics(List<EachLineODStatisticsPo> eachLineStationInfos) {
        List<EachLineODStatisticsPo> eachLineStationInfoList = this.handleEachLineStationInfos(eachLineStationInfos);
        Map<String, List<Map>> map = new HashMap<>(2);
        List<Map> nodes = new ArrayList<>();
        List<Map> links = new ArrayList<>();
        for (EachLineODStatisticsPo eachLineStationInfo : eachLineStationInfoList) {
            Map node = new HashMap(3);
            Map nodeConfig = new HashMap(2);
            node.put("id", eachLineStationInfo.getStationNo());
            node.put("stationNo", eachLineStationInfo.getStationNo());
            node.put("stationName", eachLineStationInfo.getStationName());
            node.put("getOnCount", eachLineStationInfo.getGetOnCount());
            node.put("getOffCount", eachLineStationInfo.getGetOffCount());
            nodeConfig.put("source", eachLineStationInfo.getStationNo());
            nodeConfig.put("target", eachLineStationInfo.getStationNo());
            nodes.add(node);
            links.add(nodeConfig);
            List<RelationStation> relationStationList = eachLineStationInfo.getRelationStationList();
            if (relationStationList == null || relationStationList.size() == 0) {
                continue;
            }
            for (RelationStation relationStation : relationStationList) {
                Map link = new HashMap<>(4);
                link.put("source", eachLineStationInfo.getStationNo());
                link.put("target", relationStation.getStationNo());
                link.put("tripCount", relationStation.getTripCount());
                links.add(link);
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Map mapi = nodes.get(i);
                Map mapj = nodes.get(j);
                Integer stationNoi = (Integer) mapi.get("stationNo");
                Integer stationNoj = (Integer) mapj.get("stationNo");
                if (stationNoi < stationNoj) {
                    nodes.set(i, mapj);
                    nodes.set(j, mapi);
                }
            }
        }
        map.put("nodes", nodes);
        map.put("links", links);
        return map;
    }

    private List<EachLineODStatisticsPo> handleEachLineStationInfos(List<EachLineODStatisticsPo> eachLineStationInfos) {
        Map<String, EachLineODStatisticsPo> eachLineStationInfoMap = new HashMap<>();
        for (EachLineODStatisticsPo eachLineStationInfo : eachLineStationInfos) {
            String key = eachLineStationInfo.getLineNo() + "," + eachLineStationInfo.getDirection() + "," + eachLineStationInfo.getStationNo();
            List<RelationStation> newRelationStationList = eachLineStationInfo.getRelationStationList();
            if (newRelationStationList == null) {
                newRelationStationList = new ArrayList<>();
            }
            EachLineODStatisticsPo elsi;
            if (eachLineStationInfoMap.containsKey(key)) {
                elsi = eachLineStationInfoMap.get(key);
                List<RelationStation> oldRelationStationList = elsi.getRelationStationList();
                if (newRelationStationList == null || oldRelationStationList == null) {
                    elsi.setRelationStationList(new ArrayList<>());
                    continue;
                }
                List<RelationStation> relationStations = this.handleRelationStations(newRelationStationList, oldRelationStationList);
                elsi.setRelationStationList(relationStations);
            } else {
                elsi = eachLineStationInfo;
            }
            elsi.setQueryTime(null);
            eachLineStationInfoMap.put(key, elsi);
        }
        return this.mapToList(eachLineStationInfoMap);
    }

    private List<EachLineODStatisticsPo> mapToList(Map<String, EachLineODStatisticsPo> eachLineStationInfoMap) {
        Set<Map.Entry<String, EachLineODStatisticsPo>> entries = eachLineStationInfoMap.entrySet();
        List<EachLineODStatisticsPo> eachLineStationInfoList = new ArrayList<>();
        for (Map.Entry<String, EachLineODStatisticsPo> eachLineStationInfoEntry : entries) {
            eachLineStationInfoList.add(eachLineStationInfoEntry.getValue());
        }
        return eachLineStationInfoList;
    }

    private List<RelationStation> handleRelationStations(List<RelationStation> newRelationStationList, List<RelationStation> oldRelationStationList) {
        Map<Integer, RelationStation> map = new HashMap<>();
        for (RelationStation oldRelationStation : oldRelationStationList) {
            map.put(oldRelationStation.getStationNo(), oldRelationStation);
        }
        for (RelationStation newRelationStation : newRelationStationList) {
            int stationNo = newRelationStation.getStationNo();
            RelationStation relationStation;
            if (map.containsKey(stationNo)) {
                relationStation = map.get(stationNo);
                Integer tripCount = relationStation.getTripCount();
                relationStation.setTripCount(tripCount + newRelationStation.getTripCount());
            } else {
                relationStation = newRelationStation;
            }
            map.put(stationNo, relationStation);
        }
        List<RelationStation> relationStations = new ArrayList<>();
        for (Map.Entry<Integer, RelationStation> entry : map.entrySet()) {
            relationStations.add(entry.getValue());
        }
        return relationStations;
    }

    @Override
    public ObjectResponse getLineList() {
        List<CompanyToLinePo> companys = companyDao.getCompanys();
        Set<Integer> lineSet = new HashSet<>();
        for (CompanyToLinePo c : companys) {
            List<String> lineNos = c.getLineNos();
            for (String lineNo : lineNos) {
                lineSet.add(Integer.parseInt(lineNo));
            }
        }
        List<Integer> lineList = new ArrayList(lineSet);
        Collections.sort(lineList);
        return ObjectResponse.ok(lineList);
    }

}
