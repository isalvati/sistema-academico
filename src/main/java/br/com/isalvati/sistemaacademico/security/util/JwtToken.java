package br.com.isalvati.sistemaacademico.security.util;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtToken {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * Nome do usuario
     */
    private static final String CLAIM_USERNAME = "name";

    /**
     * Data e criacao do token
     */
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * Id do registro do usuario config
     */
    private static final String ID_CLAIM = "identifier";

    /**
     * Identificador de destinatário do JWT.
     */
    private static final String AUDIENCE = "ms_user";

    /**
     * Signatário do JWT.
     */
    private static final String ISSUER = "issuer";

    /**
     * Tempo de expiração do token
     * token com duração de 1 dias
     */
    private static final int EXPIRATION_TIME = 1440;

    /**
     * Chave de criptografia do jwt
     *
     */
    SignatureAlgorithm ALGORITHMKEY = SignatureAlgorithm.HS256;
    private final Key KEY = MacProvider.generateKey(ALGORITHMKEY);

    /**
     * Chave de criptografia do jwt
     *
     */
    private String USER_PROFILE = "PROFILE";

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(JwtToken.class);

    /**
     * Retorna um novo token JWT com base nos dados do usuários.
     *
     * @param user
     * @return String
     */
    public String obterToken(User user) throws SistemaAcademicoException {
        SystemUserEntity systemUser = systemUserService.findByUsername(user.getUsername());
        if (StringUtils.isEmpty(systemUser)) {
            log.error("Erro ao buscar informacoes de usuario");
            throw new SistemaAcademicoException("Erro ao buscar informacoes de usuario");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USERNAME, systemUser.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(ID_CLAIM, systemUser.getId());
        claims.put(AUDIENCE, systemUser.getUsername());
        claims.put(ISSUER, systemUser.getProfile());
        return createToken(claims);
    }

    /**
     * Obtém o username contido no token JWT.
     *
     * @param token
     * @return String
     */
    public String getUsername(String token) {
        String username;
        try {
            Claims claims = getClaims(token);
            username = claims.get(CLAIM_USERNAME, String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Obtém o Identificador de destinatário do JWT
     *
     * @param token
     * @return String
     */
    public String getAudience(String token) {
        String audience;
        try {
            Claims claims = getClaims(token);
            audience = claims.get(AUDIENCE, String.class);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    /**
     * Obtém o Signatário do JWT.
     *
     * @param token
     * @return String
     */
    public String getIssuer(String token) {
        String issuer;
        try {
            Claims claims = getClaims(token);
            issuer = claims.get(ISSUER, String.class);
        } catch (Exception e) {
            issuer = null;
        }
        return issuer;
    }

    /**
     * Obtém o username contido no token JWT.
     *
     * @param token
     * @return String
     */
    public Integer getId(String token) {
        Integer id;
        try {
            Claims claims = getClaims(token);
            id = claims.get(ID_CLAIM, Integer.class);
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    /**
     * Retorna a data de expiração de um token JWT.
     *
     * @param token
     * @return Date
     */
    public Date getExpirationDate(String token) {
        Date expiration;
        try {
            Claims claims = getClaims(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * Verifica e retorna se um token JWT é válido.
     *
     * @param token
     * @return boolean
     */
    public boolean validToken(String token) {
        return !tokenExpired(token);
    }

    /**
     * Renova um token (refresh).
     *
     * @param token
     * @return String
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaims(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = createToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * Realiza o parse do token JWT para extrair as informações contidas no
     * corpo dele.
     *
     * @param token
     * @return Claims
     */
    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Retorna a data de expiração com base na data atual.
     *
     * @return Date
     */
    private Date createDateExpired() {
        return new Date(System.currentTimeMillis() + (EXPIRATION_TIME * 60000));
    }

    /**
     * Verifica se um token JTW está expirado.
     *
     * @param token
     * @return boolean
     */
    private boolean tokenExpired(String token) {
        Date dataExpiracao = this.getExpirationDate(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.before(new Date());
    }

    /**
     * Gera um novo token JWT contendo os dados (claims) fornecidos.
     *
     * @param claims
     * @return String
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(ALGORITHMKEY, KEY)
                .setExpiration(createDateExpired()).compact();
    }

}
