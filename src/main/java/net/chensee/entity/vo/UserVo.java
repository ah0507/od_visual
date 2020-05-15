package net.chensee.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {

    private String id;

    private String username;

    private Integer power;

    private List<String> deptNos;
}
