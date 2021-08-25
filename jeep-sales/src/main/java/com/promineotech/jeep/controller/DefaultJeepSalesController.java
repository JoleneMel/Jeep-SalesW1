package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.jeep.entity.Jeep;

//We need to tell Spring that this is a restcontroller and we can only do that here 
//This restcontroller tells spring boot that this class is special, its a controller for the jeepsalescontroller so it 
//will look into that interface to find out that the class is mapped to /jeeps, get verb needs two parameters 
@RestController
public class DefaultJeepSalesController implements JeepSalesController {

  @Override
  public List<Jeep> fetchJeeps(String model, String trim) {
    return null;
  }

}
