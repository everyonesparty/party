package everyonesparty.party.presentation.configuration;

import everyonesparty.party.presentation.resolver.KakaoIdArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/***
 * > swagger 호완성을 위해 WebMvcConfigurerAdapter 를 사용
 *     > TODO: spring 에선 WebMvcConfigurationSupport 를 권장하고 있기 때문에 고민해봐야함
 *     > 참고: https://oingdaddy.tistory.com/410
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new KakaoIdArgumentResolver());
    }
}
