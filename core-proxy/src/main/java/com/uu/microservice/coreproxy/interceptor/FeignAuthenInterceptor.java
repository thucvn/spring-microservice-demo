package com.uu.microservice.coreproxy.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uu.microservice.core.config.Constants;
import com.uu.microservice.coreproxy.security.UserPrincipal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        var authen = SecurityContextHolder.getContext().getAuthentication();
        if (authen != null) {
            try {
                System.out.println(((UserPrincipal)authen.getPrincipal()).getData());
                var str = ((UserPrincipal)authen.getPrincipal()).getData().jsonRfc6570();
                requestTemplate.header(Constants.HEADER_AUTHOR, str);
            } catch (JsonProcessingException e) {
                System.out.println("err " + e);
            }
        }
    }
}
