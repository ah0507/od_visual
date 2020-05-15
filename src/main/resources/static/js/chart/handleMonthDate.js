handleDate = function (dateString) {
    //处理月的日期
    let dateStr = dateString.split("-")
    let year = dateStr[0]
    let month = dateStr[1]
    let date = new Date(year, month, 0);
    return  dateString+'-'+date.getDate()
}


function handleWeek(date) {
    switch (new Date(date).getDay()) {
        case 0:
            return "星期天";
        case 1:
            return "星期一";
        case 2:
            return "星期二";
        case 3:
            return "星期三";
        case 4:
            return "星期四";
        case 5:
            return "星期五";
        case 6:
            return "星期六";
    }
}