package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.entity.Jeep;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

//This allows anything /jeeps, like in the uri port number it will be mapped to this class
@RequestMapping("/jeeps")
//title will show on the swagger API as the heading 
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server")})
public interface JeepSalesController {
  //@formatter:off
  @Operation(
      summary = "Returns a list of Jeeps",
      description = "Returns a list of Jeeps given an optional model and/or trim",
      responses = {
          //okay/success
          @ApiResponse(responseCode = "200", 
              description = "A list of Jeeps is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Jeep.class))),
          
          //bad input/illegal request 
          @ApiResponse(responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          
          //not found
          @ApiResponse(responseCode = "404", 
              description = "No Jeeps were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          
          //unplanned exception 
          @ApiResponse(responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      },
      
      parameters = {
          //if you pass bunch of empty characters it will return it as null 
          @Parameter(name = "model", 
                      allowEmptyValue = false, 
                      required = false,
                      description = "The model name (i.e., 'WRANGLER')"),
          @Parameter(name = "trim", 
                      allowEmptyValue = false, 
                      required = false, 
                      description = "The trim level (i.e., 'Sport')")
      }
      
      )

  //Spring will map the get request at /jeeps to the fetchjeeps method 
  @GetMapping
  //then it will return  the status 200, 400, 404, or 500 
  @ResponseStatus(code = HttpStatus.OK)
  //Spring needs annotations to read it will map the parameter model based on name of the parameter within the method call
  List<Jeep> fetchJeeps(
      @RequestParam(required = false) 
            String model, 
      @RequestParam(required = false) 
            String trim);
//@formatter:on
}
