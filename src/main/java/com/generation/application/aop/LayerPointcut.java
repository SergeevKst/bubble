package com.generation.application.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LayerPointcut {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {
    }

    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }
}
