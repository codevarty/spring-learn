package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
  // getMapping으로 hello라는 url이 들어오면 아래의 메소드를 실행
  @GetMapping("hello")
  public String hello(Model model) {
    // data에 대한 값을 hello!!로 지정
    model.addAttribute("data", "hello!!");
    // hello라는 html 파일을 찾아 화면에서 찾아 보여줌
    return "hello";
  }
  // mvc 방식
  @GetMapping("hello-mvc")
  // @RequestParam으로 name이라는 파라미터를 url에서 받아옴
  // 서버주소/hello-mvc?name=spring!! 이라고 url에 입력하면 name에 spring!!이라는 값이 들어감
  public String helloMvc(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }
  // api 방식
  @GetMapping("hello-string")
  @ResponseBody // 응답 바디에 직접 넣어주겠다는 의미
  // 데이터로 값을 넘겨 줄 수 있음
  public String helloString(@RequestParam("name") String name) {
    return "hello " + name;
  }
  // api 방식2
  @GetMapping("hello-api")
  @ResponseBody
  // 객체를 반환하면 json 방식으로 반환
  public Hello helloApi() {
    Hello hello = new Hello();
    hello.setName("hello");
    return hello;
  }
  static class Hello {
    private String name;

    public void setName(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }
}
