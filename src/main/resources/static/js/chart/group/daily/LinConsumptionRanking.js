function dataFormatter(obj) {
    var temp;
    if (obj.length != 0) {
        for (var hourNum = 0; hourNum < obj.length; hourNum++) {
            obj[hourNum] = {
                name: obj[hourNum].lineNo,
                value: obj[hourNum].getOnCount
            }
        }
    }
    return obj;
}

let lcrChartOptions = {
    baseOption: {
        timeline: {
            data: [],   //需要填写
            show: false
        },
        grid: {
            top: '25%',
            bottom: '20%',
            left: '23%'
        },
        xAxis: {
            data:[],    //不需填写，options中设置动态显示
            axisLabel: {
                interval:0, // 设置X轴的内容全部显示
                textStyle: {
                    color: '#ffffff',  //更改坐标轴文字颜色
                    fontSize: 10      //更改坐标轴文字大小
                }
            },
            axisLine: {
                //设置地名及x轴线条颜色
                show: true,
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
        yAxis: {
            name: '消费次数',
        },
        series: {
            name: '消费次数',
            type: 'bar',
            barMaxWidth: '50%',
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
                            '#ff7f56', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            },
        }
    },
    options: []
}

generatelcrChart = function (id, params, flag) {
    lcrUrlToData("/group/day/hour/lineConsumeRank", params, id, lcrChartOptions, flag)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
lcrUrlToData = function(url, params, id, newOptions, flag) {
    lcrGetSourceData(url, params, id, newOptions, flag)
}

var wholeData = new Array(24)

//根据URL获取数据
lcrGetSourceData = function(url,params, id, newOptions, flag) {
    for (var hourNumber = 0; hourNumber < 24; hourNumber++) {
        //ajax请求数据
        $.ajax({
            type: 'GET',
            url: url ,
            async: false,
            data: {
                queryTime: params.queryTime,
                hourTime: params.queryTime + ' 0' + hourNumber + ':00:00'
            },
            success: function (result) {
                var perHourData = []
                result.data.forEach(item => {
                    perHourData.push({lineNo:item.lineNo,getOnCount:item.getOnCount})
                })
                wholeData.splice(hourNumber,1,perHourData)
            } ,
            dataType: 'json'
        });
    }
    // console.log(wholeData)
    //将数据嵌入到options中
    lcrDataToOptions(wholeData, newOptions, flag)
    //根据id和options绘制图表
    generateDynamicBar(id, newOptions)
}

// 将数据嵌入到options中
lcrDataToOptions = function(sData, newOptions, flag) {
    newOptions.baseOption.timeline.data = []
    sData.forEach((item,index) => {
        let tempOption = {}
        tempOption.title = {text: index+'时',textStyle: {color: '#fff'},left: '5%', top:'5%'}
        tempOption.xAxis = {
            data: []
        }
        item.forEach(itemDetail => {
            tempOption.xAxis.data.push(itemDetail.lineNo)
        })
        tempOption.series = []
        let seriesData = dataFormatter(item)
        // console.log(seriesData)
        tempOption.series.push(
            {data: seriesData}
        )
        let labelArray = []
        seriesData.forEach(item => {
            labelArray.push(item.value)
        })
        tempOption.label = {show: true, color:'#fff', position:'top'}
        if (labelArray.length > 7) {
            tempOption.label.formatter = handleLabelShow(labelArray)
        }
        newOptions.options[index] = tempOption
        newOptions.baseOption.timeline.data.push(index+'时')
    })
    if (flag == true) {
        newOptions.baseOption.timeline.show = true
        newOptions.baseOption.grid = {
            top: '15%',
            bottom: '10%'
        }
    }else {
        newOptions.baseOption.timeline.show = false
        newOptions.baseOption.grid = {
            top: '25%',
            bottom: '20%',
        }
    }
}




