let cardTypeOptions = {
    legend: {
        icon: 'rect',
        itemWidth: 10,
        itemHeight: 10,
        itemGap: 10,
        left: '5%',
        right: '50%',
        top: '5%',
        bottom: '50%',
        textStyle: {
            color: '#fff'
        },
        data: []
    },
    series: [
        {
            name: '消费占比图',
            type: 'pie',
            left: '30%',
            top:'30%',
            right:'10%',
            color: ['#f8b51e', '#0194e1', '#ed5198', '#02e190'],
            radius: ['20%', '80%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: true,
                    position: 'inner',
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '18',
                        color: '#fff'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: []
        }
    ]
}

generateCardTypePie = function (id, params) {
    ctUrlToData("/group/day/cardTypeConsume", params, id, cardTypeOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let ctUrlToData = function(url, params, id, newOptions) {
    ctGetSourceData(url, params, id, newOptions)
}

//根据URL获取数据
let ctGetSourceData = function(url,params, id, newOptions) {
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
            ctDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generatePie(id, newOptions)
            //文字描述
            getDescCardTypeCount(data);
        } ,
        dataType: 'json'
    });
}

// 将数据嵌入到options中
let ctDataToOptions = function(data, options) {
    options.series[0].data = []
    options.legend.data = []
    data.forEach(item => {
        options.series[0].data.push({name: item.cardType,value: item.consumeCount})
        options.legend.data.push(item.cardType)
    })
}



