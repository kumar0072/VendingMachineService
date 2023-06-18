package com.state.pattern.state.action;


import com.state.pattern.entity.VendingMachine;
import com.state.pattern.state.States;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("Contains_Coin")
public class ContainsCoinMachineState extends AbstractMachineState {

    @Override
    public VendingMachine executeActionBasedOnState(VendingMachine vendingMachine) {
        log.info("ContainsCoinAbstractMachineState class, updating machine state to "+ States.Candy_Dispensed);
        vendingMachine.setState(States.Candy_Dispensed);
        return vendingMachine;
    }
}
