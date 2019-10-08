package tech.mhuang.ext.jwt.admin.bean;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
     * 可指定多个key,value
     */
    private Map<String,JwtBean> beanMap = new ConcurrentHashMap<>();

    /**
     * jwt的bean
     */
    @Data
    public static class JwtBean{
        public JwtBean(){
            this.clientId = JwtConsts.DEFAULT_CLIENT_ID;
            this.secret = JwtConsts.DEFAULT_SECRET;
            this.name = JwtConsts.DEFAULT_NAME;
            this.type = JwtConsts.DEFAULT_TYPE;
            this.headerName = JwtConsts.DEFAULT_HEADER_NAME;
            this.expireMinute = JwtConsts.DEFAULT_EXPIRE_MINUTE;
        }

        /**
         * 是否开启JWT
         */
        private boolean enable;
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
         * 过期时间、分钟、默认30天
         */
        private Long expireMinute;
    }
}