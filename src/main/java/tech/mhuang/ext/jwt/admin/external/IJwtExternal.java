package tech.mhuang.ext.jwt.admin.external;

import tech.mhuang.ext.jwt.admin.JwtHelper;

/**
 *
 * jwt 扩展类
 *
 * @author mhuang
 * @since 1.0.0
 */
public interface IJwtExternal {

    default IJwtProducer create(){
        return new JwtHelper();
    }
}
