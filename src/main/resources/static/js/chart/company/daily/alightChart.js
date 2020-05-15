//  统计每天每个公司每个站点的下车人次（站点下车人次排名图）

let AlightingChartOptions = {
    grid: {
        top: '15%',
        left: '10%',
        // bottom: 60,
    },
    xAxis: {
        data: [],
        axisLabel: {
            rotate:-30  //调整数值改变倾斜的幅度（范围-90到90）
        }
    },
    yAxis:
        {
            type: 'value',
            name: '人次',
        },
    series: [{
        data: [],
        barMaxWidth: '50%', // 最大宽度
        // barWidth: 12,//柱图宽度
        // barGap: '30%',//柱图间距
        itemStyle: {
            // 点的颜色
            normal: {
                // 每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                color: function (params) {
                    // return barColor()[params.dataIndex];
                    var colorList = [
                        '#ff5a5f', '#00a699', '#fc642d', '#484848', '#767676',
                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
                        '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
                    ];
                    return colorList[params.dataIndex]
                }
            }
        },
    }]
}

generateAlightingChart = function (id, params) {
    acUrlToData("/company/"+params.deptNo+"/day/stationOffRank", params, id, AlightingChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let acUrlToData = function (url, params, id, newOptions) {
    acGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let acGetSourceData = function (url, params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url ,
        data: {
            queryTime: params.queryTime,
        } ,
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            acDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateBar(id, newOptions)
        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let acDataToOptions = function (data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray = []
    data.forEach((item) => {
        options.xAxis.data.push(item.stationName)
        options.series[0].data.push(item.offCount)
        labelArray.push(item.offCount)
    })
    //判断数据的个数，如果<=7，则label全部显示，否则label只显示最大和最小值
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
}



