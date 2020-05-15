//  统计每天每个公司五个峰值的消费次数

let fcOptions = {
    yAxis: {
        name: '消费次数（次）',
    },
    dataset: {
        /*dimensions: ['product', '星期一', '星期二', '星期三', '星期四', '星期五','星期六','星期日'],
        source: [
            { product: '9:00', '星期一': 43.3, '星期二': 85.8, '星期三': 93.7, '星期四': 95.7, '星期五': 99.7,'星期六': 95.7, '星期五': 99.7 },
            { product: '12:00', '星期一': 83.1, '星期二': 73.4, '星期三': 55.1, '星期四': 95.7, '星期五': 49.7,'星期六': 95.7, '星期五': 99.7 },
            { product: '14:00', '星期一': 86.4, '星期二': 65.2, '星期三': 82.5, '星期四': 89.7, '星期五': 59.7,'星期六': 95.7, '星期五': 99.7 },
            { product: '18:00', '星期一': 72.4, '星期二': 53.9, '星期三': 39.1, '星期四': 66.7, '星期五': 95.7,'星期六': 95.7, '星期五': 99.7 },
            { product: '22:00', '星期一': 72.4, '星期二': 53.9, '星期三': 39.1, '星期四': 92.7, '星期五': 69.7,'星期四': 95.7, '星期五': 99.7 }
        ],*/
        source: []
    },
}

generateFiveChartFiveBars = function (id, params) {
    fcUrlToData("/company/"+params.deptNo+"/days/fivePeak", params, id, fcOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let fcUrlToData = function(url, params, id, newOptions) {
    fcGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let fcGetSourceData = function(url,params, id, newOptions) {
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
            //将数据嵌入到options中
            fcDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generateFiveBars(id, newOptions)
        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let fcDataToOptions = function (data, options) {
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
    }else{
        options.legend = {show: false}
    }
}



