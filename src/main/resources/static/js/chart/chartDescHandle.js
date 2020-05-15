function getLineDescByDay(param) {
    $.ajax({
        type: 'GET',
        url: "/line/"+param.lineNo+"/"+param.direction+"/day/desc",
        data:{
            queryTime:param.queryTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data;
                let html = data.queryTimeStr + "," + data.lineNo + "路" + data.direction + "方向" +
                    "总计消费次数共为 <span class=\"several-time\">" + data.consumeCount + "次</span>" +
                    "，换乘次数共为<span class=\"several-time\">" + data.transferCount + "次</span>" +
                    "，拥挤度平均为<span class=\"several-time\">" + handleDecimal(data.ratio) + "</span>";
                $("#desc").append(html);
            }
        },
    });
}

function getLineDescByDays(param) {
    $.ajax({
        type: 'GET',
        url: "/line/"+param.lineNo+"/"+param.direction+"/days/desc",
        data:{
            startTime:param.startTime,
            endTime:param.endTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data
                let html = "<span>" + data.startTime + "到" + data.endTime + "," + data.lineNo + data.direction + "方向" +
                "总计消费次数共为 <span class=\"several-time\">"+data.consumeCount+"次</span>" +
                "，换乘次数共为<span class=\"several-time\">"+data.transferCount+"次</span>" +
                "，拥挤度平均为<span class=\"several-time\">"+handleDecimal(data.ratio)+"</span>";
                $("#desc").append(html)
            }
        },
    });
}

function getCompDescByDay(param) {
    $.ajax({
        type: 'GET',
        url: "/company/"+param.deptNo+"/day/desc",
        data:{
            queryTime:param.queryTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data
                let html = "<span>" + data.queryTimeStr + "," + data.deptNo + "公司" +
                    "总计消费次数共为 <span class=\"several-time\">"+data.consumeCount+"次</span>" +
                    "，换乘次数共为<span class=\"several-time\">"+data.transferCount+"次</span>" +
                    "，拥挤度平均为<span class=\"several-time\">"+handleDecimal(data.ratio)+"</span>";
                $("#desc").append(html)
            }
        },
    });
}

function getCompDescByDays(param) {
    $.ajax({
        type: 'GET',
        url: "/company/"+param.deptNo+"/days/desc",
        data:{
            startTime:param.startTime,
            endTime:param.endTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data
                let html = "<span>" + data.startTime + "到" + data.endTime + "," + data.deptNo + "公司总计" +
                    "总计消费次数共为 <span class=\"several-time\">"+data.consumeCount+"次</span>" +
                    "，换乘次数共为<span class=\"several-time\">"+data.transferCount+"次</span>" +
                    "，拥挤度平均为<span class=\"several-time\">"+handleDecimal(data.ratio)+"</span>";
                $("#desc").append(html)
            }
        },
    });
}

function getGroupDescByDay(param) {
    $.ajax({
        type: 'GET',
        url: "/group/day/desc",
        data:{
            queryTime:param.queryTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data
                let html = "<span>" + data.queryTimeStr + ",集团" +
                    "总计消费次数共为 <span class=\"several-time\">"+data.consumeCount+"次</span>" +
                    "，换乘次数共为<span class=\"several-time\">"+data.transferCount+"次</span>" +
                    "，拥挤度平均为<span class=\"several-time\">"+handleDecimal(data.ratio)+"</span>";
                $("#desc").append(html)
            }
        },
    });
}

function getGroupDescByDays(param) {
    $.ajax({
        type: 'GET',
        url: "/group/days/desc",
        data:{
            startTime:param.startTime,
            endTime:param.endTime
        },
        dataType: 'json',
        success: function (res) {
            $("#desc").empty()
            if (res.code == 200 && res.data != null) {
                let data = res.data
                let html = "<span>" + data.startTime + "到" + data.endTime + ",集团" +
                    "总计消费次数共为 <span class=\"several-time\">"+data.consumeCount+"次</span>" +
                    "，换乘次数共为<span class=\"several-time\">"+data.transferCount+"次</span>" +
                    "，拥挤度平均为<span class=\"several-time\">"+handleDecimal(data.ratio)+"</span>";
                $("#desc").append(html)
            }
        },
    });
}