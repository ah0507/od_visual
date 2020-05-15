// 统计每天每个公司每个站点的消费次数（站点消费排名图）

function dataFormatter(obj) {
    if (obj.length != 0) {
        for (var hourNum = 0; hourNum < obj.length; hourNum++) {
            obj[hourNum] = {
                name: obj[hourNum].stationName,
                value: obj[hourNum].consumeCount
            }
        }
    }
    return obj;
}

let numOfConsumeOptions = {
    baseOption: {
        timeline: {
            data: [],   //需要填写
            show: false
        },
        grid: {
            top: '33%',
            bottom: '25%',
            left: '20%'
        },
        xAxis: {
            data:[],    //不需填写，options中设置动态显示
            axisLabel: {
                interval:0, // 设置X轴的内容全部显示
                textStyle: {
                    color: '#ffffff',  //更改坐标轴文字颜色
                    fontSize: 10       //更改坐标轴文字大小
                },
                rotate: -30
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

generateNumOfConsumeChartBar = function (id, params, flag) {
    noccUrlToData("/company/"+params.deptNo+"/day/stationConsumeRank", params, id, numOfConsumeOptions, flag)
}

//根据URL请求数据，并将数据转换成绘制图表所需的格式
noccUrlToData = function(url, params, id, newOptions, flag) {
    noccGetSourceData(url, params, id, newOptions, flag)
}

var wholeData = new Array(24)

//根据URL获取数据
noccGetSourceData = function(url,params, id, newOptions, flag) {
    //根据params获取起始天数的间隔
    let dateS = new Date(params.startTime)
    let dateE = new Date(params.endTime)
    let dateInterval = (dateE-dateS)/(24*60*60*1000)
    //创建固定长度的数组存放数据
    var wholeData = new Array(dateInterval)
    let timeLineData = {
        startTime: params.startTime,
        endTime: params.endTime,
        interval: dateInterval
    }
    //周的话：从6到0；    月：从最后一天-1到0
    for (var dayNumber = dateInterval; dayNumber >=0; dayNumber--) {
        let nDate = new Date(new Date(params.endTime) - dayNumber*24*60*60*1000)
        //ajax请求数据
        $.ajax({
            type: 'GET',
            url: url ,
            async: false,
            data: {
                queryTime: nDate.getFullYear() + "-" + (nDate.getMonth()+1) + "-" + nDate.getDate(),
            },
            success: function (result) {
                var perDayData = []
                result.data.forEach(item => {
                    perDayData.push({stationName:item.stationName,consumeCount:item.consumeCount})
                })
                wholeData.splice(dateInterval-dayNumber,1,perDayData)
            } ,
            dataType: 'json'
        });
    }
    // console.log(wholeData)
    //将数据嵌入到options中
    noccDataToOptions(wholeData, newOptions, timeLineData, flag)
    //根据id和options绘制图表
    generateDynamicBar(id, newOptions)
}

// 将数据嵌入到options中
noccDataToOptions = function(sData, newOptions, timeLineData, flag) {
    newOptions.baseOption.timeline.data = []
    sData.forEach((item,index) => {
        if (index <= timeLineData.interval) {
            let nDate = new Date(new Date(timeLineData.endTime) - (timeLineData.interval-index)*24*60*60*1000)
            let tempOption = {}
            let titleText = (nDate.getMonth()+1)+'月'+nDate.getDate()+'日 '+handleWeek(nDate)
            tempOption.title = {text: titleText ,textStyle: {color: '#fff'}}
            tempOption.xAxis = {
                data: []
            }
            item.forEach(itemDetail => {
                tempOption.xAxis.data.push(itemDetail.stationName)
            })
            tempOption.series = []
            let seriesData = dataFormatter(item)
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
            newOptions.baseOption.timeline.data.push((nDate.getMonth()+1)+'月'+nDate.getDate()+'日')
        }
    })
    if (flag == true) {
        newOptions.baseOption.timeline.show = true
        newOptions.baseOption.grid = {
            top: '10%',
            bottom: '15%'
        }
    }else {
        newOptions.baseOption.timeline.show = false
        newOptions.baseOption.grid = {
            top: '33%',
            bottom: '25%',
        }
    }
}




