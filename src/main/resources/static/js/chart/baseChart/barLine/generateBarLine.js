//根据data和其他配置参数生成图表
generateBarLine = function(id, newOptions) {
    //方法参数：1、所在元素的id   2、图表的配置选项newOptions
    let bar = echarts.init(document.getElementById(id));
    let barOptions = {
        grid: {
            top: '25%',
            left: '22%',
            bottom: '12%',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: [{
                name: null,
                textStyle: {
                    color: '#ffffff'
                }
            },{
                name: null,
                textStyle: {
                    color: '#ffffff'
                }
            }]
        },
        xAxis: {
            type: 'category',
            data: [],
            axisLabel: {
                interArrival: 0, // 设置X轴的内容全部显示
                //rotate: -30, // 设置倾斜角度
                color: '#fff'
            },
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            name: null,
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
        series: [{
            data: [],
            type: 'bar',
        },{
            data: [],
            type: 'line',
        }],
    }

    $.extend(true, barOptions, newOptions)

    bar.clear()
    bar.setOption(barOptions);
    window.onresize = function(){
        bar.resize();
    }
}
