package tech.mhuang.ext.jwt.admin;

import tech.mhuang.core.builder.BaseBuilder;
import tech.mhuang.ext.jwt.admin.bean.Jwt;

/**
 * jwt工厂类
 *
 * @author mhuang
 * @since 1.0.0
 */
public class JwtBuilder {

    /**
     * 构建jwt构造器
     *
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 构造器
     */
    public static class Builder implements BaseBuilder<JwtFramework> {

        private Jwt jwt;

        Builder() {
            jwt = new Jwt();
        }

        public Builder clientId(String clientId) {
            this.jwt.setClientId(clientId);
            return this;
        }

        public Builder secret(String secret) {
            this.jwt.setSecret(secret);
            return this;
        }

        public Builder name(String name) {
            this.jwt.setName(name);
            return this;
        }

        public Builder type(String type) {
            this.jwt.setType(type);
            return this;
        }

        public Builder headerName(String headerName) {
            this.jwt.setHeaderName(headerName);
            return this;
        }

        public Builder expireSecond(Long expireSecond) {
            this.jwt.setExpireSecond(expireSecond);
            return this;
        }

        @Override
        public JwtFramework builder() {
            return new JwtFramework(this.jwt);
        }
    }
}
