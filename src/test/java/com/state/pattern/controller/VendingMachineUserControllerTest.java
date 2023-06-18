package com.state.pattern.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.state.pattern.constant.MachineConstant;
import com.state.pattern.dto.UserRequest;
import com.state.pattern.state.States;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VendingMachineUserControllerTest {


    @Autowired
    MockMvc mockMvc;

    private String userRequestNoCoinJson = null;
    private String userRequestContainsCoinJson = null;
    private String userRequestCandyDispensedJson = null;
    private String userRequestNoCandyJson = null;
    private String userRequestIncorrectStateJson = null;

    private String userRequestIncorrectVendingMachineIdJson = null;
    @BeforeAll
    public void setupData() throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        //userAction_No_Coin_successfully
        UserRequest  userRequestNoCoin = new UserRequest(1, States.No_Coin);
        userRequestNoCoinJson =objectMapper.writeValueAsString(userRequestNoCoin);

        UserRequest userRequestContainsCoin = new UserRequest(2, States.Contains_Coin);
        userRequestContainsCoinJson=objectMapper.writeValueAsString(userRequestContainsCoin);

        UserRequest userRequestCandyDispensed = new UserRequest(3, States.Candy_Dispensed);
        userRequestCandyDispensedJson=objectMapper.writeValueAsString(userRequestCandyDispensed);

        //Vending machine id 4  state is No coin but user is passing Contains_Coin in UserRequest.
        UserRequest userRequestIncorrectState = new UserRequest(4, States.Contains_Coin);
        userRequestIncorrectStateJson=objectMapper.writeValueAsString(userRequestIncorrectState);

        //Vending machine id 99 is incorrect , No machine exist with this Id.
        UserRequest userRequestIncorrectVendingMachineId = new UserRequest(99, States.Contains_Coin);
        userRequestIncorrectVendingMachineIdJson=objectMapper.writeValueAsString(userRequestIncorrectVendingMachineId);

    }


    @Test
    @Sql(value="classpath:testDb.sql",executionPhase = BEFORE_TEST_METHOD)
    public void userAction_No_Coin_successfully() throws Exception {

        mockMvc.
                perform(MockMvcRequestBuilders.post("/api/user/action")
                        .content(userRequestNoCoinJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isAccepted())
                        .andExpect(jsonPath("$.nextState").value("Contains_Coin"))
                        .andExpect(jsonPath("$.response").value(MachineConstant.SUCCESSFUL_MSG));
    }

    @Test
    @Sql(value="classpath:testDb.sql",executionPhase = BEFORE_TEST_METHOD)
    public void userAction_Contains_Coin_successfully() throws Exception {

        mockMvc.
                perform(MockMvcRequestBuilders.post("/api/user/action")
                        .content(userRequestContainsCoinJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nextState").value("Candy_Dispensed"))
                .andExpect(jsonPath("$.response").value(MachineConstant.SUCCESSFUL_MSG));

    }

    @Test
    @Sql(value="classpath:testDb.sql",executionPhase = BEFORE_TEST_METHOD)
    public void userAction_Candy_Dispensed_successfully() throws Exception {

        mockMvc.
                perform(MockMvcRequestBuilders.post("/api/user/action")
                        .content(userRequestCandyDispensedJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nextState").value("No_Coin"))
                .andExpect(jsonPath("$.response").value(MachineConstant.SUCCESSFUL_MSG));

    }

    @Test
    @Sql(value="classpath:testDb.sql",executionPhase = BEFORE_TEST_METHOD)
    public void userAction_UserStateAndMachineStateNotMatch() throws Exception {

        mockMvc.
                perform(MockMvcRequestBuilders.post("/api/user/action")
                        .content(userRequestIncorrectStateJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nextState").value("No_Coin"))
                .andExpect(jsonPath("$.response").value(MachineConstant.VENDING_MACHINE_STATE_MISMATCH));
    }

    @Test
    @Sql(value="classpath:testDb.sql",executionPhase = BEFORE_TEST_METHOD)
    public void userAction_IncorrectVendingMachineId() throws Exception {

        mockMvc.
                perform(MockMvcRequestBuilders.post("/api/user/action")
                        .content(userRequestIncorrectVendingMachineIdJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.response").value(MachineConstant.VENDING_MACHINE_ID_MISMATCH));
    }

}
