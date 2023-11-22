package study.resilience4j.circuitbreaker;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam Boolean isFaultRequest,
                                       @RequestParam Boolean isIllegalStateException) {

        String response = testService.testApi(isFaultRequest, isIllegalStateException);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
