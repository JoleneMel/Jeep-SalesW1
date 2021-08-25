package com.promineotech.jeep.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
//import com.promineotech.controller.support.FetchJeepTestSupport; //from video and but not needed 
import lombok.Getter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


//Below is the test annotation 
//we are telling it that we want the tests to be run in a web environment AND to ensure the tests will not 
//run on top of each other we specify the .random_port for each test class
//host class is always local host means it will stay within the local machine environment 
//class FetchJeepTest extends FetchJeepTestSupport {
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(scripts = {
      "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
      "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
      config = @SqlConfig(encoding = "utf-8"))
  class FetchJeepTest {
    @LocalServerPort
    private int serverPort;
    
    //TEST REST TEMPLATE TO SEND THE HTTP REQUESTS 
    //This one allows a test rest template to be created for us 
    @Autowired 
    @Getter
    private TestRestTemplate restTemplate;
   
  @Test
//a test should be self describing so should have a name to what they exactly do 
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    //Given: a valid model, trim URI 
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    //this makes it so it can have two parameters 
    String uri = String.format("http://localhost/%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    System.out.println(uri);
    
    //When: a connection is made to the URI
    ResponseEntity<List<Jeep>> response = 
        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

    //Then: a success (OK - 200) status code is returned 
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}