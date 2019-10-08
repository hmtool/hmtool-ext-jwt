package tech.mhuang.ext.jwt.admin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import tech.mhuang.core.check.CheckAssert;
import tech.mhuang.ext.jwt.admin.bean.Jwt;
import tech.mhuang.ext.jwt.admin.external.IJwtExternal;
import tech.mhuang.ext.jwt.admin.external.IJwtProducer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Jwt框架实现
 *
 * @author mhuang
 * @since 1.0.0
 */
@Slf4j
public class JwtFramework {

    /**
     * jwt参数
     */
    @Getter
    private Jwt jwt;

    /**
     * jwt扩展
     */
    @Getter
    private IJwtExternal external;

    @Getter
    private Map<String, IJwtProducer> producerMap = new ConcurrentHashMap<>();

    /**
     * 获取对应创建key的生产类
     *
     * @param key 键
     * @return jwt生产类
     */
    public IJwtProducer getProducer(String key) {
        return this.producerMap.get(key);
    }

    /**
     * 自定义jwt扩展、可通过重构IJwtExternal方法重写Jwt实现、默认通过JwtHelper实现
     *
     * @param external 扩展接口、可实现后进行重构
     * @return
     */
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
        log.info("正在启动JWT配置");
        if (external == null) {
            external = new IJwtExternal() {
            };
        }
        CheckAssert.check(jwt, "没有找到JWT对象配置");
        jwt.getBeanMap().forEach((key, bean) -> {
            IJwtProducer jwtProducer = external.create(key);
            jwtProducer.add(bean);
            jwtProducer.name(key);
            this.producerMap.put(key, jwtProducer);
        });
        log.info("JWT启动成功");
    }
}