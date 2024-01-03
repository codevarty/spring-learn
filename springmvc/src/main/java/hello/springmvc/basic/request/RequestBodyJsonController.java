package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
  private ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody={}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    response.getWriter().write("ok");
  }

  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
    log.info("messageBody={}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "ok";
  }
  @ResponseBody
  @PostMapping("/request-body-json-v3")
  public String requestBodyJsonV3(@RequestBody HelloData data) {
    // objectMapper 생략 가능
//      content-type: application/json 요청시 있어야지만 HTTP 컨버터가 실행이 된다.
    log.info("username={}, age={}", data.getUsername(), data.getAge());
    return "ok";
  }
  @ResponseBody
  @PostMapping("/request-body-json-v4")
  public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
    // HttpEntity는 header 과 body를 받을 수 있음
    HelloData data = httpEntity.getBody();
    log.info("username={}, age={}", data.getUsername(), data.getAge());

    return "ok";
  }

  @PostMapping("/request-body-json-v5")
  public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
    // HttpEntity는 header 과 body를 받을 수 있음
    log.info("username={}, age={}", data.getUsername(), data.getAge());
    return data; // 응답 또한 JSON으로 변환해서 반환된다.
  }

}
