package net.chensee.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.common.DateUtil;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: CompanyController
 * @date 2019/12/30 17:18
 */
@RestController
@RequestMapping(value = "company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "周/月视图：统计每天每个公司换乘次数，拥挤度，消费人数")
    @RequestMapping(value = "/{deptNo}/days/basic",method = RequestMethod.GET)
    public ObjectResponse getEachCompBasicStatisticsPosByBusiness(@PathVariable String deptNo,
                                                                  @RequestParam(value = "startTime") String startTime,
                                                                  @RequestParam(value = "endTime") String endTime) {
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompBasicStatisticsPosByBusiness(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图：每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）")
    @RequestMapping(value = "/{deptNo}/days/cardTypeConsume",method = RequestMethod.GET)
    public ObjectResponse getEachCompCardTypeConsumeStatisticsPosByBusiness(@PathVariable String deptNo,
                                                                            @RequestParam(value = "startTime") String startTime,
                                                                            @RequestParam(value = "endTime") String endTime) {
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompCardTypeConsumeStatisticsPosByBusiness(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司五个峰值的消费次数")
    @RequestMapping(value = "/{deptNo}/days/fivePeak",method = RequestMethod.GET)
    public ObjectResponse getEachCompFivePeakStatisticsPoByBusiness(@PathVariable String deptNo,
                                                                    @RequestParam(value = "startTime") String startTime,
                                                                    @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompFivePeakStatisticsPoByBusiness(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司每条线路的消费次数(线路排名)")
    @RequestMapping(value = "/{deptNo}/days/lineConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompLineConsumeStatisticsPoByBusiness(@PathVariable String deptNo,
                                                                       @RequestParam(value = "startTime") String startTime,
                                                                       @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompLineConsumeStatisticsPoByBusinessLimit(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）")
    @RequestMapping(value = "/{deptNo}/days/lineOffRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationOffStatisticsPoByBusinessLimit(@PathVariable String deptNo,
                                                                       @RequestParam(value = "startTime") String startTime,
                                                                       @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationOffStatisticsPoByBusinessLimit(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）")
    @RequestMapping(value = "/{deptNo}/days/lineTransferRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationTransferStatisticsPoByBusinessLimit(@PathVariable String deptNo,
                                                                       @RequestParam(value = "startTime") String startTime,
                                                                       @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationTransferStatisticsPoByBusinessLimit(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）")
    @RequestMapping(value = "/{deptNo}/days/stationConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationConsumeStatisticsPoByBusinessLimit(@PathVariable String deptNo,
                                                                        @RequestParam(value = "startTime") String startTime,
                                                                        @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationConsumeStatisticsPoByBusinessLimit(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "周/月视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）")
    @RequestMapping(value = "/{deptNo}/days/stationConsume",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationConsumeStatisticsPoByBusiness(@PathVariable String deptNo,
                                                                   @RequestParam(value = "startTime") String startTime,
                                                                   @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationConsumeStatisticsPoByBusiness(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "日视图：每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）")
    @RequestMapping(value = "/{deptNo}/day/cardTypeConsume",method = RequestMethod.GET)
    public ObjectResponse getEachCompCardTypeConsumeStatisticsPoByDayBusiness(@PathVariable String deptNo,
                                                                              @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompCardTypeConsumeStatisticsPoByDayBusiness(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图：统计每天每个公司五个峰值的消费次数")
    @RequestMapping(value = "/{deptNo}/day/fivePeak",method = RequestMethod.GET)
    public ObjectResponse getEachCompFivePeakStatisticsPoByDayBusiness(@PathVariable String deptNo,
                                                                       @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompFivePeakStatisticsPoByDayBusiness(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个公司每条线路的消费次数(线路排名)")
    @RequestMapping(value = "/{deptNo}/day/lineConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompLineConsumeStatisticsPoByDayBusiness(@PathVariable String deptNo,
                                                                          @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompLineConsumeStatisticsPoByDayBusiness(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个公司每个站点的消费次数（站点消费排名图）")
    @RequestMapping(value = "/{deptNo}/day/stationConsumeRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusinessLimit(@PathVariable String deptNo,
                                                                                  @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationConsumeStatisticsPoByDayBusinessLimit(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个公司每个站点的下车人次（站点下车人次排名图）")
    @RequestMapping(value = "/{deptNo}/day/stationOffRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationOffStatisticsPoByDayBusinessLimit(@PathVariable String deptNo,
                                                                                  @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationOffStatisticsPoByDayBusinessLimit(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个公司每个站点的换乘次数（站点换乘次数排名图）")
    @RequestMapping(value = "/{deptNo}/day/stationTransferRank",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationTransferStatisticsPoByDayBusinessLimit(@PathVariable String deptNo,
                                                                                  @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationTransferStatisticsPoByDayBusinessLimit(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计每天每个公司每个站点的消费次数、下车人次、换乘次数（热力图）")
    @RequestMapping(value = "/{deptNo}/day/stationConsume",method = RequestMethod.GET)
    public ObjectResponse getEachCompStationConsumeStatisticsPoByDayBusiness(@PathVariable String deptNo,
                                                                             @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getEachCompStationConsumeStatisticsPoByDayBusiness(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "日视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/{deptNo}/day/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDayBusiness(@PathVariable String deptNo,
                                               @RequestParam(value = "queryTime") String queryTime){
        if (deptNo == null || queryTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getDescByDayBusiness(deptNo, DateUtil.getNYRDateByStr(queryTime));
    }

    @ApiOperation(value = "周视图: 统计文字描述（换乘次数，消费次数,拥挤度）")
    @RequestMapping(value = "/{deptNo}/days/desc",method = RequestMethod.GET)
    public ObjectResponse getDescByDaysBusiness(@PathVariable String deptNo,
                                                @RequestParam(value = "startTime") String startTime,
                                                @RequestParam(value = "endTime") String endTime){
        if (deptNo == null || startTime == null || endTime == null) {
            return ObjectResponse.fail("参数不能为空！");
        }
        return companyService.getDescByDaysBusiness(deptNo, DateUtil.getNYRDateByStr(startTime), DateUtil.getNYRDateByStr(endTime));
    }

    @ApiOperation(value = "通过公司得到该公司所有线路")
    @RequestMapping(value = "/lineNos", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse getCompanyLines(@RequestParam(value = "deptNos") String deptNos) {
        return companyService.getCompanyLines(deptNos);
    }

    @ApiOperation(value = "获得所有的公司")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse getCompanys() {
        return companyService.getCompanys();
    }
}
