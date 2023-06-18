package com.state.pattern.service;


import com.state.pattern.constant.MachineConstant;
import com.state.pattern.dto.UserRequest;
import com.state.pattern.dto.UserResponse;
import com.state.pattern.entity.VendingMachine;
import com.state.pattern.repository.VendingMachineRepository;
import com.state.pattern.state.States;
import com.state.pattern.state.action.AbstractMachineState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserActionService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    VendingMachineRepository vendingMachineRepository;

    public UserResponse executeAction(UserRequest userRequest) {
                String responseMsg=null;
                States nextState=null;
                AbstractMachineState abstractMachineState =
                        (AbstractMachineState) applicationContext.getBean(userRequest.getStates().name());
              VendingMachine vendingMachine = getVendorMachine(userRequest);
              log.info("UserActionService vendor machine object from database " + vendingMachine);
              if (null != vendingMachine) {
                  if (verifyUserRequestStatesWithSystemStates(userRequest, vendingMachine,abstractMachineState)) {
                      vendingMachine = abstractMachineState.executeActionBasedOnState(vendingMachine);
                      //Updating the vendor machine object  in database.
                      log.info("UserActionService vendor machine object after modification " + vendingMachine);
                      vendingMachineRepository.save(vendingMachine);
                      responseMsg = MachineConstant.SUCCESSFUL_MSG;
                  } else {
                      responseMsg = MachineConstant.VENDING_MACHINE_STATE_MISMATCH;
                  }
                  nextState = vendingMachine.getState();
              } else {
                  responseMsg = MachineConstant.VENDING_MACHINE_ID_MISMATCH;
                  log.error(responseMsg + userRequest);
              }
                UserResponse userResponse=
                        new UserResponse(responseMsg,nextState );
                return userResponse;
    }


    private VendingMachine getVendorMachine(UserRequest userRequest){
        Optional<VendingMachine> vendorMachineOptional = vendingMachineRepository.findById(userRequest.getId());
        return  vendorMachineOptional.isPresent() ? vendorMachineOptional.get():null;

    }

    private boolean verifyUserRequestStatesWithSystemStates(UserRequest userRequest, VendingMachine vendingMachine, AbstractMachineState abstractMachineState){

        if(null!=abstractMachineState && null!=userRequest.getStates() && null!= vendingMachine.getState()
            && userRequest.getStates().name().equalsIgnoreCase(vendingMachine.getState().name())){

            return true;
        }
        return false;
    }

}
