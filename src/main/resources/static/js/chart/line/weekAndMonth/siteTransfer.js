
/*站点换乘*/
let siteTransferChartOptions = {
    grid: {
        top: '15%',
        left: '10%',
    },
    xAxis: {
        data: [],
        axisLabel: {
            rotate:- 30  //调整数值改变倾斜的幅度（范围-90到90）
        },
        axisLine: {
            //设置地名及x轴线条颜色
            show: true,
            lineStyle: {
                color: '#A9A9A9'
            }
        },
    },
    yAxis:  {
        type: 'value',
        name: '消费次数(次/日)',
    },
    series: [{
        data: [],
        barMaxWidth: '50%', // 最大宽度
        itemStyle: {
            // 点的颜色
            normal: {
                // 每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                color: function (params) {
                    // return barColor()[params.dataIndex];
                    var colorList = ['#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
                        '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
                        '#FFFFBB', '#FFAA33', '#99FFFF', '#CC00CC', '#FF77FF',
                        '#CC00CC', '#C63300', '#F4E001', '#9955FF', '#66FF66',
                        '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
                        '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
                        '#FFFFBB', '#FFAA33', '#99FFFF', '#CC00CC', '#FF77FF',
                        '#CC00CC', '#C63300', '#F4E001', '#9955FF', '#66FF66'];
                    return colorList[params.dataIndex]
                }
            }
        },
    }]
}

generateSiteTransferChart = function (id,params) {
    siteTransferUrlToData("/line/"+params.lineNo+"/" + params.direction + "/days/stationInfo", params, id, siteTransferChartOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
siteTransferUrlToData = function(url, params, id, newOptions) {
    siteTransferGetSourceData(url, params, id, newOptions)

}

//根据URL获取数据
let siteTransferGetSourceData = function(url,params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url ,
        data: params,
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            siteTransferDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateBar(id, newOptions)
        } ,
        dataType: 'json'
    });
}


// 将数据嵌入到options中
let siteTransferDataToOptions = function(data, options) {
    options.xAxis.data = []
    options.series[0].data = []
    options.series[0].label = {show: true,color:'#fff',position:'top'}
    let labelArray = []
    data.forEach((item) => {
        options.xAxis.data.push(item.stationName)
        options.series[0].data.push(item.transferCount)
        labelArray.push(item.transferCount)
    })
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
    if (labelArray.length > 7) {
        options.series[0].label.formatter = handleLabelShow(labelArray)
    }
}



