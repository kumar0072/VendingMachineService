package com.state.pattern.dto;


import com.state.pattern.state.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String response;
    States nextState;
}
