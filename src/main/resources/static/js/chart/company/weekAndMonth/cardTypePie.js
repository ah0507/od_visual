//  每天每个公司每种卡类型的消费次数的统计（ 查询每种消费方式和全部消费的占比）

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
        data: []
    }
}

generateCardTypePie = function (id, params) {
    ctUrlToData("/company/"+params.deptNo+"/days/cardTypeConsume", params, id, cardTypeOptions)
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

// 将数据嵌入到options中
let ctDataToOptions = function(data, options) {
    options.series[0].data = []
    options.legend.data = []
    data.forEach(item => {
        options.series[0].data.push({name: item.cardType,value: item.consumeCount})
        options.legend.data.push(item.cardType)
    })
}



