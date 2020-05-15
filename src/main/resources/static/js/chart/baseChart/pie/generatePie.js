//根据data和其他配置参数生成图表
generatePie = function(id, newOptions) {
    //方法参数：1、所在元素的id   2、图表的配置选项newOptions
    let bar = echarts.init(document.getElementById(id));
    let barOptions = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },

        legend: {
            icon: 'rect',
            itemWidth: 10,
            itemHeight: 10,
            itemGap: 10,
            orient: 'vertical',
            left: '15%',
            top: '5%',
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
                color: ['#f8b51e', '#0194e1', '#ed5198', '#02e190'],
                radius: ['20%', '80%'],
                avoidLabelOverlap: false,
                /*label: {
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
                },*/
                data: [],
                /*itemStyle: {
                    normal: {
                        label: {        //此处为指示线文字
                            show: true,
                            formatter: '{b} : {c} ({d}%)'
                        },
                        labelLine :{
                            show:true
                        }
                    }
                }*/
            }
        ]
    }

    $.extend(true, barOptions, newOptions)

    bar.clear()
    bar.setOption(barOptions);
    window.onresize = function(){
        bar.resize();
    }

}

getDescCardTypeCount = function (data) {
    $("#cardTypeDesc").empty()
    $("#cardTypeDesc").attr("title","")
    if (data != null) {
        let html = '';
        let text = '';
        for (let i in data) {
            let temp = data[i]
            html += temp.cardType + '消费为<span class="several-time">' + temp.consumeCount + '次</span>,';
            text += temp.cardType + '消费为'+temp.consumeCount+'次,';
        }
        $("#cardTypeDesc").attr("title",text.substr(0,text.length-1))
        $("#cardTypeDesc").append(html.substr(0,html.length-1));
    }
};