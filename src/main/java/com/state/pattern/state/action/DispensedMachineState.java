package com.state.pattern.state.action;


import com.state.pattern.entity.VendingMachine;
import com.state.pattern.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component("Candy_Dispensed")
public class DispensedMachineState extends AbstractMachineState {


    public VendingMachine executeActionBasedOnState(VendingMachine vendingMachine) {
        log.info("DispensedMachineState class, updating machine state to "+ States.No_Coin);
        vendingMachine.reduceQuantity();
        log.info("DispensedMachineState class, updating machine Candy count to "+ vendingMachine.getQuantity());
        if (vendingMachine.isCandyAvailable()){
            vendingMachine.setState(States.No_Coin);
        }
        else {
            vendingMachine.setState(States.No_Candy);
        }
        return vendingMachine;
    }
}
