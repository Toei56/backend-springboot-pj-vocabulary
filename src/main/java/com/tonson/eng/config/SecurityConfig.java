package com.tonson.eng.config;

import com.tonson.eng.security.TokenFilter;
import com.tonson.eng.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    // กำหนดกฎการอนุญาตสำหรับรูปแบบคำขอ HTTP

    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    private TokenFilter tokenFilter() {
        return new TokenFilter(tokenService);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // ปิดการใช้งาน csrf
                .authorizeHttpRequests(authz -> authz
                        // อนุญาตให้เข้าถึง /vocabulary โดยไม่ต้องมีการตรวจสอบการเข้าถึง
                        .requestMatchers("/vocabulary").permitAll()
                        // path /vocabulary/* อนุญาตให้ใช้ HttpMethod.DELETE เฉพาะ role = admin
//                        .requestMatchers(HttpMethod.DELETE, "/vocabulary/*").hasAnyAuthority("admin")
                        // อนุญาตให้เข้าถึง /auth/register โดยไม่ต้องมีการตรวจสอบการเข้าถึง
                        .requestMatchers("/auth/register", "/auth/login").permitAll()

                        // คำขออื่นๆ ที่ไม่ได้กำหนด ต้องได้รับการตรวจสอบทั้งมด
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handling -> handling // กำหนดค่าวิธีจัดการกับความพยายามในการเข้าถึงโดยไม่ได้รับอนุญาต
                        .authenticationEntryPoint( // คำขอที่ไม่ได้รับอนุญาต โดยจะส่งรหัสสถานะ HTTP 401 ในการตอบกลับ
                                (req, res, err) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
