package com.xlf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xlf.sys.mapper")
public class WarehousePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehousePracticeApplication.class, args);
	}

}
