package everyonesparty.party.presentation.resolver;

import everyonesparty.party.presentation.exception.PresentationException;
import everyonesparty.party.presentation.exception.error.CommonPresentationError;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class KakaoIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == String.class &&
                StringUtils.equals(parameter.getParameterName(), "kakaoIdFromToken");
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
        String kakaoId = request.getHeader("kakaId");
        if(kakaoId == null){
            throw new PresentationException(CommonPresentationError.INTERNAL_SERVER_ERROR);
        }
        return kakaoId;
    }
}
