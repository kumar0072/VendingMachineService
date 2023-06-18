package com.state.pattern.dto;

import com.state.pattern.state.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {

    String Response ;
    List<States> states;

}
