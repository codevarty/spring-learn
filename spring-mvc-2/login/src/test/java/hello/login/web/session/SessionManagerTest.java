package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {

  SessionManager sessionManager = new SessionManager();

  @Test
  void sessionTest() {

    // HttpServletResponse 인터페이스로 테스트 하기 어려움
    // 이 문제를 스프링을 통해 해결할 수 있음
    MockHttpServletResponse response = new MockHttpServletResponse();

    // 세션 생성
    Member member = new Member();
    sessionManager.createSession(member, response);

    // 요청에 응답 쿠키가 저장
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setCookies(response.getCookies());

    // 세션 조회
    Object result = sessionManager.getSession(request);
    assertThat(result).isEqualTo(member);

    // 세션 만료
    sessionManager.expire(request);

    Object expired = sessionManager.getSession(request);
    assertThat(expired).isNull();
  }
}