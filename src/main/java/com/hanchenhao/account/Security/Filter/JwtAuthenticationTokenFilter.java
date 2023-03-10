package com.hanchenhao.account.Security.Filter;

import com.hanchenhao.account.DAO.Implement.UserInfoDAO;
import com.hanchenhao.account.Model.Persistence.UserInfo;
import com.hanchenhao.account.Security.Utiils.JwtUtils;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    final UserInfoDAO userInfoDAO;

    @Autowired
    public JwtAuthenticationTokenFilter(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        //获取token
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
        //读取用户信息token，没有token就提示登录，并抛异常
        UserInfo user = userInfoDAO.getUserInfoByUserName(name);
        //存入SecurityContextHolder中
        var authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
