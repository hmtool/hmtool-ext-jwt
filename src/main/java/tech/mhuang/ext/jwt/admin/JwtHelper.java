package tech.mhuang.ext.jwt.admin;

import tech.mhuang.core.date.DateTimeUtil;
import tech.mhuang.ext.jwt.admin.bean.Jwt;
import tech.mhuang.ext.jwt.admin.external.IJwtProducer;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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

    private Jwt jwt;

    @Override
    public void add(Jwt jwt) {
        this.jwt = jwt;
    }

    /**
     * 解析Token
     *
     * @param jsonWebToken 解析的token
     * @return
     */
    @Override
    public Map<String,Object> parse(String jsonWebToken) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwt.getSecret()))
                .parseClaimsJws(jsonWebToken).getBody();
    }

    /**
     * 刷新Token
     *
     * @param claims 刷新的数据
     * @return
     */
    @Override
    public String refresh(Map<String,Object> claims) {
        return create(claims);
    }

    /**
     * 创建Token
     *
     * @param claims 需要创建的数据
     * @return
     */
    @Override
    public String create(Map<String,Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwt.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setIssuer(jwt.getName())
                .setAudience(jwt.getClientId())
                .signWith(signatureAlgorithm, signingKey);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime dateTime = currentTime.plusMinutes(jwt.getExpireSecond());
        builder.setExpiration(DateTimeUtil.localDateTimeToDate(dateTime)).setNotBefore(DateTimeUtil.localDateTimeToDate(currentTime));
        return builder.compact();
    }
}
