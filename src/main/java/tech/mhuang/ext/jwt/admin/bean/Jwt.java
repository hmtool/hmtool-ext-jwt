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

    /**
     * 默认是authToken
     */
    private String clientId;

    /**
     * 默认是aHVhbmdtaWFv
     */
    private String secret;

    /**
     * 默认是mhuang
     */
    private String name;

    /**
     * 默认是Authorization，请求Header的头
     */
    private String type;

    /**
     * header值前缀必带参数,默认是Bearer
     */
    private String headerName;

    /**
     * 过期时间、毫秒级、默认1分钟
     */
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
