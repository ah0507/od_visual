$(function () {
    initUser();
});
function initUser() {
    $.ajax({
        type: 'GET',
        url: "/getUser",
        dataType: 'json',
        success: function (res) {
            if (res.power == 0 || res.power == 1) {
                powerIsOne()
            }
            if (res.power == 2) {
                let companyHtml = ''
                let companyList = res.deptNos
                for (let i = 0; i < companyList.length; i++) {
                    companyHtml += '<div class="company-weekly">' + companyList[i] + '</div>'
                }
                $('.company-wrapper').html(companyHtml);
                $(".choice-company").text(res.deptNos[0])
            }
        },
    });
}
function powerIsOne(){
    $.ajax({
        type: 'GET',
        url: "/company",
        dataType: 'json',
        success: function (result) {
            let companyHtml = ''
            let companyList = result.data
            for (let i = 0; i < companyList.length; i++) {
                companyHtml += '<div class="company-weekly">' + companyList[i] + '</div>'
            }
            $('.company-wrapper').html(companyHtml);
        },
    });
}
