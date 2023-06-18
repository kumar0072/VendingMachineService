package com.state.pattern.state.action;

import com.state.pattern.entity.VendingMachine;
import com.state.pattern.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component( "No_Coin")
public class NoCoinAbstractMachineState extends AbstractMachineState {
    public VendingMachine executeActionBasedOnState(VendingMachine vendingMachine) {
        log.info("NoCoinCandyStateMachine class, updating machine state to "+ States.Contains_Coin.name());
        vendingMachine.setState(States.Contains_Coin);
        return vendingMachine;
    }
}
