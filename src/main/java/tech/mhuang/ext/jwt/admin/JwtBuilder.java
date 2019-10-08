package tech.mhuang.ext.jwt.admin;

import tech.mhuang.core.builder.BaseBuilder;
import tech.mhuang.core.util.ObjectUtil;
import tech.mhuang.core.util.StringUtil;
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

    public static class ProducerBuilder implements BaseBuilder<Jwt.JwtBean>{

        private Jwt.JwtBean bean;

        public ProducerBuilder(){
            this.bean = new Jwt.JwtBean();
        }
        public ProducerBuilder enable(boolean enable){
            this.bean.setEnable(enable);
            return this;
        }
        /**
         * 设置客户端id、默认为 authToken
         *
         * @param clientId 客户端id
         * @return 返回构造器
         */
        public ProducerBuilder clientId(String clientId) {
            this.bean.setClientId(clientId);
            return this;
        }

        /**
         * 设置生成Token的签名
         *
         * @param secret 签名
         * @return 构造器
         */
        public ProducerBuilder secret(String secret) {
            this.bean.setSecret(secret);
            return this;
        }

        /**
         * 设置jwt加密值、默认mhuang
         *
         * @param name 加密值
         * @return 构造器
         */
        public ProducerBuilder name(String name) {
            this.bean.setName(name);
            return this;
        }

        /**
         * 设置jwt请求头部键 默认Authorization
         *
         * @param type 头部的键
         * @return 构造器
         */
        public ProducerBuilder type(String type) {
            this.bean.setType(type);
            return this;
        }

        /**
         * 设置jwt请求头部值开头，默认Bearer
         *
         * @param headerName 头部值开头
         * @return 构造器
         */
        public ProducerBuilder headerName(String headerName) {
            this.bean.setHeaderName(headerName);
            return this;
        }

        /**
         * 设置jwt过期的分钟数、默认是30天后过期
         *
         * @param expireMinute 过期分钟数
         * @return 构造器
         */
        public ProducerBuilder expireMinute(Long expireMinute) {
            this.bean.setExpireMinute(expireMinute);
            return this;
        }
        @Override
        public Jwt.JwtBean builder() {
            return this.bean;
        }
    }
    /**
     * 构造器
     */
    public static class Builder implements BaseBuilder<JwtFramework> {

        private Jwt jwt;

        Builder() {
            this.jwt = new Jwt();
        }

        /**
         * 创建jwt生产构造器
         * @return jwt生产构造器
         */
        public ProducerBuilder createProducerBuilder(){
            return new ProducerBuilder();
        }
        public Builder bindProducer(String key, Jwt.JwtBean value) {
            jwt.getBeanMap().put(key, value);
            return this;
        }


        /**
         * 构建JwtFramework 对象、通过start方法进行启动
         *
         * @return
         */
        @Override
        public JwtFramework builder() {
            //拼装数据
            return new JwtFramework(this.jwt);
        }
    }
}