package com.state.pattern.state.action;


import com.state.pattern.entity.VendingMachine;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractMachineState {

   public abstract VendingMachine executeActionBasedOnState(VendingMachine vendingMachine);

}
