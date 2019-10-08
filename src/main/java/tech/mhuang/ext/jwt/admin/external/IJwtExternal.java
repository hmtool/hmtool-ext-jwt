package tech.mhuang.ext.jwt.admin.external;

import tech.mhuang.ext.jwt.admin.JwtHelper;

/**
 * jwt 扩展类
 *
 * @author mhuang
 * @since 1.0.0
 */
public interface IJwtExternal {

    /**
     * 默认创建的方法，可重构
     *
     * @return 返回jwt生产接口
     */
    default IJwtProducer create(String key) {
        return new JwtHelper();
    }
}
