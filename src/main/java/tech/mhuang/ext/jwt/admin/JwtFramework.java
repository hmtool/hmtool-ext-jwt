package tech.mhuang.ext.jwt.admin;

import tech.mhuang.ext.jwt.admin.bean.Jwt;
import tech.mhuang.ext.jwt.admin.external.IJwtExternal;
import tech.mhuang.ext.jwt.admin.external.IJwtProducer;
import lombok.Data;

/**
 * Jwt框架实现
 *
 * @author mhuang
 * @since 1.0.0
 */
@Data
public class JwtFramework {

    private Jwt jwt;

    private IJwtExternal external;

    private IJwtProducer producer;

    public JwtFramework external(IJwtExternal external) {
        this.external = external;
        return this;
    }

    public JwtFramework(Jwt jwt) {
        this.jwt = jwt;
    }

    /**
     * 运行
     */
    public void start() {
        if (external == null) {
            external = new IJwtExternal() {
            };
        }
        this.producer = external.create();
        this.producer.add(jwt);
    }
}
