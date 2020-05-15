
/*线路
* 周和月
* 消费图*/
let lineConsumeChartOptions = {
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
            name: '消费（柱）',
        },{
            name: '消费（线）',
        }]
    },
    yAxis: {
        name: '消费（比例）',
    },
    series: [{
        name: '消费（柱）',
        data: [],
        type: 'bar',
        barWidth: '30%',
        itemStyle: {
            color: '#cae146'
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
        name: '消费（线）',
        data: [],
        type: 'line',
        itemStyle: {
            color: '#a01efc'
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

generateLineConsumeChartBarLine = function (id,params) {
    lineConsumeUrlToData("/line/"+params.lineNo+"/" + params.direction + "/days/basic", params, id, lineConsumeChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let lineConsumeUrlToData = function(url, params, id, newOptions) {
    lineConsumeGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let lineConsumeGetSourceData = function(url,params, id, newOptions) {
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
                getHolidayConsumeCountPoint(e,data,newOptions);
                //获得天气
                let weatherFun =  getChartData("/chart/weather",params);
                weatherFun.then(f=>{
                    getWeatherDayConsumeCountPoint(f,data,newOptions);
                    //将数据嵌入到options中
                    lineConsumeDataToOptions(data, newOptions)
                    //根据id和options绘制图表
                    generateBarLine(id, newOptions)
                });
            })

        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let lineConsumeDataToOptions = function(data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray = []
    options.series[1].data = []
    data.forEach((item) => {
        options.xAxis.data.push(item.queryTimeStr)
        options.series[0].data.push(item.consumeCount)
        labelArray.push(item.consumeCount)
        options.series[1].data.push(item.consumeCount)
    })
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
}




