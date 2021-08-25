package com.promineotech.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import lombok.Getter;

//Now here is where we get the random port, we are putting it within here so all the test classes can use it 
public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  //TEST REST TEMPLATE TO SEND THE HTTP REQUESTS 
  //This one allows a test rest remplate to be created for us 
  @Autowired 
  @Getter
  private TestRestTemplate restTemplate;
  
  //retrieve the uri with the method 
  //we want to return a String with this method 
  protected String getBaseUri() {
    //this is the test that will be running in the background
    return String.format("http://localhost:%d/jeeps", serverPort);
  }
}
