//根据data和其他配置参数生成图表
generateFiveBars = function(id, newOptions) {
    //方法参数：1、所在元素的id   2、图表的配置选项newOptions
    let bar = echarts.init(document.getElementById(id));
    let barOptions = {
        grid: {
            top: '30%',
            left: '10%',
            bottom: '15%',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            textStyle: {
                color: '#fff'
            }
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                interArrival: 0, // 设置X轴的内容全部显示
                //rotate: -30, // 设置倾斜角度
                textStyle: {
                    color: '#ffffff',  //更改坐标轴文字颜色
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
        yAxis: {
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9'
                }
            },
        },
        series: []
    }

    $.extend(true, barOptions, newOptions)

    bar.clear()
    bar.setOption(barOptions);

    window.onresize = function(){
        bar.resize();
    }

}
