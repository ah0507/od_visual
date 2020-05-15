weekAndMonthView = function (params) {

    //周视图 柱状图
    generateTransfChartBar(params);
    generateNumOfConsumeChartBar(params);
    generateNumOfGetOffChartBar(params);
    generateNumOfTransferChartBar(params);

    //周视图 柱状折线图
    generateSiteRankBarLine(params);
    generateCrowdChartBarLine(params);
    generateSaleCountBarLine(params);

    //周视图 五峰柱状图
    generateFiveChartFiveBars(params);

    //周视图 饼状图
    generateCardTypePie(params);

}
