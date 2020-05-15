/*统计每天每个时间段（每隔一小时）每条线路每个方向的上车消费次数、下车人次*/
let onAndOffPerHourChartOptions = {
    grid: {
        top: '15%',
        left: '10%',
    },
    xAxis: {
        data: [],
        axisLabel: {
            rotate: -30  //调整数值改变倾斜的幅度（范围-90到90）
        }
    },
    legend: {
        data: [{
            name: '上车(人数)',
            textStyle: {
                color: '#ffffff'
            }
        }, {
            name: '下车(人数)',
            textStyle: {
                color: '#ffffff'
            }
        }]
    },
    yAxis:
        {
            type: 'value',
            name: '消费次数(次）',
        },
    series: [
        {
            name: '上车(人数)',
            type: 'bar',
            data: [],
            barWidth: '18%',
        },
        {
            name: '下车(人数)',
            type: 'bar',
            data: [],
            barWidth: '18%',
        }
    ]
}

generateOnAndOffPerHourChart = function (id, params) {
    onAndOffPerHourUrlToData("/line/" + params.lineNo + "/" + params.direction + "/day/eachTimeBasic", params, id, onAndOffPerHourChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let onAndOffPerHourUrlToData = function (url, params, id, newOptions) {
    onAndOffPerHourGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let onAndOffPerHourGetSourceData = function (url, params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url,
        data: {
            queryTime: params.queryTime,
        },
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            onAndOffPerHourDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateBar(id, newOptions)
        },
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let onAndOffPerHourDataToOptions = function (data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray0 = []
    options.series[1].data = []
    options.series[1].label = {show: true,color:'#fff',position:'top'}
    let labelArray1 = []
    data.forEach((item) => {
        options.xAxis.data.push(item.currentTimeStr)
        options.series[0].data.push(item.getOnCount)
        labelArray0.push(item.getOnCount)
        options.series[1].data.push(item.getOffCount)
        labelArray1.push(item.getOffCount)
    })
    if (labelArray0.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray0)
    }
    if (labelArray1.length > 7) {
        options.series[1].label.formatter = handleLabelShow(labelArray1)
    }
}



