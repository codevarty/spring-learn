package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // @Bean(initMethod = "init", destroyMethod = "")
        // destroyMethod 는 기본적으로 "close", "shutdown" 메소드를 추론해서 찾는다. => destroyMethod=""으로 지정하면 자동으로 메소드를 찾는다.
        @Bean // 스프링 빈에 종속적이지 않다. 코드를 변경할 수 없는 외부 라입즈러리에 사용 가능하다. (중요!)
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello.spring.dev");
            return networkClient;
        }
    }
}
