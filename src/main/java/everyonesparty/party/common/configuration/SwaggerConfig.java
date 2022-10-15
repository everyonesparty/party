package everyonesparty.party.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 설정
 * @author gshgsh0831
 * **/
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		ApiInfoBuilder apiInfo = new ApiInfoBuilder();
		apiInfo.title("party-service api 문서");
		apiInfo.description("party-service api 문서입니다.");
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfo.build());
		docket.ignoredParameterTypes(AuthenticationPrincipal.class);
		
		ApiSelectorBuilder apis = docket
				.select()
				.apis(RequestHandlerSelectors.
						basePackage("everyonesparty.party"));
		apis.paths(PathSelectors.ant("/**"));
		return apis.build();
	}
}
