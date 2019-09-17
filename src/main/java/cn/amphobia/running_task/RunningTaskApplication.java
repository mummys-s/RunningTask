package cn.amphobia.running_task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("cn.amphobia.running_task.mapper")
public class RunningTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningTaskApplication.class, args);
    }

}
