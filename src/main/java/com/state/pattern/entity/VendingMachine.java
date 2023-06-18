package com.state.pattern.entity;

import com.state.pattern.state.States;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data // Todo : We should avoid this in production, but keeping this annotation to make thing easy for time being
@AllArgsConstructor
@NoArgsConstructor
public class VendingMachine {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private int quantity;
    private String machineName;
    private int capacity;
    private String location;
    private String model;
    @Enumerated(EnumType.STRING)
    private States state;


    public boolean  isCandyAvailable(){
       return this.getQuantity()>0 ?true :false;
    }

    public void reduceQuantity()
    {
        this.quantity=this.quantity-1;
    }
}
