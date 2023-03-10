package com.hanchenhao.account.Security.Filter;

import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Security.LoginDetails.LoginUserDetails;
import com.hanchenhao.account.Security.Utiils.JwtUtils;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    final UserInfoDAO userInfoDAO;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public JwtAuthenticationTokenFilter(UserInfoDAO userInfoDAO,
                                        RedisTemplate<String, Object> redisTemplate) {
        this.userInfoDAO = userInfoDAO;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        //从请求头获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNullOrEmpty(token)) {
            try {
                filterChain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return;
        }

        String name = JwtUtils.parseJwt(token);
        // Redis 通过token读取用户名，获取详细信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) redisTemplate.opsForValue().get("UserDetails:" + name);
        // Redis读取用户信息失败，报异常提示登录
        if (Objects.isNull(loginUserDetails)){
            throw new RuntimeException("Redis读取用户信息失败，请重新登陆");
        }
        //存入SecurityContextHolder中
        var authentication = new UsernamePasswordAuthenticationToken(loginUserDetails.getUsername(),
                loginUserDetails.getPassword(), loginUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
