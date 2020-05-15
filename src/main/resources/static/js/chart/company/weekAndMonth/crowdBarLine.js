//  统计每天每个公司换乘次数，拥挤度，消费人数
//  拥挤度

let crowdChartOptions = {
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
            name: '拥挤度（柱）',
        },{
            name: '拥挤度（线）',
        }]
    },
    yAxis: {
        name: '拥挤度（比例）',
    },
    series: [{
        name: '拥挤度（柱）',
        data: [],
        type: 'bar',
        barWidth: '50%',
        itemStyle: {
            color: '#40E0D0'
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
        name: '拥挤度（线）',
        data: [],
        type: 'line',
        itemStyle: {
            color: '#fc60fa'
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

generateCrowdChartBarLine = function (id,params) {
    ccUrlToData("/company/"+params.deptNo+"/days/basic", params, id, crowdChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let ccUrlToData = function(url, params, id, newOptions) {
    ccGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let ccGetSourceData = function(url,params, id, newOptions) {
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
            let fun = getChartData("/chart/holiday",params);
            fun.then(e=>{
                getHolidayRatioPoint(e,data,newOptions);
                //获得天气
                let weatherFun =  getChartData("/chart/weather",params);
                weatherFun.then(f=>{
                    getWeatherDayRatioPoint(f,data,newOptions);
                    //将数据嵌入到options中
                    ccDataToOptions(data, newOptions)
                    //根据id和options绘制图表
                    generateBarLine(id, newOptions)
                });

            })
        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let ccDataToOptions = function(data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray = []
    options.series[1].data = []
    data.forEach((item) => {
        options.xAxis.data.push(item.queryTimeStr)
        options.series[0].data.push(handleDecimal(item.ratio))
        labelArray.push(handleDecimal(item.ratio))
        options.series[1].data.push(handleDecimal(item.ratio))
    })
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
}


