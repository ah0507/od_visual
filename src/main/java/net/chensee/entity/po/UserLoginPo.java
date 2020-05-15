package net.chensee.entity.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "od_user_login")
public class UserLoginPo {

    @Id
    private String id;

    private String username;

    private String password;

    /**
     * 0 管理员，1 集团权限，2 公司权限
     */
    private Integer power;

    /**
     * 公司号
     */
    private List<String> deptNos;

}
