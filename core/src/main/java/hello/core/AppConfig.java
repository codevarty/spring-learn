package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    // 생성자를 통해 repository를 분리하였다.
    // 생성자 주입
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  private static MemberRepository memberRepository() {
    // 장점 : 생성자 주입 인터페이스가 한 눈에 보인다. 그리고 중복되는 부분이 없앨 수 있음
    // 이 코드만 수정하면 된다. => 편리하다.
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }
}
