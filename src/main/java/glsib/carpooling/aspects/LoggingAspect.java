package glsib.carpooling.aspects;

import glsib.carpooling.entities.UserActionLog;
import glsib.carpooling.repositories.UserActionLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private UserActionLogRepository logRepository;
    @Before("execution(public * glsib.carpooling.controllers..*(..))")
    public void logUserAction(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : "anonymous";
        String action = joinPoint.getSignature().getName();
        UserActionLog log = new UserActionLog();
        log.setUsername(username);
        log.setAction(action);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }
}
