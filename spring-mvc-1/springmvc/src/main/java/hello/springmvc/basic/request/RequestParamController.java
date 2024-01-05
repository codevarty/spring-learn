package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 서블릿 기반으로 하는 것
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    log.info("username={}, age={}", username, age);

    response.getWriter().write("ok");

  }

  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
      @RequestParam("username") String memberName,
      @RequestParam("age") int memberAge) {

    log.info("username={}, age={}", memberName, memberAge);

    // @Controller에 return String을 하게 되면 view를 찾기 시작한다.
    // 문자 그래도 반환하기 위해서 @ResponseBody를 사용함.
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
      @RequestParam String username,
      @RequestParam int age) {

    log.info("username={}, age={}", username, age);
    return "ok";
  }
  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) { // 요청 파라미터 이름과 동일하면 생략 가능

    log.info("username={}, age={}", username, age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
      @RequestParam(required = true) String username,
      @RequestParam(required = false) Integer age) {
    // int에는 null이 들어갈 수 없음 => required가 false인 경우 값을 보내지 않는 경우 null이 된다.
    // 빈 문지열이 들어오면 인식한다. "" -> null X

    log.info("username={}, age={}", username, age);
    return "ok";
  }
  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
      @RequestParam(required = true, defaultValue = "quest") String username,
      @RequestParam(required = false, defaultValue = "-1") int age) {
    // 기본값 지정하면 int 가능
    // 빈 문자도 처리해준다. ""


    log.info("username={}, age={}", username, age);
    return "ok";
  }
  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    // Map을 통해 모든 parameter 초회
    // MultiValueMap을 사용할 경우 같은 parameter의 값을 배열로 얻을 수 있음 -> userId=20&userId=30
    log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v1")
  public String modelAttributeV1(@ModelAttribute HelloData helloData) {
    // HelloData 클래스를 자동으로 생성한다.
    // 요청 파라미터와 이름이 같은 getter, setter -> 여기서는 setter에 값을 넣는다.
    // -> 자동으로 클래스를 생성하고 값을 넣어주는 역할을 한다.

    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/model-attribute-v2")
  public String modelAttributeV2(HelloData helloData) {
    // @ModelAttribute 생략 가능 자신이 만든 클래스인 경우 생략이 가능하다.
    // 생략하면 @RequestParam을 생략한 것과 혼동이 있을 수 있으니 생략하지 않아야 겠다.
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
    return "ok";
  }
}
