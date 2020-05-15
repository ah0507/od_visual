//根据data和其他配置参数生成图表
generateBar = function (id, newOptions) {
    //方法参数：1、所在元素的id   2、图表的配置选项newOptions
    let bar = echarts.init(document.getElementById(id));
    let barOptions = {
        // backgroundColor: '#ffffff',
        grid: {
            top: '25%',
            left: '20%',
            bottom: '12%',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        xAxis: {
            type: 'category',
            data: [],
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
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#A9A9A9',
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    //设置刻度线粗度(粗的宽度)
                    width:20,
                    //颜色数组，数组数量要比刻度线数量大才能不循环使用
                    color: ['rgba(0, 0, 0, 0)','#152548','#152548','#152548','#152548','#152548','#152548','#152548','#152548']
                }
            }
        },
        series: [{
            data: [],
            type: 'bar',
            itemStyle: {
            },
            barMaxWidth: 30, // 最大宽度
            barWidth: 20,//柱图宽度
            barGap: '30%',//柱图间距
        }],
    }

    $.extend(true, barOptions, newOptions)

    bar.clear()
    bar.setOption(barOptions);
}
