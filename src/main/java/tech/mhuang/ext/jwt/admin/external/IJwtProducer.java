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

    public void name(String name);
    /**
     * 添加jwt配置
     *
     * @param jwt 配置
     */
    public void add(Jwt.JwtBean jwt);

    /**
     * 解析token
     *
     * @param jsonWebToken token
     * @return 返回解析后的map对象
     */
    public Map<String, Object> parse(String jsonWebToken);

    /**
     * 刷新token
     *
     * @param claims 通过参数进行刷新
     * @return 返回刷新后的token
     */
    public String refresh(Map<String, Object> claims);

    /**
     * 创建token
     *
     * @param claims 传递需要的参数、解析后可获得
     * @return 返回创建后的加密Token
     */
    public String create(Map<String, Object> claims);
}
