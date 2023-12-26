package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url);


  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스를 시작할 때 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("cal: " + url + " message = " + message);
  }


  // 서비스 종료시 호출
  public void disconnect() {
    System.out.println("close: " + url);
  }

  @PostConstruct
  public void init() {
    System.out.println("NetworkClient.afterPropertiesSet");
    connect();
    call("호기화 연결 메시지");
  }

  @PreDestroy
  public void close() {
    System.out.println("NetworkClient.destroy");
    disconnect();
  }
}
