package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplicaiton {
    public static void run(Class<?> applicationClass, String... args){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            //익명 클래스 사용
            @Override
            protected void onRefresh(){
                super.onRefresh();
                // 서블릿 컨테이너 초기화 코드를 스프링 컨테이서 초기화 과중중에 일어나도록 설정
                TomcatServletWebServerFactory serverFactory = (TomcatServletWebServerFactory) this.getBean(ServletWebServerFactory.class); // ServletWebServerFactory로 받아도됨
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//                dispatcherServlet.setApplicationContext(this); 생성자 없어도 동작하는 이유..?
                // dispatcherServlet >>> ApplicationContextAware >> applicationContext를setter메소드를 통해 주입함
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",dispatcherServlet
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 스프링 컨테이너 초기화 > 구성정보를 초기화함

    }
}


/**
 * 스프링을 사용하지 않고 오로지 자바만으로 이루어진 컨트롤러
 * 서블릿 만들어서 요청처리 하기
 */
// 0. 스프링 관련 설정 안했지만 알아서 톰캣으로 실행
// 컨테이너 관련 모든 작업들이 알아서 진행됨
// 1. 서블릿 컨테이너를 직접 설치 하지 않고 동작하게 만들것인지? >> stand alone 프로그램에서 알아서 띄워주는 작업 할 것
// 서블릿 하나를 만드는 작업 >> 이전에 빈 서블릿 컨테이너를 띄워보자
// servelet Container의 대표예: Tomcat >> tomcat도 java 로 만든 프로그램
// embedded tomcat 이라는 내장형 톰캣이 존재함

//public static void main(String[] args) {
//    HelloController helloController = new HelloController();
//
//    // 2.  new Tomcat().start(); >> 이렇게 바로 띄울 수 있진 않음 >> 도우미 클래스를 이용해 쉽게 띄울 수 있음
//    TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // ServletWebServerFactory로 받아도됨
//    WebServer webServer = serverFactory.getWebServer(servletContext -> {
//        servletContext.addServlet("frontcontroller", new HttpServlet() {
//            @Override
//            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                // 1)인증, 보안, 다국어처리, 공통기능 >> 프론트컨트롤러에서 처리
//                // 2)서블릿 컨테이너의 매핑 기능을 할 수 있어야함
//                if( req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) { // get 매핑만
//                    String name = req.getParameter("name");
//                    // 응답을 만들어야함(상태코드, 컨텐츠타입헤더, 바디)
//
//                    String ret = helloController.hello(name); // 헬로 컨트롤러의 헬로메소드에 작업을 위임//
//                    resp.setStatus(HttpStatus.OK.value());
//                    resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                    resp.getWriter().println(ret);
//                }else{
//                    resp.setStatus(HttpStatus.NOT_FOUND.value());
//                }
//            }
//              @Override
//              protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//                  if( req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) { // get 매핑만
//                       String name = req.getParameter("name");
//                      HelloController helloController = applicationContext.getBean(HelloController.class);
//                      String ret = helloController.hello(name); // 헬로 컨트롤러의 헬로메소드에 작업을 위임
//                       resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
//                       resp.getWriter().println(ret);
//                   }else{
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//            }).addMapping("/*");

//
//          GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(){
//               //익명 클래스 사용
//           @Override
//           protected void onRefresh(){
//              super.onRefresh();
//             // 서블릿 컨테이너 초기화 코드를 스프링 컨테이서 초기화 과중중에 일어나도록 설정
//                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // ServletWebServerFactory로 받아도됨
//                WebServer webServer = serverFactory.getWebServer(servletContext -> {
//                    // bean을 뒤져 매핑정보를 갖고 있는 클래스를 찾아 요청정보를 추출해 테이블 생성
//                    // 그 이후에 웹 요청이 들어오면 그걸 참고해 이걸 담당할 빈 오브젝트와 메소드를 확인
//                    servletContext.addServlet("dispatcherServlet",
//                      //  new DispatcherServlet(applicationContext)
//                           new DispatcherServlet(this)
//                  ).addMapping("/*");
//              });
//               webServer.start();
//         }
//};
//        applicationContext.registerBean(HelloController.class); // 빈 오브젝트 클래스 정보를 직접 등록
//        applicationContext.registerBean(SimpleHelloService.class);
//        applicationContext.refresh(); // 스프링 컨테이너 초기화 > 구성정보를 초기화함
//       });
//    webServer.start();
//}
