package tobyspring.helloboot;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

//@SpringBootApplication
public class HellobootApplication {

	// 0. 스프링 관련 설정 안했지만 알아서 톰캣으로 실행
	// 컨테이너 관련 모든 작업들이 알아서 진행됨
    public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);

        // 2.  new Tomcat().start(); >> 이렇게 바로 띄울 수 있진 않음 >> 도우미 클래스를 이용해 쉽게 띄울 수 있음
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // ServletWebServerFactory로 받아도됨
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    String name = req.getParameter("name");
                    // 응답을 만들어야함(상태코드, 컨텐츠타입헤더, 바디)
                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    resp.getWriter().println("Hello"+name);
                }
            }).addMapping("/halo");
        });
        webServer.start();
    }

}

// 1. 서블릿 컨테이너를 직접 설치 하지 않고 동작하게 만들것인지? >> stand alone 프로그램에서 알아서 띄워주는 작업 할 것
// 서블릿 하나를 만드는 작업 >> 이전에 빈 서블릿 컨테이너를 띄워보자
// servelet Container의 대표예: Tomcat >> tomcat도 java 로 만든 프로그램
// embedded tomcat 이라는 내장형 톰캣이 존재함