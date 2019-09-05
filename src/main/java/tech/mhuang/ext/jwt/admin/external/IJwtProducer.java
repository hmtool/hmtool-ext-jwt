package tech.mhuang.ext.jwt.admin.external;

import tech.mhuang.ext.jwt.admin.bean.Jwt;

import java.util.Map;

/**
 * jwt生产器
 *
 * @author mhuang
 * @since 1.0.0
 */
public interface IJwtProducer {

    public void add(Jwt jwt);

    public Map<String, Object> parse(String jsonWebToken);

    public String refresh(Map<String, Object> claims);

    public String create(Map<String, Object> claims);
}
