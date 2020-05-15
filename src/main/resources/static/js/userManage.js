$(document).ready(function () {
    getPageUsers();
    getDepts();
})

function getPageUsers() {
    $('#userManage').bootstrapTable({
        url: '/visual/getUsers',
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
        pageList: [6, 12],//分页步进值
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        silent: true,
        showRefresh: false,                  //是否显示刷新按钮
        showToggle: false,
        minimumCountColumns: 2,             //最少允许的列数
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [{
            field: 'username',
            title: '用户名称',
            align: 'center',
        }, {
            field: 'power',
            title: '权限',
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
                    switch (e.power) {
                        case 0:
                            e.power = '管理者'
                            break;
                        case 1:
                            e.power = '集团'
                            break;
                        case 2:
                            e.power = '公司'
                            break;
                        default:
                            break;
                    }
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
    var html = "<button class=\"btn btn-danger\" onclick='delUser(" + JSON.stringify(row['id']) + ")'>删除</button>";
    return html;
}


//刷新表格
function getUserList() {
    $("#userManage").bootstrapTable('refresh');
}

function getUsers() {
    $.ajax({
        url: '/visual/getUsers',
        type: "get",
        dataType: 'json',
        success: function (data) {
            if (data.code == 500) {
                return;
            }
            var msg = data.data
            var html = "";
            for (var i in msg) {
                var cell = msg[i];
                var username = cell['username'] || '';
                var power = cell['power'] || '';
                var powerName = '';
                switch (power) {
                    case 0:
                        powerName = '管理者'
                        break;
                    case 1:
                        powerName = '集团'
                        break;
                    case 2:
                        powerName = '公司'
                        break;
                    default:
                        break;
                }
                html += "<tr>" +
                    "<td>" + username + "</td>" +
                    "<td>" + powerName + "</td>" +
                    "<td><button class=\"btn btn-danger\" onclick='delUser(" + JSON.stringify(cell['id']) + ")'>删除</button></td>" +
                    "</tr>";
            }
            $("table tbody").empty().append(html);
        }
    });
}

function delUser(userId) {
    if (confirm("是否要删除该用户？")) {
        $.ajax({
            url: '/visual/delUser/' + userId,
            type: "delete",
            contentType: "application/json",
            success: function (data) {
                if (data.code == 200) {
                    alert('删除成功！');
                    getUserList();
                }
            }
        });
    }
}

function getDepts() {
    $.ajax({
        url: '/company',
        type: "get",
        dataType: 'json',
        success: function (data) {
            if (data.code == 500) {
                return;
            }
            var depts = data.data
            var html = "";
            for (var i in depts) {
                var dept = depts[i]
                html += '<option value="' + dept + '">'+dept+'</option>';
            }
            $("#company select[name=deptNo]").empty().append(html);

            $('#company select[name=deptNo]').multiselect({
                nonSelectedText:"请选择",
                numberDisplayed:4,
                selectedList: 6,
                maxHeight:255,
            });
        }
    });
};

$("#addUser").on("click", function () {
    $("#addUserModal input[name=username]").val("");
    $("#addUserModal input[name=password]").val("");
    $("#addUserModal select[name=power]").val("");
    $('#company select[name=deptNo]').multiselect('clearSelection');
    $('#company select[name=deptNo]').multiselect('refresh');
    $("#addUserModal #company").hide();
    $("#addUserModal").modal();
});

$("#addUserModal select[name=power]").on("change", function () {
    let power = $(this).val();
    if (power == 2) {
        $("#addUserModal #company").show();
    } else {
        $("#addUserModal #company").hide();
        $('#company select[name=deptNo]').multiselect('clearSelection');
        $('#company select[name=deptNo]').multiselect('refresh');
    }
});

$("#saveUser").on("click", function () {
    var username = $("#addUserModal input[name=username]").val();
    var password = $("#addUserModal input[name=password]").val();
    var power = $("#addUserModal select[name=power]").val();
    var deptNos = $('#company [name=deptNo]').val()
    if (username == '' || password == '' || power == '') {
        alert("请填写完整！");
    }
    var obj = {
        username: username,
        password: password,
        power: parseInt(power),
        deptNos: deptNos
    }
    $.ajax({
        url: '/visual/addUser',
        type: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json",
        success: function (data) {
            if (data.code == 200) {
                $("#addUserModal").modal('hide');
                alert('添加成功！');
                getUserList();
            } else {
                alert('添加失败！');
            }
        }
    });
});

