

$(function () {
    $.ajax({
        type: 'GET',
        url: "/getUser",
        success: function (res) {
            if (res.power == 0) {
                getAllLines();
            } else {
                $('.company-wrapper').empty()
                powerIsOne(res.deptNos)
            }
        },
    });
});

function getAllLines(){
    $.ajax({
        type: 'GET',
        url: "/line",
        dataType: 'json',
        success: function (result) {
            let linesHtml = ''
            let lineList = result.data
            for (let i = 0; i < lineList.length; i++) {
                linesHtml += '<div class="company-weekly">' + lineList[i] + '</div>'
            }
            $('.company-wrapper').html(linesHtml);
        },
    });
}

function powerIsOne(deptNos) {
    $.ajax({
        type: 'GET',
        url: "/company/lineNos",
        data:{
          deptNos:deptNos.join(",")
        },
        success: function (result) {
            let lineHtml = '';
            let lineList = result.data
            for (var i = 0; i < lineList.length; i++) {
                lineHtml += '<div class="company-weekly">' + lineList[i] + '</div>'
            }
            $('.company-wrapper').append(lineHtml);
            $(".choice-company").text(lineList[lineList.length-1])
        },
    });
}