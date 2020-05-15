function submit() {
    var pageNumber = $("#pageNumber").val();
    var pageSize = $("#pageSize").val();
    var inTimeValue = $("#inTimeValue").val();
    var outTimeValue = $("#outTimeValue").val();
    var addTimeValue = $("#addTimeValue").val();
    var addInAndOutTimeValue = $("#addInAndOutTimeValue").val();
    var endStationTimeValue = $("#endStationTimeValue").val();
    var distanceValue = $("#distanceValue").val();
    var regularTravelChanceValue = $("#regularTravelChanceValue").val();
    var expireTimeRangeValue = $("#expireTimeRangeValue").val();
    var regularTravelTimeValue = $("#regularTravelTimeValue").val();
    var addTicketValue = $("#addTicketValue").val();
    var ratedNumber = $("#ratedNumber").val();
    var calculateHandleTime = $("#calculateHandleTime").val();
    var obj = {};
    obj["pageNumber"] = pageNumber;
    obj["pageSize"] = pageSize;
    obj["inTimeValue"] = inTimeValue;
    obj["outTimeValue"] = outTimeValue;
    obj["addTimeValue"] = addTimeValue;
    obj["addInAndOutTimeValue"] = addInAndOutTimeValue;
    obj["endStationTimeValue"] = endStationTimeValue;
    obj["distanceValue"] = distanceValue;
    obj["regularTravelChanceValue"] = regularTravelChanceValue;
    obj["expireTimeRangeValue"] = expireTimeRangeValue;
    obj["regularTravelTimeValue"] = regularTravelTimeValue;
    obj["addTicketValue"] = addTicketValue;
    obj["ratedNumber"] = ratedNumber;
    obj["calculateHandleTime"] = calculateHandleTime;
    $.ajax({
        url: "/visual",
        type: "POST",
        data: JSON.stringify(obj),
        contentType:"application/json",
        success: function (data) {
            alert("添加成功！");
            $("#pageNumber").val("");
            $("#pageSize").val("");
            $("#inTimeValue").val("");
            $("#outTimeValue").val("");
            $("#addTimeValue").val("");
            $("#addInAndOutTimeValue").val("");
            $("#endStationTimeValue").val("");
            $("#distanceValue").val("");
            $("#expireTimeRangeValue").val("");
            $("#regularTravelChanceValue").val("");
            $("#regularTravelTimeValue").val("");
            $("#addTicketValue").val("");
            $("#ratedNumber").val("");
            $("#calculateHandleTime").val("");
        }
    });
}