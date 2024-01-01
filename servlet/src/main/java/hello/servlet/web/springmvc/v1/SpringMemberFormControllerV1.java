package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 스프링이 자동으로 스프링 빈으로 등록한다. @Component : 스프링 빈 자동 등록
public class SpringMemberFormControllerV1 {

  @RequestMapping("/springmvc/v1/members/new-form") // 매핑 주소
  public ModelAndView process() {
    return new ModelAndView("new-form");
  }
}
