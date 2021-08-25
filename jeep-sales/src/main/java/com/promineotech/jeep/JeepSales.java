package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//annotations are very important 
//tomcat is how it runs - handles all its requests and such 
//snapshot is a maven convention 
//ctrl c stops it on cmd
@SpringBootApplication
public class JeepSales {

  public static void main(String[] args) {
    SpringApplication.run(JeepSales.class, args);
  }
}

