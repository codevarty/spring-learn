package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Getter와 Setter를 자동으로 작성해준다.
@Getter
@Setter
@ToString
public class HelloLombok {
  private String name;
  private int age;

  public static void main(String[] args) {
    HelloLombok helloLombok = new HelloLombok();
    helloLombok.setName("hello");
    String name1 = helloLombok.getName();
    System.out.println("name1 = " + name1);

    System.out.println("helloLombok = " + helloLombok);
  }
}
