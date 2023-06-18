package com.state.pattern.controller;

import com.state.pattern.dto.UserRequest;
import com.state.pattern.dto.UserResponse;
import com.state.pattern.service.UserActionService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@Tag(name = " Vending Machine - User Actions")
@Slf4j
public class VendingMachineUserController {


    @Autowired
    UserActionService userActionService;

    @PostMapping("/action")
    @Operation(method = "userAction()" ,summary ="This api moves the state of vending machine to next state based upon user input.",description = "This api moves the state of vending machine to next state based upon user input.")
    ResponseEntity<UserResponse> userAction( @RequestBody UserRequest userRequest){
        log.info("VendingMachineUserController Input received from user "+userRequest);
       UserResponse userResponse = userActionService.executeAction(userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.ACCEPTED);
    }

}
