//  统计每天每个公司五个峰值的消费次数

let imineChartOptions = {
    grid: {
        top: '15%',
        left: '20%',
        // bottom: 60,
    },
    xAxis: {
        data: [],
        axisLabel: {
            rotate:-60  //调整数值改变倾斜的幅度（范围-90到90）
        }
    },
    yAxis:  {
        type: 'value',
        name: '人次（次/人）',
    },
    series: [{
        data: [],
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

generateImineChart = function (id, params) {
    imineUrlToData("/company/"+params.deptNo+"/day/fivePeak", params, id, imineChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
imineUrlToData = function(url, params, id, newOptions) {
    imineGetSourceData(url, params, id, newOptions)

}

//根据URL获取数据
imineGetSourceData = function(url,params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url ,
        data: {
            queryTime: params.queryTime
        } ,
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            imineDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateLineChart(id, newOptions)
        } ,
        dataType: 'json'
    });
}


// 将数据嵌入到options中
imineDataToOptions = function(data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff'}
    data.forEach((item) => {
        options.xAxis.data.push(item.scopeTimeName)
        options.series[0].data.push(item.consumeCount)
    })
}



