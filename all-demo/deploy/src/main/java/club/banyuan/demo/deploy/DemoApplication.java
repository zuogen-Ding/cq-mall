package club.banyuan.demo.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 应用启动入口
 */
@SpringBootApplication(exclude = {
    // 禁用数据源，因为使用了druid数据源，因此需要同样禁用druid数据源的自动配置
    DataSourceAutoConfiguration.class,
    // DruidDataSourceAutoConfigure.class
})
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
