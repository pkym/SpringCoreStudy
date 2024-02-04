package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


//RestController의 경우 그 안에있는 모든 것들이 ResponseBody가 붙어있다고 가정함 >> 붙일 필요 x, RequestMapping의 경우 필요
@RestController
public class HelloController {
    private final HelloService helloService; // 정의할때 부터 초기화 해줘야함
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    @GetMapping("/hello")
    public String hello(String name) { //dispatcher servlet은 String타입이 리턴값은 언제나 뷰를 찾음
//        SimpleHelloService helloService = new SimpleHelloService(); // 직접 서비스를 생성해서 사용
        return helloService.sayHello(Objects.requireNonNull(name));
    }


//    public HelloController(HelloService helloService) {
//        this.helloService = helloService;
//    }
//    @GetMapping("/hello")
//    public String hello(String name) {
//        if (name == null || name.trim().length() == 0) throw new IllegalArgumentException();
//        return helloService.sayHello(name);
//    }
//    @GetMapping("/count")
//    public String count(String name) {
//        return name + ": " + helloService.countOf(name);
//
//    }


}
