package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

  @RequestMapping("/response-view-v1")
  public ModelAndView responseViewV1() {
    ModelAndView mav = new ModelAndView("response/hello")
        .addObject("data", "hello!");
    return mav;
  }

  // @ResponseBody => View로 가지 않고 문자열 그대로 반환한다.
  @RequestMapping("/response-view-v2")
  public String responseViewV2(Model model) {
    model.addAttribute("data", "hello!");
    return "response/hello"; // ViewResolver에서  view의 논리적 이름을 찾는다.
  }
  @RequestMapping("/response/hello")
  public void responseViewV3(Model model) {
    // 불명확하지 않기 때문에 잘 쓰이지 않는다.
    model.addAttribute("data", "hello!");
  }
}
