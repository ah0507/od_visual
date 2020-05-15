package net.chensee.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.common.DateUtil;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: GroupController
 * @date 2019/12/30 17:19
 */
@RestController
@RequestMapping(value = "group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "周/月视图：统计每天换乘次数，拥挤度，消费人数")
    @RequestMapping(value = "/days/basic", method = RequestMethod.GET)
    public ObjectResponse getGroupBasicStatisticsPosByBusiness(@RequestParam(value = "startTime") String startTime,
                                                               @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupBasicStatisticsPosByBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）")
    @RequestMapping(value = "/days/cardTypeConsume", method = RequestMethod.GET)
    public ObjectResponse getGroupCardTypeConsumeStatisticsPosByBusiness(@RequestParam(value = "startTime") String startTime,
                                                                         @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupCardTypeConsumeStatisticsPosByBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天五个峰值的消费次数")
    @RequestMapping(value = "/days/fivePeak", method = RequestMethod.GET)
    public ObjectResponse getGroupFivePeakStatisticsPoByBusiness(@RequestParam(value = "startTime") String startTime,
                                                                 @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupFivePeakStatisticsPoByBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天每条线路的消费次数(线路排名)")
    @RequestMapping(value = "/days/lineConsumeRank", method = RequestMethod.GET)
    public ObjectResponse getGroupLineConsumeStatisticsPoByBusinessLimit(@RequestParam(value = "startTime") String startTime,
                                                                         @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupLineConsumeStatisticsPoByBusinessLimit(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天每个站点的消费次数（站点消费排名图）")
    @RequestMapping(value = "/days/stationConsumeRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationConsumeStatisticsPoByBusinessLimit(@RequestParam(value = "startTime") String startTime,
                                                                            @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationConsumeStatisticsPoByBusinessLimit(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天每个站点的下车人次（站点下车人次排名图）")
    @RequestMapping(value = "/days/stationOffRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationOffStatisticsPoByBusinessLimit(@RequestParam(value = "startTime") String startTime,
                                                                            @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationOffStatisticsPoByBusinessLimit(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）")
    @RequestMapping(value = "/days/stationTransferRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationTransferStatisticsPoByBusinessLimit(@RequestParam(value = "startTime") String startTime,
                                                                            @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationTransferStatisticsPoByBusinessLimit(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）")
    @RequestMapping(value = "/days/stationConsume", method = RequestMethod.GET)
    public ObjectResponse getGroupStationConsumeStatisticsPoByBusiness(@RequestParam(value = "startTime") String startTime,
                                                                       @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationConsumeStatisticsPoByBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数")
    @RequestMapping(value = "/days/avgStatistics", method = RequestMethod.GET)
    public ObjectResponse getAvgStatisticsPoByBusiness(@RequestParam(value = "startTime") String startTime,
                                                                       @RequestParam(value = "endTime") String endTime) {
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getAvgStatisticsPoByBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "日视图：集团每天每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）")
    @RequestMapping(value = "/day/cardTypeConsume", method = RequestMethod.GET)
    public ObjectResponse getGroupCardTypeConsumeStatisticsPoByDayBusiness(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupCardTypeConsumeStatisticsPoByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图：统计集团每天五个峰值的消费次数")
    @RequestMapping(value = "/day/fivePeak", method = RequestMethod.GET)
    public ObjectResponse getGroupFivePeakStatisticsPoByDayBusiness(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupFivePeakStatisticsPoByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每条线路的消费次数(线路排名)")
    @RequestMapping(value = "/day/lineConsumeRank", method = RequestMethod.GET)
    public ObjectResponse getGroupLineConsumeStatisticsPoByDayBusiness(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupLineConsumeStatisticsPoByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个站点的消费次数（站点消费排名图）")
    @RequestMapping(value = "/day/stationConsumeRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayBusinessLimit(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationConsumeStatisticsPoByDayBusinessLimit(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个站点的下车次数（站点下车次数排名图）")
    @RequestMapping(value = "/day/stationOffRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationOffStatisticsPoByDayBusinessLimit(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationOffStatisticsPoByDayBusinessLimit(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个站点的换乘次数（站点换乘次数排名图）")
    @RequestMapping(value = "/day/stationTransferRank", method = RequestMethod.GET)
    public ObjectResponse getGroupStationTransferStatisticsPoByDayBusinessLimit(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationTransferStatisticsPoByDayBusinessLimit(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个站点的消费次数、下车人次、换乘次数（热力图）")
    @RequestMapping(value = "/day/stationConsume", method = RequestMethod.GET)
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayBusiness(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationConsumeStatisticsPoByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图：每日出行平均乘坐站数、平均在车上时间、平均乘车次数")
    @RequestMapping(value = "/day/avgStatistics", method = RequestMethod.GET)
    public ObjectResponse getAvgStatisticsPoByDayBusiness(@RequestParam(value = "queryTime") String queryTime) {
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getAvgStatisticsPoByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个时间段（1小时）每条线路的消费次数(线路排名前20的线路)")
    @RequestMapping(value = "/day/hour/lineConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getGroupLineConsumeStatisticsPoByDayHourBusiness(@RequestParam(value = "queryTime") String queryTime,
                                                                              @RequestParam(value = "hourTime") String hourTime){
        if (queryTime == null || hourTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupLineConsumeStatisticsPoByDayHourBusiness(DateUtil.getNYRDateByStr(queryTime),DateUtil.getNYRSFMDateByStr(hourTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个时间段（1小时）每个站点的消费次数（站点消费排名图）")
    @RequestMapping(value = "/day/hour/stationConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(@RequestParam(value = "queryTime") String queryTime,
                                                                                      @RequestParam(value = "hourTime") String hourTime){
        if (queryTime == null || hourTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationConsumeStatisticsPoByDayHourBusinessLimit(DateUtil.getNYRDateByStr(queryTime),DateUtil.getNYRSFMDateByStr(hourTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个时间段（1小时）每个站点的下车人次（站点下车人次排名图）")
    @RequestMapping(value = "/day/hour/stationOffRank",method = RequestMethod.GET)
    public ObjectResponse getGroupStationOffStatisticsPoByDayHourBusinessLimit(@RequestParam(value = "queryTime") String queryTime,
                                                                                  @RequestParam(value = "hourTime") String hourTime){
        if (queryTime == null || hourTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationOffStatisticsPoByDayHourBusinessLimit(DateUtil.getNYRDateByStr(queryTime),DateUtil.getNYRSFMDateByStr(hourTime));
    }

    @ApiOperation(value = "日视图: 统计集团每天每个时间段（1小时）每个站点的换乘次数（站点换乘次数排名图）")
    @RequestMapping(value = "/day/hour/stationTransferRank",method = RequestMethod.GET)
    public ObjectResponse getGroupStationTransferStatisticsPoByDayHourBusinessLimit(@RequestParam(value = "queryTime") String queryTime,
                                                                                       @RequestParam(value = "hourTime") String hourTime){
        if (queryTime == null || hourTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getGroupStationTransferStatisticsPoByDayHourBusinessLimit(DateUtil.getNYRDateByStr(queryTime),DateUtil.getNYRSFMDateByStr(hourTime));
    }

    @ApiOperation(value = "日视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/day/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDayBusiness(@RequestParam(value = "queryTime") String queryTime){
        if (queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getDescByDayBusiness(DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "周视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/days/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDaysBusiness(@RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        if (startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return groupService.getDescByDaysBusiness(DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

}
