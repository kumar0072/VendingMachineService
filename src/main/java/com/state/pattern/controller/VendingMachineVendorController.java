package com.state.pattern.controller;


import com.state.pattern.dto.UserResponse;
import com.state.pattern.dto.VendorRequest;
import com.state.pattern.dto.VendorResponse;
import com.state.pattern.service.VendorActionService;
import com.state.pattern.state.States;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/api/vendor/machine")
@Tag(name = " Vending Machine - Vendor Actions")
@Slf4j
public class VendingMachineVendorController {

    @Autowired
    VendorActionService vendorActionService;

    @PostMapping("/states")
    @Operation(method = "getAllMachineStates()",summary = "Return list of states supported by candy dispenser machine.",description = "Return list of states supported by candy dispenser machine.")
    ResponseEntity<VendorResponse> getAllMachineStates(){
        return new ResponseEntity<>(new VendorResponse("returning list of states", Arrays.asList(States.values())), HttpStatus.ACCEPTED);
    }
    @PostMapping("/refill")
    @Operation(method = "refillCandy()",summary = "Reload candy's in vending machine.",description = "Reload candy's in vending machine.")
    ResponseEntity<UserResponse> refillCandy(@Valid @RequestBody VendorRequest vendorRequest){
        log.info("VendingMachineUserController Input received from vendor "+vendorRequest);
        String details =vendorActionService.refill(vendorRequest);
        if(StringUtils.isAllEmpty(details))
        {
            details="Please verify  the ID of candy vending machine. Something is incorrect";
        }
        return new ResponseEntity<>(new UserResponse(details,null), HttpStatus.ACCEPTED);
    }
}
