package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    // 자동으로 @Configuration도 등록이 되기 때문에 충돌 문제를 없애기 위해 필터를 사용한다.
    // 보통은 제외하지 않지만 예제 코드 충돌로 제외한다.
    // 지정하지 않으면 컴포넌트를 지정한 패키지를 시작 범위로 설정한다.
//    basePackages = "hello.core.member", // 스캔 패키지 범위를 지정할 수 있음
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {
//  @Bean(name = "memoryMemberRepository")
//  MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }
}
