package com.state.pattern.service;


import com.state.pattern.dto.VendorRequest;
import com.state.pattern.entity.VendingMachine;
import com.state.pattern.repository.VendingMachineRepository;
import com.state.pattern.state.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class VendorActionService {

    @Autowired
    VendingMachineRepository vendingMachineRepository;
    public String refill(VendorRequest vendorRequest)
    {
        VendingMachine vendingMachine =null;
         Optional<VendingMachine> candyVendorMachineOptional =
                 vendingMachineRepository.findById(vendorRequest.getId());

       if( candyVendorMachineOptional.isPresent()){
           vendingMachine = candyVendorMachineOptional.get();
           log.info(log.getName()+ " database candyVendorMachine " + vendingMachine);
           vendingMachine.setCapacity(vendorRequest.getQuantity());
           vendingMachine.setState(States.No_Coin);
           log.info(log.getName()+ " Updated candyVendorMachine " + vendingMachine);
           vendingMachineRepository.save(vendingMachine);
           return vendingMachine.toString();
       }
       return null;
    }

}
