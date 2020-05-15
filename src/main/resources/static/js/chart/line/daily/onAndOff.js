
//  单日单条线各站点每个站点的上下车、在车上次数

let onAndOffChartOptions = {
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
            name: '上车次数（柱）',
        }, {
            name: '下车人次（柱）',
        }, {
            name: '在车上人数（线）',
            textStyle: {
                color: '#ffffff'
            }
        }]
    },
    yAxis: [
        {
            type: 'value',
            name: '上下车人数',
            // interval: 50,
            axisLabel: {
                formatter: '{value} '
            },
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
        {
            type: 'value',
            name: '在车上人数',
            // interval: 50,
            axisLabel: {
                formatter: '{value} '
            },
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
    ],
    series: [
        {
            name: '上车次数（柱）',
            data: [],
            type: 'bar',
            barWidth: '25%',
        },
        {
            name: '下车人次（柱）',
            data: [],
            type: 'bar',
            barWidth: '25%',
        }
        ,
        {
            name: '在车上人数（线）',
            data: [],
            type: 'line',
            yAxisIndex: 1,

        }
    ]
}

generateOnAndOffChartBarLine = function (id, params) {
    onAndOffUrlToData("/line/"+params.lineNo+"/" + params.direction + "/day/stationInfo", params, id, onAndOffChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let onAndOffUrlToData = function (url, params, id, newOptions) {
    onAndOffGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let onAndOffGetSourceData = function (url, params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url,
        data: {
            queryTime: params.queryTime
        },
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            onAndOffDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateBarLine(id, newOptions)
        },
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let onAndOffDataToOptions = function (data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray0 = []
    options.series[1].data = []
    options.series[1].label = {show: true,color:'#fff',position:'top'}
    let labelArray1 = []
    options.series[2].data = []
    options.series[2].label = {show: true,color:'#fff',position:'top'}
    let labelArray2 = []
    data.forEach((item) => {
        options.xAxis.data.push(item.stationName)
        options.series[0].data.push(item.getOnCount)
        labelArray0.push(item.getOnCount)
        options.series[1].data.push(item.getOffCount)
        labelArray1.push(item.getOffCount)
        options.series[2].data.push(item.peopleCount)
        labelArray2.push(item.peopleCount)
    })
    let biggerSeriesData = Math.max(Math.max.apply(null,options.series[0].data)/5,
        Math.max.apply(null,options.series[1].data)/5)

    options.yAxis[0].max=Math.ceil(biggerSeriesData)*5;
    options.yAxis[0].interval=Math.ceil(biggerSeriesData);
    options.yAxis[1].max=Math.ceil(Math.max.apply(null,options.series[2].data)/5)*5;
    options.yAxis[1].interval=Math.ceil(Math.max.apply(null,options.series[2].data)/5);
    options.yAxis[0].min=0;
    options.yAxis[1].min=0;
    if (labelArray0.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray0)
    }
    if (labelArray1.length > 7) {
        options.series[1].label.formatter = handleLabelShow(labelArray1)
    }
    if (labelArray2.length > 7) {
        let maxLabel = Math.max(...labelArray2)
        options.series[2].label.formatter = function (num) {
            if (num.value === maxLabel) {
                return num.value
            }else {
                return ''
            }
        }
    }
}



