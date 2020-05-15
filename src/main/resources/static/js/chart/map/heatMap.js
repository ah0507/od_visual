showLineDayHeatMap = function(id,param){
    heatMap.init(id, param, "/line/day/"+param.lineNo+"/"+param.direction+"/stationConsume");
}
showLineDaysHeatMap = function(id,param){
    heatMap.init(id, param, "/line/days/"+param.lineNo+"/"+param.direction+"/stationConsume");
}
showCompanyDayHeatMap = function(id,param){
    heatMap.init(id, param, "/company/"+param.deptNo+"/day/stationConsume");
}
showCompanyDaysHeatMap = function(id,param){
    heatMap.init(id, param, "/company/"+param.deptNo+"/days/stationConsume");
}
showGroupDayHeatMap = function(id,param){
    heatMap.init(id, param, "/group/day/stationConsume");
}
showGroupDaysHeatMap = function(id,param){
    heatMap.init(id, param, "/group/days/stationConsume");
}

let map;
heatMap = {
    heatMapData:"",
    init: function (id,param,url) {
        let that = this
        if (!isSupportCanvas()) {
            alert('热力图仅对支持canvas的浏览器适用,您所使用的浏览器不能使用热力图功能,请换个浏览器试试~')
        }
        that.getMap(id);
        that.request(param,url);
        // that.addHeatMap()
    },
    getMap:function(id){
        let initMap = new AMap.Map(id, {
            resizeEnable: true,
            center: [117.20, 39.12],
            zoom: 6
        });
        map = initMap;
    },
    request: function(param,url){
        let that = this
        $.ajax({
            url:url,
            data:param,
            type:"get",
            dataType: 'json',
            success: function (result) {
                that.heatMapData = result.data
                that.handleConsumeCount();
                that.addHeatMap();
            }
        })
    },
    handleConsumeCount: function(){
        let that = this
        let data = that.heatMapData
        for (let i in data) {
            data[i].count = data[i].consumeCount;
        }
        that.heatMapData = data;
    },
    handleOffCount: function(){
        let that = this
        let data = that.heatMapData
        for (let i in data) {
            data[i].count = data[i].offCount;
        }
        that.heatMapData = data;
    },
    handleTransferCount: function(){
        let that = this
        let data = that.heatMapData
        for (let i in data) {
            data[i].count = data[i].transferCount;
        }
        that.heatMapData = data;
    },
    getConsumeCountMap(id){
        let that = this
        that.reInitMap(id);
        that.handleConsumeCount();
        that.addHeatMap();
    },
    getOffCountMap(id){
        let that = this
        that.reInitMap(id);
        that.handleOffCount();
        that.addHeatMap();
    },
    getTransferCountMap(id){
        let that = this
        that.reInitMap(id);
        that.handleTransferCount();
        that.addHeatMap();
    },
    reInitMap(id){
        let that = this
        if (id) {
            map = null;
            that.getMap(id)
        }
    },
    addHeatMap: function () {
        let that = this
        map.plugin(["AMap.Heatmap"], function () {
            //初始化heatmap对象
            heatmap = new AMap.Heatmap(map, {
                radius: 15, //给定半径
                opacity: [0, 0.8],
                gradient: {
                    0.5: 'blue',
                    0.65: 'rgb(117,211,248)',
                    0.7: 'rgb(0, 255, 0)',
                    0.9: '#ffea00',
                    1.0: 'red'
                }
            });
            heatmap.setDataSet({
                data: that.heatMapData,
                max: 60
            });

        });
        /*new Promise(resolve =>{
            let heatData = [];
            for (let j = 0; j < that.heatMapData.length; j++) {
                let heatMapData = that.heatMapData[j];
                let obj = {};
                obj.count = heatMapData.count;
                AMap.convertFrom(new AMap.LngLat(heatMapData.lat, heatMapData.lng), "gps", function (status, res) {
                    let location = res.locations[0]
                    obj.lng = location.lng
                    obj.lat = location.lat
                    heatData.push(obj)
                    if (that.heatMapData.length == j + 1) {
                        resolve(heatData);
                    }
                });
            }
        }).then(val=>{
            map.plugin(["AMap.Heatmap"], function () {
                //初始化heatmap对象
                heatmap = new AMap.Heatmap(map, {
                    radius: 15, //给定半径
                    opacity: [0, 0.8],
                    gradient: {
                        0.5: 'blue',
                        0.65: 'rgb(117,211,248)',
                        0.7: 'rgb(0, 255, 0)',
                        0.9: '#ffea00',
                        1.0: 'red'
                    }
                });
                heatmap.setDataSet({
                    data: val,
                    max: 60
                });

            });
        });*/
    }
}

//判断浏览区是否支持canvas
function isSupportCanvas() {
    let elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}
