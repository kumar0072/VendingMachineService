//package com.state.pattern.state.action;
//
//
//import com.state.pattern.entity.VendorMachine;
//import com.state.pattern.state.States;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component("No_Candy")
//public class NoCandyMachineState extends AbstractMachineState {
//
//    public VendorMachine executeActionBasedOnState(VendorMachine vendorMachine) {
//        log.info("NoCandyMachineState class, updating machine state to "+ States.No_Candy.name());
//        vendorMachine.setState(States.No_Candy);
//        return vendorMachine;
//    }
//TODO: Vendor refill api is responsible for moving the machine from No candy to NO coin state.
//Todo: so i am not keeping this in User code for now.
//}

