package com.example.demo.aspect;

import com.example.demo.service.PostService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Aspect
@Controller
public class WebConfigAspect {
    private static Logger logger = LoggerFactory.getLogger(WebConfigAspect.class);
    private PostService postService;

    public WebConfigAspect(PostService postService) {
        this.postService = postService;
    }

    @Before("execution(* *.showAllPosts(..))")
    public void logging(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        logger.info("Current Time: " + LocalDateTime.now()+" "+ joinPoint.getSignature().getName()+"called with : "+objects);
    }

    @Before("execution(* *.showDetails(..))")
    public void notFoundAspect(JoinPoint joinPoint){
        if(postService.findById((Long)joinPoint.getArgs()[1])==null){
            throw new EntityNotFoundException((Long)joinPoint.getArgs()[1]+"Not Found.");
        }
    }

}
