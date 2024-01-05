package hello.springmvc.basic;

import lombok.Data;

@Data // getter setter를 자동으로 해준다.
public class HelloData {
  private String username;
  private int age;
}
