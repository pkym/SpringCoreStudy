package tobyspring.helloboot;

import ch.qos.logback.classic.joran.ReconfigureOnChangeTask;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

/**
 * 독립 실행형 스프링 애플리 케이션
 */

//@SpringBootApplication
@Configuration // 구성정보를 가진 클래스임을 명시 >> factory method가 있겠군
@ComponentScan // 컴포넌트 붙은 애들을 찾아 등록함  >> 매번 클래스를 작성할 필요가 없어짐
public class HellobootApplication {
    // 스프링 컨테이너가 호출할 factory method
//    @Bean
//    public HelloController helloController (HelloService helloService){
//        return new HelloController(helloService);
//    }
//    @Bean
//    public HelloService helloService(){
//        return new SimpleHelloService();
//    }
    @Bean
    public ServletWebServerFactory serverFactory(){
        return new TomcatServletWebServerFactory();
    }
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }
    public static void main(String[] args) {
//        MySpringApplicaiton.run(HellobootApplication.class, args);
        SpringApplication.run(HellobootApplication.class,args);
    }

}
