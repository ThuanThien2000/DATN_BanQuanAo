package poly.quanlyquanao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                    .requestMatchers("/api/**").permitAll() // Công khai toàn bộ đường link api

                        // Công khai
                        .requestMatchers(
                            "/api/auth/**",
                            "/api/shop/**",
                            "/api/product/{productId}/image/all",
                            "/api/checkout/get-product-detail",
                            "/api/payment/**",
                            "/api/paymentmethods/**",
                            "/api/products/detail/**",
                            "/api/products/lowstock",
                            "/api/reviews",
                            "/api/shop/{productCode}/**"
                        )
                        .permitAll()
//
//                        // Bất kỳ ai đăng nhập đều truy cập được tức tất cả role
//                        .requestMatchers(
//                            "/api/auth/change-password",
//                            "/api/user/profile/**",
//                            "/api/userinvoices/**",
//                            "/api/role/**",
//                            "/api/checkout/**",
//                            "/api/reviews/**"
//                        ).authenticated()
//
//                        // Customer Truy cập
////                        .requestMatchers(
////                            ""
////                        ).hasAuthority("CUSTOMER")
//
//                        // Cấp quyền trung gian cho STAFF cùng ADMIN
//                        .requestMatchers(
//                            "/api/user/admin/customer-list",
//                            "/api/products/all",
//                            "/api/category/all",
//                            "/api/category/status",
//                            "/api/category/single",
//                            "/api/invoice/**",
//                            "/api/invoice/{invoice_code}/detail/**"
//                        )
//                        .hasAnyAuthority("STAFF", "ADMIN")
//
//                        // Cấp quyền hạn chế lớn chỉ dành cho ADMIN
//                        .requestMatchers(
//                            "/api/user/admin/**", // những api còn lại trong /user/admin đều dành cho admin
//                            "/api/products/**", // những api còn lại trong products đều dành cho admin
//                            "/api/category/**", // những api còn lại trong category đều dành cho admin
//                            "/api/product/{productId}/image/**"
//
//                        )
//                        .hasAuthority("ADMIN")
//
//                        .anyRequest().permitAll() // những cái còn lại đều công khai hết
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Cho phép frontend
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }