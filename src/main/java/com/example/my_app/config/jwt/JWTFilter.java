package com.example.my_app.config.jwt;


import com.example.my_app.config.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final MyUserDetailService myUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // Проверяем, есть ли в заголовке Authorization Bearer токен
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtService.extractUsername(jwt);
        }

        // Если username есть и токен валидный, создаем аутентификацию
        if (username != null && jwtService.validateToken(jwt)) {
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

            // Извлекаем роли из токена
            List<String> roles = jwtService.extractRoles(jwt);  // Извлекаем роли из токена

            // Преобразуем роли в объекты GrantedAuthority
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)  // Преобразуем роли в SimpleGrantedAuthority
                    .collect(Collectors.toList());  // Преобразуем список в коллекцию

            // Создаем объект Authentication с деталями пользователя и ролями
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            // Устанавливаем детали аутентификации
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Устанавливаем аутентификацию в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Переходим к следующему фильтру в цепочке
        filterChain.doFilter(request, response);
    }
}
