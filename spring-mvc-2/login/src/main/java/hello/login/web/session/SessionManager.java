package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */
@Component
public class SessionManager {

  public static final String SESSION_COOKIE_NAME = "mySessionId";
  private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); // 동시 요청이 있는 상황에서 사용하는 맵

  /**
   * 세션 생성 로직
   */
  public void createSession(Object value, HttpServletResponse response) {

    // sessionId 생성 (임의의 추정 불가능한 랜덤 값)
    String sessionId = UUID.randomUUID().toString();
    sessionStore.put(sessionId, value);

    // 쿠키 생성
    Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
    response.addCookie(mySessionCookie);
  }
  /**
   * 세션 조회
   */
  public Object getSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie == null) {
      return null;
    }

    return sessionStore.get(sessionCookie.getValue()); // 쿠키의 값이 세션의 키가 된다.
  }

  /**
   * 세션 만료
   */
  public void expire(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie !=null) {
      sessionStore.remove(sessionCookie.getValue());
    }
  }

  public Cookie findCookie(HttpServletRequest request, String cookieName) {
    if(request.getCookies() == null) {
      return null;
    }
    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(cookieName))
        .findAny()
        .orElse(null);
  }
}
