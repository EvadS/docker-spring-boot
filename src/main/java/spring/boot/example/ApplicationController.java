package spring.boot.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author uchonyy@gmail.com
 */
@RestController
public class ApplicationController {
    @Autowired
    private ApplicationProperties properties;

    @GetMapping("/")
    public String index() {
        return "Hello World! App file: " +properties.getValue();
    }
}
