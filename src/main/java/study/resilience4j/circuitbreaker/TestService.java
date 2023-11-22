package study.resilience4j.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class TestService {

    @Transactional(readOnly = true)
    @CircuitBreaker(name = "testApi", fallbackMethod = "fallback")
    public String testApi(Boolean isFaultRequest, Boolean isIllegalStateException) {
        if (isIllegalStateException) {
            throw new IllegalStateException("IllegalStateException!");
        }
        if (isFaultRequest) {
            throw new RuntimeException("RuntimeException...");
        }

        return "OK";
    }

    private String fallback(IllegalStateException illegalStateException) {
        return "IllegalStateException Response!";
    }

    private String fallback(CallNotPermittedException callNotPermittedException) {
        return "Reject Call!!";
    }

    private String fallback(Exception exception) {
        return "Empty Response!";
    }
}
