package com.example.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/service")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    String baseUrlMain = System.getenv("BASEURL_MAIN");
    String baseUrlSub = System.getenv("BASEURLSUB");

    @Value("${spring.application.name}")
    private String applicationName;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/method1")
    public ResponseEntity method1() {
        System.out.println("inside method1");
        logger.info("Incoming request at {} for request /method1 ");
        logger.info("Incoming request at {} for request /method1 ", applicationName);
        String response = restTemplate.getForObject(baseUrlSub+"/method2", String.class);
        return ResponseEntity.ok("response from /method1 + " + response);
    }

    @GetMapping("/method2")
    public ResponseEntity method2() {
        System.out.println("method2 ");
        logger.info("Incoming request at {} at /method2", applicationName);
        return ResponseEntity.ok("response from /method2 ");
    }
}