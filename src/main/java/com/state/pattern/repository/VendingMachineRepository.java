package com.state.pattern.repository;


import com.state.pattern.entity.VendingMachine;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingMachineRepository extends ListCrudRepository<VendingMachine,Integer >{
}
