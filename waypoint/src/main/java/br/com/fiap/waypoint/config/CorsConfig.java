package br.com.fiap.waypoint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration confi = new CorsConfiguration();

        //Permite acesso do front-end React
        confi.setAllowedOrigins(
                Arrays.asList("http://localhost:colocar o portal do  front"));

        //Metodos permitidos
        confi.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        // Cabecalhos permitidos
        confi.setAllowedHeaders(
                Arrays.asList("Content-Type", "Authorization")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", confi);

        return new CorsFilter(source);
    }

}
