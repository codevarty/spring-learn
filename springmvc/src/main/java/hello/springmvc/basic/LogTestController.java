package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // @Controller 인 경우에는 ModelAndView 반환 필요 @RestController 경우 문자 그대로 반환
public class LogTestController {
//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "spring";

    log.trace("trace log={}", name);
    log.debug("debug log={}", name);
    log.info("info log={}", name);
    log.warn("warn log={}", name);
    log.error("error log={}", name);

    return "ok";
  }
}
