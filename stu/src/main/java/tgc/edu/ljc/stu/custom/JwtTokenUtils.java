package tgc.edu.ljc.stu.custom;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tgc.edu.ljc.stu.entity.SysUser;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
	public static final String SUBJECT = "ljc";  //作者
	public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
	public static final String APPSECRET_KEY = "xyz_1234"; //加密过程要掺的沙子
	private static final String ROLE_CLAIMS = "rol";

	public static String generateJsonWebToken(SysUser user) {
	    if (user.getId()==null||user.getUsername() == null) {
	            return null;
	        }
	        Map<String,Object> map = new HashMap<>();   
	        map.put(ROLE_CLAIMS, user.getRoles().toString());
	    String token = Jwts
	            .builder()
	            .setSubject(SUBJECT)
	            .setClaims(map)
	            .claim("id", user.getId())
	            .claim("name", user.getUsername())
	            //.claim("img", user.getFaceImage())
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
	            .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
	    return token;
	}
	
	/**
	 * 生成token
	 * @param username
	 * @param name
	 * @param roles 
	 * @return
	 */
	public static String createToken(String username,String name, String roles) {
	    Map<String,Object> map = new HashMap<>();   
	    map.put(ROLE_CLAIMS, roles);
	
	    String token = Jwts
	            .builder()
	            .setSubject(username)
	            .setClaims(map)
	            .claim("username",username)
	            .claim("name", name)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
	            .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
	    return token;
	}
	
	public static Claims checkJWT(String token) {
	    try {
	        final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
	        return claims;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * 获取用户姓名
	 * @param token
	 * @return
	 */
	public static String getName(String token) {
	    Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
	    return claims.get("name").toString();
	}
	
	/**
	 * 获取用户名
	 * @param token
	 * @return
	 */
	public static String getUsername(String token){
	    Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
	    return claims.get("username").toString();
	}
	
	/**
	 * 获取用户角色
	 * @param token
	 * @return
	 */
	public static String getUserRole(String token){
	    Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
	    return claims.get(ROLE_CLAIMS).toString();
	}
	
	/**
	 * 是否过期
	 * @param token
	 * @return
	 */
	public static boolean isExpiration(String token){
	    Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
	    return claims.getExpiration().before(new java.util.Date());
	}
}
