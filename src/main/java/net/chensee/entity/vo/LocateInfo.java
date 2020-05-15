package net.chensee.entity.vo;

import lombok.Data;

/**
 * @author ah
 * @title: 转换之后的坐标信息
 * @date 2020/3/4 16:28
 */
@Data
public class LocateInfo {

    private boolean China;

    private Double lat;

    private Double lng;
}
