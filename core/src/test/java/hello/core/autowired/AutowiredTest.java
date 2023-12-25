package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
  @Test
  void autowiredOption() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

  }

  static class TestBean {
    // Member클래스가 빈으로 등록이 된 것이 아니기 때문에 required = false로 지정한다.
    // 자동 주입 대상이 없으면 메소드가 호출이 되지 않는다.
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) {
      System.out.println("noBean1 = " + noBean1);
    }
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2);
    }

    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3);
    }
  }
}
