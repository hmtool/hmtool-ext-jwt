package tech.mhuang.ext.jwt.admin;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tech.mhuang.core.date.DateTimeUtil;
import tech.mhuang.ext.jwt.admin.bean.Jwt;
import tech.mhuang.ext.jwt.admin.external.IJwtProducer;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * jwt帮助类
 *
 * @author mhuang
 * @since 1.0.0
 */
public class JwtHelper implements IJwtProducer {

    private String name;
    private Jwt.JwtBean jwt;

    @Override
    public void name(String name) {
        this.name = name;
    }

    @Override
    public void add(Jwt.JwtBean jwt) {
        this.jwt = jwt;
    }

    /**
     * 解析Token
     *
     * @param jsonWebToken 解析的token
     * @return 解析后的数据
     */
    @Override
    public Map<String, Object> parse(String jsonWebToken) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwt.getSecret()))
                .parseClaimsJws(jsonWebToken).getBody();
    }

    /**
     * 刷新Token
     *
     * @param claims 刷新的数据
     * @return 返回新token
     */
    @Override
    public String refresh(Map<String, Object> claims) {
        return create(claims);
    }

    private final static String TYPE = "type";
    private final static String VALUE = "JWT";

    /**
     * 创建Token
     *
     * @param claims 需要创建的数据
     * @return token
     */
    @Override
    public String create(Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwt.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setHeaderParam(TYPE, VALUE)
                .setClaims(claims)
                .setIssuer(jwt.getName())
                .setAudience(jwt.getClientId())
                .signWith(signatureAlgorithm, signingKey);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime dateTime = currentTime.plusMinutes(jwt.getExpireMinute());
        builder.setExpiration(DateTimeUtil.localDateTimeToDate(dateTime)).setNotBefore(DateTimeUtil.localDateTimeToDate(currentTime));
        return builder.compact();
    }
}