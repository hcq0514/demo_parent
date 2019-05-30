package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JWTApplication.class)
public class JWTApplicationTest {


    @Test
    public void createJwt() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Hcq").signWith(key).compact();
        assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Hcq");
    }

    @Test
    public void createJwt2() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder()
                .setId("888")
                .setSubject("Hcq")
//                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
        System.out.println(jws);
    }

    @Test
    public void ParseJwtTest() {

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Map map = new HashMap(5);
        map.put("name", "hcq");
        map.put("name2", "hcq2");
        String token = Jwts.builder()
                .setId("88")
                .setSubject("Hcq")
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
        System.out.println(token);
        System.out.println(key.getEncoded());
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("IssuedAt:" + claims.getIssuedAt());
        System.out.println("IssuedAt:" + claims.get("name"));
        System.out.println("IssuedAt:" + claims.get("user"));
    }


    //Sample method to construct a JWT
    public String createJWT2(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("HCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQ");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey,signatureAlgorithm);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    //Sample method to validate and read the JWT
    public void parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("HCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQHCQ"))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }

    @Test
    public void testJwt(){
        String jwt = this.createJWT2("66", "hcq", "test", 3600L);
        this.parseJWT(jwt);
    }


}