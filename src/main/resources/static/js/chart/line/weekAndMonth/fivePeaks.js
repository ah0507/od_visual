/*线路周和月的五峰*/
let fPOptions = {
    yAxis: {
        name: '消费次数（次）',
    },
    dataset: {
        /*dimensions: ['product', '9:00', '12:00', '14:00', '18:00', '22:00'],
        source: [
            { product: '星期一', '9:00': 43.3, '12:00': 85.8, '14:00': 93.7, '18:00': 95.7, '22:00': 99.7 },
            { product: '星期二', '9:00': 83.1, '12:00': 73.4, '14:00': 55.1, '18:00': 95.7, '22:00': 49.7 },
            { product: '星期三', '9:00': 86.4, '12:00': 65.2, '14:00': 82.5, '18:00': 89.7, '22:00': 59.7 },
            { product: '星期四', '9:00': 72.4, '12:00': 53.9, '14:00': 39.1, '18:00': 66.7, '22:00': 95.7 },
            { product: '星期五', '9:00': 72.4, '12:00': 53.9, '14:00': 39.1, '18:00': 92.7, '22:00': 69.7 },
            { product: '星期六', '9:00': 72.4, '12:00': 53.9, '14:00': 39.1, '18:00': 85.7, '22:00': 92.7 },
            { product: '星期日', '9:00': 72.4, '12:00': 53.9, '14:00': 39.1, '18:00': 45.7, '22:00': 39.7 }
        ],*/
        source: []
    },
}

generateFPChartFiveBars = function (id, params) {
    fPUrlToData("/line/" + params.lineNo + "/" + params.direction + "/days/fivePeak", params, id, fPOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let fPUrlToData = function (url, params, id, newOptions) {
    fPGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let fPGetSourceData = function (url, params, id, newOptions) {
    //ajax请求数据
    $.ajax({
        type: 'GET',
        url: url,
        data: {
            startTime: params.startTime,
            endTime: params.endTime
        },
        success: function (result) {
            let data = result.data
            //将数据嵌入到options中
            fPDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateFiveBars(id, newOptions)
        },
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let fPDataToOptions = function (data, options) {

    options.dataset.source = []
    if (data && data.length > 0) {
        let jiaSeries = []
        for (let i = 0; i < data[0].length - 1; i++) {
            jiaSeries.push({type: 'bar'});
        }
        options.series = jiaSeries
    }
    if (data) {
        options.dataset.source = data
        options.legend = {show: true}
    } else {
        options.legend = {show: false}
    }
}



