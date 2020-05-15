/**
 * 获得图的节假日和天气接口
 * @param url
 * @param param
 * @returns {Promise<unknown>}
 */
function getChartData(url,param) {
    return new Promise(resolve => {
        $.ajax({
            type: 'GET',
            url: url,
            data:{
                startTime:param.startTime,
                endTime:param.endTime
            },
            dataType: 'json',
            success: function (res) {
                if (res.code == 200) {
                    resolve(res.data);
                }
            },
        });
    })
}

function getHolidayTransferCountPoint(HolidayData,data,options) {
    options.series[0].markPoint.data = []
    for (let i = 0; i < HolidayData.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (HolidayData[i].queryTime == data[k].queryTimeStr) {
                if (HolidayData[i].note != null) {
                    if (HolidayData[i].note =="周六"||HolidayData[i].note =="周日") {
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].transferCount / Math.ceil(10 * Math.random()),
                            itemStyle: {
                                color: {
                                    type: 'linear',
                                    x: 0,
                                    y: 0,
                                    x2: 0,
                                    y2: 1,
                                    colorStops: [{
                                        offset: 0,
                                        color: '#00CC99' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: 'yellow' // 100% 处的颜色
                                    }],
                                    global: false // 缺省为 false
                                }
                            }
                        }
                        options.series[0].markPoint.data.push(obj);
                    } else{
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].transferCount / Math.ceil(10 * Math.random())
                        }
                        options.series[0].markPoint.data.push(obj);
                    }

                }
            }
        }
    }

}

function getWeatherDayTransferCountPoint(weather,data,options){
    options.series[1].markPoint.data = [];
    for (let i = 0; i < weather.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (data[k].queryTimeStr == weather[i].queryTime.substr(0, 10)) {
                let obj = {
                    value: weather[i].state,
                    xAxis: data[k].queryTimeStr,
                    yAxis: data[k].transferCount
                }
                options.series[1].markPoint.data.push(obj);
                break;
            }
        }
    }
}

function getHolidayConsumeCountPoint(HolidayData,data,options) {
    options.series[0].markPoint.data = []
    for (let i = 0; i < HolidayData.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (HolidayData[i].queryTime == data[k].queryTimeStr) {
                if (HolidayData[i].note != null) {
                    if (HolidayData[i].note =="周六"||HolidayData[i].note =="周日") {
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].consumeCount / Math.ceil(10 * Math.random()),
                            itemStyle: {
                                color: {
                                    type: 'linear',
                                    x: 0,
                                    y: 0,
                                    x2: 0,
                                    y2: 1,
                                    colorStops: [{
                                        offset: 0,
                                        color: '#00CC99' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: 'yellow' // 100% 处的颜色
                                    }],
                                    global: false // 缺省为 false
                                }
                            }
                        }
                        options.series[0].markPoint.data.push(obj);
                    } else{
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].consumeCount / Math.ceil(10 * Math.random())
                        }
                        options.series[0].markPoint.data.push(obj);
                    }

                }
            }
        }
    }

}

function getWeatherDayConsumeCountPoint(weather,data,options){
    options.series[1].markPoint.data = [];
    for (let i = 0; i < weather.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (data[k].queryTimeStr == weather[i].queryTime.substr(0, 10)) {
                let obj = {
                    value: weather[i].state,
                    xAxis: data[k].queryTimeStr,
                    yAxis: data[k].consumeCount
                }
                options.series[1].markPoint.data.push(obj);
                break;
            }
        }
    }
}

function getHolidayRatioPoint(HolidayData,data,options) {
    options.series[0].markPoint.data = []
    for (let i = 0; i < HolidayData.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (HolidayData[i].queryTime == data[k].queryTimeStr) {
                if (HolidayData[i].note != null) {
                    if (HolidayData[i].note =="周六"||HolidayData[i].note =="周日") {
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].ratio / Math.ceil(10 * Math.random()),
                            itemStyle: {
                                color: {
                                    type: 'linear',
                                    x: 0,
                                    y: 0,
                                    x2: 0,
                                    y2: 1,
                                    colorStops: [{
                                        offset: 0,
                                        color: '#00CC99' // 0% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: 'yellow' // 100% 处的颜色
                                    }],
                                    global: false // 缺省为 false
                                }
                            }
                        }
                        options.series[0].markPoint.data.push(obj);
                    } else{
                        let obj = {
                            value: HolidayData[i].note,
                            xAxis: data[k].queryTimeStr,
                            yAxis: data[k].ratio / Math.ceil(10 * Math.random())
                        }
                        options.series[0].markPoint.data.push(obj);
                    }

                }
            }
        }
    }

}

function getWeatherDayRatioPoint(weather,data,options){
    options.series[1].markPoint.data = [];
    for (let i = 0; i < weather.length; i++) {
        for (let k = 0; k < data.length; k++) {
            if (data[k].queryTimeStr == weather[i].queryTime.substr(0, 10)) {
                let obj = {
                    value: weather[i].state,
                    xAxis: data[k].queryTimeStr,
                    yAxis: data[k].ratio
                }
                options.series[1].markPoint.data.push(obj);
                break;
            }
        }
    }
}





