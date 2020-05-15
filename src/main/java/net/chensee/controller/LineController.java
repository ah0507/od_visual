package net.chensee.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.common.DateUtil;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: LineController
 * @date 2019/12/30 17:18
 */
@RestController
@RequestMapping(value = "line")
public class LineController {

    @Autowired
    private LineService lineService;

    @ApiOperation(value = "周/月视图：查询单条线路一定时间范围内的换乘次数，拥挤度，消费次数")
    @RequestMapping(value = "/{lineNo}/{direction}/days/basic",method = RequestMethod.GET)
    public ObjectResponse getBasicPosByBusiness(@PathVariable String lineNo,
                                                @PathVariable String direction,
                                                @RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        if (lineNo == null || direction == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getBasicPosByBusiness(lineNo, direction, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图：查询单条线路一定时间范围内的五个峰值的消费次数")
    @RequestMapping(value = "/{lineNo}/{direction}/days/fivePeak",method = RequestMethod.GET)
    public ObjectResponse getFivePeakPosByBusiness(@PathVariable String lineNo,
                                                   @PathVariable String direction,
                                                @RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        if (lineNo == null || direction == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getFivePeakPosByBusiness(lineNo, direction, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图：统计单条线一定时间范围内各站点的上下车、在车上、换乘次数")
    @RequestMapping(value = "/{lineNo}/{direction}/days/stationInfo",method = RequestMethod.GET)
    public ObjectResponse getStationInfoPosByBusiness(@PathVariable String lineNo,
                                                      @PathVariable String direction,
                                                   @RequestParam(value = "startTime") String startTime,
                                                   @RequestParam(value = "endTime") String endTime){
        if (lineNo == null || direction == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getStationInfoPosByBusiness(lineNo, direction, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "日视图：查询单日单条线的五个峰值的消费次数")
    @RequestMapping(value = "/{lineNo}/{direction}/day/fivePeak",method = RequestMethod.GET)
    public ObjectResponse getFivePeakPosByDayBusiness(@PathVariable String lineNo,
                                                      @PathVariable String direction,
                                                         @RequestParam(value = "queryTime") String queryTime){
        if (lineNo == null || direction == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getFivePeakPosByDayBusiness(lineNo, direction, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图：查询单日单条线各站点每个站点的上下车、在车上、换乘次数")
    @RequestMapping(value = "/{lineNo}/{direction}/day/stationInfo",method = RequestMethod.GET)
    public ObjectResponse getStationInfoPosByDayBusiness(@PathVariable String lineNo,
                                                         @PathVariable String direction,
                                                          @RequestParam(value = "queryTime") String queryTime){
        if (lineNo == null || direction == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getStationInfoPosByDayBusiness(lineNo, direction, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图：OD分析 每天每条线路每个方向的每个站点的上下车消费次数及其两辆站点间出行人数")
    @RequestMapping(value = "/{lineNo}/{direction}/day/odStatistics",method = RequestMethod.GET)
    public ObjectResponse getODStatisticsPosByDayBusiness(@PathVariable String lineNo,
                                                          @PathVariable String direction,
                                                           @RequestParam(value = "queryTime") String queryTime){
        if (lineNo == null || direction == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getODStatisticsPosByDayBusiness(lineNo, direction, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个时间段（每隔一小时）每条线路每个方向的上车消费次数、下车人次、换乘次数")
    @RequestMapping(value = "/{lineNo}/{direction}/day/eachTimeBasic",method = RequestMethod.GET)
    public ObjectResponse getEachTimeBasicPosByDayBusiness(@PathVariable String lineNo,
                                                           @PathVariable String direction,
                                                           @RequestParam(value = "queryTime") String queryTime){
        if (lineNo == null || direction == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getEachTimeBasicPosByDayBusiness(lineNo, direction, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/{lineNo}/{direction}/day/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDayBusiness(@PathVariable String lineNo,
                                               @PathVariable String direction,
                                               @RequestParam(value = "queryTime") String queryTime){
        if (lineNo == null || direction == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getDescByDayBusiness(lineNo, direction, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "周视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/{lineNo}/{direction}/days/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDaysBusiness(@PathVariable String lineNo,
                                                @PathVariable String direction,
                                                @RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        if (lineNo == null || direction == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return lineService.getDescByDaysBusiness(lineNo, direction, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "获取所有线路")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ObjectResponse getLineList(){
        return lineService.getLineList();
    }

}
