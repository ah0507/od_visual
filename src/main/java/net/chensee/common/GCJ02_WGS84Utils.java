package net.chensee.common;

import net.chensee.entity.vo.LocateInfo;

/**
 * @author ah
 * @title: 地图坐标转换
 * @date 2020/3/4 16:27
 */
public class GCJ02_WGS84Utils {

    public static double pi = 3.1415926535897932384626;//圆周率
    public static double a = 6378245.0;//克拉索夫斯基椭球参数长半轴a
    public static double ee = 0.00669342162296594323;//克拉索夫斯基椭球参数第一偏心率平方
    //从GPS转高德
    public static LocateInfo wgs84_To_Gcj02(double lat, double lon) {
        LocateInfo info = new LocateInfo();
        if (isOutOfChina(lat, lon)) {
            info.setChina(false);
            info.setLat(lat);
            info.setLng(lon);
        }else {
            double dLat = transformLat(lon - 105.0, lat - 35.0);
            double dLon = transformLon(lon - 105.0, lat - 35.0);
            double radLat = lat / 180.0 * pi;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
            double mgLat = lat + dLat;
            double mgLon = lon + dLon;
            info.setChina(true);
            info.setLat(mgLat);
            info.setLng(mgLon);
        }
        return info;
    }
    //从高德转到GPS
    public static LocateInfo gcj02_To_Wgs84(double lat, double lon) {
        LocateInfo info = new LocateInfo();
        LocateInfo gps = transform(lat, lon);
        double lontitude = lon * 2 - gps.getLng();
        double latitude = lat * 2 - gps.getLat();
        info.setChina(gps.isChina());
        info.setLat(latitude);
        info.setLng(lontitude);
        return info;
    }
    // 判断坐标是否在国外
    private static boolean isOutOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347) {
            return true;
        }
        if (lat < 0.8293 || lat > 55.8271) {
            return true;
        }
        return false;
    }
    //转换
    private static LocateInfo transform(double lat, double lon) {
        LocateInfo info = new LocateInfo();
        if (isOutOfChina(lat, lon)) {
            info.setChina(false);
            info.setLat(lat);
            info.setLng(lon);
            return info;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        info.setChina(true);
        info.setLat(mgLat);
        info.setLng(mgLon);

        return info;
    }
    //转换纬度所需
    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }
    //转换经度所需
    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

}
