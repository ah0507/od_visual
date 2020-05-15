//  统计每天每个公司换乘次数，拥挤度，消费人数
//  换乘次数

let siteRankOptions = {
    grid: {
        left: '10%',
        bottom: '20%',
    },
    xAxis: {
        data: [],
        axisLabel: {
            rotate:-30  //调整数值改变倾斜的幅度（范围-90到90）
        }
    },
    legend: {
        data: [{
            name: '换乘次数（柱）',
        },{
            name: '换乘次数（线）',
        }]
    },
    yAxis: {
        name: '换乘次数（次）',
    },
    series: [{
        name: '换乘次数（柱）',
        data: [],
        type: 'bar',
        barWidth: '50%',
        itemStyle: {
            color: '#FF69B4'
        },
        "markPoint": {
            data: [],
            symbolSize: 50, //标记(气泡)的大小
            itemStyle: {
                color: {
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 0,
                    y2: 1,
                    colorStops: [{
                        offset: 0,
                        color: 'red' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: 'yellow' // 100% 处的颜色
                    }],
                    global: false // 缺省为 false
                }
            }
        }
    },{
        name: '换乘次数（线）',
        data: [],
        type: 'line',
        itemStyle: {
            color: '#59b0ff'
        },
        "markPoint": {
            symbol: 'diamond',
            symbolSize: 8, //标记(气泡)的大小
            data: [],
            label: {
                show: true,
                fontSize: 12
            },
        }
    }]
}

generateSiteRankBarLine = function (id, params) {
    srUrlToData("/company/"+params.deptNo+"/days/basic", params, id, siteRankOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let srUrlToData = function(url, params, id, newOptions) {
    srGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let srGetSourceData = function(url,params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url ,
        data: {
            startTime: params.startTime,
            endTime: params.endTime
        } ,
        success: function (result) {
            let data = result.data
            //获得节假日
            let fun = getChartData("/chart/holiday", params);
            fun.then(e => {
                getHolidayTransferCountPoint(e, data, newOptions);
                //获得天气
                let weatherFun = getChartData("/chart/weather", params);
                weatherFun.then(f => {
                    getWeatherDayTransferCountPoint(f, data, newOptions);
                    //将数据嵌入到options中
                    srDataToOptions(data, newOptions)
                    //根据id和options绘制图表
                    generateBarLine(id, newOptions)
                });
            })
        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let srDataToOptions = function(data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray = []
    options.series[1].data = []
    data.forEach((item) => {
        options.xAxis.data.push(item.queryTimeStr)
        options.series[0].data.push(item.transferCount)
        labelArray.push(item.transferCount)
        options.series[1].data.push(item.transferCount)
    })
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
}





