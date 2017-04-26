package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by xiaodpro on 2017/4/26.
 */
@RestControllerAdvice
@SpringBootConfiguration
@SpringBootApplication(scanBasePackages = "web.controller")
public class ServiceBootstrap {

    private static Logger logger = LoggerFactory.getLogger(ServiceBootstrap.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceBootstrap.class, args);
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<String> error(Exception e){
        logger.error("Some one error has been interrupt!", e);
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
