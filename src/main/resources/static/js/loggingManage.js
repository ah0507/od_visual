$(function () {
    getPageLogs();
})

function getPageLogs() {
    $('#loggingTable').bootstrapTable({
        url: '/visual/getLogs',
        method: 'get',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortOrder: "asc",                   //排序方式
        queryParamsType: '',
        dataType: 'json',
        paginationShowPageGo: true,
        showJumpto: true,
        pageNumber: 1, //初始化加载第一页，默认第一页
        queryParams: queryParams,//请求服务器时所传的参数
        sidePagination: 'server',//指定服务器端分页
        pageSize: 6,//单页记录数
        pageList: [6,12],//分页步进值
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        silent: true,
        showRefresh: false,                  //是否显示刷新按钮
        showToggle: false,
        minimumCountColumns: 2,             //最少允许的列数
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [{
            field: 'exceptionName',
            title: '异常名称',
            align: 'center',
        }, {
            field: 'exceptionContent',
            title: '异常内容',
            align: 'center'
        }, {
            field: 'createTime',
            title: '记录时间',
            align: 'center'
        }, {
            field: 'operation',
            title: '异常详情',
            align: 'center',
            formatter: addFunctionAlty//表格中增加按钮
        }],
        responseHandler: function (data) {  //后台返回的结果
            if (data.code == 200) {
                var columnData = data.data;
                columnData.rows.forEach(e => {
                    e.exceptionName = e.exceptionName || '';
                    e.exceptionContent = e.exceptionContent || '';
                    e.createTime = formatDate(new Date(e.createTime));
                });
                return columnData;
            }
        }
    });
}

function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        pageNum: params.pageNumber,
        pageSize: params.pageSize,
    };
    return temp;
}

// 表格中按钮
function addFunctionAlty(value, row, index) {
    var data = JSON.stringify(row) != null ? JSON.stringify(row) : null;
    var html = "<button class=\"btn btn-primary\" data-toggle='modal' data-target='#myModal' onclick='lookUp(" + data + ")'>查看</button>";
    return html;
}

function lookUp(one) {
    var exceptionDetail = one["exceptionDetail"] || '';
    var html = "<p>" + exceptionDetail + "</p>";
    $("#myModal .modal-body").empty().append(html);
}

var formatDate = function (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second = date.getSeconds();
    second = minute < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
};