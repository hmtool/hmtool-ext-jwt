package tech.mhuang.ext.jwt.admin.bean;

import lombok.Data;

/**
 *
 * JWT 配置类
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
public class Jwt {

    private String clientId;
    private String secret;
    private String name;
    private String type;
    private String headerName;
    private Long expireSecond;

    public Jwt(){
        this.clientId = JwtConsts.DEFAULT_CLIENT_ID;
        this.secret = JwtConsts.DEFAULT_SECRET;
        this.name = JwtConsts.DEFAULT_NAME;
        this.type = JwtConsts.DEFAULT_TYPE;
        this.headerName = JwtConsts.DEFAULT_HEADER_NAME;
        this.expireSecond = JwtConsts.DEFAULT_EXPIRE_SECOND;
    }
}
