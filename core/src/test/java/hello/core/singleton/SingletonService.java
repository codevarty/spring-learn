package hello.core.singleton;

// Singleton을 하나만 생성할 수 있도록 private 셍성자를 사용한다.
public class SingletonService {
  private static final SingletonService instance = new SingletonService();

  public static SingletonService getInstance() {
    return instance; // 항상 같은 인스턴스를 반환한다.
  }

  private SingletonService() {}

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }
}
