
generateAnalysisChart = function (id, params) {
    getLines("/line/"+params.lineNo+"/" + params.direction + "/day/odStatistics", params, id)
}
function getLines(url,param,id) {
    $.ajax({
        url: url,
        data: param,
        type: 'get',
        dataType: 'json',
        success:function (data) {
            if (data.code == 200) {
                $("#analysis-diagram").empty();
                getODChartFun(id,data.data);
            }
        }
    });
}

function getODChartFun(id,data) {
    var ds = new DataSet();
    var dv = ds.createView().source(data, {
        type: 'graph',
        edges: function edges(d) {
            return d.links;
        }
    });
    dv.transform({
        type: 'diagram.arc',
        marginRatio: 0.5
        // sortBy: 'frequency' // id, weight, frequency, {function}
    });
    var chart = new G2.Chart({
        container: id,
        forceFit: true,
        padding: 50,
        height:280,
    });
    chart.legend(false);
    chart.tooltip({
        showTitle: false
    });

    var edgeView = chart.view();
    edgeView.source(dv.edges, {
        tripCount: {
            alias: '出行人数'// 提示信息起别名
        }
    });
    edgeView.axis(false);
    edgeView.edge().position('x*y').shape('arc').color('source').opacity(0.5).style('tripCount', { // 使用回调函数设置属性
        lineWidth: (tripCount) => {
            return Math.log(tripCount);
        }
    }).tooltip('tripCount');


    var nodeView = chart.view();
    nodeView.source(dv.nodes, {
        stationNo: {
            alias: '站点编号'// 提示信息起别名
        },getOnCount: {
            alias: '上车总人数'// 提示信息起别名
        },getOffCount: {
            alias: '下车总人数'// 提示信息起别名
        },
    });
    nodeView.axis(false);
    nodeView.point().position('x*y').tooltip('stationNo*getOnCount*getOffCount').shape('circle').size(10).color('stationName').opacity(0.5).style({
        stroke: 'grey'
    }).label('stationName', { // label configuration for non-polar coord
        offset: -50,
        rotate: 90
    });
    chart.render();
};
