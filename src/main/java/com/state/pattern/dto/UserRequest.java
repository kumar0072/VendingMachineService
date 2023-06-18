package com.state.pattern.dto;


import com.state.pattern.state.States;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {


    @NotNull
    int id;
    @NotNull//TODO check if this field is showing in swagger as required
   States  states;

}
