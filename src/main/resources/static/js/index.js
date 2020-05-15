$(function () {
    // init();
    getPageProcesses();
    initUser();
})
$("#loginOut").on("click", function () {
    if (confirm("确定要退出吗？")) {
        location.href = "/loginOut";
    }
})

function initUser() {
    $.ajax({
        url: 'getUser',
        type: "get",
        dataType: 'json',
        success: function (data) {
            if (data) {
                $("#username").text(data.username);
                var html = '<button id="loggingManage" type="button" class="btn btn-warning btn-sm" data-dismiss="modal">\n' +
                    '            <a href="loggingManage" style="text-decoration:none;color: white;" target="_blank">异常管理</a>\n' +
                    '        </button>'
                switch (data.power) {
                    case 0:
                        html+=' <button id="userManage" type="button" class="btn btn-info btn-sm" data-dismiss="modal">\n' +
                            '            <a href="userManage" style="text-decoration:none;color: white;" target="_blank">用户管理</a>\n' +
                            '        </button>\n' +
                            '        <button id="paramsManage" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="paramsManage" style="text-decoration:none;color: white;" target="_blank">参数管理</a>\n' +
                            '        </button>\n' +
                            '        <button id="groupVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/group/dailyView" style="text-decoration:none;color: white;" target="_blank">集团可视化</a>\n' +
                            '        </button>\n' +
                            '        <button id="companyVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/company/dailyView" style="text-decoration:none;color: white;" target="_blank">公司可视化</a>\n' +
                            '        </button>\n' +
                            '        <button id="lineVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/line/dailyView" style="text-decoration:none;color: white;" target="_blank">线路可视化</a>\n' +
                            '        </button>'
                        break;
                    case 1:
                        html+='<button id="groupVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/group/dailyView" style="text-decoration:none;color: white;" target="_blank">集团可视化</a>\n' +
                            '        </button>' +
                            '<button id="companyVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/company/dailyView" style="text-decoration:none;color: white;" target="_blank">公司可视化</a>\n' +
                            '        </button>'
                        break;
                    case 2:
                        html+='<button id="companyVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/company/dailyView" style="text-decoration:none;color: white;" target="_blank">公司可视化</a>\n' +
                            '        </button>\n' +
                            '        <button id="lineVisual" type="button" class="btn btn-primary btn-sm" data-dismiss="modal">\n' +
                            '            <a href="/line/dailyView" style="text-decoration:none;color: white;" target="_blank">线路可视化</a>\n' +
                            '        </button>'
                        break;
                    default:
                        break;
                }
                $(".param").empty().append(html);

            }
        }
    });
}

// function init() {
//     $.ajax({
//         url: '/visual/getRoots',
//         type: "get",
//         dataType: 'json',
//         success: function (data) {
//             if (data == null) {
//                 return;
//             }
//             var html = "";
//             for (var i in data) {
//                 var cell = data[i];
//                 cell['startTime'] = formatDate(new Date(cell['startTime']));
//                 cell['endTime'] = formatDate(new Date(cell['endTime']));
//                 var chanceValue = cell['chanceValue'] || '';
//                 html += "<tr>" +
//                     "<td>" + cell['dataTime'] + "</td>" +
//                     "<td>" + cell['startTime'] + "</td>" +
//                     "<td>" + cell['endTime'] + "</td>" +
//                     "<td>" + chanceValue + "</td>" +
//                     "<td><button class=\"btn btn-primary\" data-toggle='modal' data-target='#myModal' onclick='lookUp(" + JSON.stringify(cell) + ")'>查看</button></td>" +
//                     "</tr>";
//             }
//             $("table tbody").empty().append(html);
//         }
//     });
// }


function getPageProcesses() {
    $('#processTable').bootstrapTable({
        url: '/visual/getRoots',
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
            field: 'dataTime',
            title: '数据日期',
            align: 'center',
        }, {
            field: 'startTime',
            title: '开始时间',
            align: 'center'
        }, {
            field: 'endTime',
            title: '结束时间',
            align: 'center'
        }, {
            field: 'chanceValue',
            title: '概率',
            align: 'center'
        }, {
            field: 'operation',
            title: '操作',
            align: 'center',
            formatter: addFunctionAlty//表格中增加按钮
        }],
        responseHandler: function (data) {  //后台返回的结果
            if (data.code == 200) {
                var columnData = data.data;
                columnData.rows.forEach(e => {
                    e.startTime = formatDate(new Date(e.startTime));
                    e.endTime = formatDate(new Date(e.endTime));
                    e.chanceValue = e.chanceValue || '';
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
    var html = "<button class=\"btn btn-primary\" data-toggle='modal' data-target='#myModal' onclick='lookUp(" + JSON.stringify(row) + ")'>查看</button>";
    return html;
}


function lookUp(one) {
    $.ajax({
        url: '/visual/getRootDetail',
        data: {
            parentName: one['taskName'],
            dataTime: one['dataTime'],
        },
        type: "get",
        dataType: 'json',
        success: function (data) {
            if (data == null) {
                return;
            }
            var html = '<dt>' + one['startTime'] + '</dt>';
            for (var i in data) {
                var cell = data[i];
                var startTime = formatDate(new Date(cell['startTime']));
                var endTime = formatDate(new Date(cell['endTime']));
                var position;
                if (i % 2 == 0) {
                    position = "pos-left clearfix";
                } else {
                    position = "pos-right clearfix";
                }
                html += '' +
                    '                                            <dd class="' + position + '">\n' +
                    '                                                <div class="circ"></div>\n' +
                    '                                                <div class="time">' + endTime + '</div>\n' +
                    '                                                <div class="events">\n' +
                    '                                                    <div class="events-header"></div>\n' +
                    '                                                    <div class="events-body">\n' +
                    '                                                        <div class="row">\n' +
                    '                                                            <div class="events-desc">' + cell['taskName'] + '</div>\n' +
                    '                                                        </div>\n' +
                    '                                                    </div>\n' +
                    '                                                    <div class="events-footer">\n' +
                    '                                                    </div>\n' +
                    '                                                </div>\n' +
                    '                                            </dd>';
            }
            html += '<dt>' + one['endTime'] + '</dt>';
            $(".VivaTimeline dl").empty().append(html);
        }
    });
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


