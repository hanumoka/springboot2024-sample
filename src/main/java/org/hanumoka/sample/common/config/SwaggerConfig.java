package org.hanumoka.sample.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        // 보안 스키마의 이름을 단순화
//        String securitySchemeName = "Bearer Auth";

        // 보안 요구사항을 API 전체에 적용
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);

        // 보안 스키마 정의
//        SecurityScheme securityScheme = new SecurityScheme()
//                .name(securitySchemeName)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")
//                .bearerFormat("JWT");

        // 컴포넌트에 보안 스키마 추가
//        Components components = new Components().addSecuritySchemes(securitySchemeName, securityScheme);

        return new OpenAPI()
//                .components(components)
//                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("2024 springboot 예제")
                .description("2024 springboot 예제")
                .version("1.0.0");
    }
}
