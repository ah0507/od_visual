let cardTypeOptions = {
    series: [{
        data: []
    }],
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
    }
}

generateCardTypePie = function (id, params) {
    ctUrlToData("/group/days/cardTypeConsume", params, id, cardTypeOptions)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
let ctUrlToData = function(url, params, id, newOptions) {
    ctGetSourceData(url, params,id,newOptions)
}

//根据URL获取数据
let ctGetSourceData = function(url,params,id,newOptions) {
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
            ctDataToOptions(data, newOptions)
            //根据id和options绘制图表
            generatePie(id, newOptions)
            //文字描述
            getDescCardTypeCount(data);
        } ,
        dataType: 'json'
    });
}

//将请求得到的数据转换成为所需格式
let ctFunction = function(sourceData) {
    // 转换步骤
    /*sourceData = [
        {
            'cardType': '公交卡',
            'consumeCount': 335
        },{
            'cardType': '老年卡',
            'consumeCount': 310
        },{
            'cardType': '移动支付',
            'consumeCount': 234
        },{
            'cardType': '城市卡',
            'consumeCount': 135
        }
    ]*/
    return sourceData
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



