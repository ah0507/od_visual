function getDayTime() {
    let currentDate = new Date()
    currentDate.setTime(currentDate.getTime() - 2 * 24 * 60 * 60 * 1000);
    let monthStr = (currentDate.getMonth() + 1).toLocaleString();
    let dayStr = currentDate.getDate().toLocaleString();
    return currentDate.getFullYear() + "-" + getMonthStr(monthStr) + "-" + getDayStr(dayStr);
}

function getWeekTime() {
    let currentDate = new Date()
    // 获取上一周的开始和结束时间
    let weekDate = new Date(currentDate.getTime() - 7 * 24 * 3600 * 1000);// 计算开始时间用
    let weekDate2 = new Date(currentDate.getTime() - 7 * 24 * 3600 * 1000);// 计算结束时间用

    let day = weekDate.getDay();
    let time = weekDate.getDate() - day + (day === 0 ? -6 : 1);

    let startDate = new Date(weekDate.setDate(time));
    let startMonthStr = (startDate.getMonth() + 1).toLocaleString();
    let startDateStr = startDate.getDate().toLocaleString();
    let beginTime = startDate.getFullYear() + '-' + getMonthStr(startMonthStr) + '-' + getDayStr(startDateStr);
    let endDate = new Date(weekDate2.setDate(time + 6));
    let endMonthStr = (endDate.getMonth() + 1).toLocaleString();
    let endDateStr = endDate.getDate().toLocaleString();
    let endTime = endDate.getFullYear() + '-' + getMonthStr(endMonthStr) + '-' + getDayStr(endDateStr);
    return beginTime + "," + endTime;
}

function getMonthTime() {
    let currentDate = new Date()
    let monthStr = currentDate.getMonth().toLocaleString();
    if (monthStr.length == 1) {
        monthStr = "0" + monthStr
    }
    return currentDate.getFullYear() + "-" + monthStr;
}

function getMonthStr(monthStr) {
    if (monthStr.length == 1) {
        monthStr = "0" + monthStr
    }
    return monthStr
}

function getDayStr(dayStr) {
    if (dayStr.length == 1) {
        dayStr = "0" + dayStr
    }
    return dayStr
}