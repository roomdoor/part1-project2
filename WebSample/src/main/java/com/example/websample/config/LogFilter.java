package com.example.websample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Hello LogFilter : " + Thread.currentThread());
        chain.doFilter(request, response); // 외부 -> 필터 (-> 내부 ->) 필터 -> 외부   // () 안의 부분을 위해 chain
        log.info("Bye LogFilter : " + Thread.currentThread());
    }
}
