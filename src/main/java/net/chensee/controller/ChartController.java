package net.chensee.controller;

import io.swagger.annotations.ApiOperation;
import net.chensee.entity.po.ObjectResponse;
import net.chensee.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ah
 * @title: ChartController
 * @date 2020/3/9 10:20
 */
@RestController
@RequestMapping("chart")
public class ChartController {

    @Autowired
    private ChartService chartService;

    @ApiOperation(value = "获得节假日的数据")
    @RequestMapping(value = "/holiday", method = RequestMethod.GET)
    public ObjectResponse getHolidaysData(@RequestParam(value = "startTime")String startTime,
                                          @RequestParam(value = "endTime")String endTime){
        return chartService.getHolidaysData(startTime, endTime);
    }

    @ApiOperation(value = "获得天气的数据")
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ObjectResponse getWeatherDayData(@RequestParam(value = "startTime")String startTime,
                                          @RequestParam(value = "endTime")String endTime){
        return chartService.getWeatherDayData(startTime, endTime);
    }
}
