//根据data和其他配置参数生成图表
generateDynamicBar = function (id, newOptions) {
    //方法参数：1、所在元素的id   2、图表的配置选项newOptions
    let bar = echarts.init(document.getElementById(id));

    let barOptions = {
        baseOption: {
            timeline: {
                left: '10%',
                right: '10%',
                axisType: 'category',
                autoPlay: true,
                playInterval: 1000,
                data: [],
                label: {
                    color: '#fff'
                },
                lineStyle: {
                    color: '#fff'
                },
                controlStyle: {
                    color: '#fff'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            calculable : true,
            grid: {
                top: 80,
                bottom: 100,
            },
            xAxis: {
                data:[],
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
                name: 'y轴',
                axisLine: {
                    lineStyle: {
                        color: '#A9A9A9'
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
            series: {
                name: '数据信息',
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
                                '#FFB7DD', '#660077', '#FFCCCC', '#FFC8B4', '#550088',
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                },
            },
            /*label : {
                show: true,
                color:'#fff',
                position:'top',
            }*/
        },
        options: []
    }

    $.extend(true, barOptions, newOptions)

    bar.clear()
    // console.log(barOptions)
    bar.setOption(barOptions);
}



